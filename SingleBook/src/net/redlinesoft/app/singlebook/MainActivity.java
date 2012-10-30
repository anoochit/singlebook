package net.redlinesoft.app.singlebook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // onclick button
        Button openButton = (Button) findViewById(R.id.buttonOpen);
        openButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				Log.d("BOOK","Open Book");
				Intent newActivity = new Intent(MainActivity.this,BookActivity.class);
				newActivity.putExtra("BookID", "bsa");				
				startActivity(newActivity);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
     
    
}
