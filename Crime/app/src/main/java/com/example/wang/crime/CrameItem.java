package com.example.wang.crime;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrameItem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrameItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrameItem extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_crame_item,container,false);
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
        mDataButton.setEnabled(false);
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
}
