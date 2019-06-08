package kafka.streams.serde;

import kafka.streams.join.SchoolKey;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public final class SchoolKeySerde extends Serdes.WrapperSerde<SchoolKey> {
    public SchoolKeySerde () {
        super(new JsonSerializer<>(), new JsonDeserializer<>(SchoolKey.class));
    }
}
