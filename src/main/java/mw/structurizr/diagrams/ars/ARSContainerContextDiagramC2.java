package mw.structurizr.diagrams.ars;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.ContainerView;
import com.structurizr.view.PaperSize;
import com.structurizr.view.ViewSet;
import lombok.Getter;
import mw.structurizr.diagrams.DiagramTagEnum;

@Getter
class ARSContainerContextDiagramC2 {

  private Container customerWebPortal;
  private Container empPortal;
  private Container mobileApplication;
  private Container arsApplication;
  private Container database;
  private Container webServer;

  public ARSContainerContextDiagramC2(SoftwareSystem system) {
    customerWebPortal = createCustomerWebPortal(system);
    empPortal = createEmpWebPortal(system);
    mobileApplication = createMobileApplicationContainer(system);
    arsApplication = createARSApplicationContainer(system);
    database = createDatabaseContainer(system);
    webServer=createWebServerContainer(system);

    customerWebPortal.addTags(DiagramTagEnum.WEB_BROWSER_TAG.getLabel());
    empPortal.addTags(DiagramTagEnum.WEB_BROWSER_TAG.getLabel());
    mobileApplication.addTags(DiagramTagEnum.MOBILE_APP_TAG.getLabel());
    database.addTags(DiagramTagEnum.DATABASE_TAG.getLabel());

  }

  public static ARSContainerContextDiagramC2 build(Workspace workspace, ARSContextDiagramC1 c1) {
    var diagram = new ARSContainerContextDiagramC2(c1.getARSystem());
    diagram.buildRelations(c1);
    diagram.buildView(workspace.getViews(), c1);
    return diagram;
  }

  private Container createDatabaseContainer(SoftwareSystem system) {
    return createContainer(system, "Database", "Stores all business data", "DB");
  }

  private Container createCustomerWebPortal(SoftwareSystem system) {
    return createContainer(system, "SPA Web App for Customers", "Web application", "SPA");
  }

  private Container createEmpWebPortal(SoftwareSystem system) {
    return createContainer(system, "SPA Web App for Employee", "Web application", "SPA");
  }

  private Container createMobileApplicationContainer(SoftwareSystem system) {
    return createContainer(
        system,
        "Mobile App",
        "Deliver patron profile funcionality via mobile app interface.",
        "Android");
  }

  private Container createARSApplicationContainer(SoftwareSystem system) {
    return createContainer(
        system, "API Application", "Main Application API.", "REST/Modular monolith");
  }

  private Container createWebServerContainer(SoftwareSystem system) {
    return createContainer(
            system, "Web server", "Serves Web Content.", "Apache Tomcat");
  }

  private Container createContainer(
      SoftwareSystem system, String name, String description, String technology) {
    return system.addContainer(name, description, technology);
  }

  private void buildView(ViewSet views, ARSContextDiagramC1 c1) {
    ContainerView containerView =
        views.createContainerView(
            c1.getARSystem(),
            "Containers",
            "The container diagram for the Airways Reservation System.");
    containerView.add(c1.getCustomer());
    containerView.add(c1.getSalesDepartmentEmp());
    containerView.add(c1.getSalesDepartmentManager());

    containerView.addAllContainers();
    containerView.setPaperSize(PaperSize.A5_Landscape);
    containerView.enableAutomaticLayout();
  }

  private void buildRelations(ARSContextDiagramC1 c1) {
    c1.getCustomer().uses(customerWebPortal, "Usees via web browser");
    c1.getCustomer().uses(mobileApplication, "Uses via phone/tablet");

    c1.getSalesDepartmentEmp().uses(empPortal, "Uses via web browser");
    c1.getSalesDepartmentManager().uses(empPortal, "Uses via web browser");


    mobileApplication.uses(arsApplication, "Sends communicates via REST");
    customerWebPortal.uses(arsApplication, "Sends communicates via REST");
    empPortal.uses(arsApplication, "Sends communicates via REST");

    webServer.uses(empPortal,"Serves");
    webServer.uses(customerWebPortal,"Serves");

    arsApplication.uses(database, "Reads/stores data");
  }
}
