package io.gitee.haust_lyb.signature.services;

import java.util.HashMap;

public interface CoreService {

    HashMap setSignPic(String data, String uuid);

    String getData(String uuid);

}
