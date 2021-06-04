package com.ridoy.poetry.Responses;

import com.ridoy.poetry.Models.Poetry;

import java.util.List;

public class GetPoetry {

    String status,message;
    List<Poetry> data;

    public GetPoetry(String status, String message, List<Poetry> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Poetry> getData() {
        return data;
    }

    public void setData(List<Poetry> data) {
        this.data = data;
    }
}
