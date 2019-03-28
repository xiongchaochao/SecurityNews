package securitynews.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import securitynews.data.News;
import securitynews.data.source.INewsDataSource;
import securitynews.data.source.remote.AnquankeDataSource;
import securitynews.utils.NewsAdapter;

public class HomePresenter implements HomeContract.Presenter, View.OnTouchListener {

    private AnquankeDataSource mAnquankeDataSource;

    private NewsAdapter mNewsAdapter;

    private Context mContext;

    private Handler mHandler;

    private HomePresenter mHomePresenter;

    public HomePresenter() {
        mHomePresenter = this;
    }

    @Override
    public void setArrayAdapter(final Context context, final ListView mNewsList, final int resourceid) {
        mContext = context;
        mAnquankeDataSource = new AnquankeDataSource();
        mHandler = new Handler();
        mAnquankeDataSource.get1PageNews(1, new INewsDataSource.get1PageNewsCallback() {
            @Override
            public void on1PageNewsLoaded(final List<News> newsList) {
                mNewsAdapter = new NewsAdapter(context, resourceid, newsList);
                Runnable mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mNewsList.setAdapter(mNewsAdapter);
                        mNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                News news = newsList.get(position);
                                String murl = news.getNewsUrl();
                                Intent intent = new Intent("securitynews.com.newscontent");
                                intent.putExtra("url", murl);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                };
                mHandler.post(mRunnable);
            }

            @Override
            public void on1PageNewsInvalid() {

            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }



}
