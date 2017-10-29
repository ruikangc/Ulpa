/*
 * This class used show the result of "search subject" function
 */

package com.example.testing;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;

public class result_show_page extends Activity{

    private ArrayList<ArrayList<String>> result_need_to_show=new ArrayList<>();

    private ArrayList<ArrayList<String>> result_used_to_add=new ArrayList<>();

    private LinearLayout add_result_view;

    private int count=0;

    private Button back_main_page;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView( R.layout.result_show_page_layout );

        add_result_view = (LinearLayout) findViewById(R.id.ll_addView);

        count=0;

        result_need_to_show=temp_data_store.getFinal_search_result();

        back_main_page=(Button) findViewById(R.id.button3);

        back_main_page.setOnClickListener(new View.OnClickListener() {

                                              @Override

                                              public void onClick(View v) {

                                                  Intent intent = new Intent(result_show_page.this, MainActivity.class);

                                                  startActivity(intent);

                                              }
                                          }

        );

        //show every available result
        for (int i=0;i<result_need_to_show.size();i++){

            addViewItem(null);

            count++;

        }

    }

    //function used to show one result, with show data and description.
    private void addViewItem(View view) {

        result_used_to_add=temp_data_store.getFinal_search_result();

        ArrayList<String> inputdata=result_used_to_add.get(count);

        if (add_result_view.getChildCount() == 0) {

            View next_result = View.inflate(this, R.layout.result_show_page_everyitem, null);

            ImageView university_image=(ImageView) next_result.findViewById(R.id.university_image_id);

            String url_image=inputdata.get(12);

            /*
             *sometimes the image of the university will not show because the time used to fetch
             * is too long. and those links of university Iput inside the database are found on google,
             * sometimes the source website not stable and will not image too.
             */

            try {

                URL thumb_u = new URL(url_image);

                Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");

                university_image.setImageDrawable(thumb_d);

            }

            catch (Exception e) {

            }

            //put every information to the location it should be
            TextView university_id = (TextView)next_result.findViewById(R.id.university_id);

            String university=inputdata.get(2);

            String temp_1="University Name : "+university;

            university_id.setText(temp_1);

            TextView state_id = (TextView)next_result.findViewById(R.id.state_id);

            String state=inputdata.get(1);

            String temp_2="State : "+state;

            state_id.setText(temp_2);

            TextView subject_name_id = (TextView)next_result.findViewById(R.id.subject_name_id);

            String subject_name=inputdata.get(5);

            String temp_3="Subject Name : "+subject_name;

            subject_name_id.setText(temp_3);

            TextView language_id = (TextView)next_result.findViewById(R.id.language_id);

            String language=inputdata.get(0);

            String temp_4="Language : "+language;

            language_id.setText(temp_4);

            TextView subject_code_id = (TextView)next_result.findViewById(R.id.subject_code_id);

            String subject_code=inputdata.get(6);

            String temp_5="Subject Code : "+subject_code;

            subject_code_id.setText(temp_5);

            TextView next_offered_id = (TextView)next_result.findViewById(R.id.next_offered_id);

            String next_offered=inputdata.get(7);

            String temp_6="Next Offered : "+next_offered;

            next_offered_id.setText(temp_6);

            TextView intensive_id = (TextView)next_result.findViewById(R.id.intensive_id);

            String intensive=inputdata.get(4);

            String temp_7="Regular / intensive / both available : "+intensive;

            intensive_id.setText(temp_7);

            TextView on_campus_id = (TextView)next_result.findViewById(R.id.on_campus_id);

            String on_campus=inputdata.get(3);

            String temp_8="Fully online / on-campus / both available : "+on_campus;

            on_campus_id.setText(temp_8);

            TextView pre_requisite_id = (TextView)next_result.findViewById(R.id.pre_requisite_id);

            String pre_requisite=inputdata.get(8);

            String temp_9="Pre-requisite language : "+pre_requisite;

            pre_requisite_id.setText(temp_9);

            TextView taught_another_university_id = (TextView)next_result.findViewById(R.id.taught_another_university_id);

            String taught_another_university=inputdata.get(9);

            String temp_10="Subject is taught via another university : "+taught_another_university;

            taught_another_university_id.setText(temp_10);

            TextView advanced_id = (TextView)next_result.findViewById(R.id.advanced_id);

            String advanced=inputdata.get(10);

            String temp_11="Also available at more advanced levels : "+advanced;

            advanced_id.setText(temp_11);

            TextView more_information_id = (TextView)next_result.findViewById(R.id.more_information_id);

            more_information_id.setText(inputdata.get(11));

            //add the child view to parent view
            add_result_view.addView(next_result);

        ArrayList<String> this_result=result_used_to_add.get(count);

        } else  {

            //this works same as the previous one, but to avoid some exception raised.
            View next_result = View.inflate(this, R.layout.result_show_page_everyitem, null);

            ImageView university_image=(ImageView) next_result.findViewById(R.id.university_image_id);

            String url_image=inputdata.get(12);

            try {

                URL thumb_u = new URL(url_image);

                Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");

                university_image.setImageDrawable(thumb_d);

            }

            catch (Exception e) {

            }

            TextView university_id = (TextView)next_result.findViewById(R.id.university_id);

            String university=inputdata.get(2);

            String temp_1="University Name : "+university;

            university_id.setText(temp_1);

            TextView state_id = (TextView)next_result.findViewById(R.id.state_id);

            String state=inputdata.get(1);

            String temp_2="State : "+state;

            state_id.setText(temp_2);

            TextView subject_name_id = (TextView)next_result.findViewById(R.id.subject_name_id);

            String subject_name=inputdata.get(5);

            String temp_3="Subject Name : "+subject_name;

            subject_name_id.setText(temp_3);

            TextView language_id = (TextView)next_result.findViewById(R.id.language_id);

            String language=inputdata.get(0);

            String temp_4="Language : "+language;

            language_id.setText(temp_4);

            TextView subject_code_id = (TextView)next_result.findViewById(R.id.subject_code_id);

            String subject_code=inputdata.get(6);

            String temp_5="Subject Code : "+subject_code;

            subject_code_id.setText(temp_5);

            TextView next_offered_id = (TextView)next_result.findViewById(R.id.next_offered_id);

            String next_offered=inputdata.get(7);

            String temp_6="Next Offered : "+next_offered;

            next_offered_id.setText(temp_6);

            TextView intensive_id = (TextView)next_result.findViewById(R.id.intensive_id);

            String intensive=inputdata.get(4);

            String temp_7="Regular / intensive / both available : "+intensive;

            intensive_id.setText(temp_7);

            TextView on_campus_id = (TextView)next_result.findViewById(R.id.on_campus_id);

            String on_campus=inputdata.get(3);

            String temp_8="Fully online / on-campus / both available : "+on_campus;

            on_campus_id.setText(temp_8);

            TextView pre_requisite_id = (TextView)next_result.findViewById(R.id.pre_requisite_id);

            String pre_requisite=inputdata.get(8);

            String temp_9="Pre-requisite language : "+pre_requisite;

            pre_requisite_id.setText(temp_9);

            TextView taught_another_university_id = (TextView)next_result.findViewById(R.id.taught_another_university_id);

            String taught_another_university=inputdata.get(9);

            String temp_10="Subject is taught via another university : "+taught_another_university;

            taught_another_university_id.setText(temp_10);

            TextView advanced_id = (TextView)next_result.findViewById(R.id.advanced_id);

            String advanced=inputdata.get(10);

            String temp_11="Also available at more advanced levels : "+advanced;

            advanced_id.setText(temp_11);

            TextView more_information_id = (TextView)next_result.findViewById(R.id.more_information_id);

            more_information_id.setText(inputdata.get(11));

            add_result_view.addView(next_result);

        }

    }

}




