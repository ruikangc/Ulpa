/*
 *This class if the adapter for Activity whether_intensive, used to show what kinds of study
 * the subjects are
 */

package com.example.testing;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.BaseAdapter;
/**
 * Created by CRK on 2017/10/3.
 */
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class adapter_whether_intensive extends BaseAdapter {

    private Context context;

    private ArrayList<String> whetehr_on_campus_shown_combo;

    //used to identify which options selected
    HashMap<Integer,Boolean> check_data = new HashMap<Integer, Boolean>();

    //adapter constructor
    public adapter_whether_intensive(Context context, ArrayList<String> whetehr_on_campus_shown_combo) {

        this.context = context;

        this.whetehr_on_campus_shown_combo = whetehr_on_campus_shown_combo;

    }

    public void updateStates(ArrayList<String> whetehr_on_campus_shown_combo) {

        this.whetehr_on_campus_shown_combo= whetehr_on_campus_shown_combo;

    }

    @Override

    public int getCount() {

        // TODO Auto-generated method stub

        if (whetehr_on_campus_shown_combo==null){

            return 0;

        }

        return whetehr_on_campus_shown_combo.size();

    }

    @Override

    public String getItem(int position) {

        // TODO Auto-generated method stub

        return whetehr_on_campus_shown_combo.get(position);

    }

    @Override

    public long getItemId(int position) {

        // TODO Auto-generated method stub

        return position;

    }

    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = LayoutInflater.from(context);

        convertView = mInflater.inflate(R.layout.whether_intensive_everyitem_layout, null);

        String viewData = whetehr_on_campus_shown_combo.get(position);

        TextView whether_on_campus = (TextView) convertView.findViewById(R.id.whether_intensive);

        whether_on_campus.setText( viewData);

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