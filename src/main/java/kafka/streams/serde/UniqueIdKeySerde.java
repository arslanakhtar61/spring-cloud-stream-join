package kafka.streams.serde;

import kafka.streams.join.UniqueIdKey;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class UniqueIdKeySerde extends Serdes.WrapperSerde<UniqueIdKey> {
    public UniqueIdKeySerde () {
        super(new JsonSerializer<>(), new JsonDeserializer<>(UniqueIdKey.class));
    }
}
