package none.training_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TypingNameActivity extends Activity {
private EditText myName,rivalName;
    public static String MY_NAME="myName";
    public static String RIVAL_NAME = "rivalName";

private Button startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typing_name);
        myName = (EditText)findViewById(R.id.insert_your_nick_edittext);
        rivalName = (EditText)findViewById(R.id.insert_rival_nick_edittext);
        startGame =(Button)findViewById(R.id.start_game_button);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myName.getText().toString().isEmpty() || rivalName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"uzupełnij pola",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent playGame = new Intent(getApplicationContext(),MultiPlayerMainClass.class);
                    playGame.putExtra(MY_NAME,myName.getText().toString());
                    playGame.putExtra(RIVAL_NAME,rivalName.getText().toString());
                    startActivity(playGame);
                }
            }
        });
    }







}

