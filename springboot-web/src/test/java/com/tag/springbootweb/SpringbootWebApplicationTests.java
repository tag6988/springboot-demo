package com.tag.springbootweb;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootWebApplication.class)
public class SpringbootWebApplicationTests {

    private  final  static String url =  "http://localhost:8088/";

    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test(){
        ResponseEntity<String> response = restTemplate.exchange(url + "/static/css/style.css" ,
                HttpMethod.GET,
                new HttpEntity(null),
                String.class);
        System.out.println("result: " + response.getBody());
    }

    @After
    public void contextLoads() {
        System.out.println("test");
    }

}
