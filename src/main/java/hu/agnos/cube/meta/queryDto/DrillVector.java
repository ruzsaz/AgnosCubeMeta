package hu.agnos.cube.meta.queryDto;

import java.io.Serializable;
import java.util.List;

public record DrillVector(List<String> dimsToDrill) implements Serializable {
}
