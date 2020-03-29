package mw.structurizr;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;

import static mw.structurizr.SecretStructurizr.API_SECRET;
import static mw.structurizr.SecretStructurizr.WORKSPACE_ID;

abstract class AbstractDiagram {

    protected abstract Workspace create(Workspace workspace);

    void publish() {

        // upload to structurizr.com (you'll need your own workspace ID, API key and API secret)
        StructurizrClient structurizrClient =
                new StructurizrClient(SecretStructurizr.API_KEY, API_SECRET);
        try {
            var workspace = new Workspace("mw-workspace","mw workspace");
            var newWorkspace=create(workspace);
            structurizrClient.putWorkspace(WORKSPACE_ID,newWorkspace);
        } catch (StructurizrClientException e) {
            throw new IllegalArgumentException(e);
        }
     }

}
