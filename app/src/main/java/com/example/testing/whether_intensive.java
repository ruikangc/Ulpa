/*
 *This class used to show the available intensity of the subjects.
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

public class whether_intensive extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    private adapter_whether_on_campus listItemAdapter;

    private Button back_main_page;

    ArrayList<String> whether_intensive;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.whether_intensive_layout);

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_whether_on_campus(this, whether_intensive);

        mListView.setAdapter(listItemAdapter);

        //put the data processing in back ground in case of big data size
        new DownloadJSON_states().execute();


        back_main_page.setOnClickListener(new View.OnClickListener() {

                                              @Override

                                              public void onClick(View v) {

                                                  Intent intent = new Intent(whether_intensive.this, MainActivity.class);

                                                  startActivity(intent);

                                              }
                                          }
        );

    }

    private class DownloadJSON_states extends AsyncTask<Void, Void, Void> {

        @Override

        protected Void doInBackground(Void... params) {

            int count=0;

            try {


                ArrayList<ArrayList<String>> all_data_from_previous=new ArrayList<>();

                ArrayList<String> whether_on_campus_user_have_choosen=new ArrayList<>();

                ArrayList<String> whether_intensive_found=new ArrayList<>();

                ArrayList<ArrayList<String>> data_before_intensive=new ArrayList<>();

                data_before_intensive=temp_data_store.getAll_data_before_intensive();

                for (int i=0;i<data_before_intensive.size();i++){

                    if (!whether_intensive_found.contains(data_before_intensive.get(i).get(4))){
                        whether_intensive_found.add(data_before_intensive.get(i).get(4));
                    }

                }


                whether_intensive=(ArrayList<String>)whether_intensive_found.clone();

                temp_data_store.setAll_data_before_intensive(data_before_intensive);

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;
        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(whether_intensive);

            listItemAdapter.notifyDataSetChanged();

        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            String options = "choose data:";

            ArrayList<String> whether_intensive_selected=new ArrayList<String>();

            ArrayList<ArrayList<String>> raw_data_precessed_intensive=new ArrayList<ArrayList<String>>();

            ArrayList<String> subject_code_final_found=new ArrayList<>();

            ArrayList<ArrayList<String>> final_result=new ArrayList<ArrayList<String>>();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                System.out.println("state.get(" + j + ")==" + state.get(j));

                if (state.get(j) != null) {

                            ArrayList<String> temp=new ArrayList<String>();

                    whether_intensive_selected.add(listItemAdapter.getItem(j));

                }

            }

            raw_data_precessed_intensive=temp_data_store.getAll_data_before_intensive();

            for (int i=0;i<raw_data_precessed_intensive.size();i++){

                if (whether_intensive_selected.contains(raw_data_precessed_intensive.get(i).get(4))){

                    final_result.add(raw_data_precessed_intensive.get(i));

                    subject_code_final_found.add(raw_data_precessed_intensive.get(i).get(6));

                }

            }

            temp_data_store.setFinal_search_result(final_result);

            Intent intent = new Intent(whether_intensive.this, result_show_page.class);

            startActivity(intent);

        }
    }
}