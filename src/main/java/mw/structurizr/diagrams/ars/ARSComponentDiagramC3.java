package mw.structurizr.diagrams.ars;

import com.structurizr.Workspace;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.view.ComponentView;
import com.structurizr.view.PaperSize;
import com.structurizr.view.ViewSet;

class ARSComponentDiagramC3 {

  private Component salesComponent;
  private Component giftComponent;
  private Component checkinComponent;
  private Component surveysComponent;
  private Component reportsComponent;
  private Component reservationsComponent;

//  private Component database;

  public ARSComponentDiagramC3(Container container) {

    salesComponent = salesComponent(container);
    giftComponent = giftComponent(container);
    checkinComponent = checkinComponent(container);
    surveysComponent = surveysComponent(container);
    reportsComponent = reportsComponent(container);
    reservationsComponent = reservationsComponent(container);
//    database=createDatabaseComponent(container);
  }

 /* private Component createDatabaseComponent(Container container) {
    var db = container.addComponent("Database", "Database storage", "DB");
    return db;
  }*/

  private Component checkinComponent(Container container) {
    return container.addComponent("Check In Component","Component realizing check in process ","Java Component");
  }

  private Component surveysComponent(Container container) {
    return container.addComponent("Surveys Component","Component for customer surveys and reviews","Java Component");
  }

  private Component reportsComponent(Container container) {
    return container.addComponent("Reports Component","Component realizing customer activity reporting","Java Component");
  }

  private Component reservationsComponent(Container container) {
    return container.addComponent(
        "Reservations Component",
        "Component  performing the ticket booking process","Java Component");
  }

  private Component giftComponent(Container container) {
    return container.addComponent(
        "Gifts Component",
        "Component realizing airline gifts distribution",
        "Java Component");
  }

  private Component salesComponent(Container container) {
    return container.addComponent(
        "Sales Management Component", "Component to managing airline offers", "Java Component");
  }

  private void buildRelations(ARSContextDiagramC1 c1,ARSContainerContextDiagramC2 c2) {
    c1.getSalesDepartmentEmp().uses(salesComponent,"Creates offer");
    c1.getSalesDepartmentManager().uses(salesComponent,"Accepts offer");

    c1.getCustomer().uses(checkinComponent,"Carries out the check-in process");
    c1.getCustomer().uses(surveysComponent,"Completes surveys and reviews");
    c1.getCustomer().uses(reportsComponent,"Gets an activity report");
    c1.getCustomer().uses(reservationsComponent,"Manages the ticket booking process");
    c1.getCustomer().uses(giftComponent,"Receives gifts from the airline");


    checkinComponent.uses(reportsComponent,"Reports");
    surveysComponent.uses(reportsComponent,"Reports");
    reservationsComponent.uses(reportsComponent,"Reports");
    giftComponent.uses(reportsComponent,"Reports");
    salesComponent.uses(reportsComponent,"Reports");

    checkinComponent.uses(c1.getEmailSystem(),"Sends email");
    surveysComponent.uses(c1.getEmailSystem(),"Sends email");
    reservationsComponent.uses(c1.getEmailSystem(),"Sends email");
    giftComponent.uses(c1.getEmailSystem(),"Sends email");
    salesComponent.uses(c1.getEmailSystem(),"Sends email");

    reservationsComponent.uses(c1.getPaymentSystem(),"Payments");

    reportsComponent.uses(c1.getReportingSystem(),"Reports");

  }

  private void buildView(ViewSet views, ARSContextDiagramC1 c1,ARSContainerContextDiagramC2 c2) {
    ComponentView component =
        views.createComponentView(
            c2.getArsApplication(),
            "Components",
            "The component diagram for the Airline Reservations System.");
    component.addAllComponents();

    component.add(c1.getSalesDepartmentEmp());
    component.add(c1.getSalesDepartmentManager());
    component.add(c1.getCustomer());

    component.add(c1.getReportingSystem());
    component.add(c1.getPaymentSystem());
    component.add(c1.getEmailSystem());

    component.setPaperSize(PaperSize.A5_Landscape);
    component.enableAutomaticLayout();
  }

  public static ARSComponentDiagramC3 build(
          Workspace workspace, ARSContainerContextDiagramC2 c2,ARSContextDiagramC1 c1) {

    var diagram = new ARSComponentDiagramC3(c2.getArsApplication());
    diagram.buildRelations(c1,c2);
    diagram.buildView(workspace.getViews(), c1,c2);
    return diagram;
  }
}
