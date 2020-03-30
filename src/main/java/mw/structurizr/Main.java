package mw.structurizr;

import mw.structurizr.diagrams.CleanMyWorkspaceAction;
import mw.structurizr.diagrams.DiagramPublisher;
import mw.structurizr.diagrams.LibraryDiagramPublisherAction;

import java.util.Arrays;
import java.util.Collections;

class Main {

  public static void main(String[] args) {
    Arrays.asList(
             CleanMyWorkspaceAction.create(),
            // SampleBankingAction.create()
            LibraryDiagramPublisherAction.create())
        .forEach(DiagramPublisher::publish);
  }
}
