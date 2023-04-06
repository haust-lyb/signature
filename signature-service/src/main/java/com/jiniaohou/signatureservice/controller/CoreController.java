package com.jiniaohou.signatureservice.controller;

import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import com.jiniaohou.signatureservice.services.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class CoreController {
    @Autowired
    CoreService coreService;

    @RequestMapping("/")
    public String def(){
        return "index";
    }

    /**
     * 返回签字二维码弹窗页面
     * @param sign_key
     * @param model
     * @throws IOException
     */
    @GetMapping("/api/getQrCode/{signKey}")
    public String getQrCode(@PathVariable(name = "signKey") String sign_key, Model model) throws IOException {
        return coreService.getQrCode(sign_key,model);
    }

    /**
     * 返回某个签字板是否已签字
     * @param sign_key
     * @return
     * @throws IOException
     */
    @GetMapping("/api/hasSubmit/{signKey}")
    @ResponseBody
    public String hasSubmit(@PathVariable(name = "signKey") String sign_key) throws IOException {
        return coreService.hasSubmit(sign_key);
    }


    /**
     * 获取签字
     * @param sign_key
     * @return
     */
    @GetMapping("/api/getData/{signKey}")
    @ResponseBody
    public String getData(@PathVariable(name = "signKey") String sign_key) {
        return coreService.getData(sign_key);
    }


    /**
     * 提交签字
     * @param signDataDto
     * @return
     */
    @PostMapping("/api/submit")
    @ResponseBody
    public SinoBaseResDTO<Object> setSignPic(@RequestBody SignDataDto signDataDto) {
        return coreService.setSignPic(signDataDto);
    }


}
