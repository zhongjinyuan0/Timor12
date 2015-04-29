package timor.timor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class SplashActivity extends ActionBarActivity {

    private ImageView splash_image;


    Handler handler=new Handler();
    private FrameLayout fragment;
    public  boolean isFirst;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        splash_image = (ImageView) findViewById(R.id.splash_image);
        fragment= (FrameLayout) findViewById(R.id.splash);

        //开启一个线程来变化图片的加载时间
        new Thread(
                new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            fragment.setBackgroundResource(R.drawable.init_bg);
                            splash_image.setBackgroundResource(R.drawable.init_logo);
                        }
                    });
                    Thread.sleep(2000);

                    //用handler的post()方法,是在主线程中运行的
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            fragment.setBackgroundResource(R.drawable.init_model);
                            //隐藏影响效果的组件
                            splash_image.setVisibility(View.GONE);
                            //使用动画工具加载动画
                            Animation animation1= AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splash_anim);
                            //启动一个动画
                            fragment.startAnimation(animation1);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                overridePendingTransition(R.anim.splash_alpha_in,R.anim.splash_alpha_out);
                init();
                if(!isFirst){
                    goGuide();
                }else
                {
                    goHome();
                }

            }
        }).start();



    }

    private void goHome() {
        Intent i=new Intent(this,HomeActivity.class);
        startActivity(i);
    }

    private void goGuide() {
        Intent intent = new Intent();
        intent.setClass(this, GuideActivity.class);
        startActivity(intent);
    }
    void init(){
        sharedPreferences = getSharedPreferences("First", MODE_PRIVATE);
        isFirst=sharedPreferences.getBoolean("isFirst",false);

    }


    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
