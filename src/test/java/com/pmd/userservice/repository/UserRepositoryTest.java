package com.pmd.userservice.repository;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.pmd.userservice.model.User;

@DataMongoTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  private User testUser;

  @BeforeEach
  void setUp() {
    testUser = new User("testUser", "pass", "test@example.com", null);
    userRepository.save(testUser);
  }

  @AfterEach
  void tearDown() {
    mongoTemplate.dropCollection(User.class);
  }

  @Test
  void findByUsername_existingUser_returnsUser() {
    User found = userRepository.findByUsername("testUser");
    assertNotNull(found);
    assertEquals("testUser", found.getUsername());
    assertEquals("test@example.com", found.getEmail());
  }

  @Test
  void findByUsername_nonExistingUser_returnsNull() {
    User found = userRepository.findByUsername("nonExistingUser");
    assertNull(found);
  }

  @Test
  void findByEmail_existingUser_returnsUser() {
    User found = userRepository.findByEmail("test@example.com");
    assertNotNull(found);
    assertEquals("testUser", found.getUsername());
    assertEquals("test@example.com", found.getEmail());
  }

  @Test
  void findByEmail_nonExistingUser_returnsNull() {
    User found = userRepository.findByEmail("nonexisting@example.com");
    assertNull(found);
  }

  @Test
  void save_newUser_savedSuccessfully() {
    User newUser = new User("newUser", "newPass", "new@example.com", null);
    User saved = userRepository.save(newUser);

    assertNotNull(saved.getId());
    assertEquals("newUser", saved.getUsername());
    assertEquals("new@example.com", saved.getEmail());

    User found = userRepository.findById(saved.getId()).orElse(null);
    assertNotNull(found);
    assertEquals("newUser", found.getUsername());
  }

  @Test
  void deleteUser_existingUser_deletedSuccessfully() {
    userRepository.delete(testUser);
    User found = userRepository.findByUsername("testUser");
    assertNull(found);
  }
}