package net.chriswilkinson.sampleconsumer;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @Author chriswilks
 */
public abstract class GenericStubTest {

    protected enum RequestMethod {
        GET,
        PUT,
        POST,
        DELETE
    }

    private WireMock cl;

    @ClassRule
    public static GenericInterceptStubClassRule stub = new GenericInterceptStubClassRule();

    @Rule
    public GenericInterceptStubRule beforeAfterOperations = new GenericInterceptStubRule();

    public GenericStubTest() {
        cl = stub.getClient();
    }

    protected WireMockServer getServer() {
        return stub.getServer();
    }

    public WireMock getClient() {
        return cl;
    }

    // We can add more verification here if we like
    public void verify(RequestMethod method, String url) {
        switch (method) {
            case GET:
                cl.verify(1, cl.getRequestedFor(cl.urlEqualTo(url)));
                break;
            case POST:
                cl.verify(1, cl.postRequestedFor(cl.urlEqualTo(url)));
                break;
            case PUT:
                cl.verify(1, cl.putRequestedFor(cl.urlEqualTo(url)));
                break;
            case DELETE:
                cl.verify(1, cl.deleteRequestedFor(cl.urlEqualTo(url)));
                break;
        }
    }

    private final class GenericInterceptStubRule implements TestRule {

        @Override
        public Statement apply(Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    //Reset the requests in the server
                    getServer().resetRequests();
                    //Run test
                    base.evaluate();
                }
            };
        }
    }
}
