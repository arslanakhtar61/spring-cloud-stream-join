package kafka.streams.join;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class SchoolKey {

    private String name;
    private String country;
    private String city;
    private int id;

    public SchoolKey() {
    }

    public SchoolKey(String name, String country, String city, int id) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("country")
    public String getCountry() {
        return country;
    }

    @JsonSetter("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonGetter("city")
    public String getCity() {
        return city;
    }

    @JsonSetter("city")
    public void setCity(String city) {
        this.city = city;
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
        return "SchoolKey{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolKey schoolKey = (SchoolKey) o;
        return id == schoolKey.id &&
                Objects.equals(name, schoolKey.name) &&
                Objects.equals(country, schoolKey.country) &&
                Objects.equals(city, schoolKey.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, city, id);
    }
}
