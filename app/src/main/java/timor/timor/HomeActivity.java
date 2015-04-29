package timor.timor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import fragment.HomeFragment;
import fragment.MessageFragment;
import fragment.PersonalCenterFragment;
import fragment.SearchFragment;


public class HomeActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener
{

    private ArrayList<Fragment> fragments;
    private RadioGroup homePagerRadioGroup;
    private ViewPager homePageViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //隐藏actionbar
        getSupportActionBar().hide();

        init();



    }

    //home ViewPage
    public void init(){
        homePagerRadioGroup = (RadioGroup) findViewById(R.id.homePagerRadioGroup);
        ((RadioButton) homePagerRadioGroup.getChildAt(0)).setChecked(true);
        homePagerRadioGroup.setOnCheckedChangeListener(this);

        homePageViewPager = (ViewPager) findViewById(R.id.homePageViewPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new SearchFragment());
        fragments.add(new MessageFragment());
        fragments.add(new PersonalCenterFragment());
        MyhomePageViewPagerAdapter myhomePageViewPagerAdapter = new MyhomePageViewPagerAdapter(getSupportFragmentManager());
        homePageViewPager.setAdapter(myhomePageViewPagerAdapter);
        homePageViewPager.setOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //关联home的Radiobutton
    @Override
    public void onPageSelected(int position) {
        if(position<2){
            RadioButton page=(RadioButton)homePagerRadioGroup.getChildAt(position);
            page.setChecked(true);
        }else {
            RadioButton page=(RadioButton)homePagerRadioGroup.getChildAt(position+1);
            page.setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //关联home的viewpager
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.homeradioButton0:
                homePageViewPager.setCurrentItem(0);
                break;
            case R.id.homeradioButton1:
                homePageViewPager.setCurrentItem(1);
                break;
            case R.id.homeradioButton2:
                homePageViewPager.setCurrentItem(2);
                break;
            case R.id.homeradioButton3:
                homePageViewPager.setCurrentItem(3);
                break;
        }
    }

    class MyhomePageViewPagerAdapter extends FragmentPagerAdapter{
        public MyhomePageViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
