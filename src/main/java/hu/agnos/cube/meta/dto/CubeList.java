package hu.agnos.cube.meta.dto;

import java.util.Map;

/**
 *
 * @author parisek
 */
public record CubeList(Map<String, CubeMetaDTO> cubeMap) {
}
