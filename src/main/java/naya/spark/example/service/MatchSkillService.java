package naya.spark.example.service;

import naya.spark.example.domain.Developer;
import naya.spark.example.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class MatchSkillService implements Serializable {

    @Qualifier("MatchingWordsSparkImpl")
    @Autowired
    private MatchingWords matchingWordsSpark;
//    @Qualifier("MatchingWordsImpl")
//    @Autowired
//    private MatchingWords matchingWords;

    @Autowired
    Sender kafkaSender;


    public void findPosition(List<Developer> developers, List<Position> positions) {


    }

    public void findPositionSpark(List<Developer> developers, List<Position> positions) {
        matchingWordsSpark.matchByKeyWords(developers, positions);

    }


}
