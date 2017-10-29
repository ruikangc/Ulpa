/*
 * This is the adapter of Activity state_university_find-language, which used to show the
 * available languages after selected the universities users prefer.
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

public class adapter_state_university_find_language extends BaseAdapter {

    private Context context;

    //this is the list contains the shown data
    private ArrayList<ArrayList<String>> language_in_state_university;

    HashMap<Integer,Boolean> check_data = new HashMap<Integer, Boolean>();

    //adapter constructor
    public adapter_state_university_find_language(Context context, ArrayList<ArrayList<String>> language_in_state_university) {

        this.context = context;

        this.language_in_state_university = language_in_state_university;

    }

    //update shown list after data processed
    public void updateStates(ArrayList<ArrayList<String>> language_in_state_university) {

        this.language_in_state_university= language_in_state_university;

    }

    @Override

    public int getCount() {

        // TODO Auto-generated method stub

        if (language_in_state_university==null){

            return 0;

        }

        return language_in_state_university.size();

    }

    @Override

    public ArrayList<String> getItem(int position) {

        // TODO Auto-generated method stub

        return language_in_state_university.get(position);
    }

    @Override

    public long getItemId(int position) {

        // TODO Auto-generated method stub

        return position;

    }

    @Override

    //generate the view user can see on screen to select from
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        convertView = mInflater.inflate(R.layout.state_university_find_language_everyitem_layout, null);

        ArrayList<String> viewData = language_in_state_university.get(position);

        TextView language = (TextView) convertView.findViewById(R.id.language_id);

        language.setText( viewData.get(0));

        TextView university = (TextView) convertView.findViewById(R.id.university_id);

        university.setText( viewData.get(2));

        CheckBox check = (CheckBox) convertView.findViewById(R.id.selected);

        check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
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