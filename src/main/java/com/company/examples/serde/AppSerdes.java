package com.company.examples.serde;

import com.company.examples.types.total_Sales;

import com.company.examples.types.txn;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {


    static final class txnSerde extends WrapperSerde<txn> {
        txnSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<txn> txn() {
        txnSerde serde = new txnSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, txn.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class total_SalesSerde extends WrapperSerde<total_Sales> {
        total_SalesSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<total_Sales> total_Sales() {
        total_SalesSerde serde = new total_SalesSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, txn.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
