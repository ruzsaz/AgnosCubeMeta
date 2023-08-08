/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.agnos.cube.meta;

import hu.agnos.cube.meta.dto.CubeList;
import hu.agnos.cube.meta.http.CubeClient;
import hu.agnos.cube.meta.dto.CubeNameAndDate;
import java.util.Optional;

/**
 *
 * @author parisek
 */
public class AgnosCubeMeta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CubeClient cc = new CubeClient();
        Optional<CubeList> cl = cc.getCubesNameAndDate("http://localhost:7979/acs");
        if (cl.isPresent()) {
            for (CubeNameAndDate cd : (cl.get()).getCubesNameAndDate()) {
                System.out.println(cd.getName());
            }
        }
    }

}
