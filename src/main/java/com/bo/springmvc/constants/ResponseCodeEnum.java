package com.bo.springmvc.constants;

/**
 * @Description 返回码枚举
 * @author Bo
 * @version 2018年7月3日　下午10:06:07
 * @码云 https://gitee.com/LeisureBo
 */
public enum  ResponseCodeEnum {
	
	NO_ACCESS_ERROR("000001", "无权访问"),
    USER_OR_PASSWORD_ERRROR("001001","用户名或密码错误"),
    SUCCESS("000000","成功"),
    SYS_PARAM_ERROR("001002","请求参数错误"),
    GENERATE_TOKEN_ERROR("001003", "生成token失败"),
    TOKEN_EXPIRE("001004","token过期"),
    TOKEN_SIGNATURE_ERROR("001005","签名验证失败"),
    SYSTEM_BUSY("001099","系统繁忙，请稍候重试");

    private final String code;
    private final String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
