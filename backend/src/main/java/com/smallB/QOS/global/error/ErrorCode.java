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

    // Coupons
    COUPON_ALREADY_USE(400, "CO001", "Coupon was already used"),
    COUPON_EXPIRE(400, "CO002", "Coupon was already expired"),

    // User
    USER_ID_DUPLICATION(400,"U001","User_id is Duplication"),
    USER_ID_NOT_EXIST(400,"U002","User_id is not Existed"),
    LOGIN_INPUT_INVALID(400, "U003", "Login input is invalid"),
    USER_UPDATE_FAILED(400, "U004", "User update failed"),
    TOKEN_EXPIRED(400,"U005","Token is Expired"),

    //StoreInfo
    STORE_ID_NOT_EXIST(400, "SI001", "Store_id is not Existed"),
    STORE_UPDATE_FAILED(400, "SI002", "Store Update Failed"),
    UNAUTHORIZED_USER(401, "SI003", "Unauthorized User"),
    STORE_ALREADY_EXIST(409, "SI004", "Store Already Exist"),
    STORE_REGISTER_FAILED(400, "SI005", "Store Register Failed"),
    STORE_CATEGORY_NOT_EXIST(400, "SI006", "Store Category is not Existed"),
    STORE_CATEGORY_INPUT_INVALID(400, "SI007", "Store Category input is invalid"),
    STORE_CATEGORY_CREATE_FAILED(400, "SI008", "Store Category Create Failed"),
    STORE_CATEGORY_PARAM_INVALID(400, "SI009", "Store Category Parameter is invalid"),
    STORE_CATEGORY_DELETE_FAILED(400, "SI010", "Store Category Delete Failed"),

    //PRODUCT
    PRODUCT_NOT_FOUND(400,"P001","Product NOT FOUND"),
    PRODUCT_UPDATE_FAILED(400,"p002","Product Update Failed"),
    CATEGORY_NOT_FOUND(400,"p003","Category NOT FOUND"),


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