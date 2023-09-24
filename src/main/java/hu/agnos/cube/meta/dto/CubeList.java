/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.agnos.cube.meta.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author parisek
 */
public class CubeList {

    private List<CubeNameAndDate> cubesNameAndDate;

    public CubeList() {
        this.cubesNameAndDate = new ArrayList<>();
    }

    public CubeList(List<CubeNameAndDate> cubesNameAndDate) {
        this.cubesNameAndDate = cubesNameAndDate;
    }

    public List<CubeNameAndDate> getCubesNameAndDate() {
        return cubesNameAndDate;
    }

    public void setCubesNameAndDate(List<CubeNameAndDate> cubesNameAndDate) {
        this.cubesNameAndDate = cubesNameAndDate;
    }

    public int size(){
        return this.cubesNameAndDate.size();
    }

    public boolean containsCubeWithName(String cubeName) {
        return cubesNameAndDate.stream().anyMatch(e -> e.getName().equals(cubeName));
    }

}
