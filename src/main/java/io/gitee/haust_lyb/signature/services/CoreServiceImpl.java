package io.gitee.haust_lyb.signature.services;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import io.gitee.haust_lyb.signature.base.SinoBaseResDTO;
import io.gitee.haust_lyb.signature.configs.StatusEnum;
import org.springframework.stereotype.Service;


@Service
public class CoreServiceImpl implements CoreService{

    TimedCache timedCache = new TimedCache(300000L);

    public SinoBaseResDTO<Object> setSignPic(String data, String uuid) {
        SinoBaseResDTO<Object> sinoBaseResDTO=new SinoBaseResDTO<>();
        if (StrUtil.isNotEmpty(data) && StrUtil.isNotEmpty(uuid) && !"null".equals(uuid)) {
            this.timedCache.put(uuid, data);
            return sinoBaseResDTO.success(null);
        } else {
            return sinoBaseResDTO.fail(StatusEnum.FAIL.code(), StatusEnum.FAIL.desc());
        }
    }

    public String getData(String uuid) {
        return (this.timedCache.get(uuid) == null) ? "" : this.timedCache.get(uuid).toString();
    }

}
