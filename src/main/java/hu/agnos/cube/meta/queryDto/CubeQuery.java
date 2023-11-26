package hu.agnos.cube.meta.queryDto;

import java.util.List;
import java.util.Objects;

public record CubeQuery(String cubeName, DrillVector originalDrill, List<BaseVectorCoordinateForCube> baseVector, DrillVectorForCube drillVector) {

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CubeQuery cubeQuery = (CubeQuery) o;
        return Objects.equals(cubeName, cubeQuery.cubeName)
                && Objects.equals(originalDrill, cubeQuery.originalDrill)
                && Objects.equals(baseVector, cubeQuery.baseVector)
                && Objects.equals(drillVector, cubeQuery.drillVector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubeName, originalDrill, baseVector, drillVector);
    }

}
