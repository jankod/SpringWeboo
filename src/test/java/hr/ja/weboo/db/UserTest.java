package hr.ja.weboo.db;

import hr.ja.weboo.model.Address;
import hr.ja.weboo.model.User;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static hr.ja.weboo.db.InMemoryStorage.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testAddress() {
        clear();
        User user = new User();
        user.setName("pero");
        user.setEmail("ssd@sds.sd");
        user.addAddress(new Address("Street 1", "City 1"));

        InMemoryStorage.persist(user);
        assertNotNull(user.getId());

        Address address = findById(Address.class, user.getAddresses().getLast().getId());
        assertNotNull(address);

        InMemoryStorage.delete(address);
        assertNull(findById(Address.class, address.getId()));

    }

    @Test
    void testAddressDelete() {
        clear(); // Clear storage before the test
        User user = new User();
        user.setName("pero");
        user.setEmail("ssd@sds.sd");
        user.addAddress(new Address("Street 1", "City 1"));

        InMemoryStorage.persist(user);
        assertNotNull(user.getId());

        Address address = findById(Address.class, user.getAddresses().getLast().getId());
        assertNotNull(address);

        InMemoryStorage.delete(user);
        assertNull(findById(Address.class, address.getId()));
        assertNull(findById(User.class, user.getId()));
        assertEquals(0, listAll(User.class).size());
        assertEquals(0, listAll(Address.class).size());

    }

    @Test
    void testValidation() {
        User user = new User();
        user.setEmail("asdas@sds.sd");
        try {
            persist(user);
        } catch (Exception e) {
            assertInstanceOf(ConstraintViolationException.class, e);
            ConstraintViolationException ex = (ConstraintViolationException) e;
            assertEquals(1, ex.getConstraintViolations().size());
            assertEquals("Name is mandatory", ex.getConstraintViolations().iterator().next().getMessage());

            ex.getConstraintViolations();
            return;
        }
        fail("Expected ConstraintViolationException was not thrown");

    }


    @Test
    public void testSave() {
        User user = new User();
        user.setName("pero");
        user.setEmail("eds@sds.sd");

        assertNull(user.getId());
        persist(user);
        assertNotNull(user.getId());

        User retrievedUser = findById(User.class, user.getId());
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testDelete() {
        clear(); // Clear storage before the test
        User user = new User();
        user.setName("pero");
        user.setEmail("asds@dss.d");
        persist(user);

        assertNotNull(user.getId());
        delete(user);

        User retrievedUser = findById(User.class, user.getId());
        assertNull(retrievedUser);
        assertEquals(0, listAll(User.class).size());
        assertEquals(0, listAll(User.class).size());
    }

    @Test
    void testFindById() {
        clear(); // Clear storage before the test
        User user = new User();
        user.setName("pero");
        user.setEmail("asds@dss.d");
        persist(user);

        assertNotNull(user.getId());
        User retrievedUser = findById(User.class, user.getId());
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user, retrievedUser);
    }

    @Test
    void testManyUserSave() {
        clear(); // Clear storage before the test
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("pero" + i);
            user.setEmail("pero" + i + "@example.com");

            persist(user);
            persist(user); // Save again to check if it updates

        }
        assertEquals(100, listAll(User.class).size());

    }
}