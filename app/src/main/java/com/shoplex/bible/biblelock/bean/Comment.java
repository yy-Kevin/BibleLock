package com.shoplex.bible.biblelock.bean;

/**
 * Created by qsk on 2017/3/28.
 */

public class Comment {

    String name; //评论者
    String content; //评论内容
    String time; //发布时间

    public String getTime() {
        return "2017-3-31 2:52:21";
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Comment(){

    }

    public Comment(String name, String content){
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
