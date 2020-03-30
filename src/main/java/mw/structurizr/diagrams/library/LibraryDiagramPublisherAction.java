package mw.structurizr.diagrams.library;

import com.structurizr.Workspace;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.ViewSet;
import lombok.Getter;
import mw.structurizr.diagrams.AbstractAction;

public class LibraryDiagramPublisherAction extends AbstractAction {

  public static LibraryDiagramPublisherAction create() {
    return new LibraryDiagramPublisherAction();
  }

  @Override
  protected Workspace create(Workspace workspace) {
    LibraryDiagramWorkspaceBuilder.build(workspace);
    return workspace;
  }
}

