package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends Activity {

    Activity activity;
    static ArrayList<FetchResource> resource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resource.clear();

        //mainのthis
        activity = this;
        final Button start_button = (Button) findViewById(R.id.start);
        ImageView top_imageView = (ImageView) findViewById(R.id.home_imageView);

        InputStream home_image = null;
        try {
            home_image = this.getAssets().open("top.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap home_bitmap = BitmapFactory.decodeStream(home_image);
        top_imageView.setImageBitmap(home_bitmap);



        InputStream is = null;
        BufferedReader br = null;
        //assertsフォルダにあるresource.jsonをInputStream型でとってくる
        try {
            is = this.getAssets().open("resource.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //InputStream型からString型に変換
        StringBuilder sb = new StringBuilder();
        String line;
        br = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //JsonObject型に変換
        try {
            JSONObject json = new JSONObject(sb.toString());
            JSONArray category = json.getJSONArray("category");
            for(int l=0; l < category.length(); l++){
                JSONObject roo = category.getJSONObject(l);

                JSONArray property = roo.getJSONArray("property");
                for(int j=0; j < property.length(); j++){

                    resource.add(new FetchResource(
                            roo.getString("name"),
                            property.getJSONObject(j).getString("question"),
                            property.getJSONObject(j).getString("answer"),
                            property.getJSONObject(j).getString("hint"),
                            property.getJSONObject(j).getString("index")
                            )
                    );
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
    protected void onStart(){
        super.onStart();
        // GAスクリーン計測　第一引数:Context, 第二引数:スクリーン名
        MeasurementGAManager.sendGAScreen(this, "ホーム");
    }


}
