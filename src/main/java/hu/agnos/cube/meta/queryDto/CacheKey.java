package hu.agnos.cube.meta.queryDto;

import hu.agnos.cube.dimension.Node;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record CacheKey(CacheKeyElement[] elements) implements Serializable {

    // TODO: short tömbbé alakítani:
    public static CacheKey fromNodeList(List<Node> nodes) {
        return new CacheKey(nodes.stream().map(CacheKeyElement::fromNode).toArray(CacheKeyElement[]::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.deepEquals(elements, cacheKey.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }

}
