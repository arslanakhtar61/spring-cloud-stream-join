package kafka.streams.join;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Person {

    private double age;

    public Person() {
    }

    public Person(double age) {
        this.age = age;
    }

    @JsonGetter("age")
    public double getAge() {
        return age;
    }

    @JsonSetter("age")
    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
