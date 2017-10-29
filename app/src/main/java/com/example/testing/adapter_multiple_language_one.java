/*
 *This class is the adapter of Activity multiple_language_one, used to match the list
 * of languages
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

public class adapter_multiple_language_one extends BaseAdapter {

    private Context context;

    //the list contains the source data for the shown list
    ArrayList<String> language_found= new ArrayList<>();

    //used to store the selected options
    HashMap<Integer,Boolean> check_data = new HashMap<Integer, Boolean>();

    //adapter constructor
    public adapter_multiple_language_one(Context context, ArrayList<String> language_found) {

        this.context = context;

        this.language_found = language_found;

    }

    //used to update the shown list after data have been processed in back ground
    public void updateStates(ArrayList<String> language_found) {

        this.language_found= language_found;

    }

    @Override

    //get how many contents need to show
    public int getCount() {

        // TODO Auto-generated method stub

        if (language_found==null){

            return 0;

        }

        return language_found.size();

    }

    @Override

    //response with the  required item in shown list
    public String getItem(int position) {

        // TODO Auto-generated method stub

        return language_found.get(position);
    }

    @Override

    public long getItemId(int position) {

        // TODO Auto-generated method stub

        return position;

    }

    @Override

    //generate the view which can be seen by users about the available selections
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        convertView = mInflater.inflate(R.layout.multiple_language_one_everyitem_layout, null);

        String viewData = language_found.get(position);

        TextView id = (TextView) convertView.findViewById(R.id.language_id);

        id.setText( viewData);

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