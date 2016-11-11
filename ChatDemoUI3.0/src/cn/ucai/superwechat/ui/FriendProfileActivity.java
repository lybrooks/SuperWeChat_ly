package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWechatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.data.NetDao;
import cn.ucai.superwechat.data.OkHttpUtils;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.ResultUtils;

public class FriendProfileActivity extends BaseActivity {

    @Bind(R.id.profile_iv_userAvatar)
    ImageView profileIvUserAvatar;
    @Bind(R.id.profile_tv_nick)
    TextView profileTvNick;
    @Bind(R.id.profile_tv_username)
    TextView profileTvUsername;
    @Bind(R.id.friends_send)
    TextView friendsSend;
    @Bind(R.id.friends_moive)
    TextView friendsMoive;
    @Bind(R.id.txt_left)
    TextView txtLeft;
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.txt_title)
    TextView txtTitle;

    @Bind(R.id.friends_addfriends)
    TextView friendsAddfriends;

    String UserName = null;

    User user = null;

    Boolean isFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        ButterKnife.bind(this);
        UserName = getIntent().getStringExtra(I.User.USER_NAME);
        if (UserName == null) {
            MFGT.finish(this);
            return;
        }
        initView();
        user = SuperWechatHelper.getInstance().getAppcontactList().get(UserName);
        if (user == null) {
            isFriend = false;
        } else {
            setUserInfo();
            isFriend = true;
        }
        isFriend(isFriend);
        syncUserInfo();

    }

    private void syncUserInfo() {
        NetDao.syncUserInfo(this, UserName, new OkHttpUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if (result != null && result.isRetMsg()) {
                        user = (User) result.getRetData();
                        if (user != null) {
                            setUserInfo();
                            if(isFriend){
                                SuperWechatHelper.getInstance().saveAppContact(user);
                            }
                        } else {
                            syncfail();
                        }
                    } else {
                        syncfail();
                    }
                } else {
                    syncfail();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void syncfail() {
        MFGT.finish(this);
        return;
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.profile_friend);

    }

    private void isFriend(boolean isFriend) {
        if (isFriend) {
            friendsSend.setVisibility(View.VISIBLE);
            friendsSend.setText(R.string.send_message);
            friendsMoive.setVisibility(View.VISIBLE);
            friendsMoive.setText(R.string.moive_chat);
        } else {
            friendsAddfriends.setVisibility(View.VISIBLE);
        }
    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), profileIvUserAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), profileTvNick);
        EaseUserUtils.setAppUserName(user.getMUserName(), profileTvUsername);
    }


    @OnClick({R.id.img_back, R.id.friends_addfriends, R.id.friends_send, R.id.friends_moive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(this);
                break;
            case R.id.friends_addfriends:
                MFGT.gotoSendMessage(this, user.getMUserName());
                break;
            case R.id.friends_send:
                MFGT.gotoChat(this, user.getMUserName());
                break;
            case R.id.friends_moive:
                if (!EMClient.getInstance().isConnected())
                    Toast.makeText(this, R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
                else {
                    startActivity(new Intent(this, VideoCallActivity.class).putExtra("username", user.getMUserName())
                            .putExtra("isComingCall", false));
                    // videoCallBtn.setEnabled(false);
                }
                break;

        }
    }
}
