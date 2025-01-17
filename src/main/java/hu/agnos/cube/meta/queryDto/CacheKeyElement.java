package hu.agnos.cube.meta.queryDto;

import hu.agnos.cube.dimension.Node;

import java.io.Serializable;

public record CacheKeyElement(int level, int id) implements Serializable {

    public static CacheKeyElement fromNode(Node node) {
        return new CacheKeyElement(node.getLevel(), node.getId());
    }

}
