package securitynews.data.source.local;

import android.support.annotation.NonNull;

import java.util.List;

import securitynews.data.News;
import securitynews.data.source.INewsDataSource;
import securitynews.utils.AppExecutors;

/**
 * 本地新闻数据源的具体实现类。
 *
 * Model层对Presenter层开放的数据访问接口的实现类。
 * 在这个类的内部，通过传入本地数据库的访问接口类来进行数据库访问
 */
public class NewsLocalDataSourceImpl implements INewsDataSource {

    private static volatile NewsLocalDataSourceImpl INSTANCE;

    private AppExecutors mAppExecutors;

    private INewsDao mINewsDao;

    private NewsLocalDataSourceImpl(@NonNull AppExecutors appExecutors, @NonNull INewsDao iNewsDao){
        this.mAppExecutors = appExecutors;
        this.mINewsDao = iNewsDao;
    }

    public NewsLocalDataSourceImpl getInstance(@NonNull AppExecutors appExecutors, @NonNull INewsDao iNewsDao){
        if (INSTANCE != null) {
            return INSTANCE;
        }
        INSTANCE = new NewsLocalDataSourceImpl(appExecutors, iNewsDao);
        return INSTANCE;
    }

    public void addNews(@NonNull final News news) {
        Runnable addRunnable = new Runnable() {
            @Override
            public void run() {
                mINewsDao.insertNews(news);
            }
        };

        mAppExecutors.getDiskIOThread().execute(addRunnable);
    }

    public void deleteNews(@NonNull final int newsId) {
        Runnable delRunnable = new Runnable() {
            @Override
            public void run() {
                mINewsDao.deleteNewsById(newsId);
            }
        };

        mAppExecutors.getDiskIOThread().execute(delRunnable);
    }

    public void deleteAllNews() {
        Runnable delAllRunnable = new Runnable() {
            @Override
            public void run() {
                mINewsDao.deleteAllNews();
            }
        };

        mAppExecutors.getDiskIOThread().execute(delAllRunnable);
    }

    @Override
    public void get1PageNews(int page, final get1PageNewsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<News> newsList = mINewsDao.getAllNews();
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (newsList.isEmpty()){
                            callback.on1PageNewsInvalid();
                        }else {
                            callback.on1PageNewsLoaded(newsList);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIOThread().execute(runnable);
    }

    @Override
    public void getNews(@NonNull final int newsId, final getNewsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final News news = mINewsDao.getNewsById(newsId);
                //线程内部开启线程调用handler来将回调方法加入UI主线程的执行队列中
                mAppExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (news == null){
                            callback.onNewsInvalid();
                        }else {
                            callback.onNewsLoaded(news);
                        }
                    }
                });
            }
        };
        mAppExecutors.getDiskIOThread().execute(runnable);
    }

}
