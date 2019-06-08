package kafka.streams.serde;

import kafka.streams.join.Person;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class PersonSerde extends Serdes.WrapperSerde<Person> {
    public PersonSerde () {
        super(new JsonSerializer<>(), new JsonDeserializer<>(Person.class));
    }
}
