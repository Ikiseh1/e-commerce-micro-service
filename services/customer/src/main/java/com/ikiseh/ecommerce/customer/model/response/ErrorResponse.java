package com.ikiseh.ecommerce.customer.model.response;

import java.util.Map;
public record ErrorResponse(

        Map<String, String> errors
) {
}
