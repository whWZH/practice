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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.transform.Result;

/**
 * Created by Administrator on 2015/9/8.
 */
public class DiaPickFragment extends DialogFragment {
    private Date mDate;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate=(Date)getArguments().getSerializable("Date");
       final int hour=mDate.getHours();
        final int minis=mDate.getMinutes();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        int year=calendar.get(Calendar.YEAR);
         int month1=calendar.get(Calendar.MONTH);
        int day1=calendar.get(Calendar.DAY_OF_MONTH);
        View v=getActivity().getLayoutInflater().inflate(R.layout.layout, null);
        DatePicker datePicker= (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month1, day1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate=new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                mDate.setHours(hour);
                mDate.setMinutes(minis);
                getArguments().putSerializable("Date",mDate);
            }
        });
        return new AlertDialog.Builder(getActivity()).setTitle("犯罪的日期").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendResukt(Activity.RESULT_OK);
            }
        })
                .setView(v)
                .create();
    }
    public static DiaPickFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable("Date",date);
        DiaPickFragment fragment = new DiaPickFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void sendResukt(int resultcode){
        if (getTargetFragment()==null){
            return;
        }
        else {
            Intent i=new Intent();
            i.putExtra("Date", mDate);
            getTargetFragment().onActivityResult(0, resultcode, i);
        }
    }
}
