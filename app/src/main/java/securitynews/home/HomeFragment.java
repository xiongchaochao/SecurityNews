package securitynews.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import securitynews.BasePresenter;
import securitynews.com.securitynews.R;
import securitynews.data.News;
import securitynews.utils.NewsAdapter;

public class HomeFragment extends Fragment implements HomeContract.View{

    private HomeContract.Presenter mPresenter;

    private ListView mListView;

    private FragmentActivity mActivity;

    private Context mContext;

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();
        mListView = mActivity.findViewById(R.id.newslist);
        mPresenter = new HomePresenter();
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mListView = view.findViewById(R.id.newslist);
        mPresenter.setArrayAdapter(mContext, mListView, R.layout.single_news);
        return view;
    }
}
