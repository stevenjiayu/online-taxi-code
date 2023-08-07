package com.xcode;

import lombok.Data;
import lombok.Getter;



public enum CommonStateEnum {


    VERICATION_CODE_ERROR(1099,"驗證碼過期"),
    SUCCESS(1,"成功"),
    FAIL(0,"失敗"),



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
