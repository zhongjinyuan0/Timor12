package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import datas.DataClass;
import timor.timor.R;
import timor.timor.SearchFragmentToINActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView gridViewOne;
    private GridView gridViewTwo;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search2, container, false);
        //找到gridview的id,设置数据
        gridViewOne = (GridView) view.findViewById(R.id.search_gridview_one);
        gridViewTwo = (GridView) view.findViewById(R.id.search_gridview_two);

        SimpleAdapter adapter = girlstreet(DataClass.girlStreet(),DataClass.girlimage());
        SimpleAdapter adapter2 = girlstreet(DataClass.manStreet(),DataClass.manimage());
        gridViewOne.setAdapter(adapter);
        gridViewTwo.setAdapter(adapter2);
        //给gridview设置点击事件
        gridViewOne.setOnItemClickListener(this);
        gridViewTwo.setOnItemClickListener(this);


        return view;
    }

    //girlstreet数据
    public SimpleAdapter girlstreet (String[] strings, int[] girlimage){

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i <strings.length ; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("image",girlimage[i]);
            map.put("text",strings[i]);
            list.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),list,R.layout.item_search_gridview_one,new String[]{"image","text"},
                new int[]{R.id.item_seach_imagedview,R.id.item_seach_textview}
               );
        return adapter;
   }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), SearchFragmentToINActivity.class);
        startActivity(intent);
    }
}
