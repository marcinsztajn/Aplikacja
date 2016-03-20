package none.training_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button button_play;
    private Button button_about;
    private Button button_rules;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_play = (Button) findViewById(R.id.button);
        button_about = (Button) findViewById(R.id.button3);
        button_rules = (Button) findViewById(R.id.button2);

        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartGame.class);
                startActivity(intent);

            }
        });

        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });

        button_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rules.class);
                startActivity(intent);
            }
        });
    }

//    public void OnClickButtonListener(){
//        button_sbm = (Button)findViewById(R.id.button);
//        button_sbm.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent  intent = new Intent(getApplicationContext(),StartGame.class);
//                        //Intent intent = new Intent("none.training_3.StartGame");
//                        startActivity(intent);
//                    }
//                }
//        );
//    }
}
