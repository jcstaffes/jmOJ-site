package com.jm.oj.util;


public class BasicResponse {
    private Boolean success;
    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public static BasicResponse create(Boolean success, Object data) {
        BasicResponse res = new BasicResponse();
        res.setSuccess(success);
        res.setData(data);
        return res;
    }

    public static BasicResponse create(Object data) {
        return BasicResponse.create(true, data);
    }
}
