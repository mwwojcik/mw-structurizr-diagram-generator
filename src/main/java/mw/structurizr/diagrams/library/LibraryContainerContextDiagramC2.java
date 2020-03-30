package mw.structurizr.diagrams.library;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.ContainerView;
import com.structurizr.view.PaperSize;
import com.structurizr.view.ViewSet;
import lombok.Getter;

@Getter
class LibraryContainerContextDiagramC2 {

    private Container webApplication;
    private Container mobileApplication;
    private Container apiApplication;
    private Container database;

    public LibraryContainerContextDiagramC2(SoftwareSystem system) {
        webApplication= createWebApplicationContainer(system);
        mobileApplication=createMobileApplicationContainer(system);
        apiApplication=createAPIApplicationContainer(system);
        database=createDatabaseContainer(system);

        webApplication.addTags(DiagramTagEnum.WEB_BROWSER_TAG.getLabel());
        mobileApplication.addTags(DiagramTagEnum.MOBILE_APP_TAG.getLabel());
        database.addTags(DiagramTagEnum.DATABASE_TAG.getLabel());
    }

    private Container createDatabaseContainer(SoftwareSystem system) {
        return createContainer(system,"Database",
                "Stores all business data","MongoDB");

    }

    private Container createWebApplicationContainer(SoftwareSystem system) {
        return createContainer(system,"Server Side Web Application",
                "Server side web application","Java Spring MVC/Tomcat");
    }

    private Container createMobileApplicationContainer(SoftwareSystem system) {
        return createContainer(system,"Mobile App",
                "Deliver patron profile funcionality via mobile app interface.","Android");
    }

    private Container createAPIApplicationContainer(SoftwareSystem system) {
        return createContainer(system,"API Application",
                "Provides Library management funcionality via REST interface.","REST");
    }



    private Container createContainer(SoftwareSystem system,String name ,String description, String technology) {
        return system.addContainer(name,description,technology);
    }


    public static LibraryContainerContextDiagramC2 build(
      Workspace workspace, LibrarySystemContextDiagramC1 c1) {
        var diagram = new LibraryContainerContextDiagramC2(c1.getLibrarySystem());
        diagram.buildRelations(c1);
        diagram.buildView(workspace.getViews(),c1);
        return diagram;
  }

    private void buildView(ViewSet views,LibrarySystemContextDiagramC1 c1) {
        ContainerView containerView =
                views.createContainerView(
                        c1.getLibrarySystem(),
                        "Containers",
                        "The container diagram for the Internet Banking System.");
        containerView.add(c1.getRegularPatron());
        containerView.add(c1.getResearcher());

        containerView.addAllContainers();
        containerView.add(c1.getDeptSystem());
        containerView.add(c1.getReportingSystem());
        containerView.setPaperSize(PaperSize.A5_Landscape);
        containerView.enableAutomaticLayout();

    }

    private void buildRelations(LibrarySystemContextDiagramC1 c1) {
        c1.getRegularPatron().uses(webApplication,"Usees via web browser");
        c1.getRegularPatron().uses(mobileApplication,"Uses via phone/tablet");
        c1.getResearcher().uses(mobileApplication,"Uses via phone/tablet");
        c1.getResearcher().uses(webApplication,"Usees via web browser");

        mobileApplication.uses(apiApplication,"Sends communicates via REST");
        webApplication.uses(apiApplication,"Sends communicates via REST");

        apiApplication.uses(c1.getReportingSystem(),"Sends events");
        apiApplication.uses(c1.getDeptSystem(),"Sends events about overdues");
        apiApplication.uses(database,"Reads/stores data");

    }


}

