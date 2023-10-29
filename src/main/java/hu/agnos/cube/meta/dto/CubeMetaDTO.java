package hu.agnos.cube.meta.dto;


import java.util.Date;
import java.util.List;

/**
 * @author parisek
 */

public record CubeMetaDTO(Date createdDate, List<DimensionDTO> dimensionHeader, String[] measureHeader) {
}
