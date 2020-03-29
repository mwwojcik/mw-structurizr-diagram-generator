package mw.structurizr.diagrams;

import com.structurizr.Workspace;
import com.structurizr.model.*;
import com.structurizr.view.*;

public class LibraryDiagramAction extends AbstractAction {

    private static final String EXISTING_SYSTEM_TAG = "Existing System";
    private static final String BANK_STAFF_TAG = "Bank Staff";
    private static final String WEB_BROWSER_TAG = "Web Browser";
    private static final String MOBILE_APP_TAG = "Mobile App";
    private static final String DATABASE_TAG = "Database";
    private static final String FAILOVER_TAG = "Failover";

    @Override
    protected Workspace create(Workspace workspace) {

        Model model = workspace.getModel();
        ViewSet views = workspace.getViews();

        model.setEnterprise(new Enterprise("Big Bank plc"));

        // people and software systems
        Person customer =
                model.addPerson(
                        Location.External,
                        "Personal Banking Customer",
                        "A customer of the bank, with personal bank accounts.");

        SoftwareSystem internetBankingSystem =
                model.addSoftwareSystem(
                        Location.Internal,
                        "Internet Banking System",
                        "Allows customers to view information about their bank accounts, and make payments.");
        customer.uses(internetBankingSystem, "Views account balances, and makes payments using");

        SoftwareSystem mainframeBankingSystem =
                model.addSoftwareSystem(
                        Location.Internal,
                        "Mainframe Banking System",
                        "Stores all of the core banking information about customers, accounts, transactions, etc.");
        mainframeBankingSystem.addTags(EXISTING_SYSTEM_TAG);
        internetBankingSystem.uses(
                mainframeBankingSystem, "Gets account information from, and makes payments using");

        SoftwareSystem emailSystem =
                model.addSoftwareSystem(
                        Location.Internal, "E-mail System", "The internal Microsoft Exchange e-mail system.");
        internetBankingSystem.uses(emailSystem, "Sends e-mail using");
        emailSystem.addTags(EXISTING_SYSTEM_TAG);
        emailSystem.delivers(customer, "Sends e-mails to");

        SoftwareSystem atm =
                model.addSoftwareSystem(Location.Internal, "ATM", "Allows customers to withdraw cash.");
        atm.addTags(EXISTING_SYSTEM_TAG);
        atm.uses(mainframeBankingSystem, "Uses");
        customer.uses(atm, "Withdraws cash using");

        Person customerServiceStaff =
                model.addPerson(
                        Location.Internal, "Customer Service Staff", "Customer service staff within the bank.");
        customerServiceStaff.addTags(BANK_STAFF_TAG);
        customerServiceStaff.uses(mainframeBankingSystem, "Uses");
        customer.interactsWith(customerServiceStaff, "Asks questions to", "Telephone");

        Person backOfficeStaff =
                model.addPerson(
                        Location.Internal,
                        "Back Office Staff",
                        "Administration and support staff within the bank.");
        backOfficeStaff.addTags(BANK_STAFF_TAG);
        backOfficeStaff.uses(mainframeBankingSystem, "Uses");

        SystemLandscapeView systemLandscapeView =
                views.createSystemLandscapeView(
                        "SystemLandscape", "The system landscape diagram for Big Bank plc.");
        systemLandscapeView.addAllElements();
        systemLandscapeView.setPaperSize(PaperSize.A5_Landscape);

        SystemContextView systemContextView =
                views.createSystemContextView(
                        internetBankingSystem,
                        "SystemContext",
                        "The system context diagram for the Internet Banking System.");
        systemContextView.setEnterpriseBoundaryVisible(false);
        systemContextView.addNearestNeighbours(internetBankingSystem);
        systemContextView.setPaperSize(PaperSize.A5_Landscape);


        // colours, shapes and other diagram styling
        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.CONTAINER).background("#438dd5").color("#ffffff");
        styles.addElementStyle(Tags.COMPONENT).background("#85bbf0").color("#000000");
        styles
                .addElementStyle(Tags.PERSON)
                .background("#08427b")
                .color("#ffffff")
                .shape(Shape.Person)
                .fontSize(22);
        styles.addElementStyle(EXISTING_SYSTEM_TAG).background("#999999").color("#ffffff");
        styles.addElementStyle(BANK_STAFF_TAG).background("#999999").color("#ffffff");
        styles.addElementStyle(WEB_BROWSER_TAG).shape(Shape.WebBrowser);
        styles.addElementStyle(MOBILE_APP_TAG).shape(Shape.MobileDeviceLandscape);
        styles.addElementStyle(DATABASE_TAG).shape(Shape.Cylinder);
        styles.addElementStyle(FAILOVER_TAG).opacity(25);
        styles.addRelationshipStyle(FAILOVER_TAG).opacity(25).position(70);

        return workspace;
    }

    public static LibraryDiagramAction create() {
     return new LibraryDiagramAction();
     }

}
