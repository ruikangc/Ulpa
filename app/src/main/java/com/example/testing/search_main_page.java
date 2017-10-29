/*
 *this page used to choose what kind of search users want.
 */

package com.example.testing;
/*
 * Created by CRK on 2017/9/29.
 */
import android.widget.Button;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class search_main_page extends Activity {

    private Button btn1;

    private Button btn2;

    private Button btn3;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

// TODO Auto-generated method stub

        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_main_page_layout);

        btn1 = (Button) findViewById(R.id.btn1);

        btn2 = (Button) findViewById(R.id.btn2);

        btn3 = (Button) findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        Intent intent = new Intent(search_main_page.this, multiple_choice_university.class);

                                        startActivity(intent);

                                    }
                                }

        );

        btn2.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        Intent intent = new Intent(search_main_page.this, multiple_language_one.class);

                                        startActivity(intent);

                                    }

                                }

        );

        btn3.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        Intent intent = new Intent(search_main_page.this, MainActivity.class);

                                        startActivity(intent);

                                    }

                                }

        );

    }

}