import java.util.Date;

public class Osoba {
    public String imie, nazwisko;
    public String pesel;
    public String adres;
    public String dataUrodzenia;
    public String dataPierwszegoNajmu;
    public int pieniadze;
    public int zadluzenia;
    public int pieniadzeZadluzenie;
    public String opis;

    public Osoba(String imie, String nazwisko, String pesel, String adres, String dataUrodzenia){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
        this.dataUrodzenia = dataUrodzenia;
        this.dataPierwszegoNajmu = "";
        this.pieniadze = 1250;
        this.zadluzenia = 0;
        this.pieniadzeZadluzenie = 0;
        this.opis = "";
    }
    public String showDataPierwszegoNajmu() throws NeverRentException{
        if(dataPierwszegoNajmu == null)
            throw new NeverRentException();
        else
            return dataPierwszegoNajmu.toString();
    }
    public String getDataPierwszegoNajmu() throws NeverRentException{
        return dataPierwszegoNajmu.toString();
    }
    public String getDataUrodzenia(){
        return dataUrodzenia;
    }
    public String getPesel(){ return pesel; }
    public String getImie(){ return imie; }
    public String getNazwisko(){ return nazwisko; }
    public int getPieniadze(){ return pieniadze; }

    @Override
    public String toString() {
        return "Osoba{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", adres='" + adres + '\'' +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                ", dataPierwszegoNajmu='" + dataPierwszegoNajmu + '\'' +
                ", pieniadze='" + pieniadze + '\'' +
                ", akta='" + opis + '\'' +
                '}';
    }
}
