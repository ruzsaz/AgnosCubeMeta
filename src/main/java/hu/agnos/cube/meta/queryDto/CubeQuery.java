package hu.agnos.cube.meta.queryDto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record CubeQuery(String cubeName, String cubeHash, DrillVector originalDrill, List<BaseVectorCoordinateForCube> baseVector, DrillVectorForCube drillVector) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CubeQuery cubeQuery = (CubeQuery) o;
        return Objects.equals(cubeHash, cubeQuery.cubeHash)
                && Objects.equals(originalDrill, cubeQuery.originalDrill)
                && Objects.equals(baseVector, cubeQuery.baseVector)
                && Objects.equals(drillVector, cubeQuery.drillVector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubeHash, originalDrill, baseVector, drillVector);
    }

}
