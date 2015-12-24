package kenyawakita.sapuri;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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


        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.alertdialog_layout, null);


        alert.setView(content);

        alert.setMessage("ヒント")
                .setNegativeButton("閉じる", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        imageView = (ImageView) content.findViewById(R.id.hint);

        InputStream hint_image = null;
        try {
            hint_image = context.getAssets().open(hint_image_name);
            Log.d("ヒント画像２",hint_image_name);
        } catch (IOException e) {
            Log.d("ヒント画像エラー",hint_image_name);
            e.printStackTrace();
        }

        Bitmap hint_bitmap = null;
        hint_bitmap = BitmapFactory.decodeStream(hint_image);
        imageView.setImageBitmap(hint_bitmap);
        imageView.setLayoutParams(layoutParams);

        return alert.create();
    }
}
