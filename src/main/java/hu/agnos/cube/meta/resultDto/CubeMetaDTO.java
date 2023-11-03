package hu.agnos.cube.meta.resultDto;

import java.util.Date;
import java.util.List;

public record CubeMetaDTO(Date createdDate, List<DimensionDTO> dimensionHeader, String[] measureHeader) {
}
