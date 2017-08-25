package fight.tecmry.com.redlive.Bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Tecmry on 2017/8/24.
 */

public class EverydaySentenceData {

    /**
     * sid : 2699
     * tts : http://news.iciba.com/admin/tts/2017-08-24-day
     * content : Most of the shadows of life are caused by standing in our own sunshine. 
     * note : 生活中大多数的阴霾归咎于我们挡住了自己的阳光。——爱默生
     * love : 2102
     * translation : 词霸小编：大部份的痛苦，都是不肯离场的结果，没有命定的不幸，只有死不放手的执着。优雅的放手，一切将云开雾散！！！
     * picture : http://cdn.iciba.com/news/word/20170824.jpg
     * picture2 : http://cdn.iciba.com/news/word/big_20170824b.jpg
     * caption : 词霸每日一句
     * dateline : 2017-08-24
     * s_pv : 0
     * sp_pv : 0
     * tags : [{"id":null,"name":null}]
     * fenxiang_img : http://cdn.iciba.com/web/news/longweibo/imag/2017-08-24.jpg
     */

    private String sid;
    private String tts;
    private String content;
    private String note;
    private String love;
    private String translation;
    private String picture;
    private String picture2;
    private String caption;
    private String dateline;
    private String s_pv;
    private String sp_pv;
    private String fenxiang_img;
    private List<TagsBean> tags;

    public static EverydaySentenceData objectFromData(String str) {

        return new Gson().fromJson(str, EverydaySentenceData.class);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getS_pv() {
        return s_pv;
    }

    public void setS_pv(String s_pv) {
        this.s_pv = s_pv;
    }

    public String getSp_pv() {
        return sp_pv;
    }

    public void setSp_pv(String sp_pv) {
        this.sp_pv = sp_pv;
    }

    public String getFenxiang_img() {
        return fenxiang_img;
    }

    public void setFenxiang_img(String fenxiang_img) {
        this.fenxiang_img = fenxiang_img;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class TagsBean {
        /**
         * id : null
         * name : null
         */

        private Object id;
        private Object name;

        public static TagsBean objectFromData(String str) {

            return new Gson().fromJson(str, TagsBean.class);
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }
    }
}
