/*
 * This class show some information about where students can study
 */

package com.example.testing;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class where_to_study extends Activity{

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.where_to_study_layout);

        TextView language_name = (TextView) findViewById(R.id.language_name);

        TextView language_introduce = (TextView) findViewById(R.id.language_introduce);

        TextView jump_to_700reasons = (TextView) findViewById(R.id.jump_to_700reasons);


        language_name.setText("How many Indigenous languages are there in Australia?");

        language_introduce.setText("There were around 300 distinct Indigenous languages " +
                "spoken around Australia at the time of the first European contact. You can " +
                "find them on the map here. The number of languages still actively spoken by " +
                "children is fewer than 20, and these languages are mostly in remote parts of " +
                "Australia. They are used for everyday talk in a number of small communities. " +
                "In recent years many people have begun to re-learn their families’ languages. " +
                "These languages are very important to their users as languages which connect " +
                "them with their heritage.");
        jump_to_700reasons.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                // TODO Auto-generated method stub

                Uri uri = Uri.parse("http://www.abc.net.au/indigenous/map/");

                Intent it = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(it);

            }

        });

    }

}
