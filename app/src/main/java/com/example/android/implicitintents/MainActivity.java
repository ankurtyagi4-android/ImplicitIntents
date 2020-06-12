package com.example.android.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

	private EditText mWebsiteEditText;
	private EditText mLocationEditText;
	private EditText mShareTextEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWebsiteEditText = findViewById(R.id.website_edittext);
		mLocationEditText = findViewById(R.id.location_edittext);
		mShareTextEditText = findViewById(R.id.share_edittext);
	}

	public void openWebsite(View view) {
		String url = mWebsiteEditText.getText().toString();
		Uri webpage = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		} else {
			Log.d("ImplicitIntents", "Can't handle this!");
			Toast.makeText(this, "Can't handle this!", Toast.LENGTH_SHORT).show();
		}
	}

	public void openLocation(View view) {
		String loc = mLocationEditText.getText().toString();
		Uri adddressUri = Uri.parse("geo:0,0?q=" + loc);
		Intent intent = new Intent(Intent.ACTION_VIEW, adddressUri);
		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		} else {
			Log.d("ImplicitIntents", "Can't handle this!");
			Toast.makeText(this, "Can't handle this!", Toast.LENGTH_SHORT).show();
		}
	}

	public void shareText(View view) {
		String txt = mShareTextEditText.getText().toString();
		String mimeType = "text/plain";
		ShareCompat.IntentBuilder intentBuilder = ShareCompat.IntentBuilder.from(this)
				.setType(mimeType)
				.setChooserTitle(R.string.share_text_with)
				.setText(txt);
		intentBuilder.startChooser();
	}
}
