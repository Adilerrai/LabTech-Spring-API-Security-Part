package com.developer.techlab.service;

import com.developer.techlab.DTO.UserLabDTO;
import org.apache.catalina.User;

import java.util.List;

public interface UserLabService {

    UserLabDTO saveUserLab(UserLabDTO userLabDTO);
    UserLabDTO getUserLabById(long userLabId);
    List<UserLabDTO> getAllUserLabs();
    void deleteUserLab(long userLabId);
    UserLabDTO updateUserLab(long userLabId, UserLabDTO userLabDTO);
}
