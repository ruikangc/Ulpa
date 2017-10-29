/*
 *adapter class for Activity language_university_state_choose, used to
 *match the list used inside
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

public class adapter_language_university_state_choose extends BaseAdapter {

    private Context context;

    /*the list used in parent Activity, which identifies the universities found by
    *selected languages.
    */
    ArrayList<ArrayList<String>> university_state_found_by_language= new ArrayList<>();

    //check_data used to identify the options made by users.
    HashMap<Integer,Boolean> check_data = new HashMap<Integer, Boolean>();

    //class constructor
    public adapter_language_university_state_choose(Context context, ArrayList<ArrayList<String>>
            university_state_found_by_language) {

        this.context = context;

        this.university_state_found_by_language = university_state_found_by_language;

    }

    //used to update the shown list after data have been processed in back ground
    public void updateStates(ArrayList<ArrayList<String>> university_state_found_by_language) {

        this.university_state_found_by_language= university_state_found_by_language;

    }

    @Override

    //identify the size of shown list
    public int getCount() {
        // TODO Auto-generated method stub
        if (university_state_found_by_language==null){

            return 0;
        }

        return university_state_found_by_language.size();
    }

    @Override

    //find specific item in shown list
    public ArrayList<String> getItem(int position) {
        // TODO Auto-generated method stub

        return university_state_found_by_language.get(position);

    }

    @Override

    public long getItemId(int position) {

        // TODO Auto-generated method stub

        return position;

    }

    @Override

    //generate the shown list which can be seen by users on screen
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        convertView = mInflater.inflate(R.layout.
                language_university_state_choose_everyitem_layout, null);

        ArrayList<String> viewData = university_state_found_by_language.get(position);

        //set the content for every shown item
        TextView university = (TextView) convertView.findViewById(R.id.university_id);

        university.setText( viewData.get(0));

        TextView language = (TextView) convertView.findViewById(R.id.language_id);

        language.setText( viewData.get(2));

        CheckBox check = (CheckBox) convertView.findViewById(R.id.selected);

        check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            //get information about which selections have been selected by users
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