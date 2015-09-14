package com.wzh.nerdlauncher;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2015/9/14.
 */
public class NerdLauncherFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intent startupIntent=new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm=getActivity().getPackageManager();
        List<ResolveInfo> activities=pm.queryIntentActivities(startupIntent,0);
        System.out.println("我找到了" + activities.size() + "个Activities.");
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo lhs, ResolveInfo rhs) {
                PackageManager pm = getActivity().getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        lhs.loadLabel(pm).toString(),
                        rhs.loadLabel(pm).toString()
                );
            }
        });
        ArrayAdapter<ResolveInfo> adapter=new ArrayAdapter<ResolveInfo>(getActivity(),android.R.layout.simple_list_item_1,activities){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                PackageManager pm=getActivity().getPackageManager();
                View v=super.getView(position, convertView, parent);
                TextView tv= (TextView) v;
                ResolveInfo ri=getItem(position);
                tv.setText(ri.loadLabel(pm));
                return v;
            }
        };
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ResolveInfo resolveInfo= (ResolveInfo) l.getAdapter().getItem(position);
        ActivityInfo activityInfo=resolveInfo.activityInfo;
        if (activityInfo==null) return;
        Intent i=new Intent(Intent.ACTION_MAIN);
        i.setClassName(activityInfo.applicationInfo.packageName,activityInfo.name);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}


