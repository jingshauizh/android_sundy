package de.hdodenhof.circleimageview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class HideActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hide_main);
		((ImageView)findViewById(R.id.im_view_object)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("HideActivity", "startActivity");
				Intent mintent = new Intent(v.getContext(), MainActivity.class);
				startActivity(mintent);
			}
		});
	}


}
