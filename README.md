# spring-boot-jms
Spring Boot 2 JMS sample with Active MQ

Prerequisites for running the application:
1. Install the Active MQ in your local machine and make sure it is started.
2. Start the activemq.bat (windows) from {$activemqinstalldir}/bin/activemq.bat

Active MQ configuration:
application.yml

spring:
  activemq:
    user: admin
    password: admin
    broker-url: tcp://localhost:61616?jms.redeliveryPolicy.maximumRedeliveries=1


I used two queues for demonstration purpose.


Run the application using mvn springboot:run

To push the message from REST endpoint: 

http://localhost:8096/transaction/order

Input:

{
		"name": "I-41771154",
        "itemPrice": 10,
        "totalPrice": 120,
        "orderStatus": "IN_PROGRESS"
	
}

Console:

2018-08-01 16:36:13.604  INFO 20340 --- [  restartedMain] com.example.activemq.Application         : Order Transaction event posted successfully...
<1> Received Order Transaction:   <OrderTransaction(id=null, sender=Test Sender, receiver=Test Receiver, amount=150.0)>
