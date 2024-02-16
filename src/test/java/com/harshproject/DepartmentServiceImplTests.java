//package com.harshproject;
//import com.harshproject.dto.DepartmentDTO;
//import com.harshproject.entity.Department;
//import com.harshproject.repository.DepartmentRepository;
//import com.harshproject.service.DepartmentServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class DepartmentServiceImplTests {
//
//    @Mock
//    private DepartmentRepository departmentRepository;
//
//    @InjectMocks
//    private DepartmentServiceImpl departmentService;
//
//    @Test
//    void getAllDepartmentDTOs() {
//        // Arrange
//        Department department1 = new Department(1L, "Department 1", "YASFDYJEHVD");
//        Department department2 = new Department(2L, "Department 2", "HCVXB");
//        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));
//
//        // Act
//        List<DepartmentDTO> result = departmentService.getAllDepartmentDTOs();
//
//        // Assert
//        assertEquals(2, result.size());
//        assertEquals("Department 1", result.get(0).getName());
//        assertEquals("Department 2", result.get(1).getName());
//    }
//
//    @Test
//    void getDepartmentDTOById() {
//        // Arrange
//        Department department = new Department(1L, "Department 1", "ICHWIOEBCIOW");
//        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
//
//        // Act
//        Optional<DepartmentDTO> result = departmentService.getDepartmentDTOById(1L);
//
//        // Assert
//        assertTrue(result.isPresent());
//        assertEquals("Department 1", result.get().getName());
//    }
//
//    @Test
//    void saveDepartmentDTO() {
//        // Arrange
//        DepartmentDTO departmentDTO = new DepartmentDTO(null, "New Department", "WIYXGWYIVX");
//        Department department = new Department(null, "New Department", "SDCBD");
//        when(departmentRepository.save(any())).thenReturn(department);
//
//        // Act
//        DepartmentDTO result = departmentService.saveDepartmentDTO(departmentDTO);
//
//        // Assert
//        assertEquals("New Department", result.getName());
//    }
//
//    @Test
//    void updateDepartmentDTO_NotFound() {
//        // Arrange
//        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        DepartmentDTO updatedDepartmentDTO = new DepartmentDTO(1L, "Updated Department", "XSNDIXN");
//
//        // Act
//        Optional<DepartmentDTO> result = departmentService.updateDepartmentDTO(1L, updatedDepartmentDTO);
//
//        // Assert
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void deleteDepartmentDTO() {
//        // Act
//        departmentService.deleteDepartmentDTO(1L);
//
//        // Assert
//        verify(departmentRepository, times(1)).deleteById(1L);
//    }
//}
