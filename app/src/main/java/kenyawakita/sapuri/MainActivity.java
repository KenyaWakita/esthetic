package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class MainActivity extends Activity {

    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //mainのthis
        activity = this;
        final Button start_button = (Button) findViewById(R.id.start);
        ImageView home_imageView = (ImageView) findViewById(R.id.home_imageView);

        InputStream home_image = null;
        try {
            home_image = this.getAssets().open("home.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap home_bitmap = BitmapFactory.decodeStream(home_image);
        home_imageView.setImageBitmap(home_bitmap);


        start_button.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
            @Override
            public void onClick(View v) {
                // ここに処理を記述する
                Intent intent = new Intent(activity, Categoryselect_Activity.class);
                startActivityForResult(intent, 0);

            }
        });

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
