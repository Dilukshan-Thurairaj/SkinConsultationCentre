import java.util.ArrayList;
import java.util.Collections;

public class Encryption {

    private final ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private char[] letters;


    //Referred https://youtu.be/jjcJCvIoj2M for guidance
    Encryption(){
        list = new ArrayList<>();
        character = ' ';
        setShuffleList();
    }

    public void setShuffleList(){
        //32 to 126 ASCII values are used
        for(int i = 32; i < 127; i++){
            list.add(character);
            character++;
        }
        //passing the list arraylist to shuffledList arraylist --> copy of arraylist
        shuffledList = new ArrayList<>(list);
        //Shuffling the order
        Collections.shuffle(shuffledList);
    }

    public String encryptText(String text){
        letters = text.toCharArray();

        for(int i = 0; i < letters.length; i++){

            for (int j = 0; j < list.size(); j++){
                if (letters[i] == list.get(j)){
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }
        return new String(letters);
    }

    public String decryptText(String text){
        letters = text.toCharArray();

        for(int i = 0; i < letters.length; i++){

            for (int j = 0; j < shuffledList.size(); j++){
                if (letters[i] == shuffledList.get(j)){
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        return new String(letters);
    }
}

