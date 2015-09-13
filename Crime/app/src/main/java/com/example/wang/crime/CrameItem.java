package com.example.wang.crime;

import android.app.Activity;
import android.app.Notification;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private ImageButton mPhotoBurron;
    private Uri imagetUri=null;
    private int Take_PHOTO=999;
    private int CROP_PHOTO=888;
    private int REQUEST_CONTACT=777;
    private ImageView iv;
    private Button mSuspectButton;
    private Button  mDialBUTTON;
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
                PickFragment pick = PickFragment.newInstance(item.getmData());
                pick.setTargetFragment(CrameItem.this, 0);
                pick.show(fm, "date");
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
        mPhotoBurron= (ImageButton) v.findViewById(R.id.crime_imageButton);
        mPhotoBurron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Filename=item.getmId().toString();
                File outputImage=new File(Environment.getExternalStorageDirectory(),Filename+".jpg");
                 imagetUri=Uri.fromFile(outputImage);
                Photo p=new Photo(imagetUri.toString());
                item.setPhoto(p);
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, imagetUri);
                startActivityForResult(i,Take_PHOTO);
            }
        });
        iv= (ImageView) v.findViewById(R.id.crime_imageView);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo p = item.getmPhoto();
                if (p == null) {
                    return;
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                String path = p.getFilename();
                ImageFragment.newInstance(path).show(fm, "DIALOG_IMAGE");
            }
        });
         registerForContextMenu(iv);
        Button reportButton=(Button)v.findViewById(R.id.crime_reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT,"犯罪记录");
                i=Intent.createChooser(i,"发送犯罪记录");
                startActivity(i);
            }
        });
        mSuspectButton= (Button) v.findViewById(R.id.crime_suspectButton);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i,REQUEST_CONTACT);
            }
        });
        mDialBUTTON= (Button) v.findViewById(R.id.crime_dialButton);
        if (item.getSuspect()!=null){
            mSuspectButton.setText("犯罪嫌疑人是"+item.getSuspect());
            mDialBUTTON.setText("打电话给"+item.getSuspect());
        }
        mDialBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+item.getmNumber()));
                startActivity(i);
            }
        });
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
        getActivity().setResult(Activity.RESULT_OK, null);
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
        if (requestCode==Take_PHOTO){
            Intent intent=new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(imagetUri, "image/*");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imagetUri);
            startActivityForResult(intent, CROP_PHOTO);
        }
        if (requestCode==CROP_PHOTO){
            try {
                Bitmap bitmap= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imagetUri));
                iv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==REQUEST_CONTACT){
            Uri contactUri=data.getData();
//            String[] queryFields=new String[]{
//                    ContactsContract.Contacts.DISPLAY_NAME
//            };
            android.database.Cursor c=getActivity().getContentResolver().query(contactUri, null, null, null, null);
            if (c.getCount()==0){
                c.close();
                return;
            }
            c.moveToFirst();
            String suspect=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneID=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                    + "=" + phoneID, null, null);
            phones.moveToFirst();
            String suspectNumber=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            item.setmSuspect(suspect);
            mSuspectButton.setText("犯罪嫌疑人是"+suspect);
            item.setmNumber(suspectNumber);
            mDialBUTTON.setText("打电话给"+suspect);
            phones.close();
            c.close();
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

    @Override
    public void onStart() {
        super.onStart();
        Photo p=item.getmPhoto();
        if (p==null){
            return;
        }
        FragmentManager fm=getActivity().getSupportFragmentManager();
        String path=p.getFilename();
        imagetUri=Uri.parse(path);
        Bitmap bitmap= null;
        try {
            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imagetUri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        iv.setImageBitmap(bitmap);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item1) {

        switch (item1.getItemId()){
            case R.id.menu_item_delete_crime:
                if (item.getmPhoto()==null){
                    return true;
                }
                Uri uri=Uri.parse(item.getmPhoto().getFilename());
                File file=new File(uri.getPath());
                file.delete();
                item.setPhoto(null);
                return true;
        }
        return super.onContextItemSelected(item1);
    }
    private String getCrimeReport(){
    String solvedString=null;
        if (item.getmSolved()){
            solvedString="问题解决了";
        }else {
            solvedString="问题没有解决";
        }
        String dateString= DateFormat.getDateTimeInstance().format(item.getmData());
        String suspect=item.getSuspect();
        if (suspect==null){
            suspect="没有犯罪嫌疑人";
        }else {
            suspect=getString(R.string.crime_report_susupect,suspect);
        }
        String report=getString(R.string.crime_report,item.getmTitle(),dateString,suspect,solvedString);
        return report;
    }

}
