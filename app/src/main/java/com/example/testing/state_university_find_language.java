/*
 * This class used to find the available languages in selected universities
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

public class state_university_find_language extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    private adapter_state_university_find_language listItemAdapter;

    private Button back_main_page;

    ArrayList<String> all_language_in_state_university;

    ArrayList<ArrayList<String>> all_language_in_state_university_pair;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.state_university_find_language_layout);

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_state_university_find_language(this, all_language_in_state_university_pair);

        mListView.setAdapter(listItemAdapter);

        new DownloadJSON_states_all_data().execute();

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                              @Override

                                              public void onClick(View v) {

                                                  Intent intent = new Intent(state_university_find_language.this, MainActivity.class);

                                                  startActivity(intent);

                                              }

                                          }

        );

    }

    //processing all subjects data about selected states
    private class DownloadJSON_states_all_data extends AsyncTask<Void, Void, Void> {

        @Override

        protected Void doInBackground(Void... params) {

            int count=0;

            try {

                ArrayList<String> all_states_user_choose=temp_data_store.getState_user_choose();

                ArrayList<String> university_in_selected_state=new ArrayList<String>();

                ArrayList<ArrayList<String>> used_to_compare_language_data=new ArrayList<ArrayList<String>>();

                ArrayList<ArrayList<String>> language_bound_state_university=new ArrayList<>();

                ArrayList<ArrayList<String>> temp_data_choosen=new ArrayList<>();

                ArrayList<String> all_university_user_have_chosen=new ArrayList<>();

                all_university_user_have_chosen=temp_data_store.getAll_university_user_choose();

                ArrayList<String> all_available_language=new ArrayList<>();

                ArrayList<ArrayList<String>> all_data_needed_in_find_language=temp_data_store.getAll_data_of_selected_universities();

                //find the languages available
                for (int i=0;i<all_data_needed_in_find_language.size();i++){

                    if (!all_available_language.contains(all_data_needed_in_find_language.get(i).get(0))){

                        all_available_language.add(all_data_needed_in_find_language.get(i).get(0));

                    }
                }

                int counter=0;

                all_language_in_state_university=(ArrayList<String>)all_available_language.clone();

                for (int i=0;i<all_language_in_state_university.size();i++){

                    for (int j=0;j<all_data_needed_in_find_language.size();j++){

                        if (all_language_in_state_university.get(i).equals(all_data_needed_in_find_language.get(j).get(0))){

                            ArrayList<String> temp=new ArrayList<>();

                            temp.add(all_language_in_state_university.get(i));//language

                            temp.add(all_data_needed_in_find_language.get(j).get(1));//state

                            temp.add(all_data_needed_in_find_language.get(j).get(2));//university

                            temp.add(String.valueOf(j));//the sequence order of it in shown list

                            language_bound_state_university.add(temp);

                            temp_data_choosen.add(all_data_needed_in_find_language.get(j));

                        }

                    }

                }

                all_language_in_state_university_pair=(ArrayList<ArrayList<String>>)language_bound_state_university.clone();

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(all_language_in_state_university_pair);

            listItemAdapter.notifyDataSetChanged();

        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            String options = "chosen data:";

            ArrayList<String> shown_language=new ArrayList<String>();

            ArrayList<String> language_user_choose=new ArrayList<>();

            ArrayList<ArrayList<String>> language_state_university_user_choose=new ArrayList<>();

            ArrayList<ArrayList<String>> all_available_data_after_state_university_language=new ArrayList<>();

            ArrayList<ArrayList<String>> all_available_data_before_language_selected=new ArrayList<>();

            ArrayList<ArrayList<String>> all_available_data_after_language_selected=new ArrayList<>();

            ArrayList<ArrayList<String>> all_data_needed_in_find_language=temp_data_store.getAll_data_of_selected_universities();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                System.out.println("state.get(" + j + ")==" + state.get(j));

                if (state.get(j) != null) {

                    ArrayList<String> temp=new ArrayList<>();

                    temp.add(listItemAdapter.getItem(j).get(0));//language

                    temp.add(listItemAdapter.getItem(j).get(1));//state

                    temp.add(listItemAdapter.getItem(j).get(2));//university

                    temp.add(listItemAdapter.getItem(j).get(3));//index in getAll_data_of_selected_universities();

                    shown_language.add(listItemAdapter.getItem(j).get(0));

                    language_user_choose.add(listItemAdapter.getItem(j).get(0));

                    language_state_university_user_choose.add(temp);

                    all_available_data_after_state_university_language.add(all_data_needed_in_find_language.get(j));

                }

            }

            all_available_data_before_language_selected=temp_data_store.getAll_data_of_selected_universities();

            for (int i=0;i<language_state_university_user_choose.size();i++){

                int temp = Integer.valueOf(language_state_university_user_choose.get(i).get(3)).intValue();

                all_available_data_after_language_selected.add(all_available_data_before_language_selected.get(temp));

            }

            temp_data_store.setAll_data_after_state_university_language(all_available_data_after_language_selected);

            temp_data_store.setLanguage_user_choose_in_university_in_state(language_user_choose);

            temp_data_store.setAll_data_state_and_university_after_language_choose(language_state_university_user_choose);

            options += "\n" + shown_language + "." ;

            Intent intent = new Intent(state_university_find_language.this, whether_on_campus.class);

            startActivity(intent);

        }

    }

}