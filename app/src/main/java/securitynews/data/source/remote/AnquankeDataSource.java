package securitynews.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import securitynews.data.News;
import securitynews.data.source.INewsDataSource;
import securitynews.utils.HttpRquest;

/**
 * 远程新闻数据源的具体实现
 *
 * 通过解析网页获得网络新闻数据
 */

public class AnquankeDataSource implements INewsDataSource {

    private AnquankeParser mAnquankeParser;

    //每次获取十条新闻
    @Override
    public void get1PageNews(int page, get1PageNewsCallback callback) {
        mAnquankeParser = new AnquankeParser();
        mAnquankeParser.getWhichPageNews(page, callback);
    }

    @Override
    public void getNews(@NonNull int newsId, getNewsCallback callback) {

    }

    class AnquankeParser {

        private  String INFO = "INFO";

        private  String ERROR = "ERROR";

        private  String API = "https://api.anquanke.com/data/v1/posts?";

        private  String ArticleBaseUrl = "https://www.anquanke.com/post/id/";

        private  String EACH_PAGE_NEWS_SIZE = "10";

        private HttpRquest mHttpRquest;

        public  void getWhichPageNews(int page, final get1PageNewsCallback callback){
            mHttpRquest = new HttpRquest();
            String url = API + "size=" + EACH_PAGE_NEWS_SIZE + "&page" + String.valueOf(page);
            final List<News> newsLst = new ArrayList<News>();
            try {
                mHttpRquest.Get(url, new HttpRquest.HttpCallback(){
                    @Override
                    public void finished(Object data) {
                        ArrayList<Integer> NewsIdLst = new ArrayList<Integer>();
                        String[] ids = getField("id", (String) data);
                        String[] tiles = getField("title",(String) data);
                        String[] contents = getField("desc", (String)data);
                        String[] dates = getField("date", (String)data);
                        int len = ids.length;
                        for(int i=0; i<len; i++) {
                            News news = new News();
                            news.setNewsTile(tiles[i]);
                            news.setNewsId(Integer.valueOf(ids[i]));
                            news.setNewsUrl(ArticleBaseUrl + ids[i]);
                            news.setContent(contents[i]);
                            news.setDate(dates[i]);
                            newsLst.add(news);
                        }
                        callback.on1PageNewsLoaded(newsLst);
                    }
                });
            } catch (IOException e) {
                callback.on1PageNewsInvalid();
                e.printStackTrace();
            }
        }

        private String[] getField(String field, String JsonData){
            String[] result;
            Log.i(INFO, "parse json data: start get value of field : " + field);
            try {
                JSONObject root = new JSONObject(JsonData);
                if(!root.has("data")) {
                    Log.e(ERROR, "parse json data: get `data` field failed!");
                    return null;
                }else if(root.has(field)) {
                    result = new String[]{root.getString(field)};
                    StringBuffer logdata = new StringBuffer();
                    for (String s:result) {
                        logdata.append(s);
                    }
                    Log.i(INFO, "parse json data: get `" + field + "` field successul: " + logdata);
                    return result;
                }
                Boolean hasJSONArray = Boolean.FALSE;
                JSONArray jsonArray = root.getJSONArray("data");
                int len = jsonArray.length();
                result = new String[len];
                for(int i=0; i<len; i++){
                    JSONObject subField = jsonArray.getJSONObject(i);
                    if(field.equals("tags")){
                        JSONArray subFieldJSONArray = subField.getJSONArray(field);
                        for(int j=0; j<subFieldJSONArray.length(); j++){
                            result[j] = subFieldJSONArray.getString(j);
                            StringBuffer logdata = new StringBuffer();
                            for (String s:result) {
                                logdata.append(s);
                            }
                            Log.i(INFO, "parse json data: get `" + field + "` field successul: " + logdata);
                            return result;
                        }
                    }else if(subField.has(field)){
                        result[i] = subField.getString(field);
                    }
                }
                StringBuffer logdata = new StringBuffer();
                for (String s:result) {
                    logdata.append(s);
                }
                Log.i(INFO, "parse json data: get `" + field + "` field successul: " + logdata);
                return result;
            } catch (JSONException e) {

                e.printStackTrace();
            }
            return null;
        }

    }
}
