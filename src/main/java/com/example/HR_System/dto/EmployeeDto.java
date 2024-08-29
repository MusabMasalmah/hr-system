package com.example.HR_System.dto;

import com.example.HR_System.enums.Roles;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String picture;

    @DecimalMin(value = "0.0", inclusive = false, message = "Credit must be positive")
    private Double credit;

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
    private String phone_number;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 0, message = "Max paid leave cannot be negative")
    private Integer max_paid_leave;

    @NotNull(message = "Role is required")
    private Roles role;

}
