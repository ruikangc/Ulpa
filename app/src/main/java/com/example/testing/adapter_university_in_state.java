/*
 * This is the adapter for Activity university_in_state used to show the universities in selected
 * states
 */

package com.example.testing;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class adapter_university_in_state extends BaseAdapter {

    private Context context;

    private ArrayList<String> listData;

    //This is the shown data, contains the university name and the related state it's in.
    HashMap<Integer,ArrayList<String>> university_state_match= new HashMap<Integer,ArrayList<String>>();

    HashMap<Integer,Boolean> check_data = new HashMap<Integer, Boolean>();

    //adapter constructor
    public adapter_university_in_state(Context context, HashMap<Integer,ArrayList<String>> university_state_match) {

        this.context = context;

        this.university_state_match = university_state_match;

    }

    public void updateStates(HashMap<Integer,ArrayList<String>> university_state_available) {

        this.university_state_match= university_state_available;

    }

    @Override

    public int getCount() {

        // TODO Auto-generated method stub

        if (university_state_match.isEmpty()){

            return 0;

        }

        return university_state_match.size();

    }

    @Override

    public ArrayList<String> getItem(int position) {

        // TODO Auto-generated method stub

        return university_state_match.get(position);

    }

    @Override

    public long getItemId(int position) {

        // TODO Auto-generated method stub

        return position;

    }

    @Override

    //generate the shown list and put the items into it.
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        convertView = mInflater.inflate(R.layout.university_in_state_everyitem, null);

        ArrayList<String> viewData = university_state_match.get(position);

        TextView id = (TextView) convertView.findViewById(R.id.friend_id);

        id.setText( viewData.get(0));

        TextView state_name = (TextView) convertView.findViewById(R.id.state_id);

        state_name.setText(viewData.get(1));

        CheckBox check = (CheckBox) convertView.findViewById(R.id.selected);

        check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override

            //supervise which item selected by users
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // TODO Auto-generated method stub

                if (isChecked) {

                    check_data.put(position,isChecked);

                } else {

                    check_data.remove(position);

                }

            }

        });

        check.setChecked((check_data.get(position) == null ? false : true));

        return convertView;

    }

}