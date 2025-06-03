package com.ikiseh.ecommerce.customer.model.response;

import com.ikiseh.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(

        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
