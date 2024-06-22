package com.pmd.userservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pmd.userservice.model.User;
import com.pmd.userservice.service.UserService;

class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllUsers() {
		User user1 = new User("user1", "pass1", "user1@example.com", null);
		User user2 = new User("user2", "pass2", "user2@example.com", null);
		when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

		List<User> users = userController.getAllUsers();

		assertEquals(2, users.size());
		verify(userService, times(1)).getAllUsers();
	}

	@Test
	void getUserById() {
		String userId = "123";
		User user = new User("user1", "pass1", "user1@example.com", null);
		when(userService.getUserById(userId)).thenReturn(Optional.of(user));

		ResponseEntity<User> response = userController.getUserById(userId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("user1", response.getBody().getUsername());
		verify(userService, times(1)).getUserById(userId);
	}

	@Test
	void createUser() {
		User user = new User("newUser", "pass", "newuser@example.com", null);
		when(userService.createUser(any(User.class))).thenReturn(user);

		User createdUser = userController.createUser(user);

		assertNotNull(createdUser);
		assertEquals("newUser", createdUser.getUsername());
		verify(userService, times(1)).createUser(user);
	}

	@Test
	void updateUser() {
		String userId = "123";
		User updatedUser = new User("user1Updated", "pass1Updated", "user1updated@example.com", null);
		when(userService.updateUser(eq(userId), any(User.class))).thenReturn(Optional.of(updatedUser));

		ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("user1Updated", response.getBody().getUsername());
		verify(userService, times(1)).updateUser(eq(userId), any(User.class));
	}

	@Test
	void deleteUser() {
		String userId = "123";
		when(userService.deleteUser(userId)).thenReturn(true);

		ResponseEntity<?> response = userController.deleteUser(userId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(userService, times(1)).deleteUser(userId);
	}
}