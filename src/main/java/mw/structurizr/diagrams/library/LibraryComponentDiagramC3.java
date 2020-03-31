package mw.structurizr.diagrams.library;

import com.structurizr.Workspace;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.view.ComponentView;
import com.structurizr.view.PaperSize;
import com.structurizr.view.ViewSet;

class LibraryComponentDiagramC3 {

  private Component bookCatalogue;
  private Component patronController;
  private Component patron;

  private Component database;

  public LibraryComponentDiagramC3(Container container) {

    bookCatalogue = createBookCatalogueComponent(container);
    patronController = createPatronControllerComponent(container);
    patron = createPatronComponent(container);

    database=createDatabaseComponent(container);
  }

  private Component createDatabaseComponent(Container container) {
    var db = container.addComponent("Database", "Database storage", "MongoDB");
    return db;
  }

  private Component createPatronComponent(Container container) {
    return container.addComponent("Patron Component","Main library operations in context of patron","Java");
  }

  public static LibraryComponentDiagramC3 build(
      Workspace workspace, LibraryContainerContextDiagramC2 c2) {
    var diagram = new LibraryComponentDiagramC3(c2.getApiApplication());
    diagram.buildRelations(c2);
    diagram.buildView(workspace.getViews(), c2);
    return diagram;
  }

  private Component createPatronControllerComponent(Container container) {
    return container.addComponent(
        "Patron Controller",
        "Rest Controller to realize library operations (checkouts,holds)",
        "REST API");
  }

  private Component createBookCatalogueComponent(Container container) {
    return container.addComponent(
        "Book Catalogue Component", "Simple Crud Component to managing book catalogue", "Java");
  }

  private void buildRelations(LibraryContainerContextDiagramC2 c2) {
    patronController.uses(c2.getMobileApplication(),"REST/JSON");
    patronController.uses(c2.getWebApplication(),"REST/JSON");
    patron.uses(c2.getDatabase(),"Reads/Writes");
  }

  private void buildView(ViewSet views, LibraryContainerContextDiagramC2 c2) {
    ComponentView component =
        views.createComponentView(
            c2.getApiApplication(),
            "Components",
            "The component diagram for the Library Managing System.");
    component.addAllComponents();
    component.setPaperSize(PaperSize.A5_Landscape);
    component.enableAutomaticLayout();
  }
}
