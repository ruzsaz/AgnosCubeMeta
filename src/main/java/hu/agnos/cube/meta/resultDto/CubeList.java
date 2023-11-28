package hu.agnos.cube.meta.resultDto;

import java.util.Map;

public record CubeList(Map<String, CubeMetaDTO> cubeMap) {

    public void renewCubeMap(Map<String, CubeMetaDTO> newContent) {
        synchronized (this) {
            cubeMap.clear();
            cubeMap.putAll(newContent);
        }
    }

}
