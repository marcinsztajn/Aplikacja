package none.training_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class StartGame extends Activity {
    Button singlePlayerButton,multiPlayerButton;
    List<OneCardModel> cardsConfigList;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        singlePlayerButton =(Button)findViewById(R.id.single_player_button);
        singlePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCards();
                EventBus.getDefault().postSticky(new Message(Message.SEND_CONFIG_LIST, cardsConfigList));
                Intent startSinglePlayer = new Intent(getApplicationContext(),SinglePlayerMainClass.class);
                startActivity(startSinglePlayer);
            }
        });
        multiPlayerButton=(Button)findViewById(R.id.multi_player_button);
        multiPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMultiPlayer = new Intent(getApplicationContext(),TypingNameActivity.class);
                startActivity(startMultiPlayer);
            }
        });


    }


    public void createCards(){
        OneCardModel firstCard = new OneCardModel(R.drawable.karta1,10);
        OneCardModel secondCard = new OneCardModel(R.drawable.karta2,40);
        OneCardModel thirdCard = new OneCardModel(R.drawable.karta3,20);
        OneCardModel fourthCard = new OneCardModel(R.drawable.karta4,50);
        OneCardModel fifthCard= new OneCardModel(R.drawable.karta5,80);
        OneCardModel sixthCard = new OneCardModel(R.drawable.karta6,30);
        OneCardModel seventhCard = new OneCardModel(R.drawable.karta7,15);
        OneCardModel eighthCard = new OneCardModel(R.drawable.karta8,18);
        OneCardModel ninthCard = new OneCardModel(R.drawable.karta9,60);
        OneCardModel tenthCard = new OneCardModel(R.drawable.karta10,45);
        OneCardModel eleventhCard = new OneCardModel(R.drawable.karta11,95);
        OneCardModel twelfthCard = new OneCardModel(R.drawable.karta12,90);


        cardsConfigList = new ArrayList<>();
        cardsConfigList.add(firstCard);
        cardsConfigList.add(secondCard);
        cardsConfigList.add(thirdCard);
        cardsConfigList.add(fourthCard);
        cardsConfigList.add(fifthCard);
        cardsConfigList.add(sixthCard);
        cardsConfigList.add(seventhCard);
        cardsConfigList.add(eighthCard);
        cardsConfigList.add(ninthCard);
        cardsConfigList.add(tenthCard);
        cardsConfigList.add(eleventhCard);
        cardsConfigList.add(twelfthCard);
    }

}
