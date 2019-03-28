package securitynews.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import securitynews.data.News;

/**
 * 新闻数据源提供的接口: Model层对Presenter开发的主要接口规范
 *
 * 异步加载数据时，presenter层调用接口获取网络数据，成功/失败后调用回调接口来应对
 */

public interface INewsDataSource {

    interface getNewsCallback{

        void onNewsLoaded(News news);

        void onNewsInvalid();
    }

    interface get1PageNewsCallback{

        void on1PageNewsLoaded(List<News> newsList);

        void on1PageNewsInvalid();
    }

//    void addNews(@NonNull News news);
//
//    void deleteNews(@NonNull int newsId);
//
//    void deleteAllNews();
    void get1PageNews(@NonNull int page, get1PageNewsCallback callback);

    void getNews(@NonNull int newsId, getNewsCallback callback);
}
