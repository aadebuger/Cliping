package com.example.test;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test.widget.ClipImageView;

public class MainActivity extends Activity {

	private ClipImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ClipImageView) findViewById(R.id.src_pic);
		// 设置需要裁剪的图片
		imageView.setImageResource(R.drawable.test_pic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_clip){
			// 此处获取剪裁后的bitmap
			Bitmap bitmap = imageView.clip();
			
			// 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] bitmapByte = baos.toByteArray();
			
			Intent intent = new Intent(this, PreviewActivity.class);
			intent.putExtra("bitmap", bitmapByte);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
