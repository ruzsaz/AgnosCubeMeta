package hu.agnos.cube.meta.queryDto;

import java.util.List;

public record ReportQuery(String reportName, List<BaseVectorCoordinate> baseVector, List<DrillVector> drillVectors, boolean isCubePreparationRequired) {
}
