package kenyawakita.sapuri;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Question_Activity extends Activity {
    Activity activity;
    private SharedPreferences Store;
    DialogFragment dialogFragment;
    FragmentManager flagmentManager;
    String category_name;
    String question_No;
    String question_image_name;
    String hint_image_name;
    BitmapDrawable  question_bitmap_drawable;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Store = getSharedPreferences("DataStore", MODE_PRIVATE);

        activity=this;
        final EditText answer = (EditText) findViewById(R.id.answer);
        final Button next = (Button) findViewById(R.id.send_button);

        View layout = findViewById(R.id.q_activity);

        //インテントでカテゴリー名と問題番号の受け取り
        Intent i=getIntent();
        category_name = i.getStringExtra("category_name");
        question_No = i.getStringExtra("No");

        setTitle(category_name);



        question_bitmap_drawable = SearchResource.getQuestion(category_name, Integer.parseInt(question_No),this);
        layout.setBackground(question_bitmap_drawable);

        //ヒント画像を代入(String型 Bitmapに統一させたい)
        hint_image_name = SearchResource.getHint(category_name, Integer.parseInt(question_No));


        //ヒントボタンを押した時のポップアップについて
        flagmentManager = getFragmentManager();
        dialogFragment = new AlertDialogFragment(this);
        Bundle bundle_url = new Bundle();
        bundle_url.putString("hint", hint_image_name);

        //ヒントのダイアログ
//        dialogFragment.setArguments(bundle_url);
//
//        dialogFragment.show(flagmentManager, "test alert dialog");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //表示されている問題の答えを取得
                String answer_true =
                        SearchResource.getAnswer(category_name,Integer.parseInt(question_No),activity);
                //もし正解したら
                if (answer.getText().toString().equals(answer_true.toUpperCase())||
                        (answer.getText().toString().equals(answer_true.toLowerCase())) ) {

                    //正解したフラグをpreferenceに格納
                    String question_filename =
                            SearchResource.getQuestion_filename(category_name,Integer.parseInt(question_No),activity);
                    SharedPreferences.Editor editor = Store.edit();
                    editor.putBoolean(question_filename,true );
                    editor.commit();


                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("正解です!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // ボタンをクリックしたときの動作

                                    Intent intent = new Intent(activity, Description_Activity.class);
                                    intent.putExtra("category_name", category_name);
                                    intent.putExtra("No", question_No);
                                    startActivityForResult(intent, 0);
                                    activity.finish();
                                }
                            });
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("不正解です")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // ボタンをクリックしたときの動作

                                }
                            });
                    builder.show();
                }
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        // GAスクリーン計測　第一引数:Context, 第二引数:スクリーン名
        MeasurementGAManager.sendGAScreen(this, "問題画面");
        // GAイベント計測　第一引数:Context, 第二引数:カテゴリ名, 第二引数:アクション名, 第二引数:ラベル名
        MeasurementGAManager.sendGAEvent(this, category_name, "問"+question_No,"Question_Activity");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description, menu);
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
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    //メニューバーにヒントボタンあり
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        TextView question = (TextView) findViewById(R.id.question);
//
//        //ヒントボタンを押した時の動作
//        if (id == R.id.hint) {
//
//            int hintcount = Store.getInt("input",-10);//"input"に何も入っていなければ，-10を代入
//            if (hintcount!=-10) {
//                question.setText(String.valueOf(hintcount));
//            }
//
//            //もし，ヒントチケットがなくなったら
//            if(hintcount<0){
//                Toast ts =Toast.makeText(this,"ヒントチケットが足りません",Toast.LENGTH_SHORT);
//                ts.show();
//            }
//
//            //ヒントチケットがまだ残っていたら，ヒントチケットを減らして，ヒントを表示
//            else {
//                //ヒントカウントを減らす
//                hintcount--;
//                //ヒントボタンを押した時のポップアップについて
//                flagmentManager = getFragmentManager();
//                dialogFragment = new AlertDialogFragment(this);
//                Bundle bundle_url = new Bundle();
//                bundle_url.putString("hint", hint_image_name);
//
//                dialogFragment.setArguments(bundle_url);
//
//                dialogFragment.show(flagmentManager, "test alert dialog");
//            }
//
//            SharedPreferences.Editor editor = Store.edit();
//            editor.putInt("input", hintcount);
//            editor.commit();
//
//            return true;
//        }
//
//
//        if (id == R.id.answer) {
//
//            int hintcount = Store.getInt("input",-10);
//            if (hintcount!=-10) {
//                question.setText(String.valueOf(hintcount));
//            }
//            //ヒントカウントを減らす
//            hintcount++;
//
//            SharedPreferences.Editor editor = Store.edit();
//            editor.putInt("input", hintcount);
//            editor.commit();
//            return true;
//        }
//
//        //トップボタンを押した時の動作
//        if (id==R.id.top) {
//            Intent intent = new Intent(activity, MainActivity.class);
//            startActivityForResult(intent, 0);
//            this.finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }

}