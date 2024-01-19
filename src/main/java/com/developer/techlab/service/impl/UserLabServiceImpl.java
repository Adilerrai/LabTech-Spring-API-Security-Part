package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.entities.UserLab;
import com.developer.techlab.repositories.UserLabRepository;
import com.developer.techlab.service.UserLabService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLabServiceImpl implements UserLabService {

    @Autowired
    UserLabRepository userLabRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserLabDTO saveUserLab(UserLabDTO userLabDTO) {
        UserLab userLab = modelMapper.map(userLabDTO, UserLab.class);
        return modelMapper.map(userLabRepository.save(userLab), UserLabDTO.class);
    }

    @Override
    public UserLabDTO getUserLabById(long userLabId) {
        UserLab userLab = userLabRepository.findById(userLabId).orElse(null);
        return (userLab != null) ? modelMapper.map(userLab, UserLabDTO.class) : null;
    }

    @Override
    public List<UserLabDTO> getAllUserLabs() {
        List<UserLab> userLabs = userLabRepository.findAll();
        return userLabs.stream()
                .map(userLab -> modelMapper.map(userLab, UserLabDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserLab(long userLabId) {
        userLabRepository.deleteById(userLabId);
    }

    @Override
    public UserLabDTO updateUserLab(long userLabId, UserLabDTO updatedUserLabDTO) {
        UserLab existingUserLab = userLabRepository.findById(userLabId).orElse(null);
        if(existingUserLab != null){
            modelMapper.map(updatedUserLabDTO, existingUserLab);
            return modelMapper.map(userLabRepository.save(existingUserLab), UserLabDTO.class);
        }
        return null;
    }
}
