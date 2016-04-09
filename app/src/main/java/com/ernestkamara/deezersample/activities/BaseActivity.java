package com.ernestkamara.deezersample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ernestkamara.deezersample.DeezerApplication;
import com.ernestkamara.deezersample.R;
import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import com.ernestkamara.deezersample.model.DataSourceFactory;

import butterknife.ButterKnife;

/**
 * @author Ernest Saidu Kamara
 * @since 09/04/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements AdapterDelegate {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(getTitle());
    }

    protected abstract int getLayoutId();

    protected abstract boolean showHomeAsUp();

    private DeezerApplication getDeezerApplication() {
        return (DeezerApplication) super.getApplicationContext();
    }

    protected DataSourceFactory getDataSourceFactory(){
        return getDeezerApplication().getDataSourceFactory();
    }

}
