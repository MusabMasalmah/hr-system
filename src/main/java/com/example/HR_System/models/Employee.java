package com.example.HR_System.models;

import com.example.HR_System.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Employee {

    @Setter
    @Getter
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

    @Setter
    @Getter
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

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

    public @NotBlank(message = "Name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @DecimalMin(value = "0.0", inclusive = false, message = "Credit must be positive") Double getCredit() {
        return credit;
    }

    public void setCredit(@DecimalMin(value = "0.0", inclusive = false, message = "Credit must be positive") Double credit) {
        this.credit = credit;
    }

    public @NotBlank(message = "Position is required") String getPosition() {
        return position;
    }

    public void setPosition(@NotBlank(message = "Position is required") String position) {
        this.position = position;
    }

    public @NotBlank(message = "Phone number is required") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid") String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(@NotBlank(message = "Phone number is required") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid") String phone_number) {
        this.phone_number = phone_number;
    }

    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    public @Min(value = 0, message = "Max paid leave cannot be negative") Integer getMax_paid_leave() {
        return max_paid_leave;
    }

    public void setMax_paid_leave(@Min(value = 0, message = "Max paid leave cannot be negative") Integer max_paid_leave) {
        this.max_paid_leave = max_paid_leave;
    }

    public @NotNull(message = "Role is required") Roles getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Role is required") Roles role) {
        this.role = role;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters") String password) {
        this.password = password;
    }
}
