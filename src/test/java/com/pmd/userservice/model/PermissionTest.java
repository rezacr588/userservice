package com.pmd.userservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class PermissionTest {

	@Test
	void testPermissionConstructor() {
		String name = "read";
		String description = "Allows reading operations";
		Permission permission = new Permission(name, description);

		assertNotNull(permission, "Permission object should not be null");
		assertEquals(name, permission.getName(), "Name should match the constructor argument");
		assertEquals(description, permission.getDescription(), "Description should match the constructor argument");
	}

	@Test
	void testSettersAndGetters() {
		Permission permission = new Permission();
		String id = "1";
		String name = "write";
		String description = "Allows writing operations";

		permission.setId(id);
		permission.setName(name);
		permission.setDescription(description);

		assertEquals(id, permission.getId(), "ID should match the setter value");
		assertEquals(name, permission.getName(), "Name should match the setter value");
		assertEquals(description, permission.getDescription(), "Description should match the setter value");
	}
}