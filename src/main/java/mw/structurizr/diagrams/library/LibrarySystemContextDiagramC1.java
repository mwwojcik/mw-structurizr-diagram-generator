package mw.structurizr.diagrams.library;

import com.structurizr.Workspace;
import com.structurizr.model.*;
import com.structurizr.view.PaperSize;
import com.structurizr.view.SystemLandscapeView;
import com.structurizr.view.ViewSet;
import lombok.Getter;

@Getter
class LibrarySystemContextDiagramC1 {

  private Enterprise library;

  private Person regularPatron;

  private Person researcher;

  private SoftwareSystem librarySystem;

  private SoftwareSystem reportingSystem;

  private SoftwareSystem deptSystem;

  private SoftwareSystem emailSystem;

  private LibrarySystemContextDiagramC1(Model model) {
    library = new Enterprise("Library Management System");
    regularPatron = createRegularPatron(model);
    researcher = createResearcher(model);
    librarySystem = createLibrarySystem(model);
    reportingSystem = createReportingSystem(model);
    deptSystem = createDeptCollectionSystem(model);
    emailSystem = createEmailSystem(model);

    setExisting(reportingSystem);
    setExisting(deptSystem);
    setExisting(emailSystem);

    createUsesRelation(
        regularPatron, librarySystem, "Views patron profiles, with current holds and checkouts.");

    createUsesRelation(
        researcher, librarySystem, "Views patron profiles, with current holds and checkouts.");

    createUsesRelation(deptSystem, emailSystem, "Sends e-mail using");
    createUsesRelation(librarySystem, reportingSystem, "Sends information to");
    createUsesRelation(librarySystem, deptSystem, "Sends overdue");
    // createDeliversRelation(emailSystem, regularPatron, "Sends emails to");
    // createDeliversRelation(emailSystem, researcher, "Sends emails to");
  }

  static LibrarySystemContextDiagramC1 build(Workspace workspace) {
    var diagram = new LibrarySystemContextDiagramC1(workspace.getModel());
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

  private void createDeliversRelation(SoftwareSystem system, Person person, String desc) {
    system.delivers(person, desc);
  }

  private void setExisting(SoftwareSystem system) {
    system.addTags(DiagramTagEnum.EXISTING_SYSTEM_TAG.getLabel());
  }

  private SoftwareSystem createEmailSystem(Model model) {
    return model.addSoftwareSystem(
        Location.Internal, "E-mail System", "The internal e-mail system.");
  }

  private Person createResearcher(Model model) {
    return model.addPerson(
        Location.External, "Rsearcher", "Researcher, with the ability to make holds and rentals.");
  }

  private Person createRegularPatron(Model model) {
    return model.addPerson(
        Location.External,
        "Regular patron",
        "Library patron, with the ability to make holds and rentals.");
  }

  private SoftwareSystem createDeptCollectionSystem(Model model) {
    return model.addSoftwareSystem(
        Location.Internal,
        "Library dept collection system.",
        "Stores all of the overdue holds and checkouts informations etc.");
  }

  private SoftwareSystem createReportingSystem(Model model) {
    return model.addSoftwareSystem(
        Location.Internal,
        "Library reporting system.",
        "Stores all of the library information like popular books,   etc.");
  }

  private SoftwareSystem createLibrarySystem(Model model) {
    return model.addSoftwareSystem(
        Location.Internal, "Library", "Allows patrons to make holds and checkouts.");
  }

  private void createUsesRelation(SoftwareSystem from, SoftwareSystem to, String desc) {
    from.uses(to, desc);
  }

  private void createUsesRelation(Person from, SoftwareSystem to, String desc) {
    from.uses(to, desc);
  }
}

