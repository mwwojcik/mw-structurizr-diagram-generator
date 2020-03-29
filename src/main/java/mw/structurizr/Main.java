package mw.structurizr;

import mw.structurizr.diagrams.CleanMyWorkspaceAction;
import mw.structurizr.diagrams.Diagram;
import mw.structurizr.diagrams.LibraryDiagramAction;
import mw.structurizr.diagrams.SampleBankingAction;

import java.util.Collections;

class Main {

  public static void main(String[] args) {
    Collections.singletonList(
            //CleanMyWorkspaceAction.create()
            //SampleBankingAction.create()
            LibraryDiagramAction.create()
    ).forEach(Diagram::publish);
  }
}
