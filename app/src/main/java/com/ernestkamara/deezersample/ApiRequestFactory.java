package com.ernestkamara.deezersample;

import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;

/**
 * @author Ernest Saidu Kamara
 * @since 07/04/16.
 */
public class ApiRequestFactory {
    protected DeezerConnect mDeezerConnect;

    public ApiRequestFactory(DeezerConnect deezerConnect) {
        mDeezerConnect = deezerConnect;
    }

    /**
     * Search the Deezer Api for Artists matches a given queryString
     * @param queryString Query string of the Artist to search for
     * @param callback RequestCallback to forwarding the request
     */
    public void newArtistRequest(String queryString, final RequestCallback callback){
        DeezerRequest request = DeezerRequestFactory.requestSearchArtists(queryString);
        makeRequest(request, callback);
    }

    /**
     * Search the Deezer Api for all Albums associated to an Artist with artistId
     * @param artistId Artist id to search for
     * @param callback RequestCallback to forwarding the request
     */
    public void newArtistAlbumRequest(long artistId, final RequestCallback callback){
        DeezerRequest request = DeezerRequestFactory.requestArtistAlbums(artistId);
        makeRequest(request, callback);
    }

    /**
     * Search the Deezer Api for all Tracks associated to an Albums with albumId
     * @param albumId Album id to to search for
     * @param callback RequestCallback to forwarding the request
     */
    public void newAlbumTracksRequest(long albumId, final RequestCallback callback){
        DeezerRequest request = DeezerRequestFactory.requestAlbumTracks(albumId);
        makeRequest(request, callback);
    }


    /**
     *  Make and send Deezer Api request
     * @param request DeezerRequest to send
     * @param callback RequestCallback to forwarding the request
     */
    private void makeRequest(DeezerRequest request, final RequestCallback callback) {
        mDeezerConnect.requestAsync(request, new JsonRequestListener() {
            @Override
            public void onResult(Object result, Object o1) {
                callback.onRequestSuccess(result);
            }

            @Override
            public void onUnparsedResult(String s, Object o) {

            }

            @Override
            public void onException(Exception e, Object o) {
                callback.onRequestFailed(e);
            }
        });
    }

    /**
     * Interface for forwarding Deezer API request result
     */
    public interface RequestCallback{
        /**
         * Forwards Deezer api request result if complete with success
         * @param result The request result
         */
        void onRequestSuccess(Object result);

        /**
         * Forwards Deezer api request exception if complete with failure
         * @param exception
         */
        void onRequestFailed(Exception exception);
    }
}
