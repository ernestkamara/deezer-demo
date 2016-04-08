package com.ernestkamara.deezersample.adapters.listerners;

/**
 * @author Ernest Saidu Kamara
 * @since 08/04/16.
 */
public interface AdapterDelegate {
    /**
     * Called when an Item clicked
     * @param object The Object clicked
     */
    void onItemClick(Object object);

    /**
     * Called when All items are loaded by the Adapter
     */
    void onItemsLoadingComplete();

    /**
     * Called when there is an error loading data to the Adapter
     * @param error
     */
    void onItemsLoadingFailed(String error);
}
