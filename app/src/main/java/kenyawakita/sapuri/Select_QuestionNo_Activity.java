package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;


public class Select_QuestionNo_Activity extends Activity implements View.OnClickListener {

    Activity activity;
    int checkid=0;
    String category_name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_question_no);
        activity = this;

        Button No1_button = (Button) findViewById(R.id.No1_button);
        Button No2_button = (Button) findViewById(R.id.No2_button);
        Button No3_button = (Button) findViewById(R.id.No3_button);
        final Button next_button = (Button) findViewById(R.id.next_button);
        View layout = findViewById(R.id.no_activity);

        //インテントでカテゴリー名の受け取り
        Intent i=getIntent();
        category_name = i.getStringExtra("category_name");
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

        TableLayout No_Table = (TableLayout) findViewById(R.id.No_Table);

        //各ボタンをリスナーに登録．OnClickした時に反応できるようにするため
        No1_button.setOnClickListener((View.OnClickListener) this);
        No2_button.setOnClickListener((View.OnClickListener) this);
        No3_button.setOnClickListener((View.OnClickListener) this);









        //次へボタンを押した時
//        next_button.setOnClickListener(new View.OnClickListener() {
//            RadioGroup Radio = (RadioGroup) findViewById(R.id.radioGroup);
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, Question_Activity.class);
//                intent.putExtra("category_name", category_name);
//
//                switch (Radio.getCheckedRadioButtonId()) {
//                    //問1を選択した時
//                    case R.id.radioButton1:
//                        intent.putExtra("No", "1");
//                        break;
//                    //問2を選択した時
//                    case R.id.radioButton2:
//                        intent.putExtra("No", "2");
//                        break;
//                    //問3を選択した時
//                    case R.id.radioButton3:
//                        intent.putExtra("No", "3");
//                        break;
//                    default:
//                        break;
//                }
//                startActivityForResult(intent, 0);
//            }
//        });


    }

    @Override
    public void onClick(View v) {

        if(v.getId() != checkid){
            //もしボタンクリックが初めてではなかったら，ボタンの色を黒に戻す
            if(checkid != 0) {
                Button precheked_button = (Button) findViewById(checkid);
                precheked_button.setBackgroundResource(R.drawable.button_defalt);
            }
            checkid=v.getId();


            Button cheked_button = (Button) findViewById(checkid);
            cheked_button.setBackgroundResource(R.color.blue);
        }

        else {
            //初期化
            checkid=0;
            Intent intent = new Intent(activity, Question_Activity.class);
            intent.putExtra("category_name", category_name);
            switch (v.getId()){
                case R.id.No1_button:
                    intent.putExtra("No", "1");
                    break;
                case R.id.No2_button:
                    intent.putExtra("No","2");
                    break;
                case R.id.No3_button:
                    intent.putExtra("No","3");
                    break;
            }
            startActivityForResult(intent, 0);
        }

    }
}