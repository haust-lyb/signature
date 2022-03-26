package io.gitee.haust_lyb.signature.configs;

/*
* 状态码枚举值
* */
public enum StatusEnum {
    SUCCESS("0","成功"),
    FAIL("-1","失败");
    private String code;
    private String desc;

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code(){return code;}
    public String desc(){return desc;}


}
