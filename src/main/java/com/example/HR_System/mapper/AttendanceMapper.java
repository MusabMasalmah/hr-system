package com.example.HR_System.mapper;

import com.example.HR_System.dto.AttendanceDto;
import com.example.HR_System.models.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(source = "employee.id", target = "employeeId")
    AttendanceDto toDto(Attendance attendance);


    Attendance toEntity(AttendanceDto attendanceDto);
}
