/*
 *This class used to find the available states in selected states
 */

package com.example.testing;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;import java.util.HashMap;

public class university_in_state extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    private adapter_university_in_state listItemAdapter;

    private Button back_main_page;

    HashMap<Integer,ArrayList<String>> university_state_match= new HashMap<Integer,ArrayList<String>>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.university_in_state_layout);

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_university_in_state(this, university_state_match);

        mListView.setAdapter(listItemAdapter);

        new DownloadJSON_states().execute();

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                              @Override

                                              public void onClick(View v) {

                                                  Intent intent = new Intent(university_in_state.this, MainActivity.class);

                                                  startActivity(intent);

                                              }
                                          }
        );

    }

    private class DownloadJSON_states extends AsyncTask<Void, Void, Void> {

        String raw_language_university_data = new String();

        ArrayList<ArrayList<String>> all_data_of_selected_states=new ArrayList<>();

        @Override

        protected Void doInBackground(Void... params) {

            int count=0;

            try {

                ArrayList<String> all_states_user_choose=temp_data_store.getState_user_choose();

                ArrayList<String> university_in_one_state=new ArrayList<String>();

                for(int i=0;i<all_states_user_choose.size();i++){

                    String raw_data_state=new String();

                    ArrayList<ArrayList<String>> temp_all_data_of_one_state=new ArrayList<ArrayList<String>>();

                    raw_data_state=HtmlService.getHtml
                            ("http://10.0.2.2:3000/api/schools/"+all_states_user_choose.get(i)+"/");

                    temp_all_data_of_one_state=HtmlService.resolveJsonMessage_all_state_data(raw_data_state);

                    all_data_of_selected_states.addAll(temp_all_data_of_one_state);

                }

                temp_data_store.setAll_data_of_states(all_data_of_selected_states);

                for(int i=0;i<all_states_user_choose.size();i++){

                    raw_language_university_data=HtmlService.getHtml
                            ("http://10.0.2.2:3000/api/schools/states/finduniversity/"+all_states_user_choose.get(i)+"/");

                    university_in_one_state = HtmlService.resolveJsonMessage_university_in_states(raw_language_university_data);

                    for(int j=0;j<university_in_one_state.size();j++){

                        ArrayList<String> temp=new ArrayList<>();

                        temp.add(university_in_one_state.get(j));

                        temp.add(all_states_user_choose.get(i));

                        university_state_match.put(count,temp);

                        count++;
                    }
                }

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(university_state_match);

            listItemAdapter.notifyDataSetChanged();

        }

    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            String options = "choose data:";

            ArrayList<String> shown_university=new ArrayList<String>();

            ArrayList<ArrayList<String>> state_university_user_choose=new ArrayList<ArrayList<String>>();

            ArrayList<String> all_university_user_choose=new ArrayList<>();

            ArrayList<ArrayList<String>> temp_all_data_states_before_university=new ArrayList<>();

            ArrayList<ArrayList<String>> all_states_data_after_university_chosen=new ArrayList<>();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                System.out.println("state.get(" + j + ")==" + state.get(j));

                if (state.get(j) != null) {

                    ArrayList<String> temp=new ArrayList<String>();

                    temp.add(listItemAdapter.getItem(j).get(0));

                    temp.add(listItemAdapter.getItem(j).get(1));

                    all_university_user_choose.add(listItemAdapter.getItem(j).get(0));

                    shown_university.add(listItemAdapter.getItem(j).get(0));

                    state_university_user_choose.add(temp);

                }

            }

            temp_all_data_states_before_university=temp_data_store.getAll_data_of_states();

            //process data once selections finished and only the available data for next process will be left
            for (int i=0;i<temp_all_data_states_before_university.size();i++){

                if (all_university_user_choose.contains(temp_all_data_states_before_university.get(i).get(2))){

                    all_states_data_after_university_chosen.add(temp_all_data_states_before_university.get(i));

                }

            }

            temp_data_store.setAll_data_of_selected_universities(all_states_data_after_university_chosen);

            temp_data_store.setUniversity_state_choose(state_university_user_choose);

            temp_data_store.setAll_university_user_choose(all_university_user_choose);

            Intent intent = new Intent(university_in_state.this, state_university_find_language.class);

            startActivity(intent);

        }
    }
}