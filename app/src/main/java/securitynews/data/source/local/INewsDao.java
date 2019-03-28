package securitynews.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import securitynews.data.News;

/**
 * Data Access Object for the database table.
 */
@Dao
public interface INewsDao {

    /**
     * Select all news from news table
     *
     * @return all news
     */
    @Query("SELECT * FROM newsTable")
    List<News> getAllNews();

    /**
     * Select a news by id
     *
     * @param NewsId the news id
     * @return the news with id
     */
    @Query("SELECT * FROM newsTable WHERE entryid=:NewsId")
    News getNewsById(int NewsId);

    /**
     * Insert a news in the database.If the news exists, replace it.
     *
     * @param news the news to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(News news);

    /**
     * Delete news by news id
     *
     * @param NewsId the number of tasks deleted. This should always be 1.
     */
    @Query("DELETE FROM newsTable WHERE entryid = :NewsId")
    int deleteNewsById(int NewsId);

    /**
     * Delete all news
     */
    @Query("DELETE FROM newsTable")
    void deleteAllNews();
}
