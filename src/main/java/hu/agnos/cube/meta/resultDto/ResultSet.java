package hu.agnos.cube.meta.resultDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hu.agnos.cube.meta.queryDto.DrillScenario;
import hu.agnos.cube.meta.queryDto.DrillVector;

/**
 * CubeServer -> ReportServer dto. Response for a single drill request from a single cube.
 * Contains some meta-info for the drill, and the results.
 *
 * @param cubeName Name of the cube who answers for the drill in this ResultSet
 * @param dimensionHeader Dimensions in the cube
 * @param measures Measures in the cube; all will be included in this ResultSet as values
 * @param originalDrill Original drill vector, as the ReportServer requested
 * @param actualDrill Vector of the actual drill made in each coordinate
 * @param response The result "data rows", as (dimension values) ->  (result values)
 */
public record ResultSet(String cubeName,
                        String[] dimensionHeader,
                        List<String> measures,
                        DrillVector originalDrill,
                        DrillScenario[] actualDrill,
                        List<ResultElement> response)
        implements Serializable {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultSet{"
                + "originalName=" + originalDrill
                + ", dimensionHeader=" + Arrays.toString(dimensionHeader)
                + ", cubeName=" + cubeName
                + ", measures=" + String.join(",", measures)
                + ", originalDrill=" + String.join(",", originalDrill.dimsToDrill())
                + ", actualDrill=" + Arrays.stream(actualDrill).map(Enum::name).collect(Collectors.joining(","))
                + ", response:");

        for (ResultElement e : response) {
            sb.append("\n\t").append(e.toString());
        }
        sb.append("\n}");
        return sb.toString();
    }

}
