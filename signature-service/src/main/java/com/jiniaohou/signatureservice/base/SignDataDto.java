package com.jiniaohou.signatureservice.base;


import lombok.Data;

@Data
public class SignDataDto {
    String base64;
    String signKey;
}
