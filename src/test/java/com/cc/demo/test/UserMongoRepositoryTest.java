package com.cc.demo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;

import com.cc.demo.repository.UserMongoRepository;
import com.cc.demo.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMongoRepositoryTest {
    @Autowired
    private UserMongoRepository repository;

    @Before
    public void setUp() throws Exception {
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
        assertEquals(users.size(), 2);
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

    @After
    public void tearDown() throws Exception {
        this.repository.deleteAll();
    }

}