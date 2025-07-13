package kh.edu.banking.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccountTypeRequest(
        @NotNull(message = "Name is required")
        String name,
        @NotNull(message = "Description is required")
        @Size(max = 150, message = "Description length maximum is 150")
        String description
) {
}
