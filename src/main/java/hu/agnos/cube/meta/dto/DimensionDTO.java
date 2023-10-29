package hu.agnos.cube.meta.dto;

import java.util.List;
import java.util.stream.Collectors;

import hu.agnos.cube.dimension.Dimension;


/**
 * @author parisek
 */
public record DimensionDTO(String name, List<LevelDTO> levels, int maxDepth) {

    public static DimensionDTO fromDimension(Dimension dimension) {
        return new DimensionDTO(dimension.getName(),
                dimension.getLevels().stream().map(LevelDTO::fromLevel).collect(Collectors.toList()),
                dimension.getLevelCount());
    }

}
