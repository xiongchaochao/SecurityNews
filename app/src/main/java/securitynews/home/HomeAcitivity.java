package securitynews.home;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import securitynews.com.securitynews.R;


public class HomeAcitivity extends FragmentActivity implements View.OnClickListener{


    LinearLayout homelayout;
    LinearLayout minelayout;

    HomeFragment homeFragment;
    private FragmentManager fragmentManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_home);
        init();
    }

    /**
     * 首次进入App的初始化操作，初始化首个fragment
     */
    private void init(){
        homelayout = (LinearLayout)findViewById(R.id.NewList);
        minelayout = (LinearLayout)findViewById(R.id.Mine);
        homelayout.setOnClickListener(this);
        minelayout.setOnClickListener(this);
        homelayout.performClick();

    }

    @Override
    public void onClick(View view) {
        fragmentManger = getSupportFragmentManager();
        switch (view.getId()){
            case R.id.NewList:
                homeFragment = new HomeFragment();
                fragmentManger.beginTransaction()
                    .add(R.id.fragmentModel, homeFragment)
                    .commit();
                break;
            case R.id.Mine:
                break;
        }
    }


}
