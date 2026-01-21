package com.example.springbootpraxis.service;

import com.example.springbootpraxis.dto.UserDTO;
import com.example.springbootpraxis.model.User;
import com.example.springbootpraxis.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User(1L, "John Doe", "john@example.com", true);
        User user2 = new User(2L, "Jane Smith", "jane@example.com", true);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserDTO> users = userService.getAllUsers();

        // Assert
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Smith", users.get(1).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User(1L, "John Doe", "john@example.com", true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<UserDTO> result = userService.getUserById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
        assertEquals("john@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserDTO userDTO = new UserDTO(null, "John Doe", "john@example.com", true);
        User savedUser = new User(1L, "John Doe", "john@example.com", true);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserDTO result = userService.createUser(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        // Arrange
        User existingUser = new User(1L, "John Doe", "john@example.com", true);
        UserDTO updateDTO = new UserDTO(1L, "John Updated", "johnupdated@example.com", true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        Optional<UserDTO> result = userService.updateUser(1L, updateDTO);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Updated", result.get().getName());
        assertEquals("johnupdated@example.com", result.get().getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(true);

        // Act
        boolean result = userService.deleteUser(1L);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetActiveUsers() {
        // Arrange
        User user1 = new User(1L, "John Doe", "john@example.com", true);
        User user2 = new User(2L, "Jane Smith", "jane@example.com", true);
        when(userRepository.findByIsActive(true)).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserDTO> activeUsers = userService.getActiveUsers();

        // Assert
        assertEquals(2, activeUsers.size());
        verify(userRepository, times(1)).findByIsActive(true);
    }
}
