package com.harshproject;
import org.junit.jupiter.api.Test;

import com.harshproject.entity.Department;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DepartmentTest {

    @Test
    void testNoArgsConstructor() {
        Department department = new Department();

        assertNotNull(department);
        assertNull(department.getId());
        assertNull(department.getName());
        assertNull(department.getDescription());
    }

    @Test
    void testAllArgsConstructor() {
        Department department = new Department(1L, "IT", "Information Technology");

        assertNotNull(department);
        assertEquals(1L, department.getId());
        assertEquals("IT", department.getName());
        assertEquals("Information Technology", department.getDescription());
    }

    @Test
    void testSettersAndGetters() {
        Department department = new Department();

        department.setId(2L);
        department.setName("HR");
        department.setDescription("Human Resources");

        assertEquals(2L, department.getId());
        assertEquals("HR", department.getName());
        assertEquals("Human Resources", department.getDescription());
    }

    
    @Test
    void testParameterizedConstructor() {
        Department department = new Department(null, "Finance", "Financial Management");

        assertNotNull(department);
        assertNull(department.getId()); // ID should be generated
        assertEquals("Finance", department.getName());
        assertEquals("Financial Management", department.getDescription());
    }

   
    @Test
    void testEqualsAndHashCode() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(1L, "IT", "Information Technology");

        assertThat(department1).isEqualTo(department2);
        assertThat(department1.hashCode()).hasSameHashCodeAs(department2);
    }

    @Test
    void testNotEquals() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(2L, "HR", "Human Resources");

        assertNotEquals(department1, department2);
    }

    @Test
    void testEqualsWithNull() {
        Department department = new Department(1L, "IT", "Information Technology");

        assertNotEquals(department,(null));
    }

    @Test
    void testEqualsWithDifferentClass() {
        Department department = new Department(1L, "IT", "Information Technology");

        assertNotEquals("Not a department", department);

    }
    @Test
    void testSetName() {
        Department department = new Department();
        department.setName("Finance");

        assertEquals("Finance", department.getName());
    }

    @Test
    void testSetDescription() {
        Department department = new Department();
        department.setDescription("Financial Management");

        assertEquals("Financial Management", department.getDescription());
    }

    @Test
    void testEqualsWithDifferentId() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(2L, "IT", "Information Technology");

        assertNotEquals(department1, department2);
    }

    @Test
    void testEqualsWithDifferentName() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(1L, "HR", "Information Technology");

        assertNotEquals(department1, department2);
    }

    @Test
    void testEqualsWithDifferentDescription() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(1L, "IT", "HR Department");

        assertNotEquals(department1, department2);

    }

    @Test
    void testEqualsWithNullValues() {
        Department department1 = new Department();
        Department department2 = new Department();

        assertEquals(department1, department2);

    }

    @Test
    void testHashCodeWithDifferentId() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(2L, "IT", "Information Technology");

        assertNotEquals(department1.hashCode(), department2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentName() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(1L, "HR", "Information Technology");

        assertNotEquals(department1.hashCode(), department2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentDescription() {
        Department department1 = new Department(1L, "IT", "Information Technology");
        Department department2 = new Department(1L, "IT", "HR Department");

        assertNotEquals(department1.hashCode(), department2.hashCode());
    }
}
