package cn.ucai.superwechat.data;

import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;

import java.io.File;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.SuperWechatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.utils.L;
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
    public static void UNuserRegister(Context context, String username, OkHttpUtils.OnCompleteListener<Result> litener) {
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

    /**
     * 查找用户信息
     */
    public static void searchUser(Context context, String username, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 添加好友
     */
    public static void AddContact(Context context, String username, String cusername, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME, username)
                .addParam(I.Contact.CU_NAME, cusername)
                .targetClass(String.class)
                .execute(listener);
    }


    /**
     * 删除好友
     */
    public static void deteUser(Context context, String mUserName, String cusername, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME, mUserName)
                .addParam(I.Contact.CU_NAME, cusername)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 下载好友列表
     */
    public static void loadContact(Context context, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME, EMClient.getInstance().getCurrentUser())
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 创建群组，没有头像
     */
    public static void createGroup(Context context, EMGroup emGroup, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, emGroup.getGroupId())
                .addParam(I.Group.NAME, emGroup.getGroupName())
                .addParam(I.Group.DESCRIPTION, emGroup.getDescription())
                .addParam(I.Group.OWNER, emGroup.getOwner())
                .addParam(I.Group.IS_PUBLIC, String.valueOf(emGroup.isPublic()))
                .addParam(I.Group.ALLOW_INVITES, String.valueOf(emGroup.isAllowInvites()))
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    /**
     * 创建群组，有头像
     */
    public static void createGroup(Context context, EMGroup emGroup, File file, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, emGroup.getGroupId())
                .addParam(I.Group.NAME, emGroup.getGroupName())
                .addParam(I.Group.DESCRIPTION, emGroup.getDescription())
                .addParam(I.Group.OWNER, emGroup.getOwner())
                .addParam(I.Group.IS_PUBLIC, String.valueOf(emGroup.isPublic()))
                .addParam(I.Group.ALLOW_INVITES, String.valueOf(emGroup.isAllowInvites()))
                .targetClass(String.class)
                .addFile2(file)
                .post()
                .execute(listener);
    }

    /**
     * 添加群组成员
     */
    public static void AddGroupMenber(Context context, EMGroup emGroup, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        String memberArr = "";
        for (String m : emGroup.getMembers()) {
            if (!m.equals(SuperWechatHelper.getInstance().getCurrentUsernName())) {
                memberArr += m + ",";
            }
        }
        L.e("AddGroupMenber" + memberArr);
        memberArr = memberArr.substring(0, memberArr.length() - 1);
        utils.setRequestUrl(I.REQUEST_ADD_GROUP_MEMBERS)
                .addParam(I.Member.GROUP_HX_ID, emGroup.getGroupId())
                .addParam(I.Member.USER_NAME, memberArr)
                .targetClass(String.class)
                .execute(listener);
    }

    /**
     * 删除单个群组成员
     */
    public static void deleteMenber(Context context, String groupId, String username, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_GROUP_MEMBER)
                .addParam(I.Member.GROUP_HX_ID, groupId)
                .addParam(I.Member.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);

    }
}
