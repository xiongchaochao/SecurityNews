package securitynews.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import securitynews.com.securitynews.R;


/**
 * 带有上拉加载的ListView封装类
 *
 * 实现：1.注册监听列表滑动情况，当列表滑动到最底部时，添加底部视图，显示加载更多视图
 * 2.
 */
public class PulmListView extends ListView {

    private OnPullUpLoadMoreListener mOnPullUpLoadMoreListener;

    private FooterView mFooterView;

    public PulmListView(Context context) {
        super(context);
    }

    public PulmListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("TAG", "加载了第二个构造方法");
        init();
    }

    /**
     * 初始化：设置屏幕滑动监听
     */
    private void init(){
        mFooterView = new FooterView(getContext(), null, 0);
        super.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                if(lastVisibleItem == totalItemCount){
                    if (mOnPullUpLoadMoreListener != null){
                        mOnPullUpLoadMoreListener.onPullUpLoadMore();
                    }
                }
            }
        });
    }

    private void showLoadMoreView(){
        if(findViewById(R.id.id_load_more_layout) != null){
            addFooterView(mFooterView);
        }
    }

    /**
     * 给本View设置上拉回调接口
     */
    public void setOnPullUpLoadMoreListener(OnPullUpLoadMoreListener mOnPullUpLoadMoreListener){

        this.mOnPullUpLoadMoreListener = mOnPullUpLoadMoreListener;

    }

    /**
     * 上拉加载更多的回调接口
     */
    public interface OnPullUpLoadMoreListener{

        void onPullUpLoadMore();

    }


}
