package com.jiniaohou.signatureservice.services;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.jiniaohou.signatureservice.base.SignDataDto;
import com.jiniaohou.signatureservice.base.SinoBaseResDTO;
import com.jiniaohou.signatureservice.base.StatusEnum;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
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
        return (this.timedCache.get(signKey) == null) ? "查无此图" : this.timedCache.get(signKey).toString();
    }


    @Override
    public void getQrCode(String signKey, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY.getRGB());
        QrCodeUtil.generate("http://192.168.31.113:8080?signKey="+signKey, config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
    }
}
