package mahdiyar.ir.marketpanel.core;

import android.app.Application;
import android.content.Intent;
import android.view.ViewGroup;

import mahdiyar.ir.loadershow.LoaderShow;
import mahdiyar.ir.marketpanel.R;
import mahdiyar.ir.marketpanel.core.api.PanelService;
import mahdiyar.ir.marketpanel.core.api.ServiceGenerator;
import mahdiyar.ir.marketpanel.core.api.model.UserInfo;
import mahdiyar.ir.marketpanel.ui.MenuActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Mahdiyar on 12/2/2016.
 */

public class ApplicationLoader extends Application {
    public static PanelService api;
    public static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSans_Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        api = ServiceGenerator.createService(PanelService.class);
    }

    public static void loadUserInfo() {
        // Show loader gear
        Call<UserInfo> userInfoCall = ApplicationLoader.api.getUserInfo(7);
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.code() == 200) {
                    ApplicationLoader.userInfo = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

}
