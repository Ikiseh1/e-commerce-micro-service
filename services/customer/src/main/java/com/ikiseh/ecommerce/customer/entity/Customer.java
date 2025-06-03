package com.ikiseh.ecommerce.customer.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    private String id;

    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
