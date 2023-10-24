package hu.agnos.cube.meta.http;

import com.google.gson.JsonSyntaxException;
import hu.agnos.cube.meta.drillDto.BaseVectorCoordinate;
import hu.agnos.cube.meta.drillDto.DrillVector;
import hu.agnos.cube.meta.drillDto.ReportQuery;
import hu.agnos.cube.meta.dto.CubeNameAndDate;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import hu.agnos.cube.meta.dto.CubeList;
import hu.agnos.cube.driver.ResultSet;
import hu.agnos.cube.meta.dto.HierarchyDTO;
import org.apache.http.client.utils.URIBuilder;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author parisek
 */
public class CubeClient {

    String serviceBaseUri;

    public CubeClient(String serviceBaseUri) {
        this.serviceBaseUri = serviceBaseUri;
    }

    public Optional<ResultSet[]> getData(String cubeName, ReportQuery query) {
        Optional<List<String>> optionalHierarchyHeaders = this.getHierarchyHeaderOfCube(cubeName);
        ResultSet[] result = null;
        if (optionalHierarchyHeaders.isPresent()) {
            List<String> hierarchyHeaders = optionalHierarchyHeaders.get();
            String baseVectorString = createBaseVectorString(hierarchyHeaders, query.baseVector());
            String drillVectorsString = createDrillVectorsString(hierarchyHeaders, query.drillVectors());
            System.out.println(cubeName + "  " + baseVectorString + "  " + drillVectorsString);
            try {
                URI uri = new URIBuilder(serviceBaseUri + "/data")
                        .addParameter("name", cubeName)
                        .addParameter("base", baseVectorString)
                        .addParameter("drill", drillVectorsString)
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .timeout(Duration.of(20, SECONDS))
                        .GET()
                        .build();

                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, BodyHandlers.ofString());
                result = new Gson().fromJson(response.body(), ResultSet[].class);
                // Write back the original drillVectorLabels...
                // the result's order should be the same as the drillVectors order to make it work.
                for (int i = 0; i < result.length; i++) {
                    result[i].setOriginalName(query.drillVectors().get(i).dimsToDrill());
                }
            } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
                Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Optional.ofNullable(result);
    }

    /**
     * Creates a String that represents a baseVector, like "4:1,17::"
     * where order of the hierarchies corresponds to the order of hierarchies
     * in the cube, and the numbers are the element ids in the hierarchy level
     * where the base of the data is.
     *
     * @param hierarchyHeaders Names of the hierarchies in the order inside the cube
     * @param baseVector Base vector in object form, with the name of the hierarchy,
     *                   and the base levels in an ordered array
     * @return The string representation of the base vector, like "4:1,17::"
     */
    private String createBaseVectorString(List<String> hierarchyHeaders, List<BaseVectorCoordinate> baseVector) {
        List<String> result = new ArrayList<>();
        for (String hierarchyName : hierarchyHeaders) {
            for (BaseVectorCoordinate coordinate : baseVector) {
                if (hierarchyName.equals(coordinate.name())) {
                    result.add(String.join(",", coordinate.levelValues()));
                    break;
                }
            }
        }
        return String.join(":", result);
    }

    /**
     * Creates a String that represents some drillVectors, like "0:0:1,1:1:0,0:0:0"
     * where order of the 0-1s corresponds to the order of hierarchies
     * in the cube, and the separate drillVectors are separated by commas.
     *
     * @param hierarchyHeaders Names of the hierarchies in the order inside the cube
     * @param drillVectors Array of drillVectors, which is an array of hierachy names
     *                     meaning the hierarchy is part of the drill.
     * @return The string representation of the drills, like 0:0:1,1:1:0,0:0:0"
     */
    private String createDrillVectorsString(List<String> hierarchyHeaders, List<DrillVector> drillVectors) {
        List<String> result = new ArrayList<>();
        for (DrillVector drillVector : drillVectors) {
            result.add(CreateSingleDrillVectorString(hierarchyHeaders, drillVector));
        }
        return String.join(",", result);
    }

    private String CreateSingleDrillVectorString(List<String> hierarchyHeaders, DrillVector drillVector) {
        List<String> result = new ArrayList<>();
        for (String hierarchyName : hierarchyHeaders) {
            if (drillVector.dimsToDrill().contains(hierarchyName)) {
                result.add(hierarchyName);
            } else {
                result.add("0");
            }
        }
        return String.join(":", result);
    }

//    public Optional<ResultSet[]> getData(String cubeName, String baseVector, String drillVectors) {
//        ResultSet[] result = null;
//        try {
//            URI uri = new URIBuilder(serviceBaseUri + "/data")
//                    .addParameter("name", cubeName)
//                    .addParameter("base", baseVector)
//                    .addParameter("drill", drillVectors)
//                    .build();
//
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(uri)
//                    .timeout(Duration.of(20, SECONDS))
//                    .GET()
//                    .build();
//
//            HttpResponse<String> response = HttpClient.newHttpClient()
//                    .send(request, BodyHandlers.ofString());
//            result = new Gson().fromJson(response.body(), ResultSet[].class);
//        } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
//            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Optional.ofNullable(result);
//    }




    public Optional<CubeList> getCubesNameAndDate() {
        CubeList cubeList = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serviceBaseUri + "/cube_list"))
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            cubeList = new Gson().fromJson(response.body(), CubeList.class);
        } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(cubeList);

    }

    public Optional<List<String>> getCubesName() {
        CubeList cubeList = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serviceBaseUri + "/cube_list"))
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            cubeList = new Gson().fromJson(response.body(), CubeList.class);
        } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cubeList != null) {
            List<String> cubeNames = new ArrayList<>();
            for (CubeNameAndDate cnd : cubeList.getCubesNameAndDate()) {
                cubeNames.add(cnd.getName());
            }
            return Optional.of(cubeNames);
        } else {
            return Optional.empty();
        }

    }

    public Optional<List<String>> getHierarchyHeaderOfCube(String cubeName) {
        List<String> result;
        try {
            URI uri = new URIBuilder(serviceBaseUri + "/hierarchy_header")
                    .addParameter("name", cubeName)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            result = new Gson().fromJson(response.body(), ArrayList.class);
            return Optional.of(result);
        } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    public Optional<String[]> getMeasureHeaderOfCube(String cubeName) {
        String[] result = null;
        try {

            URI uri = new URIBuilder(serviceBaseUri + "/measure_header")
                    .addParameter("name", cubeName)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            result = new Gson().fromJson(response.body(), String[].class);
        } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result != null) {
            return Optional.of(result);
        } else {
            return Optional.empty();
        }

    }

     public Optional<HierarchyDTO> getHierarchy(String cubeName, String hierarchyName) {
        HierarchyDTO result = null;
        try {
            URI uri = new URIBuilder(serviceBaseUri + "/hierarchy")
                    .addParameter("cubename", cubeName)
                    .addParameter("hierarchyname", hierarchyName)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            result = new Gson().fromJson(response.body(), HierarchyDTO.class);
        } catch (URISyntaxException | IOException | InterruptedException | JsonSyntaxException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result != null) {
            return Optional.of(result);
        } else {
            return Optional.empty();
        }

    }

}
