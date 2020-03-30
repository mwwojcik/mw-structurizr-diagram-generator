package mw.structurizr.diagrams;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;


public abstract class AbstractAction implements DiagramPublisher {


    public static final String WORKSPACE_NAME = "mw-workspace";
    public static final String WORKSPACE_DESCRIPTION = "mw workspace";

    protected abstract Workspace create(Workspace workspace);

    @Override
    public void publish() {

        // upload to structurizr.com (you'll need your own workspace ID, API key and API secret)
        StructurizrClient structurizrClient =
                new StructurizrClient(SecretStructurizr.API_KEY, SecretStructurizr.API_SECRET);
        try {
            var workspace = new Workspace(WORKSPACE_NAME, WORKSPACE_DESCRIPTION);
            var newWorkspace=create(workspace);
            structurizrClient.putWorkspace(SecretStructurizr.WORKSPACE_ID,newWorkspace);
        } catch (StructurizrClientException e) {
            throw new IllegalArgumentException(e);
        }
     }


}
