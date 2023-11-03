package hu.agnos.cube.meta.queryDto;

import java.util.List;

public record CubeQuery(String cubeName, List<DrillVector> originalDrills, List<BaseVectorCoordinateForCube> baseVector, List<DrillVectorForCube> drillVectors) {
}
