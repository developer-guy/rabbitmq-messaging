package org.spring.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Receiver receiver;
    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    public void run() throws Exception {
        System.out.println("Sending message :");
        rabbitTemplate.convertAndSend(RabbitmqDemoApplication.queueName, "Hello from rabbit");
        receiver.getCountDownLatch().await(10000, TimeUnit.MILLISECONDS);
        assertEquals(1, receiver.getCountDownLatch().getCount());
    }


    @Test
    public void contextLoads() {
    }

}
