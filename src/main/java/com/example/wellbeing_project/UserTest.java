package com.example.wellbeing_project.universal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
@Test
public void testConstructorAndGetters() {
    String name = "John Doe";
    String email = "john@example.com";
    String password = "password123";

    User user = new User(name, email, password);

    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
    assertEquals(password, user.getPassword());
}
}


