/*
 *This class show the selection of whether study on campus or online
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

public class whether_on_campus extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    private adapter_whether_on_campus listItemAdapter;

    private Button back_main_page;

    ArrayList<String> whether_on_campus_shown;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.whether_on_campus_layout);

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_whether_on_campus(this, whether_on_campus_shown);

        mListView.setAdapter(listItemAdapter);

        /*This is to do the data processing in the back ground not the real downloading :).
         * and if the data are large, it is necessary to put the data processing in
         * back ground because we can't do much calculation in the main UI thread and app will crash.
         */
        new DownloadJSON_states().execute();

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                              @Override

                                              public void onClick(View v) {

                                                  Intent intent = new Intent(whether_on_campus.this, MainActivity.class);

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

                ArrayList<String> whether_on_campus_state_found=new ArrayList<>();

                ArrayList<String> whether_on_campus_found=new ArrayList<>();

                ArrayList<ArrayList<String>> data_after_on_campus_resequence=new ArrayList<>();

                all_data_from_previous=temp_data_store.getAll_data_after_state_university_language();

                for (int i=0;i<all_data_from_previous.size();i++){

                    if (!whether_on_campus_state_found.contains(all_data_from_previous.get(i).get(3))){

                        whether_on_campus_state_found.add(all_data_from_previous.get(i).get(3));

                    }

                }

                whether_on_campus_shown=(ArrayList<String>)whether_on_campus_state_found.clone();

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(whether_on_campus_shown);

            listItemAdapter.notifyDataSetChanged();

        }
    }

    private class OnClickListenerImpl implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            String options = "choose data:";

            ArrayList<String> whether_on_campus_selected=new ArrayList<String>();

            ArrayList<ArrayList<String>> all_data_after_on_campus_choose=new ArrayList<ArrayList<String>>();

            ArrayList<String> all_campus_online_choose=new ArrayList<>();

            ArrayList<ArrayList<String>> all_data_from_previous=new ArrayList<>();

            all_data_from_previous=temp_data_store.getAll_data_after_state_university_language();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                System.out.println("state.get(" + j + ")==" + state.get(j));

                if (state.get(j) != null) {


                    ArrayList<String> temp=new ArrayList<String>();

                    whether_on_campus_selected.add(listItemAdapter.getItem(j));

                }

            }

            temp_data_store.setWhether_on_campus_choose(whether_on_campus_selected);

            for (int i=0;i<all_data_from_previous.size();i++){

                if (whether_on_campus_selected.contains(all_data_from_previous.get(i).get(3))){

                    all_data_after_on_campus_choose.add(all_data_from_previous.get(i));

                }

            }

            temp_data_store.setAll_data_before_intensive(all_data_after_on_campus_choose);

            Intent intent = new Intent(whether_on_campus.this, whether_intensive.class);

            startActivity(intent);

        }
    }
}