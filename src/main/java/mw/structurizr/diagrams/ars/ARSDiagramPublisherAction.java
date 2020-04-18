package mw.structurizr.diagrams.ars;

import com.structurizr.Workspace;
import mw.structurizr.diagrams.AbstractAction;

public  class ARSDiagramPublisherAction extends AbstractAction {

public static ARSDiagramPublisherAction create() {
 return new ARSDiagramPublisherAction() ;
 }



    @Override
    protected Workspace create(Workspace workspace) {
        ArsDiagramWorkspaceBuilder.build(workspace);
        return workspace;
    }
}
