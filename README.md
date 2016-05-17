This sample application uses follow tech stacks:
- Spring Boot 1.2.2.Release
- Jetty 9.x
- Cucumber JVM 1.2

The objective is to 
1. using spring boot to create an web app with JMX MBean enable and a sample mbean TestMBean
2. generating both executible jar and war artifacts
3. war can be deployed to ext'l servlet container
4. jar can be run as regular springboot executible
5. run springboot with internal jetty container: cd app && mvn spring-boot:run 
6. run cuke: mvn clean verify -Pcuke
