package kafka.streams.join;

import org.apache.kafka.streams.kstream.ValueJoiner;

import javax.validation.constraints.Null;

public class Joiner implements ValueJoiner<Person, School, Null> {

    @Override
    public Null apply(Person person, School school) {
        System.out.println("Joiner person3= " + person + " ,Joiner school3= " + school);
        return null;
    }
}
