package com.example.wang.crime;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/8.
 */
public class PickFragment extends DialogFragment {
    private Button buttonDATE,buttonTIME;
    private Item item;
    private Date mDate;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate= (Date) getArguments().getSerializable("Date");
        View v=getActivity().getLayoutInflater().inflate(R.layout.pick_layout,null);
        buttonDATE= (Button) v.findViewById(R.id.date_pick);
        buttonDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DiaPickFragment dialog = DiaPickFragment.newInstance(mDate);
                dialog.setTargetFragment(PickFragment.this, 0);
                dialog.show(fm, "date");
            }
        });
        buttonTIME= (Button) v.findViewById(R.id.time_pick);
        buttonTIME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FragmentManager fm=getActivity().getSupportFragmentManager();
                TimePickFragment timePickFragment=TimePickFragment.newInstance(mDate);
                timePickFragment.setTargetFragment(PickFragment.this,1);
                timePickFragment.show(fm,"time");
            }
        });
        return new AlertDialog.Builder(getActivity()).setTitle("设置时间还是日期？")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResukt(Activity.RESULT_OK);
                    }
                })
                .setView(v)
                .create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!= Activity.RESULT_OK) return;
        if (requestCode==0){
            mDate= (Date) data.getSerializableExtra("Date");
        }
        if (requestCode==1){
           Date mDate1= (Date) data.getSerializableExtra("Time");
            mDate.setHours(mDate1.getHours());
            mDate.setMinutes(mDate1.getMinutes());
        }
    }

    public static PickFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable("Date",date);
        PickFragment fragment = new PickFragment();
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
