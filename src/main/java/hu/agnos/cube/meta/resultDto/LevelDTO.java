package hu.agnos.cube.meta.resultDto;

import hu.agnos.cube.dimension.Level;

/**
 * @param depth Depth of this level in the dimension (0 is the root element's level)
 * @author parisek
 */
public record LevelDTO(int depth, String name) {

    public static LevelDTO fromLevel (Level level) {
        return new LevelDTO(level.getDepth(), level.getName());
    }

}