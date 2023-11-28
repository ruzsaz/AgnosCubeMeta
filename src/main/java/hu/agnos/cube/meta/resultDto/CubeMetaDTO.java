package hu.agnos.cube.meta.resultDto;

import java.util.Date;
import java.util.List;

public record CubeMetaDTO(Date createdDate, String hash, List<DimensionDTO> dimensionHeader, String[] measureHeader) {
}
