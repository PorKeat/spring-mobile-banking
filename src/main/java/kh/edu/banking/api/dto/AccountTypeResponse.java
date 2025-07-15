package kh.edu.banking.api.dto;

public record AccountTypeResponse(
        Integer id,
        String name,
        String description,
        Boolean isDeleted
) {}
