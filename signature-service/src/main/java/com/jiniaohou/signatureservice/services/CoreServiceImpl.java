package com.jiniaohou.signatureservice.services;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import com.jiniaohou.signatureservice.base.StatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;


@Service
public class CoreServiceImpl implements CoreService {

    TimedCache timedCache = new TimedCache(300000L);

    public SinoBaseResDTO<Object> setSignPic(SignDataDto signDataDto) {
        SinoBaseResDTO<Object> sinoBaseResDTO = new SinoBaseResDTO<>();
        if (StrUtil.isNotEmpty(signDataDto.getBase64()) && StrUtil.isNotEmpty(signDataDto.getSignKey()) && !"null".equals(signDataDto.getSignKey())) {
            this.timedCache.put(signDataDto.getSignKey(), signDataDto.getBase64());
            return sinoBaseResDTO.success(null);
        } else {
            return sinoBaseResDTO.fail(StatusEnum.FAIL.code(), StatusEnum.FAIL.desc());
        }
    }

    public String getData(String signKey) {
        if (this.timedCache.get(signKey) == null) {
            return "pic not found";
        } else {
            String res = this.timedCache.get(signKey).toString();
            this.timedCache.remove(signKey);
            return res;
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
