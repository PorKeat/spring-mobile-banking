package kh.edu.banking.api.dto;

public record UpdateCustomerRequest(
        String fullName,
        String gender,
        String remark
) {
}
