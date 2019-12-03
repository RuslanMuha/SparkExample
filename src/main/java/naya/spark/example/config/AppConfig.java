package naya.spark.example.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.serializer.KryoRegistrator;
import org.apache.spark.serializer.KryoSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class AppConfig implements Serializable {

    @Bean
    public JavaSparkContext sc() {
        SparkConf f = new SparkConf().setAppName("words matcher").setMaster("local[*]").
                set("spark.serializer", "org.apache.spark.serializer.KryoSerializer").
        set("spark.kryo.registrator", "com.art.spark.AvroKyroRegistrator");
        return new JavaSparkContext(f);
    }
}
