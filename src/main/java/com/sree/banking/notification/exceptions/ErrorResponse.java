package com.sree.banking.notification.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Sreenivasulu.Avula
 *
 */

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private int code;
    private String message;
    
    
}
