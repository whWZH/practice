package com.example.wang.crime;

import android.app.Activity;
import android.content.Intent;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrameItem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrameItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrameItem extends android.support.v4.app.Fragment {

    private Item item;
    private EditText editText;
    private Button mDataButton;
    private CheckBox mCheckBox;
    public CrameItem() {
           item=new Item();
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       UUID mID= (UUID) getArguments().getSerializable("mID");
        item=CrimeLab.getsCrimeLab(getActivity()).getItem(mID);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_crame_item,container,false);
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        editText= (EditText) v.findViewById(R.id.crime_title);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                item.setmTitle(s.toString());
            }
        });
        mDataButton= (Button) v.findViewById(R.id.crime_data);
        String dateS;
        dateS=DateFormat.getDateTimeInstance().format(item.getmData());
        mDataButton.setText(dateS);
        mDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                PickFragment pick= PickFragment.newInstance(item.getmData());
                pick.setTargetFragment(CrameItem.this,0);
                pick.show(fm,"date");
            }
        });
        mCheckBox= (CheckBox) v.findViewById(R.id.cerame_solve);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setmSolved(isChecked);
            }
        });
        mCheckBox.setChecked(item.getmSolved());
        editText.setText(item.getmTitle());
        return v;
    }

    public static CrameItem newInstance(UUID mID) {

        Bundle args = new Bundle();
        args.putSerializable("mID",mID);
        CrameItem fragment = new CrameItem();
        fragment.setArguments(args);
        return fragment;
    }
    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK,null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!=Activity.RESULT_OK) return;
        if (requestCode==0){
            Date date= (Date) data.getSerializableExtra("Date");
            item.setmData(date);
            String dateS;
            dateS=DateFormat.getDateTimeInstance().format(item.getmData());
            mDataButton.setText(dateS);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.getsCrimeLab(getActivity()).sveItems();
    }
}
