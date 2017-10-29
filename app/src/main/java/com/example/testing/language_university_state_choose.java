/*
 *This Activity used to select available languages based on the universities have selected
 */

package com.example.testing;
/*
 * Created by CRK on 2017/10/1.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.os.AsyncTask;
import android.util.Log;
import java.util.HashMap;
import android.view.View.OnClickListener;

public class language_university_state_choose extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    private adapter_language_university_state_choose listItemAdapter;

    private Button back_main_page;

    ArrayList<ArrayList<String>> all_university_states_available;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.language_university_state_choose_layout);

        //downloading data from URL link need to be done in back ground (another thread)
        DownloadJSON_states downloading=new DownloadJSON_states();

        downloading.execute();

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_language_university_state_choose(this,
                all_university_states_available);

        mListView.setAdapter(listItemAdapter);

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                              @Override

                                              public void onClick(View v) {

                                                  Intent intent = new Intent(language_university_state_choose.this, MainActivity.class);

                                                  startActivity(intent);

                                              }

                                          }

        );

    }

    //downloading data thread
    private class DownloadJSON_states extends AsyncTask<Void, Void, Void> {

        ArrayList<ArrayList<String>> all_data_of_language=new ArrayList<>();

        ArrayList<String> available_university=new ArrayList<>();

        ArrayList<ArrayList<String>> available_university_state=new ArrayList<>();

        @Override

        protected Void doInBackground(Void... params) {

            try {

                ArrayList<String> user_selected_language=temp_data_store.getLanguage_user_choose();

                for (int i=0;i<user_selected_language.size();i++){

                    String raw_language_university_data = new String();

                    raw_language_university_data = HtmlService.getHtml_states("http://10.0.2.2:3000/api/schools/language/"+
                            user_selected_language.get(i)+"/");

                    ArrayList<ArrayList<String>> all_states_available = HtmlService.
                            resolveJsonMessage_all_state_data(raw_language_university_data);

                    all_data_of_language.addAll(all_states_available);
                }

                temp_data_store.setAll_data_of_states(all_data_of_language);

                //after fetching the data, select the data needs to be shown and put them into the list
                for (int i=0;i<all_data_of_language.size();i++){

                    if(!available_university.contains(all_data_of_language.get(i).get(2))){

                        available_university.add(all_data_of_language.get(i).get(2));

                        ArrayList<String> temp=new ArrayList<>();

                        //university
                        temp.add(all_data_of_language.get(i).get(2));

                        //state
                        temp.add(all_data_of_language.get(i).get(1));

                        //language name
                        temp.add(all_data_of_language.get(i).get(0));

                        available_university_state.add(temp);

                    }

                }

                all_university_states_available=(ArrayList<ArrayList<String>>)available_university_state.clone();

                temp_data_store.setAll_data_after_language_classify(all_data_of_language);

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(all_university_states_available);

            listItemAdapter.notifyDataSetChanged();

        }

    }

    private class OnClickListenerImpl implements OnClickListener {

        @Override

        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            ArrayList<String> selected_university=new ArrayList<>();

            ArrayList<ArrayList<String>> not_classify_by_university_but_language=new ArrayList<>();

            ArrayList<ArrayList<String>> classify_by_university_and_language=new ArrayList<>();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                System.out.println("state.get(" + j + ")==" + state.get(j));

                if (state.get(j) != null) {

                            String university =listItemAdapter.getItem(j).get(0);

                    selected_university.add(university);

                }

            }

            not_classify_by_university_but_language=temp_data_store.getAll_data_after_language_classify();

            System.out.println(not_classify_by_university_but_language);

            //filter the source data and only leave the subject with the information have selected.
            for (int i=0;i<not_classify_by_university_but_language.size();i++){

                if(selected_university.contains(not_classify_by_university_but_language.get(i).get(2))){

                    classify_by_university_and_language.add(not_classify_by_university_but_language.get(i));

                }

            }

            temp_data_store.setAll_data_after_state_university_language(classify_by_university_and_language);

            Intent intent = new Intent(language_university_state_choose.this, whether_on_campus.class);

            startActivity(intent);

        }

    }

}