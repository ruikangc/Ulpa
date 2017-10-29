/*
 * The adapter for Activity multiple_university used to show the selections
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

public class adapter_multiple_university extends BaseAdapter {

    private Context context;

    private ArrayList<String> listData;

    //Hashmap identify the selected options
    HashMap<Integer,Boolean> check_data = new HashMap<Integer, Boolean>();

    //class constructor
    public adapter_multiple_university(Context context, ArrayList<String> listData) {

        this.context = context;

        this.listData = listData;

    }

    //update the resource after back ground process finished
    public void updateStates(ArrayList<String> all_states_available) {

        this.listData= all_states_available;

    }

    @Override

    public int getCount() {

    // TODO Auto-generated method stub

        if (listData==null){

            return 0;

        }

        return listData.size();

    }

    @Override

    public String getItem(int position) {

     // TODO Auto-generated method stub

        return listData.get(position);

    }

    @Override

    public long getItemId(int position) {

        // TODO Auto-generated method stub

        return position;

    }

    @Override

    //generate the view user can see of selections available
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        convertView = mInflater.inflate(R.layout.multiple_university_every_item, null);

        TextView id = (TextView) convertView.findViewById(R.id.friend_id);

        id.setText( listData.get(position));

        CheckBox check = (CheckBox) convertView.findViewById(R.id.selected);

        //identify the selected ones done by users
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