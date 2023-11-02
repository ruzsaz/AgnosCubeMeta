package hu.agnos.cube.meta.drillDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrillScenario {

    DRILL (true, true, false),
    DRILL_FOR_EXTRA_CALCULATIONS (true, false, false),
    NOT_REQUESTED (false, false, false),
    REQUESTED_BUT_ALLOWED_LEVEL_REACHED (false, true, false),
    REQUESTED_BUT_LEVEL_MISSING (false, false, true),
    FOR_EXTRA_CALCULATIONS_BUT_ALLOWED_LEVEL_REACHED (false, false, false),
    FOR_EXTRA_CALCULATIONS_BUT_LEVEL_MISSING (false, false, false);

    final boolean required;
    final boolean showResultAsDimValue;
    final boolean showResultAsParentsValue;

}
