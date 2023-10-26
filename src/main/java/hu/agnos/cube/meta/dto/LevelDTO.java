/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.agnos.cube.meta.dto;

import hu.agnos.molap.dimension.Level;

/**
 *
 * @author parisek
 */
public class LevelDTO implements java.io.Serializable {

    private static final long serialVersionUID = -8940196742313994740L;
    
    /**
     * Az adott szint a hierarchia milyen mélységé áll
     */
    private final int depth;
    
    /**
     * Az adott szint egyedi neve
     */
    private final String name;
    
    public LevelDTO(int depth, String name) {
        this.depth = depth;
        this.name = name;
    }

    
    public LevelDTO(Level level) {
        this.depth = level.getDepth();
        this.name = level.getName();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getDepth() {
        return depth;
    }

    public String getName() {
        return name;
    }    
}
