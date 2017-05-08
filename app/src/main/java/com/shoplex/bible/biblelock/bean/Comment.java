package com.shoplex.bible.biblelock.bean;

/**
 * Created by qsk on 2017/3/28.
 */

public class Comment {

    private String name; //评论者
    private String content; //评论内容
    private String time; //发布时间
    private int like;
    private int share;
    private int comment;
    private boolean isLike;

    public boolean isLike() {
        return true;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }



    public String getTime() {
        return "2017-4-7 1:50:21";
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
