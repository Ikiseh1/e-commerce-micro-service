package com.ikiseh.ecommerce.customer.model.request;

import com.ikiseh.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (



        String id,

        @NotNull(message = "Customer First-Name is Required")
        String firstname,

        @NotNull(message = "Customer Last-Name is Required")
        String lastname,

        @NotNull(message = "Customer email is Required")
        @Email(message = "Customer email is not a valid email address")
        String email,

        Address address

){

}

