package org.spring.io;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Sending message :");
        rabbitTemplate.convertAndSend(RabbitmqDemoApplication.queueName, "Hello from rabbit");
        receiver.getCountDownLatch().await(10000, TimeUnit.MILLISECONDS);
        System.out.println(receiver.getCountDownLatch().getCount());
    }
}
