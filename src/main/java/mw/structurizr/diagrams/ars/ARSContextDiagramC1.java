package mw.structurizr.diagrams.ars;

import com.structurizr.Workspace;
import com.structurizr.model.*;
import com.structurizr.view.PaperSize;
import com.structurizr.view.SystemLandscapeView;
import com.structurizr.view.ViewSet;
import lombok.Getter;
import mw.structurizr.diagrams.DiagramTagEnum;

@Getter
class ARSContextDiagramC1 {

  private Enterprise airlineReservationSystem;

  private Person customer;

  private Person salesDepartmentEmp;

  private Person salesDepartmentManager;

  private SoftwareSystem ARSystem;

  private SoftwareSystem reportingSystem;

  private SoftwareSystem emailSystem;

  private SoftwareSystem paymentSystem;

  /*

    private SoftwareSystem reportingSystem;

    private SoftwareSystem deptSystem;

    private SoftwareSystem emailSystem;
  */
  private ARSContextDiagramC1(Model model) {
    airlineReservationSystem = new Enterprise("Airline Reservation System");
    customer = createCustomer(model);
    salesDepartmentEmp = salesDepartmentEmp(model);
    salesDepartmentManager = salesDepartmentManager(model);

    ARSystem = createARSystem(model);

    createUsesRelation(
        customer, ARSystem, "View customer profile, with self reservations managing.");
    createUsesRelation(
        salesDepartmentEmp, ARSystem, "Manages sales, offers.");
    createUsesRelation(
        salesDepartmentManager,
            ARSystem,
        "Manages sales, offers.");

    reportingSystem = createReportingSystem(model);
    setExisting(reportingSystem);

    emailSystem = createEmailSystem(model);
    setExisting(emailSystem);

    paymentSystem=createPaymentSystem(model);
    setExisting(paymentSystem);

    createUsesRelation(ARSystem, emailSystem, "Sends e-mail via");
    createUsesRelation(ARSystem, paymentSystem, "Realize payments via");
    createUsesRelation(ARSystem, reportingSystem, "Sends information about user activity to");
  }

  private SoftwareSystem createPaymentSystem(Model model) {
    return model.addSoftwareSystem(
            Location.External, "Payment System", "External Payment System.");
  }

  static ARSContextDiagramC1 build(Workspace workspace) {
    var diagram = new ARSContextDiagramC1(workspace.getModel());
    diagram.buildView(workspace.getViews());
    return diagram;
  }

  private void buildView(ViewSet views) {
    SystemLandscapeView systemLandscapeView =
        views.createSystemLandscapeView(
            "SystemLandscape", "The system landscape diagram for Big Bank plc.");
    systemLandscapeView.addAllElements();
    systemLandscapeView.setPaperSize(PaperSize.A5_Landscape);
    systemLandscapeView.enableAutomaticLayout();
  }


  private void setExisting(SoftwareSystem system) {
    system.addTags(DiagramTagEnum.EXISTING_SYSTEM_TAG.getLabel());
  }

  /**
   * Actors
   */
  private Person salesDepartmentEmp(Model model) {
    return model.addPerson(
        Location.Internal,
        "Sales Department Employee",
        "Sales Department Employee, with ability to define Airline Offer.");
  }

  private Person salesDepartmentManager(Model model) {
    return model.addPerson(
        Location.Internal,
        "Sales Department Manager",
        "Sales Department Manager, with ability to accept Airline Offer.");
  }

  private Person createCustomer(Model model) {
    return model.addPerson(
        Location.External, "Customer", "Airline Customer with the ability to manage reservations.");
  }

  /**
   * Systems
   */
  private SoftwareSystem createEmailSystem(Model model) {
    return model.addSoftwareSystem(
            Location.External, "E-mail System", "The internal e-mail system.");
  }

  private SoftwareSystem createReportingSystem(Model model) {
    return model.addSoftwareSystem(
        Location.External, "Airlines Reporting System.", "Stores all of user activity");
  }

  private SoftwareSystem createARSystem(Model model) {
    return model.addSoftwareSystem(
        Location.Internal, "Airline Reservation System", "System for managing ticket reservations");
  }

  private void createUsesRelation(SoftwareSystem from, SoftwareSystem to, String desc) {
    from.uses(to, desc);
  }

  private void createUsesRelation(Person from, SoftwareSystem to, String desc) {
    from.uses(to, desc);
  }
}
