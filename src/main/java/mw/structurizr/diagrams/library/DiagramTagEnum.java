package mw.structurizr.diagrams.library;

import lombok.Getter;

enum DiagramTagEnum {
  EXISTING_SYSTEM_TAG("Existing System"),
  BANK_STAFF_TAG("Bank Staff"),
  WEB_BROWSER_TAG("Web Browser"),
  MOBILE_APP_TAG("Mobile App"),
  DATABASE_TAG("Database"),
  FAILOVER_TAG("Failover");

  @Getter
  private String label;

  DiagramTagEnum(String label) {

    this.label = label;
  }
}
