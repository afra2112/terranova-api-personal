package com.terranova.api.v1.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    //USER
    USER_NOT_FOUND("USR-001", HttpStatus.NOT_FOUND, "User not found"),
    USER_ALREADY_EXISTS("USR-002", HttpStatus.CONFLICT, "User already exists"),
    INVALID_BIRTH_DATE("USR-003", HttpStatus.BAD_REQUEST, "Invalid birth date"),

    //AUTH
    INVALID_CREDENTIALS("AUTH-001", HttpStatus.UNAUTHORIZED, "Invalid credentials"),
    INVALID_TOKEN("AUTH-002", HttpStatus.FORBIDDEN, "Invalid token"),
    TOKEN_EXPIRED("AUTH-003", HttpStatus.FORBIDDEN, "Token expired"),
    NULL_REFRESH_TOKEN("AUTH-004", HttpStatus.BAD_REQUEST, "Refresh token is required"),
    UNAUTHORIZED("AUTH-005", HttpStatus.UNAUTHORIZED, "Password or Email invalid."),
    FORBIDDEN("AUTH-006", HttpStatus.FORBIDDEN, "You don't have permissions to access this resource"),
    INVALID_GOOGLE_ACCOUNT("AUTH-OO7", HttpStatus.BAD_REQUEST, "Google account email does not match authenticated user"),
    GOOGLE_ALREADY_LINKED("AUTH-008", HttpStatus.BAD_REQUEST, "Google account already linked"),
    GOOGLE_ACCOUNT_ALREADY_USED("AUTH-009", HttpStatus.BAD_REQUEST, "Google account already linked to another user"),
    INVALID_FACEBOOK_TOKEN("AUTH-O10", HttpStatus.BAD_REQUEST, "Invalid facebook access token."),
    FACEBOOK_EMAIL_REQUIRED("AUTH-011", HttpStatus.INTERNAL_SERVER_ERROR, "Facebook oauth2 has responded with a null email, please try creating your account with google provider or with email and password."),
    INVALID_FACEBOOK_ACCOUNT("AUTH-012", HttpStatus.BAD_REQUEST, "Facebook account email does not match authenticated user"),
    FACEBOOK_ALREADY_LINKED("AUTH-013", HttpStatus.BAD_REQUEST, "Facebook account already linked"),
    FACEBOOK_ACCOUNT_ALREADY_USED("AUTH-014", HttpStatus.BAD_REQUEST, "Facebook account already linked to another user"),

    //PRODUCT
    ENTITY_NOT_FOUND("PRD-001", HttpStatus.NOT_FOUND, "Entity not found"),
    PRODUCT_TYPE_NOT_SUPPORTED("PRD-002", HttpStatus.BAD_REQUEST, "Product type not supported"),
    CLOUDINARY_ERROR("PRD-003", HttpStatus.INTERNAL_SERVER_ERROR, "Cloudinary error."),
    IMAGE_READ_ERROR("PRD-004", HttpStatus.INTERNAL_SERVER_ERROR, "Error with IO reading the file from the request."),
    IMAGE_NOT_BELONGS_TO_PRODUCT("PRD-005", HttpStatus.BAD_REQUEST, "This image don't belongs to this product"),
    PRODUCT_NOT_BELONGS_TO_USER("PRD-006", HttpStatus.BAD_REQUEST, "This product doesn't belong to you."),
    PRODUCT_TYPE_CANNOT_BE_CHANGED("PRD-007", HttpStatus.BAD_REQUEST, "The current product type cannot be changed, please delete it and create another one."),
    WRONG_PRODUCT_STATUS("PRD-008", HttpStatus.BAD_REQUEST, "The status of the product is incorrect."),
    PUBLISH_VALIDATION_ERROR("PRD-009", HttpStatus.BAD_REQUEST, "Error validating product data to publish."),
    LOCATION_NOT_FOUND("PRD-010", HttpStatus.NOT_FOUND, "Location not found"),
    INVALID_IMAGE_FORMAT("PRD-011", HttpStatus.BAD_REQUEST, "Image format not valid, valid formats: jpeg, png, webp, jpg"),
    IMAGE_LIMIT_EXCEEDED("PRD-012", HttpStatus.BAD_REQUEST, "Image limit is 18 images per product"),
    INVALID_IMAGE_ORDER("PRD-013", HttpStatus.INTERNAL_SERVER_ERROR, "Image order invalid."),
    CANNOT_DELETE_ALL_IMAGES("PRD-014", HttpStatus.BAD_REQUEST, "You cannot leave a product in status different to DRAFT without images"),

    //APPOINTMENT
    INVALID_TIME("APMT-001", HttpStatus.BAD_REQUEST, "End time cannot be before start time in your appointment"),
    APPOINTMENT_OVERLAP("APMT-002", HttpStatus.BAD_REQUEST, "Appointment overlaps with an existing appointment"),
    APPOINTMENT_QUOTA_EXCEEDED("APTM-003", HttpStatus.BAD_REQUEST, "Maximum appointments quota reached"),

    //GENERIC
    VALIDATION_ERROR("GEN-001", HttpStatus.BAD_REQUEST, "Validation error"),
    INTERNAL_ERROR("GEN-002", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    JSON_FORMAT_ERROR("GEN-003", HttpStatus.BAD_REQUEST, "JSON request format error, verify syntaxis."),
    OPEN_FEIGN_ERROR("GEN-004", HttpStatus.INTERNAL_SERVER_ERROR, "Open Feign internal communication error");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
