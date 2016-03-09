package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class Categoryselect_Activity extends Activity{

    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catego);
        activity = this;
        final BootstrapButton relax_button = (BootstrapButton) findViewById(R.id.relax_button);
        final BootstrapButton suji_button = (BootstrapButton) findViewById(R.id.suji_button);
        final BootstrapButton ningen_button = (BootstrapButton) findViewById(R.id.ningen_button);
        final BootstrapButton hirameki_button = (BootstrapButton) findViewById(R.id.hirameki_button);


        //リラックスボタンを押した時
        relax_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                intent.putExtra("category_name",relax_button.getText());
                startActivityForResult(intent, 0);
            }
        });

        //数字ボタンを押した時
        suji_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                intent.putExtra("category_name",suji_button.getText());
                startActivityForResult(intent, 0);
            }
        });

        //人間観察力ボタンを押した時
        ningen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                intent.putExtra("category_name",ningen_button.getText());
                startActivityForResult(intent, 0);
            }
        });
        //ひらめきボタンを押した時
        hirameki_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                intent.putExtra("category_name",hirameki_button.getText());
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

        //トップボタンを押した時の動作
        if (id==R.id.top){
            Intent intent = new Intent(activity, MainActivity.class);
            startActivityForResult(intent, 0);
            this.finish();
        }

        //トップボタンを押した時の動作
        if (id==R.id.back){
            Intent intent = new Intent(activity, MainActivity.class);
            startActivityForResult(intent, 0);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
