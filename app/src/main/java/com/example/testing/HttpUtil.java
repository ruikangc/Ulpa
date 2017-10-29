/*
 *This class contains the functions used to send message back to the API via HTTP request, different
 * from class HttpService, which contains functions precess data got from API
 */

package com.example.testing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    //get data from URL link via Http request, another option for the similar one in HtmlService.
    public static void sendGetHttpRequest(final String address, final HttpCallbackListener listener) {

        new Thread(new Runnable() {

            @Override

            public void run() {

                HttpURLConnection connection = null;

                URL url = null;

                try {
                    url = new URL(address);

                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");

                    connection.setConnectTimeout(8000);

                    connection.setReadTimeout(8000);

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer response = new StringBuffer();

                    String line;

                    while ((line = reader.readLine()) != null) {

                        response.append(line);

                    }

                    if (listener != null) {

                        listener.onFinish(response.toString());

                    }

                } catch (Exception e) {

                    listener.onError(e);

                }

            }

        }).start();

    }

    //send message by HTTP request to API
    public static void sendPostHttpRequest(final String address,final String jsonBody,final HttpCallbackListener listener){

        new Thread(new Runnable() {

            @Override

            public void run() {

                HttpURLConnection connection=null;

                URL url=null;

                try {

                    url = new URL(address);

                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("POST");

                    connection.setConnectTimeout(8000);

                    connection.setReadTimeout(8000);

                    connection.setRequestProperty("Content-Type","application/json");

                    OutputStream outputStream=connection.getOutputStream();

                    BufferedWriter requestBody=new BufferedWriter(new OutputStreamWriter(outputStream));

                    requestBody.write(jsonBody);

                    requestBody.flush();

                    requestBody.close();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer response = new StringBuffer();

                    String line;

                    while ((line = reader.readLine()) != null) {

                        response.append(line);

                    }

                    if (listener != null) {

                        listener.onFinish(response.toString());

                    }

                } catch (Exception e) {

                    listener.onError(e);

                }

            }

        }).start();

    }

}