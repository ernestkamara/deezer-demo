package com.ernestkamara.deezersample.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ernestkamara.deezersample.adapters.listerners.AdapterDelegate;
import butterknife.ButterKnife;

import java.lang.ref.WeakReference;

/**
 * @author Ernest Saidu Kamara
 * @since 08/04/16.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected final WeakReference<AdapterDelegate> mAdapterDelegate;

    public BaseViewHolder(View itemView, AdapterDelegate adapterDelegate) {
        super(itemView);
        mAdapterDelegate = new WeakReference<>(adapterDelegate);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {
        AdapterDelegate delegate = mAdapterDelegate.get();
        if (delegate != null) {
            delegate.onItemClick(getItem());
        }
    }

    /**
     * Get the Item tied to the ViewHolder
     * @return
     */
    protected abstract Object getItem();

    /**
     * Bind Item to the ViewHolder
     * @param item Item to bind
     */
    public abstract void bindItem(Object item);

}
