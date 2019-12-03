package naya.spark.example.service;

import naya.spark.example.domain.Developer;
import naya.spark.example.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Qualifier("MatchingWordsImpl")
@Service
public class MatchingWordsImpl implements MatchingWords {

    @Autowired
    private Sender kafkaSender;

    @Override
    public void matchByKeyWords(List<Developer> developers, List<Position> positions) {

        positions.forEach(p -> developers.forEach(d -> {
            boolean isMatch = match(d.getListSkillsDeveloper(), p.getListSkillsPosition(), d.getMatchPersent());
            if (isMatch) kafkaSender.send(d.getName(), p.getTitle());
        }));

    }


    private boolean match(List<String> developersSkills, List<String> positionSkills, int percent) {

        List<String> positionSkills2 = new ArrayList<>(positionSkills);
        positionSkills2.retainAll(developersSkills);

        return 100 * positionSkills2.size() / positionSkills.size() > percent;

    }
}
