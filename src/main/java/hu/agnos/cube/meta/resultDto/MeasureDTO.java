package hu.agnos.cube.meta.resultDto;

import hu.agnos.cube.measure.AbstractMeasure;

public record MeasureDTO(String name, boolean hidden) {

    public static MeasureDTO fromMeasure(AbstractMeasure measure) {
        return new MeasureDTO(measure.getName(),measure.isHidden());
    }

}
