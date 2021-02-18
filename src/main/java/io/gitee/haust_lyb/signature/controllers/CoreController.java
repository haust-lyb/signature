package io.gitee.haust_lyb.signature.controllers;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoreController {
    TimedCache timedCache = new TimedCache(300000L);

    @RequestMapping({"/"})
    public String index() {
        return "index";
    }

    /**
     * 新版的签名
     * @return
     */
    @RequestMapping({"/homev2"})
    public String homev2() {
        return "index2";
    }
    @RequestMapping({"/homev3"})
    public String homev3() {
        return "index3";
    }

    @RequestMapping({"/submit"})
    @ResponseBody
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

    @RequestMapping({"/getData"})
    @ResponseBody
    public String getData(String uuid) {
        return (this.timedCache.get(uuid) == null) ? "" : this.timedCache.get(uuid).toString();
    }
}
