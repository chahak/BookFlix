package com.example.chahakgupta.testproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Search_book extends Fragment{
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
        View rootview = inflater.inflate(R.layout.search_book, container, false);
		//setContentView(R.layout.booklist_ndfr);
	//	View rootview = inflater.inflate(R.layout.navdrawfrag2, container, false);
		//DBAdapter db = new DBAdapter(this);
		//name =  db.getalldatainList().toArray();
		//name = show.getalldatainList().toArray(new String[l.size()]);
		lv = (ListView) rootview.findViewById(R.id.listView2);
        ImageView iv = (ImageView) rootview.findViewById(R.id.gotocart);

        Bundle d = this.getArguments();
        String name = d.getString("result");
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Cartlist_ndfr fr1=new Cartlist_ndfr();
                ft.replace(R.id.fl,fr1);
                ft.commit();
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        query.whereEqualTo("Book_status", "not sold");
        query.whereEqualTo("Book_name",name);
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

        Button b = (Button)rootview.findViewById(R.id.showcart);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Cartlist_ndfr fr1=new Cartlist_ndfr();
                ft.replace(R.id.fl,fr1);
                ft.commit();
            }
        });
		//l = db.getallbooksinListnotsold();

		//**Bundle data1 = this.getArguments();
		//**String userroll = data1.getString("user").toString();
    return rootview;
	}
	/*public void showcart(View v){
		FragmentManager fm=getActivity().getSupportFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		Cartlist_ndfr fr1=new Cartlist_ndfr();
		ft.replace(R.id.fl,fr1);
		ft.commit();
	}*/
	/*public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
	
	public static int calculateInSampleSize(
	        BitmapFactory.Options options, int reqWidth, int reqHeight) {
	// Raw height and width of image
	final int height = options.outHeight;
	final int width = options.outWidth;
	int inSampleSize = 2;

	if (height > reqHeight || width > reqWidth) {
	    if (width > height) {
	        inSampleSize = Math.round((float)height / (float)reqHeight);
	    } else {
	        inSampleSize = Math.round((float)width / (float)reqWidth);
	    }
	}
	return inSampleSize;
	}
	public static Bitmap decodeSampledBitmapFromResource(String resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(resId, options);
	}*/
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
			textView3.setTextColor(Color.RED);
			final TextView textView4 = (TextView) row.findViewById(R.id.tvauthor);
			textView4.setText(barray[position].getString("Book_author"));
		//	final TextView textView5 = (TextView) row.findViewById(R.id.tvseller);
		//	textView5.setText(barray[position].getString("Book_seller"));
			final ImageView imageview = (ImageView) row.findViewById(R.id.image);
			int resID = getResources().getIdentifier(barray[position].getString("Book_image"), "drawable",context.getPackageName() );
          //  Drawable dr = getDrawable(resID);
			imageview.setImageResource(resID);
            //imageview.setImageDrawable(dr);
			final DBAdapter db = new DBAdapter(context);
			//Book book = db.getbook(barray[position].code);
			//String im = book.image;
			
			//****get image from dbadapter1
			/*DBAdapter1 db1 = new DBAdapter1(context);
			String name = db1.getbook(barray[position].code);
			Uri muri = Uri.parse(name);
			 s = getPath(muri);
		     System.out.println("Image Path : " + s);
		     imageview.setImageBitmap(decodeSampledBitmapFromResource(s, 200, 200));
		    // imageview.setImageURI(muri);*/
		/* **	DBAdapter1 db1 = new DBAdapter1(context);
			String name = db1.getbook(barray[position].code);
			Uri selectedImageUri = Uri.parse(name);
			String[] projection = { MediaColumns.DATA };
        	Cursor cursor = managedQuery(selectedImageUri, projection, null, null,null);
        	int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        	cursor.moveToFirst();
        	String selectedImagePath = cursor.getString(column_index);
        	Bitmap bm;
        	BitmapFactory.Options options = new BitmapFactory.Options();
        	options.inJustDecodeBounds = true;
        	BitmapFactory.decodeFile(selectedImagePath, options);
        	final int REQUIRED_SIZE = 200;
        	int scale = 1;
        	while (options.outWidth / scale / 2 >= REQUIRED_SIZE
        	&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
        	scale *= 2;
        	options.inSampleSize = scale;
        	options.inJustDecodeBounds = false;
        	bm = BitmapFactory.decodeFile(selectedImagePath, options);
        	imageview.setImageBitmap(bm);*/
          //  final String result;
            final Button b =(Button) row.findViewById(R.id.addcart);
            ParseUser user = ParseUser.getCurrentUser();
            String u = user.getString("username");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Cart_details");
            query.whereEqualTo("Current_user",u);
            query.whereEqualTo("Book_code",barray[position].getString("Book_code"));
            query.countInBackground(new CountCallback() {
                @Override
                public void done(int i, ParseException e) {
                    if (e == null) {
                        String result;
                        if (i > 0) {
                            result = "true";
                        }
                        else {

                            result = "false";
                        }
                       // b.setText(""+i);
                        if (result.equals("true")) {
                            b.setBackgroundColor(Color.LTGRAY);
                            b.setEnabled(false);
                            b.setText("in cart");

                        }
                    }
                        else {
                        Log.d("item", "Error: " + e.getMessage());

                        }
                    }

            });
		/*	String result;
			if(db.checkcartifempty().equals("yes"))
				result = "false";
			else
			    result = db.checkcart(barray[position].getString("Book_code"));

			if(result.equals("true")){
				b.setEnabled(false);
				b.setText("in cart");
			}*/
			
			b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					b.setEnabled(false);
					b.setText("in cart");
                    b.setBackgroundColor(Color.LTGRAY);
				//	DBAdapter db = new DBAdapter(context);
				//	String code = textView1.getText().toString();
					String code = barray[position].getString("Book_code");
                   // Message.message(context,""+code);
					String name =barray[position].getString("Book_name");
					String price = "Rs." + barray[position].getString("Book_price");
					String publish =barray[position].getString("Book_publisher");
					String author = barray[position].getString("Book_author");
                    String image = barray[position].getString("Book_image");
                    ParseUser user = ParseUser.getCurrentUser();
                    String u = user.getString("username");

				//	String condition = "try";

					db.insertcartdata(code,name,publish,author,price,image,u);

					
				}
			});
			row.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//Show_book sb = new Show_book();
					Bundle data = new Bundle();
					data.putString("pos", barray[position].getString("Book_code"));
                    FragmentManager fm=getActivity().getSupportFragmentManager();//
                    FragmentTransaction ft=fm.beginTransaction();
                    Show_book fr1=new Show_book();
                    fr1.setArguments(data);
                    ft.replace(R.id.fl,fr1);
                    ft.commit();
				//	Intent i1 = new Intent(getActivity(), Show_book.class);
				//	i1.putExtra("pos", barray[position].getString("Book_code"));
				//	startActivity(i1);
					
				}
			});

						return row;
			
		}
		
}


}
