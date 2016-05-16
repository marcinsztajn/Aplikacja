package none.training_3;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MultiPlayerMainClass extends FragmentActivity {
    private static final String TAG = "MultiPlayerMainClass";
    TextView myDeviceName, rivalDeviceName, tvMyPoints, tvRivalPoints, tvLeftGames;
    private List<OneCardModel> myActualCards;
    private List<OneCardModel> rivalActualCards;
    private List<ImageView> listOfThumbnails;
    private ImageView myMainCard, rivalMainCard, firstThumbnail, secondThumbnail, thirdThumbnail;
    private Button confirmQuequeButton;
    private int numberOfMyPoints = 0;
    private int numberofRivalPoints = 0;
    private int numberOfLeftGames = 0;
    private Bundle extras;
    private String whoStart;
    private int myCardPosition;
    private int rivalCardPosition;
    private FragmentManager fm = getSupportFragmentManager();
    private ArrayList<OneCardModel> createdCardsList;
    private CustomDialogFragment customDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player);
        init();
        customDialogFragment = new CustomDialogFragment(whoStart, 6, "", null);
        customDialogFragment.show(fm, "");

    }

    private void init() {
        myDeviceName = (TextView) findViewById(R.id.tv_to_insert_my_bt_name);
        rivalDeviceName = (TextView) findViewById(R.id.tv_to_insert_rival_bt_name);
        extras = getIntent().getExtras();
        if (extras != null) {
            myDeviceName.setText(extras.getString(TypingNameActivity.MY_NAME));
            rivalDeviceName.setText(extras.getString(TypingNameActivity.RIVAL_NAME));
        }
        createCards();
        chooseCards(createdCardsList.size());
        rivalMainCard = (ImageView) findViewById(R.id.rival_main_card_imageview);
        firstThumbnail = (ImageView) findViewById(R.id.first_thumbnail_imageview);
        secondThumbnail = (ImageView) findViewById(R.id.second_thumbnail_imageview);
        thirdThumbnail = (ImageView) findViewById(R.id.third_thumbnail_imageview);
        listOfThumbnails = new ArrayList<>();
        listOfThumbnails.add(firstThumbnail);
        listOfThumbnails.add(secondThumbnail);
        listOfThumbnails.add(thirdThumbnail);
        chooseWhoStart();
        tvMyPoints = (TextView) findViewById(R.id.place_to_insert_my_points);
        tvRivalPoints = (TextView) findViewById(R.id.place_to_insert_rival_points);
        myMainCard = (ImageView) findViewById(R.id.my_main_card_imageview);
        confirmQuequeButton = (Button) findViewById(R.id.accept_queque_button);
        tvLeftGames = (TextView) findViewById(R.id.tv_number_of_left_games);

        firstThumbnail.setOnClickListener(new View.OnClickListener() {
            int tag;

            @Override
            public void onClick(View v) {
                if (firstThumbnail.getTag() != null) {
                    tag = (Integer) firstThumbnail.getTag();
                } else {
                    return;
                }
                if (whoStart.equals(myDeviceName.getText().toString())) {
                    for (int i = 0; i < listOfThumbnails.size(); i++) {
                        if (listOfThumbnails.get(i).getTag() == null) {
                            continue;
                        }
                        if (listOfThumbnails.get(i).getTag().equals(tag)) {

                            if (i >= myActualCards.size()) {
                                i = myActualCards.size() - 1;
                            }
                            Log.d(TAG, "found egsist tag is firstThumbnail " + String.valueOf(tag));
                            Picasso.with(getApplicationContext()).load(myActualCards.get(i).pathResource).resize(250, 451).centerInside().into(myMainCard);
                            myMainCard.setTag(listOfThumbnails.get(i).getTag());

                        }
                    }
                } else {
                    for (int i = 0; i < listOfThumbnails.size(); i++) {
                        if (listOfThumbnails.get(i).getTag() == null) {
                            continue;
                        }
                        if (listOfThumbnails.get(i).getTag().equals(tag)) {
                            if (i >= rivalActualCards.size()) {
                                i = rivalActualCards.size() - 1;
                            }
                            Log.d(TAG, "found egsist tag is firstThumbnail " + String.valueOf(tag));
                            Picasso.with(getApplicationContext()).load(rivalActualCards.get(i).pathResource).resize(250, 451).centerInside().into(rivalMainCard);
                            rivalMainCard.setTag(listOfThumbnails.get(i).getTag());

                        }

                    }

                }
            }
        });
        secondThumbnail.setOnClickListener(new View.OnClickListener() {
            int tag;

            @Override
            public void onClick(View v) {
                if (secondThumbnail.getTag() != null) {
                    tag = (Integer) secondThumbnail.getTag();
                } else {
                    return;
                }
                if (whoStart.equals(myDeviceName.getText().toString())) {
                    for (int i = 0; i < listOfThumbnails.size(); i++) {
                        if (listOfThumbnails.get(i).getTag() == null) {
                            continue;
                        }
                        if (listOfThumbnails.get(i).getTag().equals(tag)) {
                            if (i >= myActualCards.size()) {
                                i = myActualCards.size() - 1;
                            }
                            Log.d(TAG, "found egsist tag is secondThumbnail " + String.valueOf(tag));
                            Picasso.with(getApplicationContext()).load(myActualCards.get(i).pathResource).resize(250, 451).centerInside().into(myMainCard);
                            myMainCard.setTag(listOfThumbnails.get(i).getTag());

                        }
                    }
                } else {
                    for (int i = 0; i < listOfThumbnails.size(); i++) {
                        if (listOfThumbnails.get(i).getTag() == null) {
                            continue;
                        }
                        if (listOfThumbnails.get(i).getTag().equals(tag)) {
                            if (i >= rivalActualCards.size()) {
                                i = rivalActualCards.size() - 1;
                            }
                            Log.d(TAG, "found egsist tag is secondThumbnail " + String.valueOf(tag));
                            Picasso.with(getApplicationContext()).load(rivalActualCards.get(i).pathResource).resize(250, 451).centerInside().into(rivalMainCard);
                            rivalMainCard.setTag(listOfThumbnails.get(i).getTag());

                        }

                    }

                }
            }
        });

        thirdThumbnail.setOnClickListener(new View.OnClickListener() {
            int tag;

            @Override
            public void onClick(View v) {
                if (thirdThumbnail.getTag() != null) {
                    tag = (Integer) thirdThumbnail.getTag();
                } else {
                    return;
                }
                if (whoStart.equals(myDeviceName.getText().toString())) {
                    for (int i = 0; i < listOfThumbnails.size(); i++) {
                        if (listOfThumbnails.get(i).getTag() == null) {
                            continue;
                        }
                        if (listOfThumbnails.get(i).getTag().equals(tag)) {
                            if (i >= myActualCards.size()) {
                                i = myActualCards.size() - 1;
                            }
                            Log.d(TAG, "found egsist tag is thirdThumbnail " + String.valueOf(tag));
                            Picasso.with(getApplicationContext()).load(myActualCards.get(i).pathResource).resize(250, 451).centerInside().into(myMainCard);
                            myMainCard.setTag(listOfThumbnails.get(i).getTag());

                        }
                    }
                } else {
                    for (int i = 0; i < listOfThumbnails.size(); i++) {
                        if (listOfThumbnails.get(i).getTag().equals(tag)) {
                            if (listOfThumbnails.get(i).getTag() == null) {
                                continue;
                            }
                            Log.d(TAG, "found egsist tag is thirdThumbnail " + String.valueOf(tag));
                            if (i >= rivalActualCards.size()) {
                                i = rivalActualCards.size() - 1;
                            }
                            Picasso.with(getApplicationContext()).load(rivalActualCards.get(i).pathResource).resize(250, 451).centerInside().into(rivalMainCard);
                            rivalMainCard.setTag(listOfThumbnails.get(i).getTag());

                        }

                    }

                }
            }
        });

        confirmQuequeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "onClick: whoStart: " + whoStart + ", " + myMainCard.getTag() + ", " + rivalMainCard.getTag() + ", myPoints: " + numberOfMyPoints + ", rivalPoints: " + numberofRivalPoints);

                if (whoStart.equals(myDeviceName.getText().toString())) { // jestem na moim ruchu
                    if (myMainCard.getDrawable() == null) {
                        Toast.makeText(getApplicationContext(), "napierw wybierz kartę", Toast.LENGTH_SHORT).show();
                    } else {
                        if (myMainCard.getTag() != null && rivalMainCard.getTag() != null) { // dwie karty wrzucone
                            setCardPositions(myActualCards, myMainCard);
                            setCardPositions(rivalActualCards, rivalMainCard);

                            Log.i(TAG, "check for myCardPosition: " + myCardPosition + ", rivalCardPosition: " + rivalCardPosition);
                            if (isCardStrongerThan(myActualCards.get(myCardPosition), rivalActualCards.get(rivalCardPosition))) {
                                numberOfMyPoints++; // punkt dla mnie
                                Log.i(TAG, "numberOfMyPoints incremented to: " + numberOfMyPoints);
                                customDialogFragment = new CustomDialogFragment(rivalDeviceName.getText().toString(), 4, "punkt dla : " + myDeviceName.getText().toString(), new OnDialogHidedListener() {
                                    @Override
                                    public void onDialogHided() {
                                        whoStart = rivalDeviceName.getText().toString();
                                        setCardsToThumbnails(whoStart);
                                    }
                                });

                                numberOfLeftGames--;
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(rivalMainCard);
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(myMainCard);
                                if (!myActualCards.get(myCardPosition).cardUsed) {
                                    myActualCards.get(myCardPosition).cardUsed = true;
                                }
                                removeUsedCards(rivalActualCards);
                                removeUsedCards(myActualCards);
                                customDialogFragment.show(fm, "");
                            } else {
                                numberofRivalPoints++; // punkt dla rywala
                                Log.i(TAG, "numberOfRivalPoints incremented to: " + numberOfMyPoints);
                                customDialogFragment = new CustomDialogFragment(rivalDeviceName.getText().toString(), 4, "punkt dla : " + rivalDeviceName.getText().toString(), new OnDialogHidedListener() {
                                    @Override
                                    public void onDialogHided() {
                                        whoStart = rivalDeviceName.getText().toString();
                                        setCardsToThumbnails(whoStart);
                                    }
                                });

                                numberOfLeftGames--;
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(rivalMainCard);
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(myMainCard);
                                removeUsedCards(rivalActualCards);
                                removeUsedCards(myActualCards);
                                customDialogFragment.show(fm, "");
                            }
                            myMainCard.setTag(null);
                            rivalMainCard.setTag(null);
                        } else {

                            setCardPositions(myActualCards, myMainCard);
                            myActualCards.get(myCardPosition).cardUsed = true;
                            whoStart = rivalDeviceName.getText().toString();
                            handleFirstTurn(whoStart);
                        }
                    }

                } else if (whoStart.equals(rivalDeviceName.getText().toString())) { // rozgrywa rywal
                    if (rivalMainCard.getDrawable() == null) {
                        Toast.makeText(getApplicationContext(), "napierw wybierz kartę", Toast.LENGTH_SHORT).show();
                    } else {
                        if (myMainCard.getTag() != null && rivalMainCard.getTag() != null) {
                            setCardPositions(myActualCards, myMainCard);
                            setCardPositions(rivalActualCards, rivalMainCard);

                            Log.i(TAG, "check for myCardPosition: " + myCardPosition + ", rivalCardPosition: " + rivalCardPosition);
                            if (isCardStrongerThan(myActualCards.get(myCardPosition), rivalActualCards.get(rivalCardPosition))) {
                                numberOfMyPoints++;
                                Log.i(TAG, "numberOfMyPoints incremented to: " + numberOfMyPoints);
                                customDialogFragment = new CustomDialogFragment(myDeviceName.getText().toString(), 4, "punkt dla : " + myDeviceName.getText().toString(), new OnDialogHidedListener() {
                                    @Override
                                    public void onDialogHided() {
                                        whoStart = myDeviceName.getText().toString();
                                        setCardsToThumbnails(whoStart);
                                    }
                                });

                                numberOfLeftGames--;
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(rivalMainCard);
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(myMainCard);
                                removeUsedCards(myActualCards);
                                if (!rivalActualCards.get(rivalCardPosition).cardUsed) {
                                    rivalActualCards.get(rivalCardPosition).cardUsed = true;
                                }
                                removeUsedCards(rivalActualCards);
                                customDialogFragment.show(fm, "");
                            } else {
                                numberofRivalPoints++;
                                Log.i(TAG, "numberOfRivalPoints incremented to: " + numberOfMyPoints);
                                customDialogFragment = new CustomDialogFragment(myDeviceName.getText().toString(), 4, "punkt dla : " + rivalDeviceName.getText().toString(), new OnDialogHidedListener() {
                                    @Override
                                    public void onDialogHided() {
                                        whoStart = myDeviceName.getText().toString();
                                        setCardsToThumbnails(whoStart);
                                    }
                                });

                                numberOfLeftGames--;
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(rivalMainCard);
                                Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(myMainCard);
                                myMainCard.setTag(null);
                                rivalMainCard.setTag(null);
                                removeUsedCards(myActualCards);
                                if (!rivalActualCards.get(rivalCardPosition).cardUsed) {
                                    rivalActualCards.get(rivalCardPosition).cardUsed = true;
                                }
                                removeUsedCards(rivalActualCards);
                                customDialogFragment.show(fm, "");
                            }
                            myMainCard.setTag(null);
                            rivalMainCard.setTag(null);
                        } else {
                            setCardPositions(rivalActualCards, rivalMainCard);
                            //setCardPositions();
                            rivalActualCards.get(rivalCardPosition).cardUsed = true;
                            whoStart = myDeviceName.getText().toString();
                            handleFirstTurn(whoStart);
                        }
                    }
                } else {
                    throw new IllegalStateException("Wrong state");
                }
            }
        });
    }

    private void removeUsedCards(List<OneCardModel> list) {
        Log.d(TAG, "inside removeUsedCards :");
        if (list.size() > 0) {

            int i = 0;
            List<OneCardModel> newConfig = new ArrayList<>(list);
            for (OneCardModel oneCardModel : list) {
                if (oneCardModel.cardUsed) {
                    Log.d(TAG, "removed posiition at :" + i);
                    newConfig.remove(oneCardModel);
                }
                i++;
            }
            if (list == myActualCards) {
                myActualCards = newConfig;
            } else {
                rivalActualCards = newConfig;
            }
        }
    }

    private void handleFirstTurn(String whoStart) {
        Log.d(TAG, "handleFirstTurn: " + whoStart);

        final String versusPlayer = whoStart.equals(myDeviceName.getText().toString()) ? myDeviceName.getText().toString() : rivalDeviceName.getText().toString();

        customDialogFragment = new CustomDialogFragment(versusPlayer, 4, "", new OnDialogHidedListener() {
            @Override
            public void onDialogHided() {
                MultiPlayerMainClass.this.whoStart = versusPlayer;
                setCardsToThumbnails(MultiPlayerMainClass.this.whoStart);
            }
        });

        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(whoStart.equals(rivalDeviceName.getText().toString()) ? myMainCard : rivalMainCard);

        customDialogFragment.show(fm, "");
    }

    private boolean isCardStrongerThan(OneCardModel cardModel1, OneCardModel cardModel2) {
        if (cardModel1.powerOfCard > cardModel2.powerOfCard) {

            Log.d(TAG, "isCardStrongerThan: " + cardModel1.powerOfCard + " > " + cardModel2.powerOfCard);

            return true;
        } else {
            Log.d(TAG, "isCardStrongerThan: " + cardModel1.powerOfCard + " < " + cardModel2.powerOfCard);
            return false;
        }

    }


    private void setCardPositions(List<OneCardModel> list, ImageView whichMainCard) {


        int myCardTag = (int) whichMainCard.getTag();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).pathResource == myCardTag) {
                if (list == myActualCards) {
                    myCardPosition = i;
                    Log.i(TAG, "found myCardPosition with index: " + i);
                } else {
                    rivalCardPosition = i;
                    Log.i(TAG, "found rivalCardPosition with index: " + i);
                }
            }
        }
    }


    private void chooseCards(int size) {
        int cardNumber, allocation;
        myActualCards = new ArrayList<>();
        rivalActualCards = new ArrayList<>();
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
        allocation = size / 2;
        numberOfLeftGames = allocation;
        for (int i = 0; i < numbers.size(); i++) {
            if (i < allocation) {
                myActualCards.add(createdCardsList.get(numbers.get(i)));
                Log.i(getLocalClassName(), "card number added to myActualCards: " + numbers.get(i));
                Log.i(getLocalClassName(), "size of myActualCards: " + myActualCards.size());

            } else {
                rivalActualCards.add(createdCardsList.get(numbers.get(i)));
                Log.i(getLocalClassName(), "card number added to rivalActualCards: " + numbers.get(i));
                Log.i(getLocalClassName(), "size of rivalActualCards: " + rivalActualCards.size());
            }

        }


    }

    private void chooseWhoStart() {
        Random r = new Random();
        int who = r.nextInt(2);
        if (who == 0) {
            Log.d(getLocalClassName(), "rozpoczyna gracz:" + myDeviceName.getText().toString());
            whoStart = myDeviceName.getText().toString();
            setCardsToThumbnails(whoStart);
        } else {
            Log.d(getLocalClassName(), "rozpoczyna gracz:" + rivalDeviceName.getText().toString());
            whoStart = rivalDeviceName.getText().toString();
            setCardsToThumbnails(whoStart);
        }


    }

    private void setCardsToThumbnails(String who) {
        Log.d(getClass().getSimpleName(), "setCardToThumbnails: " + who);
        if (who.equals(myDeviceName.getText().toString())) {
            for (int i = 0; i < listOfThumbnails.size(); i++) {
                if (i >= myActualCards.size()) {
                    if (myActualCards.size() == 2) {
                        listOfThumbnails.get(0).setTag(myActualCards.get(0).pathResource);
                        listOfThumbnails.get(1).setTag(myActualCards.get(1).pathResource);
                        Picasso.with(getApplicationContext()).load(myActualCards.get(0).pathResource).fit().into(listOfThumbnails.get(0));
                        Picasso.with(getApplicationContext()).load(myActualCards.get(1).pathResource).fit().into(listOfThumbnails.get(1));
                        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listOfThumbnails.get(2));
                        listOfThumbnails.get(2).setTag(null);


                    } else if (myActualCards.size() == 1) {
                        listOfThumbnails.get(0).setTag(myActualCards.get(0).pathResource);
                        // listOfThumbnails.get(1).setTag(rivalActualCards.get(1).pathResource);
                        Picasso.with(getApplicationContext()).load(myActualCards.get(0).pathResource).fit().into(listOfThumbnails.get(0));
                        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listOfThumbnails.get(1));
                        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listOfThumbnails.get(2));
                        listOfThumbnails.get(1).setTag(null);
                        listOfThumbnails.get(2).setTag(null);

                    } else {
                        Toast.makeText(getApplicationContext(), "need to develop this", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else {
                    listOfThumbnails.get(i).setTag(myActualCards.get(i).pathResource);
                    Picasso.with(getApplicationContext()).load(myActualCards.get(i).pathResource).fit().into(listOfThumbnails.get(i));
                }
            }
        } else {
            for (int i = 0; i < listOfThumbnails.size(); i++) {
                if (i >= rivalActualCards.size()) {
                    // i=rivalActualCards.size()-1;
                    if (rivalActualCards.size() == 2) {
                        listOfThumbnails.get(0).setTag(rivalActualCards.get(0).pathResource);
                        listOfThumbnails.get(1).setTag(rivalActualCards.get(1).pathResource);
                        Picasso.with(getApplicationContext()).load(rivalActualCards.get(0).pathResource).fit().into(listOfThumbnails.get(0));
                        Picasso.with(getApplicationContext()).load(rivalActualCards.get(1).pathResource).fit().into(listOfThumbnails.get(1));
                        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listOfThumbnails.get(2));
                        listOfThumbnails.get(2).setTag(null);


                    } else if (rivalActualCards.size() == 1) {
                        listOfThumbnails.get(0).setTag(rivalActualCards.get(0).pathResource);
                        // listOfThumbnails.get(1).setTag(rivalActualCards.get(1).pathResource);
                        Picasso.with(getApplicationContext()).load(rivalActualCards.get(0).pathResource).fit().into(listOfThumbnails.get(0));
                        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listOfThumbnails.get(1));
                        Picasso.with(getApplicationContext()).load(R.color.blue_transparent_80).fit().into(listOfThumbnails.get(2));
                        listOfThumbnails.get(1).setTag(null);
                        listOfThumbnails.get(2).setTag(null);

                    } else {
                        Toast.makeText(getApplicationContext(), "need to develop this", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } else {
                    listOfThumbnails.get(i).setTag(rivalActualCards.get(i).pathResource);
                    Picasso.with(getApplicationContext()).load(rivalActualCards.get(i).pathResource).fit().into(listOfThumbnails.get(i));
                }
            }
        }
    }

    public void createCards() {
        OneCardModel firstCard = new OneCardModel(R.drawable.karta1, 10);
        OneCardModel secondCard = new OneCardModel(R.drawable.karta2, 40);
        OneCardModel thirdCard = new OneCardModel(R.drawable.karta3, 20);
        OneCardModel fourthCard = new OneCardModel(R.drawable.karta4, 50);
        OneCardModel fifthCard = new OneCardModel(R.drawable.karta5, 80);
        OneCardModel sixthCard = new OneCardModel(R.drawable.karta6, 30);
        OneCardModel seventhCard = new OneCardModel(R.drawable.karta7, 15);
        OneCardModel eighthCard = new OneCardModel(R.drawable.karta8, 18);
        OneCardModel ninthCard = new OneCardModel(R.drawable.karta9, 60);
        OneCardModel tenthCard = new OneCardModel(R.drawable.karta10, 45);
        OneCardModel eleventhCard = new OneCardModel(R.drawable.karta11, 95);
        OneCardModel twelfthCard = new OneCardModel(R.drawable.karta12, 90);
        createdCardsList = new ArrayList<>();
        createdCardsList.add(firstCard);
        createdCardsList.add(secondCard);
        createdCardsList.add(thirdCard);
        createdCardsList.add(fourthCard);
        createdCardsList.add(fifthCard);
        createdCardsList.add(sixthCard);
        createdCardsList.add(seventhCard);
        createdCardsList.add(eighthCard);
        createdCardsList.add(ninthCard);
        createdCardsList.add(tenthCard);
        createdCardsList.add(eleventhCard);
        createdCardsList.add(twelfthCard);
    }

    interface OnDialogHidedListener {
        void onDialogHided();
    }

    public class CustomDialogFragment extends DialogFragment {
        String who;
        int counter;
        String whoWin;
        OnDialogHidedListener onDialogHidedListener;

        public CustomDialogFragment(String who, int counter, String whoWin, OnDialogHidedListener onDialogHidedListener) {
            this.who = who;
            this.counter = counter;
            this.whoWin = whoWin;
            this.onDialogHidedListener = onDialogHidedListener;

        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
        }

        @Override
        public void onStart() {
            super.onStart();
            Dialog d = getDialog();
            if (d != null) {
                int width = ViewGroup.LayoutParams.MATCH_PARENT;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                d.getWindow().setLayout(width, height);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dialog_appearance, container, false);

        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }

        @Override
        public void onViewCreated(final View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            TextView whoNow = (TextView) view.findViewById(R.id.who_now_tv);
            TextView simpleTextWhatNow = (TextView) view.findViewById(R.id.what_now_tv);
            TextView simpleTextAfter = (TextView) view.findViewById(R.id.after_tv_simple_text);
            final TextView timeCount = (TextView) view.findViewById(R.id.time_count_tv);
            final TextView whoWinThisGame = (TextView) view.findViewById(R.id.who_win_tv);

            if (numberOfLeftGames == 0) {
                simpleTextWhatNow.setText("");
                simpleTextAfter.setText("");
                whoWinThisGame.setText("");
                counter = 5;
                if (numberOfMyPoints > numberofRivalPoints) {
                    who = myDeviceName.getText().toString();
                } else if (numberOfMyPoints < numberofRivalPoints) {
                    who = rivalDeviceName.getText().toString();
                } else {
                    who = "REMIS";
                }
                whoNow.setTextSize(60);
                whoNow.setText("WYGRYWA: " + who);

            } else {
                whoNow.setText(who);
            }

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (numberOfLeftGames > 0) {
                        if (counter > 1) {
                            if (counter > 2) {
                                whoWinThisGame.setText(whoWin);
                            } else {
                                whoWinThisGame.setText("");
                            }
                            counter--;
                            timeCount.setText(String.valueOf(counter));
                            handler.postDelayed(this, 1000L);
                            return;
                        }
                        tvMyPoints.setText(String.valueOf(numberOfMyPoints));
                        tvRivalPoints.setText(String.valueOf(numberofRivalPoints));

                        tvLeftGames.setText(String.valueOf(numberOfLeftGames));
                        customDialogFragment.dismiss();
                        if (CustomDialogFragment.this.onDialogHidedListener != null) {
                            CustomDialogFragment.this.onDialogHidedListener.onDialogHided();
                        }
                    } else {
                        if (counter > 1) {
                            counter--;
                            timeCount.setText(String.valueOf(counter));
                            handler.postDelayed(this, 1000L);
                            return;
                        }
                        finish();
                    }

                }
            }, 1000L);


        }


    }


}

