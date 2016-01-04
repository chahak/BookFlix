package com.example.chahakgupta.testproject;

import java.util.List;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PagerFrag2 extends Fragment{
    private ListView lv;
    //private List<Book> l;
    ParseObject[] bname;
    Context ctx;
    String s;
    DBAdapter db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //super.onCreate(savedInstanceState);
        ctx = getActivity();
        View rootview = inflater.inflate(R.layout.pagerfrag2, container, false);
        //setContentView(R.layout.booklist_ndfr);
        //	View rootview = inflater.inflate(R.layout.navdrawfrag2, container, false);
        //DBAdapter db = new DBAdapter(this);
        //name =  db.getalldatainList().toArray();
        //name = show.getalldatainList().toArray(new String[l.size()]);
     /*   lv = (ListView) rootview.findViewById(R.id.listViewp);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        query.whereEqualTo("Book_status", "not sold");
        query.whereEqualTo("flag",1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> itemList, ParseException e) {
                if (e == null) {

                    bname = new ParseObject[itemList.size()];
                    bname = itemList.toArray(bname);
                    adapter ad =   new adapter(ctx, bname);
                    lv.setAdapter(ad);
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });

        //l = db.getallbooksinListnotsold();

        //**Bundle data1 = this.getArguments();
        //**String userroll = data1.getString("user").toString();*/
        return rootview;
    }
    class adapter extends ArrayAdapter<ParseObject>{

        Context context;
        ParseObject barray[];
        String userroll;
        public adapter(Context c, ParseObject btitles[]) {
            // TODO Auto-generated constructor stub
            super(c, R.layout.rowview,btitles);
            this.context = c;
            this.barray=btitles;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


            final View row = layoutInflater.inflate(R.layout.rowview, parent,false);
            //final TextView textView1 = (TextView) row.findViewById(R.id.tvcode);
            //textView1.setText(barray[position].code);
            //if(bname[position].status.equals("not sold")){

            final TextView textView2 = (TextView) row.findViewById(R.id.tvname);
            textView2.setText(barray[position].getString("Book_name"));
            // Message.message(context,barray[position].name);
            final TextView textView3 = (TextView) row.findViewById(R.id.tvprice);
            textView3.setText("Rs." + barray[position].getString("Book_price"));
   //         textView3.setTextColor(Color.RED);
            final TextView textView4 = (TextView) row.findViewById(R.id.tvauthor);
            textView4.setText(barray[position].getString("Book_author"));
            //	final TextView textView5 = (TextView) row.findViewById(R.id.tvseller);
            //	textView5.setText(barray[position].getString("Book_seller"));
            final ImageView imageview = (ImageView) row.findViewById(R.id.image);
            if(barray[position].getString("image_status").equals("false")) {
                int resID = getResources().getIdentifier(barray[position].getString("Book_image"), "drawable", context.getPackageName());
                //  Drawable dr = getDrawable(resID);
                imageview.setImageResource(resID);
            }
            else{
                ParseFile file = barray[position].getParseFile("file");
                file.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, ParseException e) {
                        Bitmap bmp = BitmapFactory
                                .decodeByteArray(bytes, 0,bytes.length);
                        imageview.setImageBitmap(bmp);
                    }
                });
            }
            //imageview.setImageDrawable(dr);
            final DBAdapter db = new DBAdapter(context);




           /* final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Image");
            query2.whereEqualTo("Image",barray[position].getString("Book_code"));
            query2.countInBackground(new CountCallback() {
                @Override
                public void done(int i, ParseException e) {
                    if (i > 0) {
                        query2.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects, ParseException e) {
                                ParseFile file = (ParseFile) parseObjects.get(0).get("ImageFile");
                                file.getDataInBackground(new GetDataCallback() {
                                    @Override
                                    public void done(byte[] bytes, ParseException e) {
                                        Bitmap bmp = BitmapFactory
                                                .decodeByteArray(bytes, 0, bytes.length);
                                        imageview.setImageBitmap(bmp);
                                    }
                                });
                            }
                        });
                    }
                }
            });
*/

            final Button b =(Button) row.findViewById(R.id.addcart);
            b.setVisibility(View.INVISIBLE);
		/*	String result;
			if(db.checkcartifempty().equals("yes"))
				result = "false";
			else
			    result = db.checkcart(barray[position].getString("Book_code"));

			if(result.equals("true")){
				b.setEnabled(false);
				b.setText("in cart");
			}*/

           // ImageView iv = (ImageView)row.findViewById(R.id.iv_next);
            //iv.setVisibility(View.INVISIBLE);
            //Button b1 = (Button)row.findViewById(R.id.show);
        /*    row.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //Show_book sb = new Show_book();
                    Bundle data = new Bundle();
                    data.putString("pos", barray[position].getObjectId());
                    FragmentManager fm=getActivity().getSupportFragmentManager();//
                    FragmentTransaction ft=fm.beginTransaction();
                    Show_book fr1=new Show_book();
                    fr1.setArguments(data);
                    ft.replace(R.id.flp,fr1);
                    ft.addToBackStack(null);
                    ft.commit();
                    //	Intent i1 = new Intent(getActivity(), Show_book.class);
                    //	i1.putExtra("pos", barray[position].getString("Book_code"));
                    //	startActivity(i1);

                }
            });*/

            return row;

        }

    }

}
