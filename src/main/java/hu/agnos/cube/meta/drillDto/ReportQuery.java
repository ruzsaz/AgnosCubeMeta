package hu.agnos.cube.meta.drillDto;

import java.util.List;

public record ReportQuery(String reportName, List<BaseVectorCoordinate> baseVector, List<DrillVector> drillVectors) {
}
