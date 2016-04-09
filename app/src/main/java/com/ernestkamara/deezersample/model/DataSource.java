package com.ernestkamara.deezersample.model;

import android.content.Context;
import android.os.Handler;

import com.ernestkamara.deezersample.ApiRequestFactory;
import com.ernestkamara.deezersample.DeezerApplication;

import java.lang.ref.WeakReference;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public abstract class DataSource<T> {
    private Handler mDefaultHandler;
    protected WeakReference<Delegate> mDelegate;
    protected ApiRequestFactory mApiRequestFactory;

    public DataSource(Context context) {
        DeezerApplication applicationContext = (DeezerApplication) context.getApplicationContext();
        mDefaultHandler = applicationContext.getDefaultHandler();
        mApiRequestFactory = applicationContext.getApiRequestFactory();
    }

    /**
     * Get the total number of items in the data set list.
     *
     * @return  The total number of items.
     */
    public abstract int getItemCount();


    /**
     * Get the object of type T at a give position in thr data set list
     *
     * @param position Position of item to get (0 indexed).
     * @return Item at position.
     */
    public abstract T getItem(int position);

    /**
     * Set the listener for changing in the data set
     * @param delegate
     */
    public final void setDelegate(Delegate delegate) {
        this.mDelegate = new WeakReference<>(delegate);
    }


    /**
     * Notifies delegate of data set changing.
     */
    protected final void notifyDataLoaded() {
        final Delegate delegate = mDelegate.get();
        if (delegate != null) {
            mDefaultHandler.post(new Runnable() {
                @Override
                public void run() {
                    delegate.notifyDataLoaded();
                }
            });
        }
    }

    /**
     * Notifies delegate of errors when loading data
     */
    protected final void notifyError(final String error) {
        final Delegate delegate = mDelegate.get();
        if (delegate != null) {
            mDefaultHandler.post(new Runnable() {
                @Override
                public void run() {
                    delegate.notifyError(error);
                }
            });
        }
    }

    /**
     * Load data to the data set
     */
    public abstract void fetchData();

    /**
     * Interface for listening to changes in the data set.
     */
    public interface Delegate {
        /**
         * Called when some data has changed in the data set.
         *
         */
        void notifyDataLoaded();

        /**
         * Called when a request to load data has failed.
         */
        void notifyError(String error);
    }
}
