package com.harshproject.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO implements Serializable{
	private static final long serialVersionUID = -2485728909120813688L;
	private Long id;
    private String name;
    private String description;
    
}
