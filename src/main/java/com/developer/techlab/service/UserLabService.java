package com.developer.techlab.service;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.entities.UserLab;
import org.apache.catalina.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserLabService {

    UserLabDTO saveUserLab(UserLabDTO userLabDTO);
    UserLabDTO getUserLabById(long userLabId);
    List<UserLabDTO> getAllUserLabs();
    void deleteUserLab(long userLabId);
    UserLabDTO updateUserLab(long userLabId, UserLabDTO userLabDTO);
    UserLabDTO getByEmailInObject(String email);
    void checkExistEmail(UserLabDTO userDto);
    void SiNoEqualCheckEmailExist(UserLab userExist, UserLabDTO userDto);


    UserLabDTO loadUserByEmail(String email) throws ChangeSetPersister.NotFoundException;
}
