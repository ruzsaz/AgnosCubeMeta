package hu.agnos.cube.meta.resultDto;

import hu.agnos.cube.dimension.Level;

/**
 * CubeServer -> ReportServer dto. This is a level in a dimension.
 *
 * @param depth Depth of this level in the dimension (0 is the root element's level)
 * @param name The level's name
 */
public record LevelDTO(int depth, String name) {

    public static LevelDTO fromLevel (Level level) {
        return new LevelDTO(level.getDepth(), level.getName());
    }

}
