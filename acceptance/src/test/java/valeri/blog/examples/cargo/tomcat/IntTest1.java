package valeri.blog.examples.cargo.tomcat;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;

/**
 * A quick test to make sure the app is really deployed.
 *
 * @author David Valeri
 */
public class IntTest1 {

    @Test
    public void test() throws Exception {

        String spec = "http://localhost:8280/acceptaance/hello/mike";

        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity responseEntity=restTemplate.getForEntity(spec, String.class);

        assertTrue(responseEntity.getStatusCode().value()==200);
    }
}
