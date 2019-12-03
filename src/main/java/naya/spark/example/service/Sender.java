package naya.spark.example.service;

import naya.spark.example.domain.Developer;
import naya.spark.example.domain.Position;

import java.io.Serializable;

public interface Sender extends Serializable {
   void send(String name, String position);
}
