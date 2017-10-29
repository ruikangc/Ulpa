/*
 * This class used to choose the language users like
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

public class multiple_language_one extends Activity {

    private Button mButtonGetValue;

    private ListView mListView;

    //match the adapter used to show the data
    private adapter_multiple_language_one listItemAdapter;

    private Button back_main_page;

    ArrayList<String> all_language_available;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.multiple_language_one_layout);

        //start downloading data
        DownloadJSON_states downloading=new DownloadJSON_states();

        downloading.execute();

        back_main_page=(Button) findViewById(R.id.button3);

        mButtonGetValue = (Button) findViewById(R.id.get_value);

        mListView = (ListView) findViewById(R.id.list);

        mButtonGetValue.setOnClickListener(new OnClickListenerImpl());

        listItemAdapter = new adapter_multiple_language_one(this, all_language_available);

        mListView.setAdapter(listItemAdapter);

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        Intent intent = new Intent(multiple_language_one.this, MainActivity.class);

                                        startActivity(intent);

                                    }

                                }

        );

    }

    private class DownloadJSON_states extends AsyncTask<Void, Void, Void> {

        String raw_language_university_data = new String();

        ArrayList<String> all_language_available_inside = new ArrayList<>();

        @Override

        protected Void doInBackground(Void... params) {

            try {

                raw_language_university_data = HtmlService.getHtml_states("http://10.0.2.2:3000/api/schools/languagelist/alllanguage");

                all_language_available_inside = HtmlService.resolveJsonMessage_language(raw_language_university_data);


            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            all_language_available=(ArrayList<String>) all_language_available_inside.clone();

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            listItemAdapter.updateStates(all_language_available);

            listItemAdapter.notifyDataSetChanged();

        }
    }

    private class OnClickListenerImpl implements OnClickListener {

        @Override

        public void onClick(View v) {

            HashMap<Integer, Boolean> state = listItemAdapter.check_data;

            ArrayList<String> selected_language=new ArrayList<String>();

            for (int j = 0; j < listItemAdapter.getCount(); j++) {

                if (state.get(j) != null) {

                            String language =listItemAdapter.getItem(j);

                    selected_language.add(language);
                }

            }

            temp_data_store.setLanguage_user_choose(selected_language);

            Intent intent = new Intent(multiple_language_one.this, language_university_state_choose.class);

            startActivity(intent);

        }

    }

}