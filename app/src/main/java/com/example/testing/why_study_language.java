/*
 *This class show information about why study language
 */

package com.example.testing;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class why_study_language extends Activity{

        @Override

        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.why_study_language_layout);

            TextView language_name = (TextView) findViewById(R.id.language_name);

            TextView language_introduce = (TextView) findViewById(R.id.language_introduce);

            TextView jump_to_700reasons = (TextView) findViewById(R.id.jump_to_700reasons);

            language_name.setText("Why should I study a language? How will it help me?");

            language_introduce.setText("Learning a language does not just involve memorising " +
                    "long lists of vocabulary, studying tricky grammatical structures and" +
                    " getting your tongue around strange sounds – though it is about" +
                    " all these things too! It involves getting immersed in another " +
                    "culture, a new way of looking a");

            jump_to_700reasons.setOnClickListener(new OnClickListener() {

                @Override

                public void onClick(View view) {

                    // TODO Auto-generated method stub

                    Uri uri = Uri.parse("https://www.llas.ac.uk/700reasons.html");

                    Intent it = new Intent(Intent.ACTION_VIEW, uri);

                    startActivity(it);
                }

            });
        }

}
