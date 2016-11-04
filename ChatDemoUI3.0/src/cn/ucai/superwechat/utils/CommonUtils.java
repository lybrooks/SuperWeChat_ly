package cn.ucai.superwechat.utils;

import android.widget.Toast;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWechatApplication;


public class CommonUtils {
    public static void showLongToast(String msg){
        Toast.makeText(SuperWechatApplication.applicationContext,msg,Toast.LENGTH_LONG).show();
    }
    public static void showShortToast(String msg){
        Toast.makeText(SuperWechatApplication.applicationContext,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(int rId){
        showLongToast(SuperWechatApplication.applicationContext.getString(rId));
    }
    public static void showShortToast(int rId){
        showShortToast(SuperWechatApplication.applicationContext.getString(rId));
    }
    public static void  showMsgShortToast(int msgId){
        if(msgId>0){
            showShortToast(SuperWechatApplication.getInstance().getResources()
                    .getIdentifier(I.MSG_PREFIX_MSG,"string",SuperWechatApplication.getInstance().getPackageName()));
        }else {
            showShortToast(R.string.msg_1);
        }

    }
}
