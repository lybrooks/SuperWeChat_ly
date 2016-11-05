package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.Constant;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/5.
 */
public class ProfileFragment extends Fragment {

    @Bind(R.id.profile_iv_userAvatar)
    ImageView profileIvUserAvatar;
    @Bind(R.id.profile_tv_nick)
    TextView profileTvNick;
    @Bind(R.id.profile_tv_username)
    TextView profileTvUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        setUserInfo();
    }

    private void setUserInfo() {
        EaseUserUtils.setCurrentAppUserAvatar(getActivity(),profileIvUserAvatar);
        EaseUserUtils.setCurrentAppUserNick(profileTvNick);
        EaseUserUtils.setCurremtAppUserName(profileTvUsername);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.profile_tv_money, R.id.profile_tv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_tv_money:
                RedPacketUtil.startChangeActivity(getActivity());
                break;
            case R.id.profile_tv_setting:
                MFGT.gotoSetting(getContext());
                break;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(((MainActivity)getActivity()).isConflict){
            outState.putBoolean("isConflict", true);
        }else if(((MainActivity)getActivity()).getCurrentAccountRemoved()){
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }
}
