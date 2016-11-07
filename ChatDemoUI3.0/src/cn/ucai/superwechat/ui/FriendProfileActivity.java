package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

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

    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user == null) {
            MFGT.finish(this);
        }
        initView();
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(R.string.profile_title);
        friendsSend.setVisibility(View.VISIBLE);
        friendsMoive.setVisibility(View.VISIBLE);

        setUserInfo();

    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), profileIvUserAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), profileTvNick);
        EaseUserUtils.setAppUserName(user.getMUserName(), profileTvUsername);
    }

    @OnClick(R.id.friends_send)
    public void onClick() {
    }
}
