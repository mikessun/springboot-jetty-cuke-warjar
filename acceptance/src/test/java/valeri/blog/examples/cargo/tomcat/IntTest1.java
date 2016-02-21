package valeri.blog.examples.cargo.tomcat;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * A quick test to make sure the app is really deployed.
 *
 * @author David Valeri
 */
public class IntTest1 {

    @Test
    public void test() throws Exception {

        String spec = "http://localhost:8080/acceptaance/hello/mike";

        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity responseEntity=restTemplate.getForEntity(spec, String.class);
    }
}
