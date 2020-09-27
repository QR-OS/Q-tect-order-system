package com.smallB.QOS.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    // Coupon
    COUPON_ALREADY_USE(400, "CO001", "Coupon was already used"),
    COUPON_EXPIRE(400, "CO002", "Coupon was already expired"),

    // User
    USER_ID_DUPLICATION(400,"U001","User_id is Duplication"),
    USER_ID_NOT_EXIST(400,"U002","User_id is not Existed"),
    LOGIN_INPUT_INVALID(400, "U003", "Login input is invalid"),
    USER_UPDATE_FAILED(400, "U004", "User update failed"),

    //PRODUCT
    PRODUCT_NOT_FOUND(400,"P001","Product NOT FOUND"),
    PRODUCT_UPDATE_FAILED(400,"p002","Product Update Failed"),
    CATEGORY_NOT_FOUND(400,"p003","Category NOT FOUND")
    TOKEN_EXPIRED(403,"U005","Token is Expired"),

    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}