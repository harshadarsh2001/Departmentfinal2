package com.harshproject;
import org.junit.jupiter.api.Test;
import com.harshproject.entity.UserInfo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserInfoTest {

    @Test
    void testNoArgsConstructor() {
        UserInfo userInfo = new UserInfo();

        assertNotNull(userInfo);
        assertEquals(0, userInfo.getId());
        assertNull(userInfo.getName());
        assertNull(userInfo.getEmail());
        assertNull(userInfo.getPassword());
        assertNull(userInfo.getRoles());
    }

    @Test
    void testAllArgsConstructor() {
        UserInfo userInfo = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertNotNull(userInfo);
        assertEquals(1, userInfo.getId());
        assertEquals("John Doe", userInfo.getName());
        assertEquals("john@example.com", userInfo.getEmail());
        assertEquals("password123", userInfo.getPassword());
        assertEquals("ROLE_USER", userInfo.getRoles());
    }

    @Test
    void testParameterizedConstructor() {
        UserInfo userInfo = new UserInfo("Jane Doe", "jane@example.com", "password456", "ROLE_ADMIN");

        assertNotNull(userInfo);
        assertEquals(0, userInfo.getId()); // ID should be generated
        assertEquals("Jane Doe", userInfo.getName());
        assertEquals("jane@example.com", userInfo.getEmail());
        assertEquals("password456", userInfo.getPassword());
        assertEquals("ROLE_ADMIN", userInfo.getRoles());
    }

    @Test
    void testSettersAndGetters() {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(2);
        userInfo.setName("Alice");
        userInfo.setEmail("alice@example.com");
        userInfo.setPassword("securePassword");
        userInfo.setRoles("ROLE_USER");

        assertEquals(2, userInfo.getId());
        assertEquals("Alice", userInfo.getName());
        assertEquals("alice@example.com", userInfo.getEmail());
        assertEquals("securePassword", userInfo.getPassword());
        assertEquals("ROLE_USER", userInfo.getRoles());
    }

    @Test
    void testEqualsAndHashCode() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertThat(userInfo1).isEqualTo(userInfo2);
        assertThat(userInfo1.hashCode()).hasSameHashCodeAs(userInfo2);
    }

    @Test
    void testNotEquals() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(2, "Jane Doe", "jane@example.com", "password456", "ROLE_ADMIN");

        assertNotEquals(userInfo1, userInfo2);
    }
    
    @Test
    void testNoArgsConstructor1() {
        UserInfo userInfo = new UserInfo();

        assertNotNull(userInfo);
        assertEquals(0, userInfo.getId());
        assertNull(userInfo.getName());
        assertNull(userInfo.getEmail());
        assertNull(userInfo.getPassword());
        assertNull(userInfo.getRoles());
    }

    @Test
    void testAllArgsConstructor1() {
        UserInfo userInfo = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertNotNull(userInfo);
        assertEquals(1, userInfo.getId());
        assertEquals("John Doe", userInfo.getName());
        assertEquals("john@example.com", userInfo.getEmail());
        assertEquals("password123", userInfo.getPassword());
        assertEquals("ROLE_USER", userInfo.getRoles());
    }

    @Test
    void testParameterizedConstructor1() {
        UserInfo userInfo = new UserInfo("Jane Doe", "jane@example.com", "password456", "ROLE_ADMIN");

        assertNotNull(userInfo);
        assertEquals(0, userInfo.getId()); // ID should be generated
        assertEquals("Jane Doe", userInfo.getName());
        assertEquals("jane@example.com", userInfo.getEmail());
        assertEquals("password456", userInfo.getPassword());
        assertEquals("ROLE_ADMIN", userInfo.getRoles());
    }

    @Test
    void testSettersAndGetters1() {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(2);
        userInfo.setName("Alice");
        userInfo.setEmail("alice@example.com");
        userInfo.setPassword("securePassword");
        userInfo.setRoles("ROLE_USER");

        assertEquals(2, userInfo.getId());
        assertEquals("Alice", userInfo.getName());
        assertEquals("alice@example.com", userInfo.getEmail());
        assertEquals("securePassword", userInfo.getPassword());
        assertEquals("ROLE_USER", userInfo.getRoles());
    }

    @Test
    void testEqualsAndHashCode1() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertThat(userInfo1).isEqualTo(userInfo2);
        assertThat(userInfo1.hashCode()).hasSameHashCodeAs(userInfo2);
    }

    @Test
    void testNotEquals1() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(2, "Jane Doe", "jane@example.com", "password456", "ROLE_ADMIN");

        assertNotEquals(userInfo1, userInfo2);
    }

    @Test
    void testEqualsWithNull() {
        UserInfo userInfo = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertNotEquals(userInfo,(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        UserInfo userInfo = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertNotEquals("Not a UserInfo",userInfo);
    }
    @Test
    void testSetName() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Bob");

        assertEquals("Bob", userInfo.getName());
    }

    @Test
    void testSetEmail() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("bob@example.com");

        assertEquals("bob@example.com", userInfo.getEmail());
    }

    @Test
    void testSetPassword() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("newPassword");

        assertEquals("newPassword", userInfo.getPassword());
    }

    @Test
    void testSetRoles() {
        UserInfo userInfo = new UserInfo();
        userInfo.setRoles("ROLE_ADMIN");

        assertEquals("ROLE_ADMIN", userInfo.getRoles());
    }

    @Test
    void testEqualsWithDifferentId() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(2, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertNotEquals(userInfo1,userInfo2);
    }

    @Test
    void testEqualsWithDifferentName() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "Jane Doe", "jane@example.com", "password456", "ROLE_ADMIN");

        assertNotEquals(userInfo1,userInfo2);
    }

    @Test
    void testEqualsWithDifferentEmail() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "John Doe", "jane@example.com", "password123", "ROLE_USER");

        assertNotEquals(userInfo1,userInfo2);
    }

    @Test
    void testEqualsWithDifferentPassword() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "John Doe", "john@example.com", "password456", "ROLE_USER");

        assertNotEquals(userInfo1,userInfo2);
    }

    @Test
    void testEqualsWithDifferentRoles() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_ADMIN");

        assertNotEquals(userInfo1,userInfo2);
    }

    @Test
    void testEqualsWithNullValues() {
        UserInfo userInfo1 = new UserInfo();
        UserInfo userInfo2 = new UserInfo();

        assertEquals(userInfo1,userInfo2);
    }

    @Test
    void testHashCodeWithDifferentId() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(2, "John Doe", "john@example.com", "password123", "ROLE_USER");

        assertNotEquals(userInfo1.hashCode(), userInfo2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentName() {
        UserInfo userInfo1 = new UserInfo(1, "John Doe", "john@example.com", "password123", "ROLE_USER");
        UserInfo userInfo2 = new UserInfo(1, "Jane Doe", "jane@example.com", "password456", "ROLE_ADMIN");

        assertNotEquals(userInfo1.hashCode(), userInfo2.hashCode());
    }


}
