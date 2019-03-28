package securitynews.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRquest {

    private static String INFO = "INFO";

    private static String ERROR = "ERROR";

    private static URL mUrl;

    private static HttpURLConnection mHttpURLConnection;

    private static StringBuffer mResponseData;

    private static HttpCallback mCallback;

    public void Get(final String url, final HttpCallback callback) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mResponseData = new StringBuffer();
                try {
                    URL murl = new URL(url);
                    mHttpURLConnection = (HttpURLConnection) murl.openConnection();
                    mHttpURLConnection.setRequestMethod("GET");
                    mHttpURLConnection.setConnectTimeout(5*1000);
                    mHttpURLConnection.setReadTimeout(10*1000);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if(mHttpURLConnection.getResponseCode()==200){
                        Log.i(INFO, "HTTP Access " + mUrl + "successful, return code: 200!");
                        BufferedReader bufferedReader =new BufferedReader(
                                new InputStreamReader(mHttpURLConnection.getInputStream()));
                        int c;
                        while ((c= bufferedReader.read()) != -1){
                            mResponseData.append((char)c);
                        }
                        callback.finished(mResponseData.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 回调接口：数据传输完毕的回调接口
     */
    public interface HttpCallback{

        void finished(Object data);

    }

}
