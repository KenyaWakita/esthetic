package kenyawakita.sapuri;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class NingenQ1 extends Activity {

    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ningenq1);


//        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            File f = new File( "sample.xml" );
//            Document doc = builder.parse( f );
//            Element root = doc.getDocumentElement();
//            NodeList children = root.getChildNodes();
//            for( int i=0; i<children.getLength(); i++ ) {
//                Node child = children.item(i);
//                if( child instanceof Element ) {
//                    Element childElement = (Element) child;
//                    System.out.println( childElement.getTagName() );
//                    Log.d("aaaa",childElement.getTagName());
//                }
//            }
//        }           catch( ParserConfigurationException e )
//        {
//        }
//        catch( SAXException e )
//        {
//        }
//        catch( IOException e ) {
//        }
//



        activity=this;
        final EditText answer = (EditText) findViewById(R.id.answer2);

        final Button next = (Button) findViewById(R.id.send2_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                String ans =answer.getText().toString();

                if ((ans.contains("おすすめ"))||(ans.contains("すすめ"))) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("正解です．\n" +
                            "解説画面に移ります")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    Intent intent = new Intent(activity, NingenA1.class);
                                    startActivityForResult(intent, 0);
                                }
                            });
                    builder.show();


                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("不正解です")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                }
                            })
                            .setNegativeButton("no", null);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.hint) {

            return true;
        }

        if (id == R.id.answer) {
            Intent intent = new Intent(activity, NingenA1.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
