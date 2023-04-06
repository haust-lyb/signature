package com.jiniaohou.signatureservice.services;


import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CoreService {
    SinoBaseResDTO<Object> setSignPic(SignDataDto signDataDto);
    String getData(String signKey);
    void getQrCode(String signKey, HttpServletResponse response) throws IOException;
}
