package com.example.chahakgupta.testproject;

import android.content.Context;
import android.widget.Toast;

public class Message {

	public static void message(Context c,String str) {
		Toast.makeText(c, str, Toast.LENGTH_LONG).show();
		
	}
}
