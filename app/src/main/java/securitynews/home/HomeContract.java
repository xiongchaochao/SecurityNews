package securitynews.home;

import android.content.Context;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import securitynews.BasePresenter;
import securitynews.BaseView;
import securitynews.data.News;

public interface HomeContract {

    interface Presenter extends BasePresenter{

       void setArrayAdapter(Context context, ListView mNewsList, int resourceid);

    }

    interface View extends BaseView<Presenter>{



    }


}
