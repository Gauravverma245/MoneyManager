package com.project.moneymanager.model;/*
 * @author gauravverma
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be empty")
    private String name;

    @Column(unique = true)
    @NotNull(message = "Email should not be empty")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Password should not be empty")
    @Size(min = 5, message = "Password should be at least 5 characters")
    private String password;

    private Long age;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
