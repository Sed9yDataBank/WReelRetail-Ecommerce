package com.wreel.retail.enums;

public enum PaymentEnum implements CodeEnum{

    PAID_ONLINE(0, "Already Paid Online"),
    PAID_ON_DELIVERY(1, "Paid On Order Delivery")
    ;

    private  int code;
    private String msg;

    PaymentEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
