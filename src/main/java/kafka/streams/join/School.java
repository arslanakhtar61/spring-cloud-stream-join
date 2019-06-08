package kafka.streams.join;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class School {

    private String address;

    public School() {
    }

    public School(String address) {
        this.address = address;
    }

    @JsonGetter("address")
    public String getAddress() {
        return address;
    }

    @JsonSetter("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "address='" + address + '\'' +
                '}';
    }
}
