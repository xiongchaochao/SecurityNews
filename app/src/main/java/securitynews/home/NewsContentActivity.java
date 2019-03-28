package securitynews.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import securitynews.com.securitynews.R;

public class NewsContentActivity extends Activity {

    private WebView mWebView;

    private ProgressBar mProgressBar;

    private Intent mIntent;

    private String mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newscontent_home);
        init();
    }

    @SuppressLint("JavascriptInterface")
    private void init(){
        mIntent = getIntent();
        if (mIntent == null){
            return;
        }
        mAction = mIntent.getAction();
        if(!mAction.equals("securitynews.com.newscontent")){
            return;
        }
        mWebView = (WebView) findViewById(R.id.webview);;
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mWebView.loadUrl( mIntent.getStringExtra("url"));
    }

}
