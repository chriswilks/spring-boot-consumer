package net.chriswilkinson.sampleconsumer;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chrisw on 20/06/2016.
 */

@Component
public class ConsumerPort {
    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public ConsumerPort(@Value("${producer}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    public String customers() {
        String body = restTemplate.getForEntity(url + "/customers", String.class).getBody();
        JSONObject response = new JSONObject(body);
        return response.toString();
    }

    public String secondCustomer() {
        String body = restTemplate.getForEntity(url + "/customers/2", String.class).getBody();
        JSONObject response = new JSONObject(body);
        return response.toString();
    }
}
