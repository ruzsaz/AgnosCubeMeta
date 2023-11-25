package hu.agnos.cube.meta.queryDto;

import java.util.Arrays;

public record DrillVectorForCube(DrillScenario[] drillRequired) {

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DrillVectorForCube that = (DrillVectorForCube) o;
        return Arrays.equals(drillRequired, that.drillRequired);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(drillRequired);
    }

}
