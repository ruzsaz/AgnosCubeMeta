/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.agnos.cube.meta.dto;

import hu.agnos.molap.dimension.Dimension;


/**
 *
 * @author parisek
 */
public class DimensionDTO implements java.io.Serializable {

    private static final long serialVersionUID = -8940196742313994740L;

    /**
     * A dimenzió kockán belüli egyedi neve
     */
    private final String uniqueName;

    /**
     * mivel egy dimenzióban egynél több hierarchia is lehet, így a
     * hierarchiákat tömben tároljuk
     */
    private final HierarchyDTO[] hierarchies;

    public DimensionDTO(String uniqueName, HierarchyDTO[] hierarchies) {
        this.uniqueName = uniqueName;
        this.hierarchies = hierarchies;
    }

    
    
    public DimensionDTO(Dimension dimension) {
        this.uniqueName = dimension.getUniqueName();
        this.hierarchies = new HierarchyDTO[dimension.getHierarchyCnt()];
        for(int i = 0; i < dimension.getHierarchyCnt(); i++){
            this.hierarchies[i] = new HierarchyDTO(dimension.getHierarchyById(i));
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public HierarchyDTO[] getHierarchies() {
        return hierarchies;
    }

    
    
}
