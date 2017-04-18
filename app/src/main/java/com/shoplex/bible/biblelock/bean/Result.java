package com.shoplex.bible.biblelock.bean;

/**
 * Created by qsk on 2017/4/17.
 */

public class Result<T> {


    /**
     * message : Successfully get book<4>
     * code : 200
     * data : {"score":8,"update_timestamp":1489039719,"author":"","introduction":"<p>Upon the death of multi-millionaire playboy Sun Jicheng, Jinan city suddenly becomes the center of a maelstrom of Jianghu intrigue. Once again, the mysterious death and/or disappearance of some of the most powerful and famous martial artists in recent years, such as the \u201cEnd the Heavens, Destroy the Earth\u201d crime-fighting duo Gao Tianjue and Guo Mie, and the master thief \u201cLaughing General\u201d Li, come to the spotlight. Smack dab in the middle of all the plotting and fighting is Ingot, a scrappy beggar who is far more than what he seems. A Gulong novel, translated by deathblade.<\/p>","cover":"https://dajvcwee6u41f.cloudfront.net/books/4.png","views":41,"title_en":"Dragon King With Seven Stars","links":{"self":"https://api.beta.wuxiamagic.com/books/4"},"word_count":76775,"translator":"","chapter_count":25,"title_zh":"七星龙王","is_collected":false,"create_timestamp":0,"keywords":"","collection_id":0,"id":4,"is_published":true}
     */

    private String message;
    private int code;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * score : 8
         * update_timestamp : 1489039719
         * author :
         * introduction : <p>Upon the death of multi-millionaire playboy Sun Jicheng, Jinan city suddenly becomes the center of a maelstrom of Jianghu intrigue. Once again, the mysterious death and/or disappearance of some of the most powerful and famous martial artists in recent years, such as the “End the Heavens, Destroy the Earth” crime-fighting duo Gao Tianjue and Guo Mie, and the master thief “Laughing General” Li, come to the spotlight. Smack dab in the middle of all the plotting and fighting is Ingot, a scrappy beggar who is far more than what he seems. A Gulong novel, translated by deathblade.</p>
         * cover : https://dajvcwee6u41f.cloudfront.net/books/4.png
         * views : 41
         * title_en : Dragon King With Seven Stars
         * links : {"self":"https://api.beta.wuxiamagic.com/books/4"}
         * word_count : 76775
         * translator :
         * chapter_count : 25
         * title_zh : 七星龙王
         * is_collected : false
         * create_timestamp : 0
         * keywords :
         * collection_id : 0
         * id : 4
         * is_published : true
         */

        private int score;
        private int update_timestamp;
        private String author;
        private String introduction;
        private String cover;
        private int views;
        private String title_en;
        private LinksBean links;
        private int word_count;
        private String translator;
        private int chapter_count;
        private String title_zh;
        private boolean is_collected;
        private int create_timestamp;
        private String keywords;
        private int collection_id;
        private int id;
        private boolean is_published;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(int update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public int getWord_count() {
            return word_count;
        }

        public void setWord_count(int word_count) {
            this.word_count = word_count;
        }

        public String getTranslator() {
            return translator;
        }

        public void setTranslator(String translator) {
            this.translator = translator;
        }

        public int getChapter_count() {
            return chapter_count;
        }

        public void setChapter_count(int chapter_count) {
            this.chapter_count = chapter_count;
        }

        public String getTitle_zh() {
            return title_zh;
        }

        public void setTitle_zh(String title_zh) {
            this.title_zh = title_zh;
        }

        public boolean isIs_collected() {
            return is_collected;
        }

        public void setIs_collected(boolean is_collected) {
            this.is_collected = is_collected;
        }

        public int getCreate_timestamp() {
            return create_timestamp;
        }

        public void setCreate_timestamp(int create_timestamp) {
            this.create_timestamp = create_timestamp;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(int collection_id) {
            this.collection_id = collection_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIs_published() {
            return is_published;
        }

        public void setIs_published(boolean is_published) {
            this.is_published = is_published;
        }

        public static class LinksBean {
            /**
             * self : https://api.beta.wuxiamagic.com/books/4
             */

            private String self;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }
        }
    }
}
