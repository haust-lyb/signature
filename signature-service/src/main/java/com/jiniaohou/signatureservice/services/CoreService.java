package com.jiniaohou.signatureservice.services;


import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CoreService {
    SinoBaseResDTO<Object> setSignPic(SignDataDto signDataDto);
    String getData(String signKey);
    String getQrCode(String signKey, Model model) throws IOException;
    String hasSubmit(String signKey);
}
