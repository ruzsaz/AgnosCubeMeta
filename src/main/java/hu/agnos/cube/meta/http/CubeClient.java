/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.agnos.cube.meta.http;

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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import hu.agnos.cube.meta.dto.CubeList;
import hu.agnos.cube.driver.ResultSet;
import org.apache.http.client.utils.URIBuilder;

import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author parisek
 */
public class CubeClient {

        public Optional<ResultSet[]> getData(String serviceBaseUri, String cubeName, String baseVector, String drillVectors) {
        ResultSet[] result = null;
        try {
            URI uri = new URIBuilder(serviceBaseUri + "/data")
                    .addParameter("name", cubeName)
                    .addParameter("base", baseVector)
                    .addParameter("drill", drillVectors)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.of(20, SECONDS))
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
            result = new Gson().fromJson(response.body(), ResultSet[].class);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(result);

    }

    
    public Optional<CubeList> getCubesNameAndDate(String serviceBaseUri) {
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
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.ofNullable(cubeList);

    }

    public Optional<List<String>> getCubesName(String serviceBaseUri) {
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
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cubeList != null) {
            List<String> cubeNames = new ArrayList<>();
            for (CubeNameAndDate cnd : cubeList.getCubesNameAndDate()) {
                cubeNames.add(cnd.getName());
            }
            return Optional.ofNullable(cubeNames);
        } else {
            return Optional.empty();
        }

    }

    public Optional<String[]> getHierarchyHeaderOfCube(String serviceBaseUri, String cubeName) {
        String[] result = null;
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
            result = new Gson().fromJson(response.body(), String[].class);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result != null) {
            return Optional.ofNullable(result);
        } else {
            return Optional.empty();
        }

    }

    public Optional<String[]> getMeasureHeaderOfCube(String serviceBaseUri, String cubeName) {
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
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(CubeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result != null) {
            return Optional.ofNullable(result);
        } else {
            return Optional.empty();
        }

    }

}
