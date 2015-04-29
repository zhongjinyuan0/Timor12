package datas;


import timor.timor.R;

/**
 * Created by aaa on 15-4-27.
 */
public class DataClass {
//初始化搜素页面的各模块的数据
    public  static String[] girlStreet(){
        String[] s={"连衣裙","半身裙","T恤","针织衫","衬衫","雪纺衫","牛仔裤"
        ,"打底裤","女士内裤","文胸","套装","大码女装","围巾丝巾","腰带腰链","女鞋","女包"
               , "女士钱包"
        };
        return  s;
    }
    public static String[] manStreet(){
        String[] s={"T恤","衬衫","POLO衫","背心","牛仔长裤","休闲裤","工装裤","夹克"
                ,"男士内裤","男士皮带","男鞋","男包","男士钱包","眼镜配饰"
        };
        return  s;
    }

    public static String[] beautShop() {

        String[] s={
            "面部护肤", "时尚彩妆", "身体护理", "香水", "精油芳疗", "假发美容", "个人洗护", "男士护肤"

        } ;
        return  s;
    }
    public static int[] girlimage(){
        int s[]={R.drawable.x50025145, R.drawable.x50025258,R.drawable.x50025783,R.drawable.x50029627
                 ,R.drawable.x50025265,R.drawable.x50025233,R.drawable.x50025227,R.drawable.x50100711,
                R.drawable.x50026191,R.drawable.x50025984,R.drawable.x50025827,R.drawable.x50102773,
                R.drawable.x50548037,R.drawable.x50522046,R.drawable.x50025829,R.drawable.x51052003,
                R.drawable.x50067127
        };

        return s;
    }

    public static int[] manimage(){
        int s[]={R.drawable.x50105438,R.drawable.x50037921,R.drawable.x50037920,R.drawable.x50074114,
                R.drawable.x51050005,R.drawable.x50026259,R.drawable.x50776001,R.drawable.x50026329,
                R.drawable.x50026192,R.drawable.x50540046,R.drawable.x50026637,R.drawable.x51042006,
                R.drawable.x50067127,R.drawable.x50023064
        };
        return s;

    }






}
