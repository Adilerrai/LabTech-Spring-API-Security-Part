package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.entities.UserLab;
import com.developer.techlab.repositories.UserLabRepository;
import com.developer.techlab.service.UserLabService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserLabServiceImpl implements UserLabService {


    UserLabRepository userLabRepository;

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

    @Override
    public UserLabDTO getByEmailInObject(String email)
    {
        UserLab user = userLabRepository.findByEmailAndDeletedFalse(email).orElseThrow(() -> new UsernameNotFoundException("User not found with this email : " + email));
        return modelMapper.map(user, UserLabDTO.class);
    }

    @Override
    public void checkExistEmail(UserLabDTO userDto)
    {
        if(userDto.getEmail().equals(getByEmail(userDto.getEmail())))
        {
        }
    }

    @Override
    public void SiNoEqualCheckEmailExist(UserLab userExist, UserLabDTO userDto)
    {
        if (!userDto.getEmail().equals(userExist.getEmail()))
        {
            checkExistEmail(userDto);
        }
    }

    @Override
    public UserLabDTO loadUserByEmail(String email) {
        UserLab userLab = userLabRepository.findByEmailAndDeletedFalse(email).orElse(null);
        return (userLab != null) ? modelMapper.map(userLab, UserLabDTO.class) : null;
    }


    // Define getByEmail method
    private String getByEmail(String email) {
        UserLab user = userLabRepository.findByEmailAndDeletedFalse(email).orElse(null);
        return (user != null) ? user.getEmail() : null;
    }

}
