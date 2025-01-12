package hu.agnos.cube.meta.resultDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;


public record NodeDTO(@JsonProperty("id") String knownId, String name) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        final NodeDTO dimValue = (NodeDTO) o;
        return Objects.equals(knownId, dimValue.knownId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knownId);
    }

}
