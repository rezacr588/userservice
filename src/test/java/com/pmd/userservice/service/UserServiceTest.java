package com.pmd.userservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.pmd.userservice.model.User;
import com.pmd.userservice.repository.UserRepository;

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
	void getAllUsers() {
		User user1 = new User("user1", "pass1", "user1@example.com", null);
		User user2 = new User("user2", "pass2", "user2@example.com", null);
		when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

		List<User> users = userService.getAllUsers();

		assertEquals(2, users.size());
		verify(userRepository, times(1)).findAll();
	}

	@Test
	void getUserById() {
		String userId = "123";
		User user = new User("user1", "pass1", "user1@example.com", null);
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		Optional<User> result = userService.getUserById(userId);

		assertTrue(result.isPresent());
		assertEquals("user1", result.get().getUsername());
		verify(userRepository, times(1)).findById(userId);
	}

	@Test
	void createUser() {
		User user = new User("newUser", "pass", "newuser@example.com", null);
		when(userRepository.save(any(User.class))).thenReturn(user);

		User createdUser = userService.createUser(user);

		assertNotNull(createdUser);
		assertEquals("newUser", createdUser.getUsername());
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void updateUser() {
		String userId = "123";
		User existingUser = new User("user1", "pass1", "user1@example.com", null);
		User updatedUser = new User("user1Updated", "pass1Updated", "user1updated@example.com", null);

		when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
		when(userRepository.save(any(User.class))).thenReturn(updatedUser);

		Optional<User> result = userService.updateUser(userId, updatedUser);

		assertTrue(result.isPresent());
		assertEquals("user1Updated", result.get().getUsername());
		verify(userRepository, times(1)).findById(userId);
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	void deleteUser() {
		String userId = "123";
		User user = new User("user1", "pass1", "user1@example.com", null);
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		boolean result = userService.deleteUser(userId);

		assertTrue(result);
		verify(userRepository, times(1)).findById(userId);
		verify(userRepository, times(1)).delete(user);
	}
}