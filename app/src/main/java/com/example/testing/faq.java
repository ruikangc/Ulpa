/*
*This is the class for the "FAQ" section on ULPA website, which including some questions and
* the answers to that questions.
 */

package com.example.testing;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class faq extends Activity{

    //each questions set as a button so is clickable
    private Button question_one;

    private Button question_two;

    private Button question_three;

    //used to identify whether user want to show the answers or hide
    private int whether_shown_one=0;

    private int whether_shown_two=0;

    private int whether_shown_three=0;

    protected void onCreate(Bundle savedInstanceState) {

// TODO Auto-generated method stub

        super.onCreate(savedInstanceState);

        setContentView(R.layout.faq);

        question_one = (Button) findViewById(R.id.question1);

        question_two = (Button) findViewById(R.id.question2);

        question_three = (Button) findViewById(R.id.question3);

        //set specific click response for each question
        question_one.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        if (whether_shown_one==0){

                                            whether_shown_one=1;

                                            TextView question_1=(TextView) findViewById(R.id.question_one);

                                            question_1.setVisibility(View.VISIBLE);

                                        }

                                        else{

                                            TextView question_1=(TextView) findViewById(R.id.question_one);

                                            question_1.setVisibility(View.GONE);

                                            whether_shown_one=0;

                                        }

                                    }
                                }
        );

        question_two.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        if (whether_shown_two==0){

                                            whether_shown_two=1;

                                            TextView question_1=(TextView) findViewById(R.id.question_two);

                                            question_1.setVisibility(View.VISIBLE);
                                        }
                                        else{

                                            TextView question_1=(TextView) findViewById(R.id.question_two);

                                            question_1.setVisibility(View.GONE);

                                            whether_shown_two=0;

                                        }

                                    }
                                }
        );

        question_three.setOnClickListener(new View.OnClickListener() {

                                    @Override

                                    public void onClick(View v) {

                                        if (whether_shown_three==0){

                                            whether_shown_three=1;

                                            TextView question_1=(TextView) findViewById(R.id.question_three);

                                            question_1.setVisibility(View.VISIBLE);

                                        }

                                        else{

                                            TextView question_1=(TextView) findViewById(R.id.question_three);

                                            question_1.setVisibility(View.GONE);

                                            whether_shown_three=0;

                                        }

                                    }

                                }
        );

    }

}
