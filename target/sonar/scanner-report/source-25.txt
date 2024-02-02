package com.harshproject.service;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.harshproject.dto.DepartmentDTO;
import com.harshproject.entity.Department;
import com.harshproject.repository.Repository;

@Service
@Cacheable(value = "departments")
public class DepartmentServiceImpl implements DepartmentService {

    private final Repository departmentRepository;

    public DepartmentServiceImpl(Repository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDTO> getAllDepartmentDTOs() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    
    public Optional<DepartmentDTO> getDepartmentDTOById(Long id) {
        return departmentRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public DepartmentDTO saveDepartmentDTO(DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);

        return convertToDTO(savedDepartment);
    }

    @Override
    public Optional<DepartmentDTO> updateDepartmentDTO(Long id, DepartmentDTO updatedDepartmentDTO) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    BeanUtils.copyProperties(updatedDepartmentDTO, existingDepartment);
                    Department updatedDepartment = departmentRepository.save(existingDepartment);
                    return convertToDTO(updatedDepartment);
                });
    }

    @Override
    public void deleteDepartmentDTO(Long id) {
        departmentRepository.deleteById(id);
    }

    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department, departmentDTO);
        return departmentDTO;
    }

    private Department convertToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        return department;
    }
    
}
