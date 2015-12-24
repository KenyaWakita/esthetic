package kenyawakita.sapuri;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Answer_Activity extends Activity {

    Activity activity;
    String category_name;
    String question_No;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        activity = this;
        final Button Back_button = (Button) findViewById(R.id.back_select_question_no);
        ImageView answer_imageView = (ImageView) findViewById(R.id.answer_imageView);

        View layout = findViewById(R.id.a_activity);

        //インテントでカテゴリー名の受け取り
        Intent i=getIntent();
        category_name = i.getStringExtra("category_name");
        question_No = i.getStringExtra("No");

        setTitle(category_name);

        switch(category_name) {
            case "リラックス":
                layout.setBackgroundResource(R.color.bootstrap_brand_success);
                break;
            case "数字":
                layout.setBackgroundResource(R.color.bootstrap_brand_info);
                break;
            case "人間観察力":
                layout.setBackgroundResource(R.color.bootstrap_brand_primary);
                break;
            case "ひらめき":
                layout.setBackgroundResource(R.color.bootstrap_brand_warning);
                break;
            case "IQテスト":
                layout.setBackgroundResource(R.color.bootstrap_brand_danger);
                break;

            default:
                break;
        }


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
                if (roo.getString("name").equals(category_name)){
                    JSONArray property = roo.getJSONArray("property");
                    String answer_image_name =
                            property.getJSONObject(Integer.parseInt(question_No)-1).getString("answer");

                    InputStream answer_image = this.getAssets().open(answer_image_name);
                    Bitmap answer_bitmap = BitmapFactory.decodeStream(answer_image);
                    answer_imageView.setImageBitmap(answer_bitmap);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                intent.putExtra("category_name",category_name);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        // GAスクリーン計測　第一引数:Context, 第二引数:スクリーン名
        MeasurementGAManager.sendGAScreen(this, "解答画面");
        // GAイベント計測　第一引数:Context, 第二引数:カテゴリ名, 第二引数:アクション名, 第二引数:ラベル名
        MeasurementGAManager.sendGAEvent(this, category_name, "問"+question_No,"Question_Activity");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //トップボタンを押した時の動作
        if (id==R.id.top){
            Intent intent = new Intent(activity, MainActivity.class);
            startActivityForResult(intent, 0);
        }

        return super.onOptionsItemSelected(item);
    }

}
