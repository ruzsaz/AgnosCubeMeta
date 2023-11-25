package hu.agnos.cube.meta.queryDto;

import java.util.List;
import java.util.Objects;

public record CubeQuery(String cubeName, List<DrillVector> originalDrills, List<BaseVectorCoordinateForCube> baseVector, List<DrillVectorForCube> drillVectors) {

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CubeQuery cubeQuery = (CubeQuery) o;
        return Objects.equals(cubeName, cubeQuery.cubeName)
                && Objects.equals(originalDrills, cubeQuery.originalDrills)
                && Objects.equals(baseVector, cubeQuery.baseVector)
                && Objects.equals(drillVectors, cubeQuery.drillVectors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubeName, originalDrills, baseVector, drillVectors);
    }

}
