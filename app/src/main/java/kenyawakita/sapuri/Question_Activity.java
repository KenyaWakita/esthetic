package kenyawakita.sapuri;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Question_Activity extends Activity {
    Activity activity;
    private SharedPreferences hintStore;
    DialogFragment dialogFragment;
    FragmentManager flagmentManager;
    String category_name;
    String question_No;
    String hint_image_name;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        hintStore = getSharedPreferences("DataStore", MODE_PRIVATE);

        activity=this;
        final EditText answer = (EditText) findViewById(R.id.answer);
        final Button next = (Button) findViewById(R.id.send_button);

        TextView question = (TextView) findViewById(R.id.question);
        ImageView question_imageview = (ImageView) findViewById(R.id.question_imageView);

        View layout = findViewById(R.id.q_activity);

        //インテントでカテゴリー名と問題番号の受け取り
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
                    String question_image_name =
                            property.getJSONObject(Integer.parseInt(question_No)-1).getString("question");
                    hint_image_name=
                            property.getJSONObject(Integer.parseInt(question_No) - 1).getString("hint");

                    InputStream question_image = this.getAssets().open(question_image_name);
                    Bitmap question_bitmap = BitmapFactory.decodeStream(question_image);
                    question_imageview.setImageBitmap(question_bitmap);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((answer.getText().toString().equals("B"))||(answer.getText().toString().equals("b"))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("正解です!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // ボタンをクリックしたときの動作

                                    Intent intent = new Intent(activity, Answer_Activity.class);
                                    intent.putExtra("category_name",category_name);
                                    intent.putExtra("No",question_No);
                                    startActivityForResult(intent, 0);
                                }
                            });
                    builder.show();
                }

                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("不正解です")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                // ボタンをクリックしたときの動作

                                }
                            })
                            .setNegativeButton("no",null);
                    builder.show();
                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        TextView question = (TextView) findViewById(R.id.question);

        //ヒントボタンを押した時の動作
        if (id == R.id.hint) {

            int hintcount = hintStore.getInt("input",-10);//"input"に何も入っていなければ，-10を代入
            if (hintcount!=-10) {
                question.setText(String.valueOf(hintcount));
            }

            //もし，ヒントチケットがなくなったら
            if(hintcount<0){
                Toast ts =Toast.makeText(this,"ヒントチケットが足りません",Toast.LENGTH_SHORT);
                ts.show();
            }

            //ヒントチケットがまだ残っていたら，ヒントチケットを減らして，ヒントを表示
            else {
                //ヒントカウントを減らす
                hintcount--;
                //ヒントボタンを押した時のポップアップについて
                flagmentManager = getFragmentManager();
                dialogFragment = new AlertDialogFragment(this);
                Bundle bundle_url = new Bundle();
                bundle_url.putString("hint", hint_image_name);

                dialogFragment.setArguments(bundle_url);

                dialogFragment.show(flagmentManager, "test alert dialog");
            }



            SharedPreferences.Editor editor = hintStore.edit();
            editor.putInt("input", hintcount);
            editor.commit();

            return true;
        }


        if (id == R.id.answer) {

            int hintcount = hintStore.getInt("input",-10);
            if (hintcount!=-10) {
                question.setText(String.valueOf(hintcount));
            }
            //ヒントカウントを減らす
            hintcount++;

            SharedPreferences.Editor editor = hintStore.edit();
            editor.putInt("input", hintcount);
            editor.commit();
            return true;
        }

        //トップボタンを押した時の動作
        if (id==R.id.top){
            Intent intent = new Intent(activity, MainActivity.class);
            startActivityForResult(intent, 0);
        }

        return super.onOptionsItemSelected(item);
    }

}