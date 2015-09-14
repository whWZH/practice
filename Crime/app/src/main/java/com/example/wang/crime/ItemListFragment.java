package com.example.wang.crime;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

/**
 * Created by Administrator on 2015/9/6.
 */
public class ItemListFragment extends ListFragment {
    private ArrayList<Item> mItems;
    private mAdapter mad;
    private boolean mSubtitleVisible;
    private Callbacks mCallbacks;
    public interface Callbacks{
        void onCrimeselected(Item item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks= (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mItems=CrimeLab.getsCrimeLab(getActivity()).getmItems();
        mad=new mAdapter(mItems,getActivity().getApplicationContext());
        setListAdapter(mad);
        setRetainInstance(true);
        mSubtitleVisible=false;
        getActivity().setTitle("Crime");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Item i= mItems.get(position);
//        Intent intent=new Intent(getActivity(),CrimePagerActivity.class);
//        intent.putExtra("ID", i.getmId());
//        startActivityForResult(intent,0);
        mCallbacks.onCrimeselected(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((mAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0){

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
        MenuItem showSubtitle=menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible&&showSubtitle!=null){
            showSubtitle.setTitle(R.string.hide_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crime:
                Item item1=new Item();
                CrimeLab.getsCrimeLab(getActivity()).addCrime(item1);
                ((mAdapter)getListAdapter()).notifyDataSetChanged();
                mCallbacks.onCrimeselected(item1);
                return true;
//            case R.id.menu_item_show_subtitle:
//                if (getActivity().getActionBar().getSubtitle()==null){
////                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
////                    mSubtitleVisible=true;
////                    item.setTitle(R.string.hide_subtitle);
//                }else {
////                    getActivity().getActionBar().setSubtitle(null);
////                    mSubtitleVisible=false;
////                    item.setTitle(R.string.show_subtitle);
//                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.itemlist_fragment,container,false);
        if (mSubtitleVisible){
            getActivity().getActionBar().setSubtitle(R.string.subtitle);
        }
        ListView listView=(ListView)v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater1=mode.getMenuInflater();
                inflater1.inflate(R.menu.crime_list_item_context,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_item_delete_crime:
                        mAdapter adapter= (mAdapter) getListAdapter();
                        CrimeLab crimeLab=CrimeLab.getsCrimeLab(getActivity());
                        for (int i=adapter.getCount()-1;i>=0;i--){
                            if (getListView().isItemChecked(i)){
                                crimeLab.deleteCrime((Item) adapter.getItem(i));
                            }
                        }
                        mode.finish();
                        adapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        registerForContextMenu(listView);
        TextView tv= (TextView) v.findViewById(R.id.list_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item=new Item();
                CrimeLab.getsCrimeLab(getActivity()).addCrime(item);
                Intent i=new Intent(getActivity(),CrimePagerActivity.class);
                i.putExtra("ID",item.getmId());
                startActivityForResult(i,0);
            }
        });
        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position=info.position;
        mAdapter adapter= (mAdapter) getListAdapter();
        Item item1= (Item) adapter.getItem(position);
        switch (item.getItemId()){
            case R.id.menu_item_delete_crime:
                CrimeLab.getsCrimeLab(getActivity()).deleteCrime(item1);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
