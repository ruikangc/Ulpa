/*
*This class is the support class which contains some functions may be used by other classes, and
* most of them are about processing the HTML data
 */

package com.example.testing;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;

public class HtmlService {

    //obtain HTML source string
    public static String getHtml_states(String path) throws Exception {

        URL url = new URL(path);

        //build path with given URL link
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        conn.setConnectTimeout(5 * 1000);

        //obtain the stream data from given URL
        InputStream inStream = conn.getInputStream();

        byte[] data = readInputStream(inStream);

        //change stream data into String
        String html_states = new String(data, "UTF-8");

        System.out.println(html_states);

        return html_states;

    }

    /*redundant one for same function because same functions are used in the back ground
    *process of one Activity, to avoid possible function conflict (not necessary)
    */
    public static String getHtml(String path) throws Exception {

        URL url = new URL(path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        conn.setConnectTimeout(5 * 1000);

        InputStream inStream = conn.getInputStream();

        byte[] data = readInputStream(inStream);

        String html = new String(data, "UTF-8");

        System.out.println(html);

        return html;

    }

    //support function to read input stream
    public static byte[] readInputStream(InputStream inStream) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[2048];

        int len = 0;

        while ((len = inStream.read(buffer)) != -1) {

            outStream.write(buffer, 0, len);

        }

        inStream.close();

        return outStream.toByteArray();

    }

    //used to resolve the data about states, find the available states
    public static ArrayList<String> resolveJsonMessage_states(String jsonMessage) throws Exception {

        ArrayList<String> languagename_cities_pair = new ArrayList<String>();

        if (jsonMessage != null) {

            try {

                //the data will be fetched from JSON String
                JSONObject jsonObject = new JSONObject(jsonMessage);

                JSONArray resultJsonArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < resultJsonArray.length(); i++) {

                    JSONObject jsonItem=new JSONObject();

                    jsonItem = resultJsonArray.getJSONObject(i);

                    String firstItem=jsonItem.getString("state");

                    languagename_cities_pair.add(firstItem);

                }

            } catch (JSONException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

        return languagename_cities_pair;

    }

    //resolve data about available language.
    public static ArrayList<String> resolveJsonMessage_language(String jsonMessage) throws Exception {

        ArrayList<String> languagename_cities_pair = new ArrayList<String>();

        if (jsonMessage != null) {

            try {

                //data fetched from JSON String
                JSONObject jsonObject = new JSONObject(jsonMessage);

                JSONArray resultJsonArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < resultJsonArray.length(); i++) {

                    JSONObject jsonItem=new JSONObject();

                    jsonItem = resultJsonArray.getJSONObject(i);

                    String firstItem=jsonItem.getString("name");

                    languagename_cities_pair.add(firstItem);

                }

            } catch (JSONException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

        return languagename_cities_pair;
    }



    //resolve data of the detail of each language
    public static ArrayList<ArrayList<String>> resolveJsonMessage_language_detail(String jsonMessage) throws Exception {

        ArrayList<ArrayList<String>> language_detail = new ArrayList<ArrayList<String>>();

        if (jsonMessage != null) {

            try {

                JSONObject jsonObject = new JSONObject(jsonMessage);

                JSONArray resultJsonArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < resultJsonArray.length(); i++) {

                    JSONObject jsonItem=new JSONObject();

                    jsonItem = resultJsonArray.getJSONObject(i);

                    String firstItem=jsonItem.getString("name");

                    String secondItem=jsonItem.getString("description");

                    ArrayList<String> temp=new ArrayList<>();

                    temp.add(firstItem);

                    temp.add(secondItem);

                    language_detail.add(temp);

                }

            } catch (JSONException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

        return language_detail;

    }

    //resolve data of the universities in the selected states
    public static ArrayList<String> resolveJsonMessage_university_in_states(String jsonMessage) throws Exception {

        ArrayList<String> state_university_pair = new ArrayList<String>();

        if (jsonMessage != null) {

            try {

                JSONObject jsonObject = new JSONObject(jsonMessage);

                JSONArray resultJsonArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < resultJsonArray.length(); i++) {

                    JSONObject jsonItem=new JSONObject();

                    jsonItem = resultJsonArray.getJSONObject(i);

                    String firstItem=jsonItem.getString("name");

                    state_university_pair.add(firstItem);

                }

            } catch (JSONException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

        return state_university_pair;

    }

    //resolve all the data of the selected states
    public static  ArrayList<ArrayList<String>>  resolveJsonMessage_all_state_data(String jsonMessage) throws Exception {

        ArrayList<ArrayList<String>> temp_data_all_data_in_state=new ArrayList<ArrayList<String>>();

        if (jsonMessage != null) {

            try {

                JSONObject jsonObject = new JSONObject(jsonMessage);

                JSONArray resultJsonArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < resultJsonArray.length(); i++) {

                    ArrayList<String> temp_data_one_subjectcode=new ArrayList<String>();

                    JSONObject jsonItem=new JSONObject();

                    jsonItem = resultJsonArray.getJSONObject(i);

                    //find the detail information of subjects and store them
                    String language_name_Item=jsonItem.getString("language_name");

                    temp_data_one_subjectcode.add(language_name_Item);

                    String state_Item=jsonItem.getString("state");

                    temp_data_one_subjectcode.add(state_Item);

                    String university_Item=jsonItem.getString("university_name");

                    temp_data_one_subjectcode.add(university_Item);

                    String campus_online_Item=jsonItem.getString("study_choice");

                    temp_data_one_subjectcode.add(campus_online_Item);

                    String intensive_regular_Item=jsonItem.getString("intensity");

                    temp_data_one_subjectcode.add(intensive_regular_Item);

                    String subject_name_Item=jsonItem.getString("title");

                    temp_data_one_subjectcode.add(subject_name_Item);

                    String subject_code_Item=jsonItem.getString("code");

                    temp_data_one_subjectcode.add(subject_code_Item);

                    String next_offered_Item=jsonItem.getString("next_offered");

                    temp_data_one_subjectcode.add(next_offered_Item);

                    String pre_requisite_language_Item=jsonItem.getString("prerequisite");

                    temp_data_one_subjectcode.add(pre_requisite_language_Item);

                    String whether_via_other_university_Item=jsonItem.getString("other_university");

                    temp_data_one_subjectcode.add(whether_via_other_university_Item);

                    String whether_advanced_level_Item=jsonItem.getString("non_beginner_level_available");

                    temp_data_one_subjectcode.add(whether_advanced_level_Item);

                    String subject_url_Item=jsonItem.getString("url");

                    temp_data_one_subjectcode.add(subject_url_Item);

                    String university_logo_url=jsonItem.getString("logo");

                    temp_data_one_subjectcode.add(university_logo_url);

                    temp_data_all_data_in_state.add(temp_data_one_subjectcode);

                }

            } catch (JSONException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }

        return temp_data_all_data_in_state;

    }

}
