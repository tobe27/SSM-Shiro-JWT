package model;

import java.util.LinkedHashMap;

/**
 * 用来返回controller层请求的结果
 * 默认为三个返回项：code,message,data
 * 如果有其他更多返回项，使用result方法拼接
 */
public class ResponseData extends LinkedHashMap<String, Object> {

    public ResponseData result(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public ResponseData success(){
        this.put("code",200);
        this.put("message", "success");
        return this;
    }

    public ResponseData fail(){
        this.put("code",400);
        this.put("message", "fail");
        return this;
    }

    public ResponseData unauthorized(){
        this.put("code",401);
        this.put("message", "the current user is unauthorized");
        return this;
    }

    public ResponseData code(int code) {
        return result("code",code);
    }

    public ResponseData message(String message) {
        return result("message", message);
    }

    public ResponseData data(Object data) {
        return result("data", data);
    }
}
