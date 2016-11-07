package cn.ucai.superwechat.data;
import android.content.Context;
import java.io.File;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.utils.MD5;

/**
 * Created by Administrator on 2016/10/17.
 */
public class NetDao {

    /**
     * 注册请求
     */
    public static void UserRegister(Context context, String username, String nick, String password, OkHttpUtils.OnCompleteListener<Result> litener) {
        OkHttpUtils<Result> uitls = new OkHttpUtils<>(context);
        uitls.setRequestUrl(I.REQUEST_REGISTER)
                .post()
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.NICK, nick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(Result.class)
                .execute(litener);
    }

    /**
     * 取消注册请求
     */
    public static void UNuserRegister(Context context, String username,OkHttpUtils.OnCompleteListener<Result> litener) {
        OkHttpUtils<Result> uitls = new OkHttpUtils<>(context);
        uitls.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(Result.class)
                .execute(litener);
    }

    /**
     * 登录请求
     */
    public static void Login(Context context, String username, String password, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 修改用户昵称
     */
    public static void updateNick(Context context, String username, String nick, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.NICK, nick)
                .targetClass(String.class)
                .execute(listener);

    }
    /**
     * 更改头像
     */
    public static void updateAvatar(Context context, String muserName, File file, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID, muserName)
                .addParam(I.AVATAR_TYPE, I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 通过用户帐号查询用户信息
     */
    public static void syncUserInfo(Context context, String username, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }
    /**查找用户信息*/
    public static void searchUser(Context context, String username,OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }



}
