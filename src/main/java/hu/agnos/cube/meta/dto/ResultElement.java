package hu.agnos.cube.meta.dto;

/**
 * Ez az osztály az eredményhalmaz egy sorát reprzentálja
 *
 * @author ruzsaz
 */

public record ResultElement(NodeDTO[] header, double[] measureValues, int drillVectorId) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\tElement:\n\t");
        sb.append(printHeader()).append("\n\t");
        sb.append(printMeasures());
        return sb.toString();
    }

    public String printHeader() {
        StringBuilder sb = new StringBuilder("\tHeader: ");
        for (NodeDTO s : header) {
            sb.append(s.toString()).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String printMeasures() {
        StringBuilder sb = new StringBuilder("\tMeasures: ");
        for (double s : measureValues) {
            sb.append(s).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public ResultElement deepCopy() {
        NodeDTO[] tempHeader = new NodeDTO[header.length];
        System.arraycopy(header, 0, tempHeader, 0, header.length);

        double[] tempMeasureValues = new double[measureValues.length];
        System.arraycopy(measureValues, 0, tempMeasureValues, 0, measureValues.length);

        return new ResultElement(tempHeader, tempMeasureValues, drillVectorId);
    }

}
