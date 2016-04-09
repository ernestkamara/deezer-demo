package com.ernestkamara.deezersample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.deezer.sdk.network.connect.DeezerConnect;
import com.ernestkamara.deezersample.model.DataSourceFactory;

import butterknife.ButterKnife;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class DeezerApplication extends Application {
    private ApiRequestFactory mApiRequestFactory;
    private Handler mHandler;
    private DataSourceFactory mDataSourceFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        DeezerConnect deezerConnect = DeezerConnect.forApp(getString(R.string.deezer_app_id))
                                                   .withContext(this)
                                                   .build();
        mApiRequestFactory = new ApiRequestFactory(deezerConnect);
        mHandler = new Handler(Looper.getMainLooper());
        mDataSourceFactory = new DataSourceFactory(this);
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }

    public ApiRequestFactory getApiRequestFactory(){
        return mApiRequestFactory;
    }

    public Handler getDefaultHandler() {
        return mHandler;
    }

    public DataSourceFactory getDataSourceFactory() {
        return mDataSourceFactory;
    }
}
