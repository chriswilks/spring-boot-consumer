package net.chriswilkinson.sampleconsumer;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.rules.ExternalResource;

/**
 * @Author chriswilks
 */
public class GenericInterceptStubClassRule extends ExternalResource {

    private WireMockServer server = new WireMockServer();
    private WireMock wireMockClient = new WireMock();

    @Override
    protected void before() throws Throwable {
        // start the server
        server.start();
        // configure it
        addStubs();
        System.out.println("InClassRule");
    }

    private void addStubs() {
        // Generic Response Stub
        server.stubFor(WireMock.any(WireMock.urlMatching(".*"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{\"status\" : \"OK\"}")));
    }

    @Override
    protected void after() {
        // stop the server
        server.stop();
    }

    public WireMockServer getServer() {
        return server;
    }

    public WireMock getClient() {
        return wireMockClient;
    }
}
