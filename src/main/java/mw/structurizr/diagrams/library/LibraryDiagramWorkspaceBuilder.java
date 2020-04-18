package mw.structurizr.diagrams.library;

import com.structurizr.Workspace;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;
import mw.structurizr.diagrams.DiagramTagEnum;

class LibraryDiagramWorkspaceBuilder {
  public static void build(Workspace workspace) {
    makeStyles(workspace.getViews());
    var c1 = LibrarySystemContextDiagramC1.build(workspace);
    var c2 = LibraryContainerContextDiagramC2.build(workspace, c1);
    var c3 = LibraryComponentDiagramC3.build(workspace, c2);
  }

  private static void makeStyles(ViewSet viewSet) {
    // colours, shapes and other diagram styling
    Styles styles = viewSet.getConfiguration().getStyles();
    styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
    styles.addElementStyle(Tags.CONTAINER).background("#438dd5").color("#ffffff");
    styles.addElementStyle(Tags.COMPONENT).background("#85bbf0").color("#000000");
    styles
        .addElementStyle(Tags.PERSON)
        .background("#08427b")
        .color("#ffffff")
        .shape(Shape.Person)
        .fontSize(22);
    styles
        .addElementStyle(DiagramTagEnum.EXISTING_SYSTEM_TAG.getLabel())
        .background("#999999")
        .color("#ffffff");
    styles
        .addElementStyle(DiagramTagEnum.BANK_STAFF_TAG.getLabel())
        .background("#999999")
        .color("#ffffff");
    styles.addElementStyle(DiagramTagEnum.WEB_BROWSER_TAG.getLabel()).shape(Shape.WebBrowser);
    styles
        .addElementStyle(DiagramTagEnum.MOBILE_APP_TAG.getLabel())
        .shape(Shape.MobileDeviceLandscape);
    styles.addElementStyle(DiagramTagEnum.DATABASE_TAG.getLabel()).shape(Shape.Cylinder);
    styles.addElementStyle(DiagramTagEnum.FAILOVER_TAG.getLabel()).opacity(25);
    styles.addRelationshipStyle(DiagramTagEnum.FAILOVER_TAG.getLabel()).opacity(25).position(70);
  }
}
