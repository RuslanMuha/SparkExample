package naya.spark.example;

import naya.spark.example.domain.Developer;
import naya.spark.example.domain.Position;
import naya.spark.example.service.MatchSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner, Serializable {


    @Autowired
    MatchSkillService eq;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> skills1 = Arrays.asList("Java", "maven", "OOP", "Spring", "JPA");
        List<String> skills2 = Arrays.asList("Java", "JPA", "MongoDD", "C++", "maven");
        List<Position> positions = new ArrayList<>();
        List<Developer> developers = new ArrayList<>();
        positions.add(new Position(skills1,"Java Developer"));
        positions.add(new Position(skills2,"Java/C++ Developer"));
        developers.add(new Developer(skills1,"Vasya",50));
        developers.add(new Developer(skills2,"Petya",50));

//        eq.findPosition(developers,positions);
      eq.findPositionSpark(developers,positions);

    }
}
