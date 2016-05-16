package none.training_3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SinglePlayerMainClass extends Activity {
    private List<OneCardModel> cardsConfig;
    private List<OneCardModel> userActualCards;
    private List<OneCardModel> computerActualCards;
    private List<ImageView> listofThumbnailsforUser;
    private List<ImageView> listofThumbnailsforComputer;
    private ImageView userCardMainImageView, computerCardMainImageView;
    private int numberOfUserPoints = 0;
    private int numberOfComputerPoints = 0;
    private int numberOfLeftGames = 0;
    private int count = 0;
    private int part = 0;
    private int countOfImageViews = 0;

    private boolean isEmptyMainView = true;
    private boolean isExecuted = true;
    private ImageView firstUserThumbnailImageView, secondUserThumbnailImageView, thirdUserThumbnailImageView;
    private ImageView firstComputerThumbnailImageView, secondComputerThumbnailImageView, thirdComputerThumbnailImageView;
    private Button chooseRandomCardsToPlayersButton, playButton, resoultButton;
    private TextView userPoints, computerPoints, leftGames, simplyNameUser, simplyNameComputer;
    private RelativeLayout userRelativeToHiglight, computerRelativeToHiglight;

    // rejestracja EventBusa
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    // główna  metoda biblioteki  EventBus, odbierającą wiadomość przychodzącą z innej klasy.
    // Ustawia ona tutejszy config czyli liste obiektów tworzonych w klasie StartGame, aby móc dalej na nich operować.
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(Message message) {
        switch (message.key) {
            case Message.SEND_CONFIG_LIST:
                // przychodząca message.data jest typu Object, wiec trzeba go zrzutować na obiekt,który nas interesuje(List<OneCardModel>)
                cardsConfig = (List<OneCardModel>) message.data;
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_player);
        userCardMainImageView = (ImageView) findViewById(R.id.place_to_instert_user_card);
        computerCardMainImageView = (ImageView) findViewById(R.id.place_to_insert_computer_card);

        firstUserThumbnailImageView = (ImageView) findViewById(R.id.first_thumbnail_imageview);
        secondUserThumbnailImageView = (ImageView) findViewById(R.id.second_thumbnail_imageview);
        thirdUserThumbnailImageView = (ImageView) findViewById(R.id.third_thumbnail_imageview);

        firstComputerThumbnailImageView = (ImageView) findViewById(R.id.first_komputer_thumbnail_imageview);
        secondComputerThumbnailImageView = (ImageView) findViewById(R.id.second_komputer_thumbnail_imageview);
        thirdComputerThumbnailImageView = (ImageView) findViewById(R.id.third_komputer_thumbnail_imageview);

        chooseRandomCardsToPlayersButton = (Button) findViewById(R.id.choose_random_cards_button);
        playButton = (Button) findViewById(R.id.play_button_in_single_player);
        resoultButton = (Button) findViewById(R.id.resoult_button);

        leftGames = (TextView) findViewById(R.id.number_of_lefts_games);
        userPoints = (TextView) findViewById(R.id.place_to_insert_user_points_textview);
        computerPoints = (TextView) findViewById(R.id.place_to_insert_computer_points_textview);
        simplyNameComputer = (TextView) findViewById(R.id.name_computer_textview);
        simplyNameUser = (TextView) findViewById(R.id.name_player_textview);

        userRelativeToHiglight = (RelativeLayout) findViewById(R.id.relative_with_main_user_card);
        computerRelativeToHiglight = (RelativeLayout) findViewById(R.id.relative_with_main_computer_card);


        chooseRandomCardsToPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // po kliknięciu 'losuj' ustawia odpowiednie pola na odpowiednie wartosci(zeruje)

                if (!cardsConfig.isEmpty()) {
                    numberOfLeftGames = cardsConfig.size() / 2;
                    numberOfUserPoints = 0;
                    numberOfComputerPoints = 0;
                    userPoints.setText("");
                    computerPoints.setText("");
                    simplyNameComputer.setBackgroundResource(R.color.white_transparent_full);
                    simplyNameUser.setBackgroundResource(R.color.white_transparent_full);
                    leftGames.setText(String.valueOf(numberOfLeftGames));
                    // wywołuje metodę do losowania kart
                    chooseCards(cardsConfig.size());
                    putCardsToThumbnails();
                }

            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // metoda do pobrania karty  z miniaturki i umieszczenia jej w głównym widoku dla kart  komputera oraz gracza
                putCardToMainViews();
            }
        });

        resoultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sprawdzenie wyniku, która karta wygrała
                whichCardWin();
            }
        });

    }


//Wyrejestrowanie EventBusa
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    /*
    metoda chooseCards na podstawie wielkosci configa(ilosci kart)
    tworzy dwie odrębne listy obiektów OneCardModel(dla usera i dla komputera), w  których będą przetrzymywane obiekty z configa,
    odpowiednio rozdzielone na pół na te dwie listy. Obiekty te są losowane  spośród dostepnych. Nie powtarzają się.

     */
    private void chooseCards(int size) {
        int cardNumber, allocation;
        userActualCards = new ArrayList<>();
        computerActualCards = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        Random numberGenerator = new Random();
        while (numbers.size() < size) {
            cardNumber = numberGenerator.nextInt(size);
            // Log.i(getLocalClassName(), "card number after generator: " + cardNumber);
            if (!numbers.contains(cardNumber)) {
                numbers.add(cardNumber);
                Log.i(getLocalClassName(), "card number added to ArrayList: " + cardNumber);
                Log.i(getLocalClassName(), "ArrayList size: " + numbers.size());

            }

        }
        allocation = size /2;
        for (int i = 0; i < numbers.size(); i++) {
            if (i < allocation) {
                userActualCards.add(cardsConfig.get(numbers.get(i)));
                Log.i(getLocalClassName(), "card number added to userActualCards: " + numbers.get(i));
                Log.i(getLocalClassName(), "size of userActualCards: " + userActualCards.size());

            } else {
                computerActualCards.add(cardsConfig.get(numbers.get(i)));
                Log.i(getLocalClassName(), "card number added to computerActualCards: " + numbers.get(i));
                Log.i(getLocalClassName(), "size of computerActualCards: " + computerActualCards.size());
            }

        }


    }

    // wrzuca karty wylosowane do małych okienek podglądu
    private void putCardsToThumbnails() {
        //stworzenie dwóch oddzielnych list ImageView i dodanie do nich odpowiednich okienek
        listofThumbnailsforComputer = new ArrayList<>();
        listofThumbnailsforUser = new ArrayList<>();

        listofThumbnailsforUser.add(firstUserThumbnailImageView);
        listofThumbnailsforUser.add(secondUserThumbnailImageView);
        listofThumbnailsforUser.add(thirdUserThumbnailImageView);

        listofThumbnailsforComputer.add(firstComputerThumbnailImageView);
        listofThumbnailsforComputer.add(secondComputerThumbnailImageView);
        listofThumbnailsforComputer.add(thirdComputerThumbnailImageView);

        chooseAndPlay("choose_random");

    }

    // metoda ta wrzuca z małych okienek do dużych okien odpowiednich kart
    private void putCardToMainViews() {
        // sprawdzenie odpowiednich elementów  w celu zabezpieczenia przed blędami
        if (listofThumbnailsforUser != null && numberOfLeftGames >= 0 && isEmptyMainView && !userActualCards.isEmpty() && !computerActualCards.isEmpty()) {
            //najważniejsza metoda
            chooseAndPlay("play");
        } else if (!isEmptyMainView || userActualCards == null || computerActualCards == null) {
            if (!isEmptyMainView) {
                return;
            } else {
                Toast.makeText(getApplicationContext(), "najpierw wylosuj karty!", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(getApplicationContext(), "najpierw wylosuj karty!", Toast.LENGTH_SHORT).show();
            return;
        }

    }
// sprawdzenie wyniku, kto wygrał w danym starciu
    private void whichCardWin() {

        if (!isEmptyMainView) {
            if (numberOfLeftGames > 0) {
                for (int i = 0; i < userActualCards.size(); i++) {
                    // sprawdzenie która z 3 dostępnych kart jest równa karcie ustawionej w innym miejscu
                    if (userActualCards.get(i).pathResource == (Integer) userCardMainImageView.getTag()) {
                        //sprawdzenie które karty są silniejsze
                        if (userActualCards.get(i).powerOfCard > computerActualCards.get(i).powerOfCard) {
                            if (!isExecuted) {
                                return;
                            } else {
                                // zdobycie punktu przez usera, inkrementacja odpowiednich zmiennych
                                Toast.makeText(getApplicationContext(), "punkt dla Ciebie!", Toast.LENGTH_SHORT).show();
                                numberOfUserPoints++;
                                numberOfLeftGames--;
                                isExecuted = false;
                            }
                            // różne efekty specjalne w nowym wątku po wykonywane po 1,8sekundy
                            doSmthingAfterSeconds(1800, userPoints,numberOfUserPoints,"user", userRelativeToHiglight);

                        } else if (userActualCards.get(i).powerOfCard < computerActualCards.get(i).powerOfCard) {
                            if (!isExecuted) {
                                return;
                            } else {
                                Toast.makeText(getApplicationContext(), "punkt dla komputera!", Toast.LENGTH_SHORT).show();
                                numberOfComputerPoints++;
                                numberOfLeftGames--;
                                isExecuted = false;
                            }
                            doSmthingAfterSeconds(1800, computerPoints,numberOfComputerPoints, "computer", computerRelativeToHiglight);
                        } else {
                            Toast.makeText(getApplicationContext(), "remis!", Toast.LENGTH_SHORT).show();
                            numberOfUserPoints++;
                            numberOfComputerPoints++;
                            userPoints.setText(String.valueOf(numberOfUserPoints));
                            doSmthingAfterSeconds(1800, userPoints,0, "marcinpuchcia", null);
                            computerPoints.setText(String.valueOf(numberOfComputerPoints));

                        }

                    }

                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "umieść karty!", Toast.LENGTH_SHORT).show();
        }
    }

    //metoda do efektów specjalnych po upływie czasu 'seconds' wyrażonego w milisekundach
    private void doSmthingAfterSeconds(int seconds, final TextView fieldToPut,final int numberOfPoints, final String who, final RelativeLayout relativeToHiglight) {
        if (relativeToHiglight != null) {
            relativeToHiglight.setBackgroundResource(R.color.green_to_higlight);
        } else {
            return;
        }
        // nowy wątek
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (who.toString().equals("user")) {
                    //punkt dla użytkownika, wyprowadzenie zmian na ekran(wpisanie w odpowiednie TextView-y)
                    fieldToPut.setText(String.valueOf(numberOfPoints));
                    leftGames.setText(String.valueOf(numberOfLeftGames));

                } else if (who.toString().equals("computer")) {
                    fieldToPut.setText(String.valueOf(numberOfPoints));
                    leftGames.setText(String.valueOf(numberOfLeftGames));
                } else {
                    return;
                }

                relativeToHiglight.setBackgroundResource(R.color.white_transparent_full);
                // bardzo fajna biblioteka do operacji na ImageView
                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(userCardMainImageView);
                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(computerCardMainImageView);
                // odpowiednie flagi boolowskie
                isEmptyMainView = true;
                isExecuted = true;

                if (numberOfLeftGames < 0) {
                    numberOfLeftGames = 0;
                }
                if (numberOfLeftGames == 0) {
                    // kiedy koniec gry,  po 0,7 sekundy odpowiednie akcje
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // przywrócenie poczatkowego stanu wszystkich okienek (nastawienie odpowiedniego koloru)
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(userCardMainImageView);
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(computerCardMainImageView);
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforComputer.get(0));
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforComputer.get(1));
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforComputer.get(2));
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforUser.get(0));
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforUser.get(1));
                            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforUser.get(2));

                            final String whoWin;
                            if (numberOfComputerPoints > numberOfUserPoints) {
                                whoWin = "wygrał komputer!";
                                simplyNameComputer.setBackgroundResource(R.color.green_to_higlight_transparent_70);
                            } else if (numberOfComputerPoints == numberOfUserPoints) {
                                whoWin = "remis";
                            } else {
                                whoWin = "wygrałeś!";
                                simplyNameUser.setBackgroundResource(R.color.green_to_higlight_transparent_70);
                            }
                            Toast.makeText(getApplication(), "koniec gry! Wynik: " + whoWin, Toast.LENGTH_SHORT).show();
                            isEmptyMainView = true;
                            //czyszczenie list z aktualnymi kartami kazdego  z graczy
                            userActualCards.clear();
                            computerActualCards.clear();

                        }
                    }, 700);
                }

            }

        }, seconds);

    }

    // główna metoda do operacji ładowania odpowiednich kart w okna
    // jeżeli "choose_random" to ładowane są odpowiednie z posiadanych kart  do miniaturek ImageView.
    // W przypadku komputera, nie widzimy jego kart(było by to bez sensu).
    // W przypadku "play" ładowane są odpowiednie karty w główne okna. Wszystko dzieje się po kolei.
    // wykorzystywane są dwa liczniki do: count i countOfImageViews.
    // Gdy count będzie równy ostatniemu elementowi z widocznych kart, będą pakowane kolejne z listy, w miniaturki ImageView
    private void chooseAndPlay(String which) {
        int segment = 3;
        int part = 0;
        int startIndex = part * segment;
        int endIndex = part * segment + 2;
        if (endIndex >= userActualCards.size()) {
            endIndex = userActualCards.size() - 1;
        }
        if (which.toString().equals("choose_random")) {
            count = 0;
            countOfImageViews = 0;
            for (int x = startIndex; x <= endIndex; x++) {
                //
                Picasso.with(getApplicationContext()).load(userActualCards.get(x).pathResource).fit().into(listofThumbnailsforUser.get(x));
                Picasso.with(getApplicationContext()).load(R.drawable.sabacc_retreat_card).fit().into(listofThumbnailsforComputer.get(x));
            }
            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(userCardMainImageView);
            Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(computerCardMainImageView);
            isEmptyMainView = true;

        } else if (which.equals("play")) {
            if (numberOfLeftGames > 0) {
                Picasso.with(getApplicationContext()).load(userActualCards.get(count).pathResource).resize(250, 451).centerInside().into(userCardMainImageView);
                userCardMainImageView.setTag(userActualCards.get(count).pathResource);
                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforUser.get(countOfImageViews));

                Picasso.with(getApplicationContext()).load(computerActualCards.get(count).pathResource).resize(250, 451).centerInside().into(computerCardMainImageView);
                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listofThumbnailsforComputer.get(countOfImageViews));
                isEmptyMainView = false;
                count++;
                countOfImageViews++;
                if (countOfImageViews >= listofThumbnailsforComputer.size()) {
                    countOfImageViews = 0;
                    if (count < userActualCards.size()) {
                        Picasso.with(getApplicationContext()).load(userActualCards.get(count).pathResource).fit().into(listofThumbnailsforUser.get(countOfImageViews));
                        Picasso.with(getApplicationContext()).load(userActualCards.get(count + 1).pathResource).fit().into(listofThumbnailsforUser.get(countOfImageViews + 1));
                        Picasso.with(getApplicationContext()).load(userActualCards.get(count + 2).pathResource).fit().into(listofThumbnailsforUser.get(countOfImageViews + 2));

                        Picasso.with(getApplicationContext()).load(R.drawable.sabacc_retreat_card).fit().into(listofThumbnailsforComputer.get(countOfImageViews));
                        Picasso.with(getApplicationContext()).load(R.drawable.sabacc_retreat_card).fit().into(listofThumbnailsforComputer.get(countOfImageViews + 1));
                        Picasso.with(getApplicationContext()).load(R.drawable.sabacc_retreat_card).fit().into(listofThumbnailsforComputer.get(countOfImageViews + 2));
                    } else {
                        return;
                    }

                }

            } else {
                return;
            }

        }
    }

}

