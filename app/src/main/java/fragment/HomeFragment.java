package fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import autoview.MyGridView;
import autoview.MyScrollView;
import datas.Goodsclass;
import timor.timor.R;


public class HomeFragment extends Fragment implements MyScrollView.onScrollViewListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
private View mybar;
private ViewPager viewpager;
private MyScrollView myScrollView;
private LinearLayout linearLayout;
private BitmapUtils bitmapu;
private HttpUtils httpUtils;
private ArrayList<ImageView> imageviews;
private TopViewpagerAdapter adapter;
private MyGridView myGridView;
private Gridviewadapter gridviewadapter;
private RadioGroup radioGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        imageviews=new ArrayList<ImageView>();
        goods=new ArrayList<Goodsclass>();
        myScrollView= (MyScrollView) view.findViewById(R.id.homescrollview);
        myScrollView.setListener(this);
        myScrollView.smoothScrollTo(0, 0);
        mybar=view.findViewById(R.id.mybar);
        myGridView= (MyGridView) view.findViewById(R.id.homegridview);
        viewpager= (ViewPager) view.findViewById(R.id.homeviewpager);
        radioGroup= (RadioGroup) view.findViewById(R.id.canstopradiogroup);

        adapter=new TopViewpagerAdapter();
        gridviewadapter=new Gridviewadapter();
        viewpager.setAdapter(adapter);
        myGridView.setAdapter(gridviewadapter);
        linearLayout= (LinearLayout) view.findViewById(R.id.buttoncontainer);
        bitmapu=new BitmapUtils(getActivity());
        httpUtils=new HttpUtils();


        getViewpagerAdte();
        getHScrolldate();
        getGridviewadte();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onScroll(MyScrollView myScrollView,int t) {
        int heigt=viewpager.getHeight()+mybar.getHeight();
        float rio=(float)Math.min(Math.max(t,0),heigt)/heigt;
        int alp=(int)(rio*255);
        mybar.getBackground().setAlpha(alp);
        //Toast.makeText(getActivity(),myScrollView.getScrollX()+"",Toast.LENGTH_SHORT).show();
        if(radioGroup.getTop()==myScrollView.getScrollY()){
            radioGroup.setVisibility(View.INVISIBLE);
        }
    }
//viewpager适配器
    class TopViewpagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageview=new ImageView(getActivity());
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageviews.add(imageview);
            container.addView(imageview);
            return imageview;
        }
    }
private ArrayList<Goodsclass> goods;
//gridview适配器
    class Gridviewadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return goods.size();
        }

        @Override
        public Goodsclass getItem(int position) {
            return goods.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=LayoutInflater.from(getActivity()).inflate(R.layout.homemygridview,null);
                holder.imageView= (ImageView) convertView.findViewById(R.id.gridimg);
                holder.textView1= (TextView) convertView.findViewById(R.id.xianjia);
                holder.textView2= (TextView) convertView.findViewById(R.id.yuanjia);
                holder.textView3= (TextView) convertView.findViewById(R.id.zhekuo);
                holder.textView4= (TextView) convertView.findViewById(R.id.gridtitle);
                convertView.setTag(holder);
            }
            else {
                holder= (ViewHolder) convertView.getTag();
            }
            Goodsclass goodsclass=goods.get(position);
            bitmapu.display(holder.imageView,goodsclass.img);
            holder.textView1.setText("¥" + goodsclass.newprice + " ");
            holder.textView2.setText("¥"+goodsclass.oldprice+"");
            holder.textView3.setText(goodsclass.discount+"折");
            holder.textView4.setText(goodsclass.title);
            return convertView;
        }
        class ViewHolder{
            ImageView imageView;
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
        }
    }
    //viewpager数据
    private void getViewpagerAdte(){
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.tao.maxia.cc/goods/?today=true&sort=sellnum&psize=20&pnum=1", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONObject jsonob=new JSONObject(responseInfo.result);
                    JSONArray jsona=jsonob.getJSONArray("goods");
                    for (int i = 0; i <2; i++) {
                        JSONObject jsonbb=jsona.getJSONObject(i);
                        JSONObject jsonimag=jsonbb.getJSONObject("imgs");
                        String img=jsonimag.getString("big");
                        bitmapu.display(imageviews.get(i), img);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(),"fff",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //横向scrollview数据
    private void getHScrolldate(){
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.tao.maxia.cc/v2/category/", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                try {
                    JSONArray jsonArray=new JSONArray(responseInfo.result);
                    for (int i = 1; i <jsonArray.length(); i++) {
                        JSONObject jsonob=jsonArray.getJSONObject(i);
                        String lable=jsonob.getString("category_name");
                        View view=LayoutInflater.from(getActivity()).inflate(R.layout.imageandtext, null);
                        ImageView imag= (ImageView) view.findViewById(R.id.imageup);
                        TextView text= (TextView) view.findViewById(R.id.textdown);
                        bitmapu.display(imag,jsonob.getString("icon"));
                        text.setText(lable);
                        linearLayout.addView(view);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }

    //gridview数据
    private void getGridviewadte(){
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://api.tao.maxia.cc/v2/index/?sort=sellnum&psize=20&pnum=1", new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject jsonob=new JSONObject(responseInfo.result);
                    JSONArray jsona=jsonob.getJSONArray("goods");
                    for (int i = 0; i <jsona.length(); i++) {
                        JSONObject jsonbb=jsona.getJSONObject(i);
                        JSONObject jsonimag=jsonbb.getJSONObject("imgs");
                        Goodsclass good=new Goodsclass();
                        good.img=jsonimag.getString("big");
                        good.newprice=jsonbb.getString("now_price");
                        good.oldprice=jsonbb.getString("origin_price");
                        good.discount=jsonbb.getString("discount");
                        good.title=jsonbb.getString("title");
                        goods.add(good);
                    }
                    gridviewadapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }
}
