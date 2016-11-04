package cn.ucai.superwechat.utils;

import android.widget.Toast;

import cn.ucai.superwechat.SuperWechatApplication;


public class CommonUtils {
    public static void showLongToast(String msg){
        Toast.makeText(SuperWechatApplication.getInstance(),msg,Toast.LENGTH_LONG).show();
    }
    public static void showShortToast(String msg){
        Toast.makeText(SuperWechatApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(int rId){
        showLongToast(SuperWechatApplication.getInstance().getString(rId));
    }
    public static void showShortToast(int rId){
        showShortToast(SuperWechatApplication.getInstance().getString(rId));
    }
}
