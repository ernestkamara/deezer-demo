package com.ernestkamara.deezersample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.deezer.sdk.network.connect.DeezerConnect;

import butterknife.ButterKnife;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class DeezerApplication extends Application {
    private ApiRequestFactory mApiRequestFactory;
    private Handler mHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        DeezerConnect deezerConnect = DeezerConnect.forApp(getString(R.string.deezer_app_id))
                                                   .withContext(this)
                                                   .build();
        mApiRequestFactory = new ApiRequestFactory(deezerConnect);
        mHandler = new Handler(Looper.getMainLooper());
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }

    public ApiRequestFactory getApiRequestFactory(){
        return mApiRequestFactory;
    }

    public Handler getDefaultHandler() {
        return mHandler;
    }
}
