package com.xcode;

import lombok.Data;
import lombok.Getter;



public enum CommonStateEnum {

    SUCCESS(1,"成功"),
    FAIL(0,"失敗")

    ;

    @Getter
    private  int code;
    @Getter
    private String value;

    CommonStateEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }


}
