package com.ernestkamara.deezersample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.deezer.sdk.model.Artist;
import com.ernestkamara.deezersample.R;

import com.ernestkamara.deezersample.adapters.FilterableArtistAdapter;
import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.ernestkamara.deezersample.model.FilterableArtistDataSource;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, AdapterDelegate {
    @Bind(R.id.filterable_list)ListView mListView;
    @Bind(R.id.empty_state_view) RelativeLayout mContainerLayout;

    private FilterableArtistAdapter artistAdapter;
    private FilterableArtistDataSource dataSource;
    private MenuItem mSearchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataSource = new FilterableArtistDataSource(this);
        artistAdapter = new FilterableArtistAdapter(this, dataSource, this);
        mListView.setAdapter(artistAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mSearchMenuItem = menu.findItem(R.id.action_search);

        mSearchMenuItem.setEnabled(true);

        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setFocusable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.equals("")) {
            showEmptyView();
            return false;
        } else {
            dataSource.doSearch(newText);
            return true;
        }
    }

    public void onSearch(String query) {
        dataSource.doSearch(query);
        mListView.setVisibility(View.VISIBLE);
        mContainerLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Object object) {
        Intent intent = new Intent(this, ArtistAlbumsActivity.class);
        intent.putExtra(ArtistAlbumsActivity.EXTRA_ARTIST, (Artist)object);
        startActivity(intent);
    }

    @Override
    public void onItemsLoadingComplete() {
        mListView.setVisibility(View.VISIBLE);
        mContainerLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemsLoadingFailed(String error) {
        mListView.setVisibility(View.GONE);
        mContainerLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.empty_state_view)
    public void requestSearch(){
        mSearchMenuItem.expandActionView();
        hideEmptyView();
    }

    private void hideEmptyView(){
        mListView.setVisibility(View.VISIBLE);
        mContainerLayout.setVisibility(View.GONE);
    }

    private void showEmptyView(){
        mListView.setVisibility(View.GONE);
        mContainerLayout.setVisibility(View.VISIBLE);
    }
}
