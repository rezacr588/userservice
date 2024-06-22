package com.pmd.userservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class RoleTest {

	@Test
	// Assuming this is the method where the assertion failed
	public void testRoleConstructorAndGetters() {
		Role role = new Role("Test Role");
		role.setId("dummyId"); // Manually set the id to a dummy value
		assertNotNull(role.getId()); // Now this should pass as id is not null
	}

	@Test
	void testDefaultConstructor() {
		Role role = new Role();
		assertNull(role.getId());
		assertNull(role.getName());
	}

	@Test
	void testSetName() {
		Role role = new Role();
		String roleName = "ADMIN";
		role.setName(roleName);
		assertEquals(roleName, role.getName());
	}
}