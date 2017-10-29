/*
 *This class realize the "CONTACT" function on ULPA website, which can send message from the user
 * to the ULPA. Here, the message will be sent to the RESTful API has been built.
 */

package com.example.testing;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
/**
 * Created by CRK on 2017/10/1.
 */
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import org.json.JSONObject;

public class contact extends Activity{

    private EditText first_name;

    private EditText last_name;

    private EditText contact_number;

    private EditText email;

    private EditText preferred_contact_method;

    private EditText your_message;

    private ArrayList<String> optional;

    //identify whether user click the "contact method" button.
    private int whether_click;

    //identify whether Email is correct
    private int whether_email;

    private String user_select_method;

    //generate UI
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.contact_layout);

        whether_click=0;

        ArrayList<String> list = new ArrayList<String>();

        list.add("Email");

        list.add("Phone");

        optional=new ArrayList<>();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);

        Spinner sp=(Spinner) findViewById(R.id.spinner2);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                whether_click++;

                String[] str= {"Email","Phone"};

                if(optional.size()==0){

                    optional.add(str[pos]);

                    user_select_method=str[pos];

                }else {

                    optional.clear();

                }

            }

            @Override

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        sp.setAdapter(adapter);

        EditText first_name = (EditText) this.findViewById(R.id.first_name);

        EditText last_name = (EditText) this.findViewById(R.id.last_name);

        EditText contact_number = (EditText) this.findViewById(R.id.contact_number);

        contact_number.setInputType(InputType.TYPE_CLASS_PHONE);

        EditText email = (EditText) this.findViewById(R.id.email);

        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        EditText your_message = (EditText) this.findViewById(R.id.your_message);

        Button send_message = (Button) this.findViewById((R.id.send_message));

        send_message.setOnClickListener(new OnClickListenerImpl());

    }

    private class OnClickListenerImpl implements OnClickListener {

        @Override

        public void onClick(View v) {

            int count=0;

            whether_email=0;

            EditText first_name = (EditText) findViewById(R.id.first_name);

            EditText last_name = (EditText) findViewById(R.id.last_name);

            EditText contact_number = (EditText) findViewById(R.id.contact_number);

            EditText email = (EditText) findViewById(R.id.email);

            EditText your_message = (EditText) findViewById(R.id.your_message);

            if(whether_click>0){

                count++;

            }

            String first_name_data= first_name.getText().toString();

            if (!first_name_data.equals("")){

                count++;

            }

            String last_name_data= last_name.getText().toString();

            if (!last_name_data.equals("")){

                count++;

            }
            String contact_number_data= contact_number.getText().toString();

            if (!contact_number_data.equals("")){

                count++;

            }

            String email_data= email.getText().toString();

            if (!email_data.equals("")){

                /*
                 *here I use whether there is "@" symbol in the input String to identify whether
                 * it is available Email address just like the ULPA website, it is not all correct
                 * because sometimes many developer believe it will be better to use "regular
                 * expression" to match like "@xxx.com", it is still not correct enough because
                 * now there are some a little strange Email address available like "@xx.com.a"
                 * so this part of identify Email address whether available require deeply research.
                 */
                if(email_data.contains("@")){

                    count++;

                    whether_email++;

                }

                else{

                    Toast t =Toast.makeText(contact.this, "Please provide correct Email", Toast.LENGTH_LONG);

                    t.show();

                }

            }

            String your_message_data= your_message.getText().toString();

            if (!your_message_data.equals("")){

                count++;

            }

            //identify whether user has put in all required data
            if (count!=6&&   whether_email!=0){

                Toast t =Toast.makeText(contact.this, "Please enter all required data", Toast.LENGTH_LONG);

                t.show();

            }

            if (count!=6&&   whether_email==0){

                Toast t =Toast.makeText(contact.this, "Please enter all required data", Toast.LENGTH_LONG);

                t.show();

            }

            else{

                ArrayList<ArrayList<String>> transfer=new ArrayList<>();

                String firstname=first_name.getText().toString();

                String lastname=last_name.getText().toString();

                String phonenumber=contact_number.getText().toString();

                String emailaddress=email.getText().toString();

                String message=your_message.getText().toString();

                try {

                    //package the message into JSON message with relative description.
                    JSONObject object = new JSONObject();

                    object.put("first name", firstname);

                    object.put("last name", lastname);

                    object.put("contact number", phonenumber);

                    object.put("email address", emailaddress);

                    object.put("message", message);

                    object.put("user prefer contact method",user_select_method);

                    String send=object.toString();

                    //send the message back to RESTful API via HTTP request
                    HttpUtil.sendPostHttpRequest("http://10.0.2.2:3000/sendmessage/", send, new HttpCallbackListener() {

                        @Override

                        public void onFinish(String response) {

                        }

                        @Override

                        public void onError(Exception e) {

                        }

                    });

                }catch(Exception e){

                }

                //reset the text to tell the user message has been sent
                first_name.setText("store already");

                last_name.setText("store already");

                contact_number.setText("store already");

                email.setText("store already");

                your_message.setText("store already");

            }

        }
    }

}
