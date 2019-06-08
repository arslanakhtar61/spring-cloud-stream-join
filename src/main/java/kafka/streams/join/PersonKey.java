package kafka.streams.join;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class PersonKey {

    private String firstName;
    private String lastName;
    private int id;

    public PersonKey() {
    }

    public PersonKey(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    @JsonGetter("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonSetter("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonGetter("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonSetter("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonKey{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonKey personKey = (PersonKey) o;
        return id == personKey.id &&
                Objects.equals(firstName, personKey.firstName) &&
                Objects.equals(lastName, personKey.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }
}
