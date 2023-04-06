package com.jiniaohou.signatureservice.controller;

import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import com.jiniaohou.signatureservice.services.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CoreController {

    @Autowired
    CoreService coreService;

    @RequestMapping("/")
    public String def(){
        return "index";
    }


    @GetMapping("/api/getQrCode/{signKey}")
    public void getQrCode(@PathVariable(name = "signKey") String sign_key, HttpServletResponse response) throws IOException {
        coreService.getQrCode(sign_key, response);
    }

    @PostMapping("/api/submit")
    @ResponseBody
    public SinoBaseResDTO<Object> setSignPic(@RequestBody SignDataDto signDataDto) {
        return coreService.setSignPic(signDataDto);
    }

    @GetMapping("/api/getData/{signKey}")
    @ResponseBody
    public String getData(@PathVariable(name = "signKey") String sign_key) {
        return coreService.getData(sign_key);
    }
}
