package com.jiniaohou.signatureservice.services;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import com.jiniaohou.signatureservice.base.StatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;

@Service
public class CoreServiceImpl implements CoreService {

    private static volatile TimedCache timedCache = CacheUtil.newTimedCache(60000);

    public SinoBaseResDTO<Object> setSignPic(SignDataDto signDataDto) {
        SinoBaseResDTO<Object> sinoBaseResDTO = new SinoBaseResDTO<>();
        System.out.println("setSignPic dto = " + JSONUtil.toJsonPrettyStr(signDataDto));
        if (StrUtil.isNotEmpty(signDataDto.getBase64()) && StrUtil.isNotEmpty(signDataDto.getSignKey()) && !"null".equals(signDataDto.getSignKey())) {
            this.timedCache.put(signDataDto.getSignKey(), signDataDto.getBase64());
            return sinoBaseResDTO.success(null);
        } else {
            return sinoBaseResDTO.fail(StatusEnum.FAIL.code(), StatusEnum.FAIL.desc());
        }
    }

    public String getData(String signKey) {
        System.out.println("getData signKey = " + signKey);
        Object o = this.timedCache.get(signKey, true);
        if (o == null) {
            return "pic not found";
        } else {
            return o.toString();
        }
    }


    @Override
    public String getQrCode(String signKey, Model model) throws IOException {
        model.addAttribute("signKey", signKey);
        return "qrcode";
    }

    @Override
    public String hasSubmit(String signKey) {
        return (this.timedCache.get(signKey) == null) ? "pic not found" : "success";
    }
}
