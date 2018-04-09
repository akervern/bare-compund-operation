package org.nuxeo.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.test.DefaultRepositoryInit;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.ecm.restapi.server.jaxrs.adapters.OperationAdapter;
import org.nuxeo.ecm.restapi.test.BaseTest;
import org.nuxeo.ecm.restapi.test.RestServerFeature;
import org.nuxeo.jaxrs.test.CloseableClientResponse;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.Jetty;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(FeaturesRunner.class)
@Features(RestServerFeature.class)
@RepositoryConfig(init = DefaultRepositoryInit.class, cleanup = Granularity.METHOD)
@Jetty(port = 18090)
@Deploy("org.nuxeo.sample.silicon-sample-operation-core")
public class TestCompoundAttach extends BaseTest {

    @Inject
    protected CoreSession session;

    private static String PARAMS = "{\"params\":{\"compoundDocs\":[\"1234\",\"4567\"]}}";

    @Test
    public void shouldCallTheOperation() {
        try (CloseableClientResponse response = getResponse(RequestType.GET,
                "path/default-domain")) {
            // Then the operation call succeeds
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }

        System.out.println("path/default-domain" + "/@" + OperationAdapter.NAME + "/" + CompoundAttach.ID);
        // When I call the REST binding on the document resource
        try (CloseableClientResponse response = getResponse(RequestType.POSTREQUEST,
                "path/default-domain/@" + OperationAdapter.NAME + "/" + CompoundAttach.ID, PARAMS)) {

            // Then the operation call succeeds
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }
    }
}
