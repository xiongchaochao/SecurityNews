package securitynews.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * 每一条新闻对象的数据模型
 */

@Entity(tableName = "newsTable")
public class News {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private int NewsId;

    @NonNull
    @ColumnInfo(name = "tile")
    private String NewsTile;

    @NonNull
    @ColumnInfo(name = "url")
    private String NewsUrl;

    @NonNull
    @ColumnInfo(name = "date")
    private String Date;

    @NonNull
    @ColumnInfo(name = "content")
    private String content;

    @NonNull
    @ColumnInfo(name = "ismark")
    private Boolean isMark;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getMark() {
        return isMark;
    }

    public void setMark(Boolean mark) {
        isMark = mark;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getNewsId() {
        return NewsId;
    }

    public void setNewsId(int newsId) {
        NewsId = newsId;
    }

    public String getNewsTile() {
        return NewsTile;
    }

    public void setNewsTile(String newsTile) {
        NewsTile = newsTile;
    }

    public String getNewsUrl() {
        return NewsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        NewsUrl = newsUrl;
    }
}
