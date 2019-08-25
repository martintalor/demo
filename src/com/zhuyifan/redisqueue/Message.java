package com.zhuyifan.redisqueue;

import java.io.Serializable;

/** 
* @Author zhuyifan
* @Time 2019年5月24日 上午10:22:58 
* @Version 1.0
* <p>Description:Message.java:</p>
*/
public class Message implements Serializable {

    private static final long serialVersionUID = -389326121047047723L;
    private int id;
    private String content;
    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
