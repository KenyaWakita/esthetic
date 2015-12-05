package kenyawakita.sapuri;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class AlertDialogFragment extends DialogFragment {
    private String[] menulist = {"戻る"};

    private Context context;
    String category_name;
    String question_No;
    LinearLayout layout;
    ImageView imageView;
    TextView textView;

    public  AlertDialogFragment(Context context){
        this.context=context;

    };

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String hint_image_name = bundle.getString("hint");

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("ヒント");

        alert.setItems(menulist, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int idx) {
                // 選択１
                if (idx == 0) {
                }
                // cancel"
                else {
                    // nothing to do
                }
            }
        });

        // リニアレイアウトの設定
        layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        // レイアウトを中央寄せにするためMATCH_PARENTにする
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        // レイアウト中央寄せ
        layout.setGravity(Gravity.CENTER);
        alert.setView(layout);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);



        //画像をセット
        imageView = new ImageView(getActivity());
        InputStream hint_image = null;
        try {
            hint_image = context.getAssets().open(hint_image_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap hint_bitmap = BitmapFactory.decodeStream(hint_image);
        imageView.setImageBitmap(hint_bitmap);

        // 画像の設定
//        imageView = new ImageView(getActivity());
//        imageView.setImageResource(R.drawable.pop);
        imageView.setLayoutParams(layoutParams);
        layout.addView(imageView);

        return alert.create();
    }
}
