package kenyawakita.sapuri;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Description_Activity extends Activity {

    Activity activity;
    String category_name;
    String question_No;
    BitmapDrawable description_bitmap_drawable;

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


        description_bitmap_drawable = SearchResource.getDescription(category_name, Integer.parseInt(question_No), this);
        layout.setBackground(description_bitmap_drawable);


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

}
