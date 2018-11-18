package io.falcon.assignment;

public class PayloadReply {

    String content;
    String timestamp;
    String status;

    public String getContent() {
        return this.content;
    }

    public String getTimestamp(){
        return this.timestamp;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
}