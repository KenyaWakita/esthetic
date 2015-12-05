package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        final BootstrapButton IQ_button = (BootstrapButton) findViewById(R.id.IQ_button);

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

        //IQテストボタンを押した時
        IQ_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                intent.putExtra("category_name",IQ_button.getText());
                startActivityForResult(intent, 0);
            }
        });
    }
}
