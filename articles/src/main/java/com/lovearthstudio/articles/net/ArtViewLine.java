package com.lovearthstudio.articles.net;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Author：Mingyu Yi on 2016/4/30 16:56
 * Email：461072496@qq.com
 */
public class ArtViewLine extends RealmObject {
    //主键,realm现在不支持自增字段
    //@PrimaryKey
    //private int inc;

    //频道名字
    @Required
    private String channel;
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }



    //文章的tid的min
    private long tidmin;

    public long getTidmin() {
        return tidmin;
    }

    public void setTidmin(long tidmin) {
        this.tidmin = tidmin;
    }
    //文章的tid的min
    private long tidmax;

    public long getTidmax() {
        return tidmax;
    }

    public void setTidmax(long tidmax) {
        this.tidmax = tidmax;
    }
    public ArtViewLine() { }





}
