package io.gitee.haust_lyb.signature.controllers;

import io.gitee.haust_lyb.signature.services.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class CoreController {

    @Autowired
    CoreService coreService;

    @RequestMapping({"/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/{path}")
    public String path(@PathVariable("path") String path){
        return path+".html";
    }


    @RequestMapping({"/submit"})
    @ResponseBody
    public HashMap setSignPic(String data, String uuid) {
        return coreService.setSignPic(data,uuid);
    }

    @RequestMapping({"/getData"})
    @ResponseBody
    public String getData(String uuid) {
        return coreService.getData(uuid);
    }
}
