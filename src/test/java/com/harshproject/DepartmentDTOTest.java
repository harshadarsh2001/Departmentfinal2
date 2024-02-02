package com.harshproject;

import com.harshproject.dto.DepartmentDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DepartmentDTOTest {

    @Test
    void testNoArgsConstructor() {
        DepartmentDTO departmentDTO = new DepartmentDTO();

        assertNotNull(departmentDTO);
        assertNull(departmentDTO.getId());
        assertNull(departmentDTO.getName());
        assertNull(departmentDTO.getDescription());
    }

    @Test
    void testAllArgsConstructor() {
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "IT", "Information Technology");

        assertNotNull(departmentDTO);
        assertEquals(1L, departmentDTO.getId());
        assertEquals("IT", departmentDTO.getName());
        assertEquals("Information Technology", departmentDTO.getDescription());
    }

    @Test
    void testBuilder() {
        DepartmentDTO departmentDTO = DepartmentDTO.builder()
                .id(2L)
                .name("HR")
                .description("Human Resources")
                .build();

        assertNotNull(departmentDTO);
        assertEquals(2L, departmentDTO.getId());
        assertEquals("HR", departmentDTO.getName());
        assertEquals("Human Resources", departmentDTO.getDescription());
    }

    @Test
    void testSettersAndGetters() {
        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setId(3L);
        departmentDTO.setName("Finance");
        departmentDTO.setDescription("Finance Department");

        assertEquals(3L, departmentDTO.getId());
        assertEquals("Finance", departmentDTO.getName());
        assertEquals("Finance Department", departmentDTO.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1L, "IT", "Information Technology");
        DepartmentDTO departmentDTO2 = new DepartmentDTO(1L, "IT", "Information Technology");

        assertThat(departmentDTO1).isEqualTo(departmentDTO2);
        assertThat(departmentDTO1.hashCode()).hasSameHashCodeAs(departmentDTO2);
    }

    @Test
    void testEqualsAndHashCodeForNullFields() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO();
        DepartmentDTO departmentDTO2 = new DepartmentDTO();

        assertThat(departmentDTO1).isEqualTo(departmentDTO2);
        assertThat(departmentDTO1.hashCode()).hasSameHashCodeAs(departmentDTO2);
    }

    @Test
    void testNotEqualsForDifferentIds() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO(1L, "IT", "Information Technology");
        DepartmentDTO departmentDTO2 = new DepartmentDTO(2L, "IT", "Information Technology");

        assertThat(departmentDTO1).isNotEqualTo(departmentDTO2);
    }

    // Add more test cases as needed for your specific requirements


}
