package securitynews.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import securitynews.com.securitynews.R;
import securitynews.data.News;

public class NewsAdapter extends ArrayAdapter<News> {

    private int mResourceId;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View mView = LayoutInflater.from(getContext()).inflate(mResourceId, null);
        TextView mTextView = mView.findViewById(R.id.newsTile);
        mTextView.setText(news.getNewsTile());
        return mView;
    }


}
