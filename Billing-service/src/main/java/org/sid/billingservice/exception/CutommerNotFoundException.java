package org.sid.billingservice.exception;

public class CutommerNotFoundException extends RuntimeException {
    public CutommerNotFoundException(String customer_not_found) {
        super(customer_not_found);
    }
}
