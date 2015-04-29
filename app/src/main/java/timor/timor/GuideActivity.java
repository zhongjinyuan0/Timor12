package timor.timor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class GuideActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager guide_viewpager;
    private ArrayList<View> views;
    private RadioGroup guide_radiogroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getSupportActionBar().hide();
        guide_viewpager = (ViewPager) findViewById(R.id.guide_viewpager);
        guide_radiogroup = (RadioGroup) findViewById(R.id.guide_radiogroup);

//      创建集合存放要加载的布局

        views =new ArrayList<View>();
        views.add(getLayoutInflater().inflate(R.layout.item_guide_one,null));
        views.add(getLayoutInflater().inflate(R.layout.item_guide_two,null));
        views.add(getLayoutInflater().inflate(R.layout.item_guide_three,null));
        views.add(getLayoutInflater().inflate(R.layout.item_guide_four,null));
        views.add(getLayoutInflater().inflate(R.layout.item_guide_five,null));

        guide_viewpager.setAdapter(new MyPagerAdapter());
        guide_viewpager.setCurrentItem(0);
        guide_radiogroup.setOnCheckedChangeListener(this);
        guide_viewpager.setOnPageChangeListener(this);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.guide_btn1:
                guide_viewpager.setCurrentItem(0);
                break;
            case R.id.guide_btn2:
                guide_viewpager.setCurrentItem(1);
                break;
            case R.id.guide_btn3:
                guide_viewpager.setCurrentItem(2);
                break;
            case R.id.guide_btn4:
                guide_viewpager.setCurrentItem(3);
                break;
            case R.id.guide_btn5:
                guide_viewpager.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        RadioButton btn= (RadioButton) guide_radiogroup.getChildAt(i);
        btn.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            if(views!=null){
                return views.size();
            }
            return 0;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            View view=views.get(position);
            container.addView(view);
            if(position==views.size()-1){
                views.get(position).findViewById(R.id.guide_image).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sf = getSharedPreferences("First", MODE_PRIVATE);
                        SharedPreferences.Editor editor=sf.edit();
                        editor.putBoolean("isFirst",true);
                        editor.commit();
                        goHome();
                    }
                });
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

    }
        public void goHome(){
            Intent i= new Intent(GuideActivity.this,HomeActivity.class);
            startActivity(i);

        }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
