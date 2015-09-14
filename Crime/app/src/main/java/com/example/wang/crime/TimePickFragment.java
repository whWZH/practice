package com.example.wang.crime;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Administrator on 2015/9/8.
 */
public class TimePickFragment extends DialogFragment {
    private Date mDate;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate= (Date) getArguments().getSerializable("Time");
        View v=getActivity().getLayoutInflater().inflate(R.layout.time_pick,null);
        TimePicker timePicker= (TimePicker) v.findViewById(R.id.time_layout_timepick);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minut) {
                mDate.setHours(hourOfDay);
                mDate.setMinutes(minut);
                getArguments().putSerializable("Time",mDate);
            }
        });
        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle("犯罪的时间")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent();
                        i.putExtra("Time",mDate);
                        getTargetFragment().onActivityResult(1, Activity.RESULT_OK,i);
                    }
                })
                .create();
    }

    public static TimePickFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable("Time",date);
        TimePickFragment fragment = new TimePickFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
