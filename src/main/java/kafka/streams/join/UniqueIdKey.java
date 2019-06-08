package kafka.streams.join;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

public class UniqueIdKey {

    private int id;

    public UniqueIdKey() {
    }

    public UniqueIdKey(int id) {
        this.id = id;
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
        return "UniqueIdKey{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqueIdKey that = (UniqueIdKey) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
