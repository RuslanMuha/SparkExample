package naya.spark.example.service;

import org.springframework.stereotype.Service;

@Service
public class KafkaSender implements Sender {
    @Override
    public void send(String name, String position) {
        System.out.println("Send position "+ name+ " to " + position);
    }
}
