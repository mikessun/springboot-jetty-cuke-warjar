package com.websystique.springmvc.controller;

import com.websystique.springmvc.configuration.ExternalConfigComponent;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.websystique.springmvc.domain.Message;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
public class HelloWorldRestController {
    static final String aa = "testttt";
    @Autowired
    ExternalConfigComponent externalConfigComponent;

    @Autowired
    Environment env;

    @RequestMapping("/hello/{player}")
    public Message message(@PathVariable String player) {
        System.out.println(getMsg());

        Message msg = new Message(player, "Hello " + player + " at " + getMsg() + "...." + externalConfigComponent.propertyOne + "===" + env.getProperty("val1")
        );
        return msg;
    }

    @RequestMapping(value = {"/pdf"}, method = RequestMethod.GET)
    public ResponseEntity getMessage(final WebRequest request) throws IOException {
        String parameter = request.getParameter("error");
        if (parameter != null) {
            return returnError();
        }
        return returnPdf();
    }

    private ResponseEntity returnError() {
        return new ResponseEntity("{'respnseCode':100400,'desc':'invalid parameter error','properties':{}}",
                getHttpHeaders(2), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity returnPdf() throws IOException {
        ClassPathResource pdfFile = new ClassPathResource("pdf-sample.pdf");
        byte[] bytes = IOUtils.toByteArray(pdfFile.getInputStream());
        HttpHeaders headers = getHttpHeaders(1);

        ResponseEntity responseEntity = new ResponseEntity(bytes, headers, HttpStatus.OK);

        return responseEntity;
    }

    private HttpHeaders getHttpHeaders(int contentTypeCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Type", contentTypeCode == 1 ? MediaType.APPLICATION_OCTET_STREAM_VALUE : MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private String getMsg() {
        return new Date().toString();
    }
}
