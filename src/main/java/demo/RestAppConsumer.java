package demo;

import demo.config.AppConfig;
import demo.consumer.Consumer;
import demo.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RestAppConsumer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Consumer consumer = new Consumer();
        consumer.getUsers();
        consumer.addUser(new User(3L, "James", "Brown", (byte) 22));
        consumer.updateUser(new User(3L, "James", "Brown", (byte) 82));
        consumer.deleteUser(3L);
        System.out.println(Consumer.result);
    }
}
