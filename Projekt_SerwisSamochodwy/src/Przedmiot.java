public class Przedmiot {
    public String nazwa;
    public double objetosc;
    public Przedmiot(int tmp){
        switch (tmp){
            case 0: this.nazwa = "Klucz"; break;
            case 1: this.nazwa = "Kabel"; break;
            case 2: this.nazwa = "Skrzynka"; break;
            case 3: this.nazwa = "Narzedzia"; break;
            case 4: this.nazwa = "Wiertarka"; break;
            case 5: this.nazwa = "Opony"; break;
        }
        this.objetosc = (Math.random()*15)+5;
    }
    public double getObjetosc(){ return objetosc; }

    @Override
    public String toString() {
        return "Przedmiot{" +
                "nazwa='" + nazwa + '\'' +
                ", objetosc=" + objetosc +
                '}';
    }
}
