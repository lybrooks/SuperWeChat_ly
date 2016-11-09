package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import com.hyphenate.easeui.domain.User;


import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.ChatActivity;
import cn.ucai.superwechat.ui.FriendProfileActivity;
import cn.ucai.superwechat.ui.LoginActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.RegisterActivity;
import cn.ucai.superwechat.ui.SendMessage_Activity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.VideoCallActivity;

public class MFGT {
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public static void gotoMainActivity(Activity context) {
        startActivity(context, MainActivity.class);
    }

    public static void startActivity(Activity context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void startActivity(Activity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    private static void startActivityForResult(Activity mContext, Intent intent, int requestCode) {
        mContext.startActivityForResult(intent, requestCode);
        mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void gotologin(Activity context) {
        startActivity(context, LoginActivity.class);
    }

    public static void gotoRegister(Activity context) {
        startActivity(context, RegisterActivity.class);
    }


    public static void gotoSetting(Context context) {
        startActivity((Activity) context, SettingsActivity.class);
    }

    public static void gotoAddContact(Activity context) {
        startActivity(context, AddContactActivity.class);
    }

    public static void gotoFriendProfile(Activity context, User user) {
        Intent intent = new Intent(context, FriendProfileActivity.class);
        intent.putExtra(I.User.USER_NAME, user);
        startActivity(context,intent);
    }

    public static void gotoSendMessage(Activity context, String mUserName) {
        Intent intent = new Intent(context, SendMessage_Activity.class);
        intent.putExtra(I.User.USER_NAME, mUserName);
        startActivity(context,intent);
    }
    public static void gotoChat(Activity context, String mUserName) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("userId", mUserName);
        startActivity(context,intent);
    }

    public static void gotoVideo(Activity context,String username,boolean a) {
        Intent intent = new Intent(context,VideoCallActivity.class );
        intent.putExtra("username",username);
        intent.putExtra("isComingCall",a);
        startActivity(context,intent);
    }
}
