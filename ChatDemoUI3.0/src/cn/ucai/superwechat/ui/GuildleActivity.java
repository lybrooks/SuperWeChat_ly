package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class GuildleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guildle);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_login, R.id.img_regester})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_login:
                MFGT.gotologin(this);
                break;
            case R.id.img_regester:
                MFGT.gotoRegister(this);
                break;
        }
    }
}
