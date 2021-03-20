package com.company.examples;


import com.company.examples.serde.JsonSerializer;
import com.company.examples.types.product;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {

        String bootstrapServers = "127.0.0.1:9092";
        String topic = "product";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        // create the producer
        KafkaProducer<String, product> producer = new KafkaProducer<String, product>(properties);
        product t1 = new product();

        t1.withproductID("t1");
        t1.withproductDate("1231212");
        t1.withCustID("c1");
        t1.withproductAmount(140.0);
        t1.withProdCat("p1");
        t1.withProduct("Phone");
        t1.withCity("Mumbai");
        t1.withState("MH");
        t1.withPaymentMode("aa");

        ProducerRecord<String, product> record =
                new ProducerRecord<String, product>(topic,  t1.getCustID() , t1);

        // send data - asynchronous
        producer.send(record);

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }
}
