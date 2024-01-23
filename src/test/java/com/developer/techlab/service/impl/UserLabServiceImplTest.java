package com.developer.techlab.service.impl;

import com.developer.techlab.DTO.UserLabDTO;
import com.developer.techlab.entities.UserLab;
import com.developer.techlab.entities.enums.Role;
import com.developer.techlab.repositories.UserLabRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

class UserLabServiceImplTest {

    @Mock
    UserLabRepository userLabRepository;

    ModelMapper modelMapper;

    @InjectMocks
    UserLabServiceImpl userLabServiceImpl;

    @BeforeEach
    void setUp() {
        userLabRepository = mock(UserLabRepository.class);
        modelMapper = new ModelMapper();
        userLabServiceImpl = new UserLabServiceImpl(userLabRepository, modelMapper);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void saveUserLab() {
            // Arrange
            UserLabDTO userLabDTO = new UserLabDTO();
            userLabDTO.setId(1L);
            userLabDTO.setRole(Role.TECHNICIEN);
            // Assume that the modelMapper.map() method will return a UserLab object
            UserLab userLab = new UserLab();
            userLab.setId(1L);
            userLab.setRole(Role.TECHNICIEN);
            when(userLabRepository.save(any(UserLab.class))).thenReturn(userLab);

            // Act
            UserLabDTO savedUserLabDTO = userLabServiceImpl.saveUserLab(userLabDTO);

            // Assert
            assertEquals(userLabDTO.getId(), savedUserLabDTO.getId());
            assertEquals(userLabDTO.getRole(), savedUserLabDTO.getRole());
        }



    @Test
    void getUserLabById() {
        // Arrange
        UserLab userLab = new UserLab();
        userLab.setId(1L);
        userLab.setRole(Role.TECHNICIEN);
        when(userLabRepository.findById(anyLong())).thenReturn(Optional.of(userLab));

        // Act
        UserLabDTO foundUserLabDTO = userLabServiceImpl.getUserLabById(1L);

        // Assert
        assertNotNull(foundUserLabDTO);
        assertEquals(userLab.getId(), foundUserLabDTO.getId());
        assertEquals(userLab.getRole(), foundUserLabDTO.getRole());
    }

    @Test
    void getAllUserLabs() {
        // Arrange
        List<UserLab> userLabs = new ArrayList<>();
        UserLab userLab1 = new UserLab();
        userLab1.setId(1L);
        userLab1.setRole(Role.TECHNICIEN);
        userLabs.add(userLab1);

        UserLab userLab2 = new UserLab();
        userLab2.setId(2L);
        userLab2.setRole(Role.TECHNICIEN);
        userLabs.add(userLab2);

        when(userLabRepository.findAll()).thenReturn(userLabs);

        // Act
        List<UserLabDTO> userLabDTOs = userLabServiceImpl.getAllUserLabs();

        // Assert
        assertNotNull(userLabDTOs);
        assertEquals(2, userLabDTOs.size());
    }

    @Test
    void deleteUserLab() {
        // No need to arrange anything for this test

        // Act
        userLabServiceImpl.deleteUserLab(1L);

        // Assert
        verify(userLabRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUserLab() {
        // Arrange
        UserLabDTO userLabDTO = new UserLabDTO();
        userLabDTO.setId(1L);
        userLabDTO.setRole(Role.TECHNICIEN);
        UserLab userLab = new UserLab();
        userLab.setId(1L);
        userLab.setRole(Role.TECHNICIEN);
        when(userLabRepository.findById(anyLong())).thenReturn(Optional.of(userLab));
        when(userLabRepository.save(any(UserLab.class))).thenReturn(userLab);

        // Act
        UserLabDTO updatedUserLabDTO = userLabServiceImpl.updateUserLab(1L, userLabDTO);

        // Assert
        assertNotNull(updatedUserLabDTO);
        assertEquals(userLabDTO.getId(), updatedUserLabDTO.getId());
        assertEquals(userLabDTO.getRole(), updatedUserLabDTO.getRole());
    }
}