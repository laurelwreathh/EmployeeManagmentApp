package com.example.spirngbackend.mappers;

import com.example.spirngbackend.dtos.EmployeeDTO;
import com.example.spirngbackend.models.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.WARN, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {

    List<EmployeeDTO> toDTOList(List<Employee> list);
    List<Employee> toModelList(List<EmployeeDTO> listDTO);

    Employee toModel(EmployeeDTO employeeDTO);
    EmployeeDTO toDTO(Employee employee);
}
