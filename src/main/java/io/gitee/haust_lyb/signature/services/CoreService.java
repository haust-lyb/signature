package io.gitee.haust_lyb.signature.services;

import io.gitee.haust_lyb.signature.base.SinoBaseResDTO;


public interface CoreService {

    SinoBaseResDTO<Object> setSignPic(String data, String uuid);

    String getData(String uuid);

}
