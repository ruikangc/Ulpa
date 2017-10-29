/*
*This is the class for the "what language can I study" function on ULPA website, and will show the
* available languages users can choose from and the descriptions to them, and if user wish, can
* directly jump to the "search subjects" function with the selected language.
 */

package com.example.testing;
import android.content.Intent;
import java.util.ArrayList;
/*
 * Created by CRK on 2017/10/1.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class language_detail extends Activity{

    private ArrayList<ArrayList<String>> result_need_to_show=new ArrayList<>();

    private ArrayList<ArrayList<String>> result_used_to_add=new ArrayList<>();

    private LinearLayout add_result_view;

    private int count=0;

    private String current_find_one;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView( R.layout.language_detail_show_page );

        add_result_view = (LinearLayout) findViewById(R.id.ll_addView);

        count=0;

        result_need_to_show=temp_data_store.getLanguage_detail();

        //add item needs to show one by one to the parent layout, to start, add a "null" view
        for (int i=0;i<result_need_to_show.size();i++){

            addViewItem(null);

            count++;

        }

    }

    /*
     *for add item view to the layout, consider if there is no result (and in my app design, there
     * will always be available result), to avoid raise exception, I write these code.
     */
    private void addViewItem(View view) {

        result_used_to_add=temp_data_store.getLanguage_detail();

        ArrayList<String> inputdata=result_used_to_add.get(count);

        if (add_result_view.getChildCount() == 0) {

            View next_result = View.inflate(this, R.layout.language_detail_show_page_everyitem, null);

            TextView language_id = (TextView)next_result.findViewById(R.id.language_name);

            String language=inputdata.get(0);

            current_find_one=language;

            //there are 2 items need to be shown ,the name of language and descriptions to them.
            String temp_1="Language Name : "+language;

            language_id.setText(temp_1);

            TextView description_id = (TextView)next_result.findViewById(R.id.language_description);

            String description=inputdata.get(1);

            String temp_2="Language Description : "+description;

            description_id.setText(temp_2);

            Button search_language=(Button)  next_result.findViewById(R.id.language_search);

            search_language.setOnClickListener(new OnClickListener(){

                @Override

                public void onClick(View view) {

                    // TODO Auto-generated method stub

                    ArrayList<String> select_language=new ArrayList<>();

                    select_language.add(current_find_one);

                    temp_data_store.setLanguage_user_choose(select_language);

                    Intent intent=new Intent(language_detail.this,language_university_state_choose.class);

                    startActivity(intent);
                }

            });

            add_result_view.addView(next_result);

            ArrayList<String> this_result=result_used_to_add.get(count);

        } else  {

            View next_result = View.inflate(this, R.layout.language_detail_show_page_everyitem, null);

            TextView language_id = (TextView)next_result.findViewById(R.id.language_name);

            String language=inputdata.get(0);

            current_find_one=language;

            String temp_1="Language Name : "+language;

            language_id.setText(temp_1);

            TextView description_id = (TextView)next_result.findViewById(R.id.language_description);

            String description=inputdata.get(1);

            String temp_2="Language Description : "+description;

            description_id.setText(temp_2);

            Button search_language=(Button)  next_result.findViewById(R.id.language_search);

            search_language.setOnClickListener(new OnClickListener(){

                @Override

                public void onClick(View view) {

                    // TODO Auto-generated method stub

                    ArrayList<String> select_language=new ArrayList<>();

                    select_language.add(current_find_one);

                    temp_data_store.setLanguage_user_choose(select_language);

                    Intent intent=new Intent(language_detail.this,language_university_state_choose.class);

                    startActivity(intent);

                }

            });

            add_result_view.addView(next_result);

        }

    }

}




