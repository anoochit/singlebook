package net.redlinesoft.app.singlebook;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView.ScaleType;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class BookActivity extends Activity implements ViewFactory,
		OnGestureListener {

	private static final int SWIPE_MIN_DISTANCE = 50;
	private static final int SWIPE_MAX_OFF_PATH = 80;
	private static final int SWIPE_THRESHOLD_VELOCITY = 100;
	int position = 0;
	int i = 0;
	ImageSwitcher iSwitcher;
	ImageViewTouch mImage;
	GestureDetector gd;

	int[] mImageIds = { R.drawable.bsa000, R.drawable.bsa001,
			R.drawable.bsa002, R.drawable.bsa003, R.drawable.bsa004,
			R.drawable.bsa005, R.drawable.bsa006, R.drawable.bsa007,
			R.drawable.bsa008, R.drawable.bsa009, R.drawable.bsa010,
			R.drawable.bsa011, R.drawable.bsa012, R.drawable.bsa013,
			R.drawable.bsa014, R.drawable.bsa015, R.drawable.bsa016,
			R.drawable.bsa017, R.drawable.bsa018, R.drawable.bsa019,
			R.drawable.bsa020, R.drawable.bsa021, R.drawable.bsa022,
			R.drawable.bsa023 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,1);

		setContentView(R.layout.activity_book);

		Intent intent = getIntent();
		final String BookID = intent.getStringExtra("BookID");
		Log.d("BOOK", "ID=" + BookID);

		// load cover image index=0
		loadPage(0);

		gd = new GestureDetector(this, this);

		final SeekBar seek_Bar = (SeekBar)findViewById(R.id.seekBar);
		seek_Bar.setMax(mImageIds.length-1);
		
		seek_Bar.setOnSeekBarChangeListener( new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				loadPage(arg1);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
	}

	void loadPage(int index) {
		// initial image view touch
		mImage = (ImageViewTouch) findViewById(R.id.image);
		mImage.setImageBitmap(BitmapFactory.decodeResource(getResources(),mImageIds[index]));		
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		try {
			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
				return false;
			// right to left swipe
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				setPositionNext();
				// iSwitcher.setImageResource(mImageIds[position]);
				loadPage(position);
				Toast.makeText(BookActivity.this, position, Toast.LENGTH_SHORT)
						.show();

			} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
				// left to right flip
				setPositionPrev();
				// iSwitcher.setImageResource(mImageIds[position]);
				loadPage(position);
				Toast.makeText(BookActivity.this, position, Toast.LENGTH_SHORT)
						.show();
			}
		} catch (Exception e) {
			// nothing
			return true;
		}

		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (gd.onTouchEvent(event))
			return true;
		else
			return false;
	}

	public void setPositionNext() {
		position++;
		if (position > mImageIds.length - 1) {
			position = 0;
		}
	}

	public void setPositionPrev() {
		position--;
		if (position < 0) {
			position = mImageIds.length - 1;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_book, menu);
		return true;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		return null;
	}

}
