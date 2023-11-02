package hu.agnos.cube.meta.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hu.agnos.cube.meta.drillDto.DrillScenario;
import hu.agnos.cube.meta.drillDto.DrillVector;

/**
 * Response for a single drill request towards a single cube.
 * Contains some meta-info for the drill, and the results.
 *
 * @param cubeName Name of the cube who answers for the drill in this ResultSet
 * @param dimensionHeader Dimensions in the cube
 * @param measures Measures in the cube; all will be included in this ResultSet as values
 * @param originalDrill Original drill vector, as the ReportServer requested
 * @param actualDrill Vector of the actual drill made in each coordinate
 * @param response The result "data rows", as (dimension values) ->  (result values)
 */
public record ResultSet(String cubeName, String[] dimensionHeader, List<String> measures, DrillVector originalDrill, DrillScenario[] actualDrill, List<ResultElement> response) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultSet{"
                + "originalName=" + originalDrill
                + ", dimensionHeader=" + dimensionHeader
                + ", cubeName=" + cubeName
                + ", measures=" + String.join(",", measures)
                + ", originalDrill=" + String.join(",", originalDrill.dimsToDrill())
                + ", actualDrill=" + Arrays.stream(actualDrill).map(Enum::name).collect(Collectors.joining(","))
                + ", response:");

        for (ResultElement e : this.response) {
            sb.append("\n\t").append(e.toString());
        }
        sb.append("\n}");
        return sb.toString();
    }

//    public ResultSet deepCopy() {
//        ResultSet result = new ResultSet(new String(name));
//        for (ResultElement r : this.response) {
//            result.addResponse(r);
//        }
//        return result;
//    }

}
