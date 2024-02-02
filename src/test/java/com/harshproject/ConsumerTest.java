package com.harshproject;
import com.harshproject.dto.DepartmentDTO;
import com.harshproject.listener.Listener;
import com.harshproject.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ConsumerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private Listener consumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void receiveDepartmentMessage_ValidJson_UpdateExistingDepartment() {
        // Arrange
        String jsonMessage = "{\"id\": 1, \"name\": \"Test\", \"description\": \"Test Department\"}";
        DepartmentDTO departmentDTO = new DepartmentDTO(1L, "Test", "Test Department");

        // Mock the behavior of the service methods
        when(departmentService.getDepartmentDTOById(1L)).thenReturn(Optional.of(departmentDTO));

        // Act
        consumer.receiveDepartmentMessage(jsonMessage);

        // Assert
        verify(departmentService, times(1)).updateDepartmentDTO(1L, departmentDTO);
    }

    @Test
    void receiveDepartmentMessage_ValidJson_SaveNewDepartment() {
        // Arrange
        String jsonMessage = "{\"name\": \"NewDepartment\", \"description\": \"New Department\"}";
        DepartmentDTO departmentDTO = new DepartmentDTO(null, "NewDepartment", "New Department");

        // Mock the behavior of the service methods
        when(departmentService.getDepartmentDTOById(null)).thenReturn(Optional.empty());

        // Act
        consumer.receiveDepartmentMessage(jsonMessage);

        // Assert
        verify(departmentService, times(1)).saveDepartmentDTO(departmentDTO);
    }
}
