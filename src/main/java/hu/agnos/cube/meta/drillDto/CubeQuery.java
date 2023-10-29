package hu.agnos.cube.meta.drillDto;

import java.util.List;

public record CubeQuery(String cubeName, List<DrillVector> originalDrills, List<BaseVectorCoordinateForCube> baseVector, List<DrillVectorForCube> drillVectors) {
}
