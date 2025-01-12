package hu.agnos.cube.meta.queryDto;

import java.io.Serializable;
import java.util.List;

public record BaseVectorCoordinate(String name, List<String> levelValues) implements Serializable {
}
