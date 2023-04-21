import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Gamer implements Serializable {
    public static ObservableList gamers = FXCollections.observableArrayList();
    public String name;
    public int score;
    public static File file = new File("./src/Gamers.txt");

    public Gamer(String name, int score){
        this.name = name;
        this.score = score;
    }

    public static void addGamer(String name, int score) throws IOException {

        FileOutputStream fos = new FileOutputStream(file,false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(new Gamer(name,score));
        oos.close();
        gamers.add(new Gamer(name,score).toString());

    }

    public static void loadGamers() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        List<Gamer> results = new LinkedList<>();

        boolean reading = true;

        while(reading) {
            try{
                results.add((Gamer)ois.readObject());
            } catch(EOFException ex){
                reading=false;
            }
        }

        ois.close();

        for(Gamer obj : results){
            gamers.add(obj.toString());
        }

    }

    public String toString(){
        return (name + " | " + score);
    }
}
