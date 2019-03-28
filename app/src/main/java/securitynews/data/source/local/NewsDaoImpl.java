package securitynews.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import securitynews.data.News;

@Database(entities = {News.class}, version = 1)
public abstract class NewsDaoImpl extends RoomDatabase {

    private static NewsDaoImpl INSTANCE;

    public abstract INewsDao iNewsDao();

    private static final Object object = new Object();

    public static NewsDaoImpl getInstance(Context context){
        synchronized (object){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        NewsDaoImpl.class, "news.db").build();
            }
        }
        return INSTANCE;
    }

}
