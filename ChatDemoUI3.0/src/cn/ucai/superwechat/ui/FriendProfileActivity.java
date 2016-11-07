package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
    }

    @OnClick(R.id.friends_send)
    public void onClick() {
    }
}
