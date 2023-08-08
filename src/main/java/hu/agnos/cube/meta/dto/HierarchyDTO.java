/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.agnos.cube.meta.dto;
import hu.agnos.molap.dimension.Hierarchy;
import hu.agnos.molap.dimension.Level;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author parisek
 */
public class HierarchyDTO implements java.io.Serializable {

    private static final long serialVersionUID = -8940196742313994740L;

    /**
     * A hierarchia szintek tára
     */
    private List<LevelDTO> levels;

    /**
     * A hierarchia egyedi neve
     */
    private String hierarchyUniqueName;

    /**
     * A hierarchia szintjeinek száma (ebbe a base level szint is benne van)
     */
    private int maxDepth;

    public HierarchyDTO(List<LevelDTO> levels, String hierarchyUniqueName, int maxDepth) {
        this.levels = levels;
        this.hierarchyUniqueName = hierarchyUniqueName;
        this.maxDepth = maxDepth;
    }

    
    
    public HierarchyDTO(Hierarchy hierarchy) {        
        this.levels = new ArrayList<>();
        for(Level level : hierarchy.getLevels()){
            this.levels.add(new LevelDTO(level));
        }        
        this.hierarchyUniqueName = hierarchy.getHierarchyUniqueName();
        this.maxDepth = hierarchy.getMaxDepth();
    }

    public List<LevelDTO> getLevels() {
        return levels;
    }

    public void setLevels(List<LevelDTO> levels) {
        this.levels = levels;
    }

    public String getHierarchyUniqueName() {
        return hierarchyUniqueName;
    }

    public void setHierarchyUniqueName(String hierarchyUniqueName) {
        this.hierarchyUniqueName = hierarchyUniqueName;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    

    
}
