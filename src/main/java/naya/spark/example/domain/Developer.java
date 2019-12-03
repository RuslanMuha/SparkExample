package naya.spark.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer implements Serializable {
    private List<String> listSkillsDeveloper;
    private String name;
    private int matchPersent;


}
