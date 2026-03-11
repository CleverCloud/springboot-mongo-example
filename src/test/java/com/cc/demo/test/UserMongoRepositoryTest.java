package com.cc.demo.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.cc.demo.repository.UserMongoRepository;
import com.cc.demo.entity.User;

@SpringBootTest
public class UserMongoRepositoryTest {
    @Autowired
    private UserMongoRepository repository;

    @BeforeEach
    public void setUp() {
        User user1 = new User("Alice", "Clever");
        User user2 = new User("Bob", "Cloud");
        assertNull(user1.getId());
        assertNull(user2.getId());
        this.repository.save(user1);
        this.repository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    public void testFetchData() {
        User userA = repository.findByFirstName("Bob");
        assertNotNull(userA);
        List<User> users = repository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    public void testDataUpdate() {
        User userB = repository.findByFirstName("Bob");
        userB.setFirstName("Robert");
        repository.save(userB);
        User userC = repository.findByFirstName("Robert");
        assertNotNull(userC);
        assertEquals("Cloud", userC.getLastName());
    }

    @AfterEach
    public void tearDown() {
        this.repository.deleteAll();
    }

}
