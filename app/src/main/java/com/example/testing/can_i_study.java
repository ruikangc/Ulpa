/*
 * This is the class for "Can I study a language taught at a university other than my own?" function
 * on the ULPA website, and the data not complete but only need to add real data as the format shown
 * below.
 */

package com.example.testing;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class can_i_study extends Activity {

    @Override

    //generate UI page
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.can_i_study_layout);

        //show the title
        TextView title = (TextView) findViewById(R.id.language_name);

        //show the content of the title
        TextView introduce = (TextView) findViewById(R.id.language_introduce);

        //set the URL link to the web page users can find more information from
        TextView jump_to_700reasons = (TextView) findViewById(R.id.jump_to_700reasons);

        title.setText("What is Cross-Institutional Enrolment?");

        introduce.setText("If there’s a language you’d like to study which is offered at another " +
                "university other than your own, you may be able to enrol in it—if you meet the " +
                "requirements of both universities (the ‘incoming’ university and the ‘outgoing’ " +
                "university). You can take the course on-campus (if you are able to get to the " +
                "campus), or by distance, if the course if offered online. This page will give " +
                "you the tools to investigate whether cross-institutional enrolment will be right " +
                "for you.");

        //set the click and jump to web page
        jump_to_700reasons.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                // TODO Auto-generated method stub

                Uri uri = Uri.parse("https://www.ulpa.edu.au/can-study-at-university-other-than-own/");

                Intent it = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(it);

            }

        });

    }

}
