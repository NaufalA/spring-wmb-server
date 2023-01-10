package com.warungmakanbahari.warungmakanbahari.shared.dtos;

public class SuccessResponse<T> extends CommonResponse {
    private T data;

    public SuccessResponse() {
    }

    public SuccessResponse(String code, String message, T data) {
        super.setCode(code);
        super.setStatus("SUCCESS");
        super.setMessage(message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
