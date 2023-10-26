package hu.agnos.cube.meta.dto;


import java.util.Objects;

public record NodeDTO(String id, String knownId, String name) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeDTO dimValue = (NodeDTO) o;
        return Objects.equals(knownId, dimValue.knownId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knownId);
    }

}
