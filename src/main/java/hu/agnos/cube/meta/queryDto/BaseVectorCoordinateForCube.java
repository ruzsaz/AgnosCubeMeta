package hu.agnos.cube.meta.queryDto;

import java.io.Serializable;
import java.util.Objects;

public record BaseVectorCoordinateForCube(String dimensionName, String levelValuesString) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BaseVectorCoordinateForCube that = (BaseVectorCoordinateForCube) o;
        return Objects.equals(dimensionName, that.dimensionName) && Objects.equals(levelValuesString, that.levelValuesString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimensionName, levelValuesString);
    }

}
