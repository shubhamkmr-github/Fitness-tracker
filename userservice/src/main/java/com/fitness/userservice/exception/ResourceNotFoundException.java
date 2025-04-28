package com.fitness.userservice.exception;


import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldValue;
    private String fieldName;
    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue) {
        super(String.format("Resource %s not found with %s : %s", resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
