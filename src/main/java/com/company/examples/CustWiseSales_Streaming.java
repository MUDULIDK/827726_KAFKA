package com.company.examples;

import com.company.examples.serde.AppSerdes;

import com.company.examples.types.total_Sales;
import com.company.examples.types.product;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class CustWiseSales_Streaming {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "product-cust-sales");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");


        StreamsBuilder streamsBuilder = new StreamsBuilder();

        //1. Transform these product  into totalSales Events:
        KStream<String, product> KS0 = streamsBuilder.stream("product", Consumed.with(AppSerdes.String(), AppSerdes.product()));

        //1. Transform these events into total Sales Events:
        KStream<String,total_Sales> KS1 = KS0.map((key, product)->new KeyValue<>(
                key,totalSales.getTotalSalesFrom(product)));
        KS1.print(Printed.<String, total_Sales>toSysOut().withLabel("KS1"));

        //2. group them based on the customer_id:
        KGroupedStream<String,total_Sales> KGS0 = KS1.groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.total_Sales()));

        // 3.Compute the sum of rewards by customer id:
        KTable<String, total_Sales> KT0 = (KGS0.reduce((aggValue, newValue)-> {
            newValue.setTotalSaleAmount(newValue.getproductAmount().doubleValue()+aggValue.getTotalSaleAmount().doubleValue());
            return newValue;
        }));

        //Very Important ;' Reduce method comes with some limitation, It does not allow to change Type of Input and its output.
        //Here

//        KT0.toStream().print(Printed.<String, total_Sales>toSysOut().withLabel("Final sale"));


        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams");
            streams.cleanUp();
        }));
    }


}



