package com.jiniaohou.signatureservice.base;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class SinoBaseResDTO<T> implements Serializable {

    private String status;

    private String msg;

    private String reqNo;

    private T dataBody;

    public SinoBaseResDTO() {}

    public SinoBaseResDTO(T dataBody) {
        this.dataBody = dataBody;
    }

    public SinoBaseResDTO(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public SinoBaseResDTO(String status, String msg, T dataBody) {
        this.status = status;
        this.msg = msg;
        this.dataBody = dataBody;
    }

    public SinoBaseResDTO(String status, String msg, String reqNo, T dataBody) {
        this.status = status;
        this.msg = msg;
        this.reqNo = reqNo;
        this.dataBody = dataBody;
    }

    public static <T> SinoBaseResDTO<T> create(T t){
        return new SinoBaseResDTO<T>(t);
    }

    public static <T> SinoBaseResDTO<T> create(T t, StatusEnum statusEnum){
        return new SinoBaseResDTO<T>(statusEnum.code(), statusEnum.desc(), t);
    }

    public static <T> SinoBaseResDTO<T> createSuccess(T t, String msg){
        return new SinoBaseResDTO<T>(StatusEnum.SUCCESS.code(), StrUtil.isBlank(msg) ? StatusEnum.SUCCESS.desc() : msg, t);
    }

    public static <T> SinoBaseResDTO<T> create(T t, StatusEnum statusEnum, String msg){

        return new SinoBaseResDTO<T>(statusEnum.code(), msg, t);
    }

    public static <T> SinoBaseResDTO<T> success(T t, String msg){
        return new SinoBaseResDTO<T>(StatusEnum.SUCCESS.code(), StrUtil.isBlank(msg) ? StatusEnum.SUCCESS.desc() : msg, t);
    }

    public static <T> SinoBaseResDTO<T> success(T t){
        return new SinoBaseResDTO<T>(StatusEnum.SUCCESS.code(), StatusEnum.SUCCESS.desc(), t);
    }

    public static <T> SinoBaseResDTO<T> fail(String errorstatus, String errormsg) {
        return new SinoBaseResDTO<>(errorstatus, errormsg);
    }

    public static <T> boolean isSuccess(SinoBaseResDTO<T> result) {
        if (result == null) {
            return false;
        } else {
            if (StatusEnum.SUCCESS.code().equals(result.getStatus())) {
                return true;
            } else {
                return false;
            }
        }

    }
}
