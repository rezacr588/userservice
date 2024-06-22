package com.pmd.userservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testDefaultConstructor() {
		User user = new User();
		assertNotNull(user.getPermissions(), "Permissions list should not be null");
		assertTrue(user.getPermissions().isEmpty(), "Permissions list should be empty");
	}

	@Test
	void testParameterizedConstructor() {
		String username = "testUser";
		String password = "testPass";
		String email = "test@example.com";
		Role role = new Role("admin");

		role.setId("dummyId");

		User user = new User(username, password, email, role);

		user.setId("dummyId");

		assertEquals(username, user.getUsername(), "Username should match the constructor argument");
		assertEquals(password, user.getPassword(), "Password should match the constructor argument");
		assertEquals(email, user.getEmail(), "Email should match the constructor argument");
		assertEquals(role, user.getRole(), "Role should match the constructor argument");
		assertNotNull(user.getPermissions(), "Permissions list should not be null");
		assertTrue(user.getPermissions().isEmpty(), "Permissions list should be empty");
	}
}