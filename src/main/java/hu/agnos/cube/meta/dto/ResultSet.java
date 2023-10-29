package hu.agnos.cube.meta.dto;

import java.util.List;

import hu.agnos.cube.meta.drillDto.DrillVector;

/**
 * @author parisek
 */

public record ResultSet(String cubeName, String[] dimensionHeader, List<String> measures, DrillVector originalDrill, List<ResultElement> response) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResultSet{"
                + "originalName=" + originalDrill
                + ", dimensionHeader=" + dimensionHeader
                + ", cubeName=" + cubeName
                + ", measures=" + String.join(",", measures)
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
