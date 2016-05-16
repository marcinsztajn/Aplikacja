package none.training_3;
/*
pomocnicza klasa Message, wykorzystywana przez bibliotekę EventBus.
key - będzie zawierał daną stałą, którą będziemy używać do weryfikowania danej wiadomości
data - jest obiektem, czyli możemy tu umieścić dowolną rzecz, którą chcemy(listę,stringa,inta itp)
SEND_CONFIG_LIST -
 */
public class Message {
    public static final String SEND_CONFIG_LIST = "SEND_CONFIG_LIST";

    public String key;
    public Object data;

    /*
    konstruktor do ustawiania pól
     */
    public Message(String key, Object data){
        this.key = key;
        this.data = data;
    }



}
