package mahdiyar.ir.marketpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import mahdiyar.ir.loadershow.LoaderShow;
import mahdiyar.ir.marketpanel.core.ApplicationLoader;
import mahdiyar.ir.marketpanel.core.api.model.UserInfo;
import mahdiyar.ir.marketpanel.ui.MenuActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main)
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loadUserInfo();
    }

    private void loadUserInfo() {
        // Show loader gear
        LoaderShow.show(viewGroup);

        Call<UserInfo> userInfoCall = ApplicationLoader.api.getUserInfo(7);
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.code() == 200) {
                    ApplicationLoader.userInfo = response.body();
                    LoaderShow.hide((ViewGroup) findViewById(R.id.activity_main));
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }
}
