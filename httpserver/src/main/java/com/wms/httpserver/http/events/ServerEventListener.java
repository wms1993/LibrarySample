package com.wms.httpserver.http.events;

public interface ServerEventListener {

    /**
     *
     * A resource was successfully served
     *
     *
     * @param pResource
     */
    void onRequestServed(String pResource);

    /**
     *
     * An error was encountered when trying to serve a resource
     *
     *
     * @param pResource
     */
    void onRequestError(String pResource);

    /**
     *
     * The server is ready to start handling requests.
     *
     */
    void onServerReady(String pAddress, int pPort);

}
