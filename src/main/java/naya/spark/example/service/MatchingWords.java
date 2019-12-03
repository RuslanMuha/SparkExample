package naya.spark.example.service;

import naya.spark.example.domain.Developer;
import naya.spark.example.domain.Position;


import java.io.Serializable;
import java.util.List;

public interface MatchingWords extends Serializable {
    void matchByKeyWords(List<Developer> developers, List<Position> positions);
}
