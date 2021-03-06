package com.hyphenate.easeui.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.controller.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.domain.Group;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.R;

public class EaseUserUtils {

    static EaseUserProfileProvider userProvider;

    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }

    /**
     * get EaseUser according username
     *
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username) {
        if (userProvider != null)
            return userProvider.getUser(username);

        return null;
    }

    public static User getAppUserInfo(String username) {
        if (userProvider != null)
            return userProvider.getAppUser(username);

        return null;
    }
    public static User getCurrentAppUserInfo() {
        String usernames = EMClient.getInstance().getCurrentUser();
        if (userProvider != null)
            return userProvider.getAppUser(usernames);

        return null;
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        EaseUser user = getUserInfo(username);
        if (user != null && user.getAvatar() != null) {
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_default_avatar).into(imageView);
            }
        } else {
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            EaseUser user = getUserInfo(username);
            if (user != null && user.getNick() != null) {
                textView.setText(user.getNick());
            } else {
                textView.setText(username);
            }
        }
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setAppUserAvatar(Context context, String username, ImageView imageView) {
        User user = getAppUserInfo(username);
        if(user==null){
            user = new User(username);
        }
        if (user != null && user.getAvatar() != null) {
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.default_hd_avatar).into(imageView);
            }
        } else {
            Glide.with(context).load(R.drawable.default_hd_avatar).into(imageView);
        }
    }

    /**
     * set user avatar
     *
     * @param
     */
    public static void setAppUserPathAvatar(Context context, String path, ImageView imageView) {

        if (path!= null) {
            try {
                int avatarResId = Integer.parseInt(path);
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.default_hd_avatar).into(imageView);
            }
        } else {
            Glide.with(context).load(R.drawable.default_hd_avatar).into(imageView);
        }
    }


    /**
     * set app user's nickname
     */
    public static void setAppUserNick(String username, TextView textView) {
        if (textView != null) {
            User user = getAppUserInfo(username);
            if (user != null && user.getMUserNick() != null) {
                textView.setText(user.getMUserNick());
            } else {
                textView.setText(username);
            }
        }
    }


    public static void setCurrentAppUserAvatar(FragmentActivity activity, ImageView imageview) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserAvatar(activity, username, imageview);
    }

    public static void setCurrentAppUserNick(TextView textview) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserNick(username, textview);
    }

    public static void setCurremtAppUserName(TextView textview) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserName("微信号：",username, textview);
    }
    public static void setCurremtAppUserNameNo(TextView textview) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserName("",username, textview);
    }

    private static void setAppUserName(String suffix,String username, TextView textview) {
        textview.setText(suffix+username);
    }

    public static void setAppUserName(String mUserName, TextView textview) {
        textview.setText("微信号："+mUserName);
    }


    public static void setAppGroupAvatar(Context context, String hixd, ImageView imageView) {
        if (hixd!= null) {
            try {
                int avatarResId = Integer.parseInt(Group.getAvatar(hixd));
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(Group.getAvatar(hixd)).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_group_icon).into(imageView);
            }
        } else {
            Glide.with(context).load(R.drawable.ease_group_icon).into(imageView);
        }
    }
}
