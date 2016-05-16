package none.training_3;

public class OneCardModel {
    /*
    zmienne obiektu pojedynczej karty:
    pathResource - przetrzymuje adres do zasobu karty
    powerOfCard - moc karty, dowolnie ustawiana
     */
    int pathResource;
    int powerOfCard;
    boolean cardUsed;


    /*
    konstruktor do ustawiania pól tej klasy
     */
    public OneCardModel(int path, int power) {
        this.pathResource = path;
        this.powerOfCard = power;
        cardUsed = false;
    }
}
