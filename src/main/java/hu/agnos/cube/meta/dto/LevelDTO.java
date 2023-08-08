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
    
    /**
     * A sznthez tartozó azonosítót tartalamzó oszlop neve, szerepe a
     * cube-ról készült reportnál jelentkezik
     */
    private final String idColumnName;
    
    /**
     * A szinthez tartozó kódot tartalamzó oszlop neve, szerepe a
     * cube-ról készült reportnál jelentkezik
     */
    private final String codeColumnName;
    
    /**
     * A szinthez tartozó nevet tartalamzó oszlop neve, szerepe a
     * cube-ról készült reportnál jelentkezik
     */    
    private final String nameColumnName;

    public LevelDTO(int depth, String name, String idColumnName, String codeColumnName, String nameColumnName) {
        this.depth = depth;
        this.name = name;
        this.idColumnName = idColumnName;
        this.codeColumnName = codeColumnName;
        this.nameColumnName = nameColumnName;
    }

    
    public LevelDTO(Level level) {
        this.depth = level.getDepth();
        this.name = level.getName();
        this.idColumnName = level.getIdColumnName();
        this.codeColumnName = level.getCodeColumnName();
        this.nameColumnName = level.getNameColumnName();
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

    public String getIdColumnName() {
        return idColumnName;
    }

    public String getCodeColumnName() {
        return codeColumnName;
    }

    public String getNameColumnName() {
        return nameColumnName;
    }

    
}
