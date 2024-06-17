package org.ecom;

import org.ecom.Model.ProductAdditionDTO;
import org.ecom.Model.UserRegistrationDTO;

public class Utils {

    public static void validateUserRegistrationDTO(UserRegistrationDTO userDTO) {
        if(userDTO == null || userDTO.getName().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("User registration DTO cannot be null.");
        }
    }

    public static void validateProductAdditionDTO(ProductAdditionDTO productDTO) {
        if(productDTO == null || productDTO.getName().isEmpty() || productDTO.getDesc().isEmpty() || productDTO.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Product addition DTO cannot be null.");
        }
    }
}
