/*
 *This Activity used to select universities in the states have selected
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

public class multiple_choice_university extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    private adapter_multiple_university listItemAdapter;

    private Button back_main_page;

    ArrayList<String> all_states_available;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.multiple_choice_university_one);

        //downloading data based on the selected states
        DownloadJSON_states downloading=new DownloadJSON_states();

        downloading.execute();

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_multiple_university(this, all_states_available);

        mListView.setAdapter(listItemAdapter);

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        Intent intent = new Intent(multiple_choice_university.this, MainActivity.class);

                                        startActivity(intent);

                                    }

                                }

        );

    }

    //downloading all the subject information in the selected states
    private class DownloadJSON_states extends AsyncTask<Void, Void, Void> {

        String raw_language_university_data = new String();

        ArrayList<String> all_states_available = new ArrayList<>();

        @Override

        protected Void doInBackground(Void... params) {

            try {
                raw_language_university_data = HtmlService.getHtml_states("http://10.0.2.2:3000/api/schools/states");

                all_states_available = HtmlService.resolveJsonMessage_states(raw_language_university_data);

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(all_states_available);

            listItemAdapter.notifyDataSetChanged();

        }

    }

    // find the clicked item
    private class OnClickListenerImpl implements OnClickListener {

        @Override
        public void onClick(View v) {
            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            ArrayList<String> selected_states=new ArrayList<String>();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                System.out.println("state.get(" + j + ")==" + state.get(j));

                if (state.get(j) != null) {

                    String area =listItemAdapter.getItem(j);

                    selected_states.add(area);

                }

            }

            //store data into a class.
            temp_data_store.setState_user_choose(selected_states);

            Intent intent = new Intent(multiple_choice_university.this, university_in_state.class);

            startActivity(intent);

        }
    }
}