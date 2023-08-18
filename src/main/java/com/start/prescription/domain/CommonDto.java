package com.start.prescription.domain;


import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass // 공통속성 처리
public abstract class CommonDto {
    private String code;
    private String message;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
