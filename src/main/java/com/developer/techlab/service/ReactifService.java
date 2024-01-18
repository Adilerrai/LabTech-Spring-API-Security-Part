// ReactifService.java
package com.developer.techlab.service;

import com.developer.techlab.DTO.ReactifDTO;
import com.developer.techlab.entities.Reactif;

import java.util.List;

public interface ReactifService {
    ReactifDTO saveReactif(ReactifDTO reactifDTO);
    ReactifDTO getReactifById(long reactifId);
    List<ReactifDTO> getAllReactifs();
    void deleteReactif(long reactifId);
    ReactifDTO updateReactif(long reactifId, ReactifDTO updatedReactifDTO);
}
