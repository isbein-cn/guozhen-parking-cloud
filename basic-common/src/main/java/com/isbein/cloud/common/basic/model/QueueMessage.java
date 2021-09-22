package com.isbein.cloud.common.basic.model;

import lombok.Data;

@Data
public class QueueMessage<T> {

    private String msgId;

    private T data;

    private String type;

    public QueueMessage(String msgId,T data,String type){
        this.msgId = msgId;
        this.data = data;
        this.type = type;
    }
}
