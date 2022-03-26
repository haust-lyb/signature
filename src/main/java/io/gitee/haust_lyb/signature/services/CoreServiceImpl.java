package io.gitee.haust_lyb.signature.services;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CoreServiceImpl implements CoreService{

    TimedCache timedCache = new TimedCache(300000L);

    public HashMap setSignPic(String data, String uuid) {
        HashMap<Object, Object> rs = new HashMap<>();
        if (StrUtil.isNotEmpty(data) && StrUtil.isNotEmpty(uuid) && !"null".equals(uuid)) {
            this.timedCache.put(uuid, data);
            rs.put("status", "0");
            rs.put("msg", "成功");
        } else {
            rs.put("status", "-1");
            rs.put("msg", "失败");
        }
        return rs;
    }

    public String getData(String uuid) {
        return (this.timedCache.get(uuid) == null) ? "" : this.timedCache.get(uuid).toString();
    }

}
