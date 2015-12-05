package kenyawakita.sapuri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NingenA1 extends Activity {

    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ningena1);

        activity=this;

        final Button back_button = (Button) findViewById(R.id.button);
        back_button.setOnClickListener(new View.OnClickListener() {
            /** �{�^�����N���b�N�������ɌĂ΂�� */
            @Override
            public void onClick(View v) {
                // �����ɏ������L�q����
                Intent intent = new Intent(activity, Select_QuestionNo_Activity.class);
                startActivityForResult(intent, 0);

            }
        });

    }

}