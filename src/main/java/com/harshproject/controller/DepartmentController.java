package com.harshproject.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshproject.dto.DepartmentDTO;
import com.harshproject.exception.DepartmentNotFoundException;
import com.harshproject.exception.MyJsonConversionException;
import com.harshproject.service.DepartmentService;
import com.harshproject.util.DepartmentEmailAspect;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@SecurityRequirement(name = "bearer-key")
public class DepartmentController {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;
    private final RabbitTemplate rabbitTemplate;
    private final DepartmentEmailAspect departmentEmailAspect;
    private static final String DEPARTMENT_NOT_FOUND_MESSAGE = "Department not found with ID: ";

    public DepartmentController(DepartmentService departmentService, RabbitTemplate rabbitTemplate,
                                DepartmentEmailAspect departmentEmailAspect) {
        this.departmentService = departmentService;
        this.rabbitTemplate = rabbitTemplate;
        this.departmentEmailAspect = departmentEmailAspect;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get all departments", description = "Retrieve a list of all departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Fetching all Departments. Request Headers: {}", headers);

        List<DepartmentDTO> departmentDTOs = departmentService.getAllDepartmentDTOs();
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get department by ID", description = "Retrieve a department by its ID")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable long id, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Fetching department by ID: {}. Request Headers: {}", id, headers);

        try {
            Optional<DepartmentDTO> departmentDTOOptional = departmentService.getDepartmentDTOById(id);
            return departmentDTOOptional.map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                    .orElseThrow(() -> {
                        departmentEmailAspect.sendErrorEmail("getDepartmentById",DEPARTMENT_NOT_FOUND_MESSAGE + id);
                        return new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND_MESSAGE + id);
                    });
        } catch (DepartmentNotFoundException ex) {
            // Use your DepartmentEmailAspect to send an email
            departmentEmailAspect.sendErrorEmail("getDepartmentById",DEPARTMENT_NOT_FOUND_MESSAGE + id);

            // Re-throw the exception to be handled globally
            throw ex;
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Update department by ID", description = "Update a department based on its ID")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable Long id,
            @Parameter(name = "updatedDepartmentDTO", description = "Updated department information", required = true)
            @RequestBody DepartmentDTO updatedDepartmentDTO, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Updating department with ID {}: {}. Request Headers: {}", id, updatedDepartmentDTO, headers);

        try {
            Optional<DepartmentDTO> updated = departmentService.updateDepartmentDTO(id, updatedDepartmentDTO);
            return updated.map(updatedDTO -> new ResponseEntity<>(updatedDTO, HttpStatus.OK))
                    .orElseThrow(() -> {
                        departmentEmailAspect.sendErrorEmail("updateDepartment", DEPARTMENT_NOT_FOUND_MESSAGE + id);
                        return new DepartmentNotFoundException(DEPARTMENT_NOT_FOUND_MESSAGE+ id);
                    });
        } catch (DepartmentNotFoundException ex) {
            // Use your DepartmentEmailAspect to send an email
            departmentEmailAspect.sendErrorEmail("updateDepartment",DEPARTMENT_NOT_FOUND_MESSAGE + id);

            // Re-throw the exception to be handled globally
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Delete department by ID", description = "Delete a department based on its ID")
    public ResponseEntity<Void> deleteDepartment(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable Long id, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Deleting department with ID: {}. Request Headers: {}", id, headers);

        departmentService.deleteDepartmentDTO(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Save new department", description = "Save a new department")
    public ResponseEntity<DepartmentDTO> saveDepartment(
            @Parameter(name = "departmentDTO", description = "New department information", required = true)
            @RequestBody DepartmentDTO departmentDTO, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Saving a new Department: {}. Request Headers: {}", departmentDTO, headers);

        DepartmentDTO savedDepartmentDTO = departmentService.saveDepartmentDTO(departmentDTO);

        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.CREATED);
    }


    @PostMapping("/rabbitmq/save")
    @Operation(summary = "Save new department with RabbitMQ", description = "Save a new department and send a message to RabbitMQ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department saved successfully",
                    headers = @Header(name = "Message", description = "Message sent successfully")),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<DepartmentDTO> saveDepartmentWithRabbitMQ(
            @Parameter(name = "departmentDTO", description = "New department information", required = true)
            @RequestBody DepartmentDTO departmentDTO, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Saving a new Department: {}. Request Headers: {}", departmentDTO, headers);

        departmentDTO.setId(null);

		DepartmentDTO savedDepartmentDTO = departmentService.saveDepartmentDTO(departmentDTO);

		String message = convertDepartmentDTOToJson(savedDepartmentDTO);
		rabbitTemplate.convertAndSend("department-exchange", "myRoutingKey", message);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Message", "Message sent successfully");

		return ResponseEntity.status(HttpStatus.CREATED)
		        .headers(responseHeaders)
		        .body(savedDepartmentDTO);
    }

    @PutMapping("/{id}/rabbitmq/update")
    @Operation(summary = "Update department by ID with RabbitMQ", description = "Update a department based on its ID and send a message to RabbitMQ")
    public ResponseEntity<DepartmentDTO> updateDepartmentWithRabbitMQ(
            @Parameter(name = "id", description = "ID of the department", in = ParameterIn.PATH, required = true)
            @PathVariable Long id,
            @Parameter(name = "updatedDepartmentDTO", description = "Updated department information", required = true)
            @RequestBody DepartmentDTO updatedDepartmentDTO, RequestEntity<Void> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        logger.info("Updating department with ID {}: {}. Request Headers: {}", id, updatedDepartmentDTO, headers);

        try {
            updatedDepartmentDTO.setId(id);

            Optional<DepartmentDTO> updated = departmentService.updateDepartmentDTO(id, updatedDepartmentDTO);

            String message = convertDepartmentDTOToJson(updatedDepartmentDTO);
            rabbitTemplate.convertAndSend("department-exchange", "myRoutingKey", message);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Message", "Message sent successfully");

            return updated.map(updatedDTO -> ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(updatedDTO))
                    .orElseGet(() -> ResponseEntity.notFound()
                            .headers(responseHeaders)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    public String convertDepartmentDTOToJson(DepartmentDTO departmentDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(departmentDTO);
        } catch (JsonProcessingException e) {
            throw new MyJsonConversionException("Error converting DepartmentDTO to JSON", e);
        }
    }
}

