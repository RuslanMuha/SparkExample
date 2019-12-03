package naya.spark.example.service;

import naya.spark.example.domain.Developer;
import naya.spark.example.domain.Position;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Qualifier("MatchingWordsSparkImpl")
@Service
public class MatchingWordsSparkImpl implements MatchingWords {



    @Autowired
    JavaSparkContext sc;

    @Autowired
    private Sender kafkaSender;


    private boolean match(List<String> positionSkills, List<String> developerSkills, int percent) {


        JavaRDD<String> positionRDD = sc.parallelize(positionSkills);
        JavaRDD<String> developerRDD = sc.parallelize(developerSkills);
        JavaRDD<String> intersection = positionRDD.intersection(developerRDD);
        long countMatchers = intersection.count();
        int countSkillsInJobDescription = positionSkills.size();

        return 100 * countMatchers / countSkillsInJobDescription > percent;
    }


    @Override
    public void matchByKeyWords(List<Developer> developers, List<Position> positions) {


        JavaRDD<Developer> devRDD = sc.parallelize(developers);
        JavaRDD<Position> posRDD = sc.parallelize(positions);

        JavaPairRDD<Developer, Position> data = devRDD.cartesian(posRDD).filter((a) -> match(a._1.getListSkillsDeveloper(), a._2.getListSkillsPosition(), 50));
        data.foreach(v -> kafkaSender.send(v._1.getName(), v._2.getTitle()));


        devRDD.foreach(d -> posRDD.foreach(p -> {
            boolean isMatch = match(p.getListSkillsPosition(), d.getListSkillsDeveloper(), d.getMatchPersent());
            if (isMatch) {
                kafkaSender.send(d.getName(), p.getTitle());
            }
        }));


    }
}
