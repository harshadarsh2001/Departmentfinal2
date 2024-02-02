package com.harshproject.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshproject.dto.DepartmentDTO;
import com.harshproject.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("singleton")
public class Listener {

    private final Logger logger = LoggerFactory.getLogger(Listener.class);
    private final DepartmentService departmentService;

    public Listener(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RabbitListener(queues = "department-queue")
    public void receiveDepartmentMessage(String message) {
        logger.info("Received department message: {}", message);
        DepartmentDTO receivedDepartment = convertJsonToDepartmentDTO(message);
        processReceivedDepartment(receivedDepartment);
    }

    public static DepartmentDTO convertJsonToDepartmentDTO(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, DepartmentDTO.class);
        } catch (Exception e) {
            // Log the exception or handle it in a way that suits your application
            LoggerFactory.getLogger(Listener.class).error("Error converting JSON to DepartmentDTO", e);
            return null;
        }
    }

    private void processReceivedDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO != null) {
            if (departmentDTO.getId() != null) {
                handleUpdateOperation(departmentDTO);
            } else {
                handleSaveOperation(departmentDTO);
            }
        } else {
            logger.error("Failed to process department. DepartmentDTO is null.");
        }
    }

    private void handleSaveOperation(DepartmentDTO departmentDTO) {
        departmentService.saveDepartmentDTO(departmentDTO);
        logger.info("Saved department to the database: {}", departmentDTO);
    }

    private void handleUpdateOperation(DepartmentDTO departmentDTO) {
        Long departmentId = departmentDTO.getId();
        Optional<DepartmentDTO> existingDepartment = departmentService.getDepartmentDTOById(departmentId);

        if (existingDepartment.isPresent()) {
            departmentService.updateDepartmentDTO(departmentId, departmentDTO);
            logger.info("Updated department in the database: {}", departmentDTO);
        } else {
            logger.error("Failed to update department. Department with ID {} not found.", departmentId);
        }
    }
}
