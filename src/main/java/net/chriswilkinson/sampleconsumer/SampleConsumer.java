package net.chriswilkinson.sampleconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chrisw on 20/06/2016.
 */

@SpringBootApplication
@RestController
public class SampleConsumer {

    @Autowired
    ConsumerPort consumerPort;

    public static void main(String[] args) {
        SpringApplication.run(SampleConsumer.class, args);
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/consumedcustomers")
    public ResponseEntity<String> customers() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return new ResponseEntity<String>(consumerPort.customers(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping("/secondcustomer")
    public ResponseEntity<String> secondCustomer() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        return new ResponseEntity<String>(consumerPort.secondCustomer(), responseHeaders, HttpStatus.OK);
    }
}
