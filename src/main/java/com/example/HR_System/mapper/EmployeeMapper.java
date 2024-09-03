package com.example.HR_System.mapper;

import com.example.HR_System.dto.EmployeeDto;
import com.example.HR_System.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "picture", expression = "java(employee.getPicture() != null ? new String(employee.getPicture()) : null)")
    EmployeeDto toDto(Employee employee);

}
