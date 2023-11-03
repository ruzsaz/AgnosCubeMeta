package hu.agnos.cube.meta.queryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum DrillScenario {

    DRILL(true, true, false),
    DRILL_FOR_EXTRA_CALCULATIONS(true, false, false),
    NOT_REQUESTED(false, false, false),
    REQUESTED_BUT_ALLOWED_LEVEL_REACHED(false, true, false),
    REQUESTED_BUT_LEVEL_MISSING(false, false, true),
    FOR_EXTRA_CALCULATIONS_BUT_ALLOWED_LEVEL_REACHED(false, false, false),
    FOR_EXTRA_CALCULATIONS_BUT_LEVEL_MISSING(false, false, false);

    private final boolean required; // Shows if drill is required in the dimension
    private final boolean showResultAsDimValue; // Indicates that this dimension's coordinate values should be shown in the requested drill
    private final boolean showResultAsParentsValue; // Shows if the value of the drill should be read from the parent's value

}
