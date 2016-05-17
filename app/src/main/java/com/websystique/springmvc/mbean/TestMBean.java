package com.websystique.springmvc.mbean;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "MyApp:name=TestMbean")
public class TestMBean {

    @ManagedOperation(description = "Returns something.")
    public String getSomething() {
        return "Something";
    }

}
