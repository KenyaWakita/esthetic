package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Select_QuestionNo_Activity extends Activity implements View.OnClickListener {

    Activity activity;
    int focused_button_id =-1;
    String category_name;
    private SharedPreferences correct_flag;
    boolean[] correct_flagBoolean;
    int category_size=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_question_no);
        activity = this;

        TableLayout tableLayout = (TableLayout) findViewById(R.id.No_Table);

        View layout = findViewById(R.id.question_No_activity);


        //プレファレンス
        correct_flag = getSharedPreferences("DataStore", MODE_PRIVATE);


        //インテントでカテゴリー名の受け取り
        Intent i = getIntent();
        category_name = i.getStringExtra("category_name");
        setTitle(category_name);
//        switch (category_name) {
//            case "リラックス":
//                layout.setBackgroundResource(R.color.bootstrap_brand_success);
//                break;
//            case "数字":
//                layout.setBackgroundResource(R.color.bootstrap_brand_info);
//                break;
//            case "人間観察力":
//                layout.setBackgroundResource(R.color.bootstrap_brand_primary);
//                break;
//            case "図形":
//                layout.setBackgroundResource(R.color.bootstrap_brand_warning);
//                break;
//            case "IQテスト":
//                layout.setBackgroundResource(R.color.bootstrap_brand_danger);
//                break;
//            default:
//                break;
//        }



        //選択されているカテゴリーの問題数を計算
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                category_size++;
            }
        }


        correct_flagBoolean = new boolean[category_size];
        //正解しているかどうかを配列に格納
        for(int k =0; k<category_size; k++) {
            String question_filename = SearchResource.getQuestion_filename(category_name,k+1,this);
                    correct_flagBoolean[k]=correct_flag.getBoolean(question_filename, false);//question_fileに何も入っていなければ，falseを代入(正解していない)
        }


        TableRow row;
        TableRow.LayoutParams row_layout_params
                = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT); // -2はLayoutParams.WRAP_CONTENT

        // Layout用
        LinearLayout[] array_layout_view = new LinearLayout[category_size];

        // button用
        Button[] array_button_view = new Button[category_size];

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int y;

        for(y = 0; y < (category_size/3); ++y){
            Log.d("log", String.valueOf(y));
            // 行ごとにRowの初期化
            row = new TableRow(this);
            row.setLayoutParams(row_layout_params);

            for(int x = 0; x < 3; ++x){ // 列
                int index = x + y * 3;
                array_layout_view[index] = new LinearLayout(this);
                array_button_view[index] = new Button(this);

                array_button_view[index].setId(index);
                array_button_view[index].setWidth(width / 4);
                array_button_view[index].setHeight(width / 4);


                BitmapDrawable bitmapDrawable;
                //もし正解していたらsolvedアイコンをセット
                if(correct_flagBoolean[index]){
                    bitmapDrawable = SearchResource.getQuestion_No_solved(
                            category_name,index+1,this);
                }
                else{
                bitmapDrawable = SearchResource.getQuestion_No(
                        category_name,index+1,this);
                }
                //問題番号の画像をset
                array_button_view[index].setBackground(bitmapDrawable);

                array_layout_view[index].addView(array_button_view[index]);
                array_layout_view[index].setPadding(50,20,30,70);
                array_button_view[index].setOnClickListener(this);

                // rowに入れていく
                row.addView(array_layout_view[index]);
            }
            // 1行入れて次のループへ
            tableLayout.addView(row);
        }

        //最後の1行は，問題によって列の数が違うので，ここで作る
        int row_size;
        //選択されたカテゴリーの問題数が3の倍数だったら，3を代入
        if(category_size%3 != 0) {
            row_size = category_size % 3;


            row = new TableRow(this);
            row.setLayoutParams(row_layout_params);
            for (int x = 0; x < row_size; ++x) {
                int index = x + y * 3;
                array_layout_view[index] = new LinearLayout(this);
                array_button_view[index] = new Button(this);

                array_button_view[index].setId(index);
                array_button_view[index].setWidth(width / 4);
                array_button_view[index].setHeight(width / 4);

                BitmapDrawable bitmapDrawable;
                //もし正解していたらsolvedアイコンをセット
                if(correct_flagBoolean[index]){
                    bitmapDrawable = SearchResource.getQuestion_No_solved(
                            category_name,index+1,this);
                }
                else{
                    bitmapDrawable = SearchResource.getQuestion_No(
                            category_name,index+1,this);
                }
                //問題番号の画像をset
                array_button_view[index].setBackground(bitmapDrawable);

                array_layout_view[index].addView(array_button_view[index]);
                array_layout_view[index].setPadding(50, 20, 30, 70);
                array_button_view[index].setOnClickListener(this);

                // rowに入れていく
                row.addView(array_layout_view[index]);

            }
            tableLayout.addView(row);
        }

    }

    @Override
    public void onClick(View v) {

        //v.getId()とは，今，クリックされたボタンのId
        if(v.getId() != focused_button_id){
            //もしボタンクリックが初めてではなかったら，ボタン画像を元の画像に戻す
            if(focused_button_id != -1) {
                BitmapDrawable bitmapDrawable;
                //もし正解していたらsolvedアイコンをセット
                if(correct_flagBoolean[focused_button_id]){
                    bitmapDrawable = SearchResource.getQuestion_No_solved(
                            category_name,focused_button_id+1,this);
                }
                //まだ正解していなかったら，unsolveアイコンをセット
                else{
                    bitmapDrawable = SearchResource.getQuestion_No(
                            category_name, focused_button_id + 1, this);
                }
                Button pre_focused_button = (Button) findViewById(focused_button_id);
                pre_focused_button.setBackground(bitmapDrawable);
            }
            focused_button_id =v.getId();

            Button focused_button = (Button) findViewById(focused_button_id);
            focused_button.setBackgroundResource(R.drawable.focused_button_design);
        }

        //二連続で同じボタンをクリックした時
        else {
            //初期化
            focused_button_id =-1;
            Intent intent = new Intent(activity, Question_Activity.class);
            intent.putExtra("category_name", category_name);
            intent.putExtra("No",String.valueOf(v.getId() + 1));

            startActivityForResult(intent, 0);
            focused_button_id =v.getId();

        }
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

        //トップボタンを押した時の動作
        if (id==R.id.top){
            Intent intent = new Intent(activity, MainActivity.class);
            startActivityForResult(intent, 0);
        }

        //戻るボタンを押した時の動作
        if (id==R.id.back){
            Intent intent = new Intent(activity, Categoryselect_Activity.class);
            startActivityForResult(intent, 0);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


}