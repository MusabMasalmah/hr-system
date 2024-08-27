package com.example.HR_System.models;

import com.example.HR_System.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Picture is required")
    @Lob // Assuming `Byte` represents an image, consider using `@Lob` for large objects
    private Byte picture;

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

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private Roles role;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;
}
