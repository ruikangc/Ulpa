/*
 *This is the interface if the APP needs to check whether message has been received by API
 * by reading the feedback message from API
 */

package com.example.testing;

/**
 * Created by CRK on 2017/10/26.
 */

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}