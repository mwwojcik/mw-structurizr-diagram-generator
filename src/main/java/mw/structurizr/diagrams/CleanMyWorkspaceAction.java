package mw.structurizr.diagrams;

import com.structurizr.Workspace;

public class CleanMyWorkspaceAction extends AbstractAction {
  public static CleanMyWorkspaceAction create() {
    return new CleanMyWorkspaceAction();
  }

  @Override
  protected Workspace create(Workspace workspace) {
    return new Workspace(WORKSPACE_NAME, WORKSPACE_DESCRIPTION);
  }
}
