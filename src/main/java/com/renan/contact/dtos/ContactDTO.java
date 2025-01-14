package com.renan.contact.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactDTO(
    
                        @NotBlank(message = "Name is mandatory")
                        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name,

                        @NotBlank(message = "Email is mandatory")
                        @Email(message = "Email should be valid")String email,


                        @NotBlank(message = "Phone number is mandatory")
                        @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
                         String phoneNumber) {
    
}
