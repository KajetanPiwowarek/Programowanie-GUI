import java.io.Serializable;

public class Save implements Serializable {
    private int punkty;
    private String nazwa;
    private String results;
    public Save(int punkty, String nazwa, String results){
        this.punkty = punkty;
        this.nazwa = nazwa;
        this.results = results;
    }

    public String toString() {
        return "Save{" +
                "punkty=" + punkty +
                ", nazwa='" + nazwa + '\'' +
                ", results='" + results + '\'' +
                '}';
    }
}
