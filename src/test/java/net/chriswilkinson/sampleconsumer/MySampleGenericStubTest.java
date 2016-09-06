package net.chriswilkinson.sampleconsumer;

import org.junit.*;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

/**
 * @Author chriswilks
 */

public class MySampleGenericStubTest extends GenericStubTest {

    @BeforeClass
    public static void setup() {
//        addStub(RequestMethod.POST, "/my/url", "/my/json/body/file");
    }

    @Test
    public void SimpleMockTest() {
        given().
        when().
                get("http://127.0.0.1:8080/test").
        then().
                statusCode(200).body(containsString("OK"));
    }

    @Test
    public void testCustomerList() {
        // Make a get request to the service
        given().
                header("Content-Type", "application/json").
        when().
                get("http://127.0.0.1:8081/consumedcustomers/").
        then().
                statusCode(200);

        // See what subsequent requests the service made
        verify(RequestMethod.GET, "/api/customers");
    }

    @Test
    public void testSecondCustomer() {
        // Make a get request to the service
        given().
                header("Content-Type", "application/json").
        when().
                get("http://127.0.0.1:8081/secondcustomer/").
        then().
                statusCode(200);

        // See what subsequent requests the service made
        verify(RequestMethod.GET, "/api/customers/2");
    }
}
