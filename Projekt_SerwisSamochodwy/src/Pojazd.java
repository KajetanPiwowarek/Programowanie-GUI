public class Pojazd {
    enum TypSilnika{
        V2,V4,V6,V8,R2,R3,R4,R5;
    }
    public String nazwa;
    public double objetoscPojazdu;
    public String typSilnika, typ;
    public double pojemnoscSilinika;
    public Pojazd(){
        int tmp = (int)(Math.random()*8);
        switch (tmp){
            case 0: this.typSilnika = TypSilnika.V2.toString(); break;
            case 1: this.typSilnika = TypSilnika.V4.toString(); break;
            case 2: this.typSilnika = TypSilnika.V6.toString(); break;
            case 3: this.typSilnika = TypSilnika.V8.toString(); break;
            case 4: this.typSilnika = TypSilnika.R2.toString(); break;
            case 5: this.typSilnika = TypSilnika.R3.toString(); break;
            case 6: this.typSilnika = TypSilnika.R4.toString(); break;
            case 7: this.typSilnika = TypSilnika.R5.toString(); break;
        }
        switch (tmp){
            case 0: this.pojemnoscSilinika = 1.0; break;
            case 1: this.pojemnoscSilinika = 1.4; break;
            case 2: this.pojemnoscSilinika = 1.5; break;
            case 3: this.pojemnoscSilinika = 1.2; break;
            case 4: this.pojemnoscSilinika = 0.8; break;
            case 5: this.pojemnoscSilinika = 2.0; break;
            case 6: this.pojemnoscSilinika = 1.8; break;
            case 7: this.pojemnoscSilinika = 1.6; break;
        }
    }
    public double getObjetoscPojazdu(){ return objetoscPojazdu;}

    @Override
    public String toString() {
        return "Pojazd{" +
                "nazwa='" + nazwa + '\'' +
                ", objetoscPojazdu=" + objetoscPojazdu +
                ", typSilnika='" + typSilnika + '\'' +
                ", typ='" + typ + '\'' +
                ", pojemnoscSilinika=" + pojemnoscSilinika +
                '}';
    }
}
//======================================================================================================================
class SamochodTerenowy extends Pojazd{
    public SamochodTerenowy(){
        this.objetoscPojazdu = (Math.random()*21)+30;
        int tmp1 = (int)(Math.random()*6);
        switch (tmp1){
            case 0: this.nazwa = "Hummer"; break;
            case 2: this.nazwa = "Suzuki"; break;
            case 3: this.nazwa = "Jeep"; break;
            case 4: this.nazwa = "Land Rover"; break;
            case 5: this.nazwa = "Nissan"; break;
            case 6: this.nazwa = "Toyota"; break;
        }
        int tmp2 = (int)(Math.random()*4);
        switch (tmp2){
            case 0: this.typ = "SUV"; break;
            case 1: this.typ = "Crossover"; break;
            case 2: this.typ = "Pick-up"; break;
            case 3: this.typ = "Off-road"; break;
        }
    }
}
//======================================================================================================================
class SamochodMiejski extends Pojazd{
    public SamochodMiejski(){
        this.objetoscPojazdu = (Math.random()*11)+25;
        int tmp1 = (int)(Math.random()*6);
        switch (tmp1){
            case 0: this.nazwa = "Nissan"; break;
            case 2: this.nazwa = "Opel"; break;
            case 3: this.nazwa = "Peugeot"; break;
            case 4: this.nazwa = "Renault"; break;
            case 5: this.nazwa = "Seat"; break;
            case 6: this.nazwa = "Skoda"; break;
        }
        int tmp2 = (int)(Math.random()*4);
        switch (tmp2){
            case 0: this.typ = "hatchback"; break;
            case 1: this.typ = "coupé"; break;
            case 2: this.typ = "kabriolet"; break;
            case 3: this.typ = "kombi"; break;
        }
    }
}
//======================================================================================================================
class Motocykl extends Pojazd{
    public Motocykl(){
        this.objetoscPojazdu = (Math.random()*5)+2;
        int tmp1 = (int)(Math.random()*6);
        switch (tmp1){
            case 0: this.nazwa = "Honda"; break;
            case 2: this.nazwa = "Yamaha"; break;
            case 3: this.nazwa = "Kawasaki"; break;
            case 4: this.nazwa = "Romet"; break;
            case 5: this.nazwa = "Junak"; break;
            case 6: this.nazwa = "Suzuki"; break;
        }
        int tmp2 = (int)(Math.random()*4);
        switch (tmp2){
            case 0: this.typ = "Naked"; break;
            case 1: this.typ = "Sportowe"; break;
            case 2: this.typ = "Turystyczne"; break;
            case 3: this.typ = "Cruiser"; break;
        }
    }
}
//======================================================================================================================
class Amfibia extends Pojazd{
    public Amfibia(){
        this.objetoscPojazdu = (Math.random()*31)+35;
        int tmp = (int)(Math.random()*6);
        switch (tmp){
            case 0: this.nazwa = "Amphicar"; break;
            case 2: this.nazwa = "Rinspeed sQuba"; break;
            case 3: this.nazwa = "Amphi-Ranger"; break;
            case 4: this.nazwa = "Dutton Surf"; break;
            case 5: this.nazwa = "Gibbs Aquada"; break;
            case 6: this.nazwa = "Hydra Spyder"; break;
        }
        int tmp2 = (int)(Math.random()*2);
        switch (tmp2){
            case 0: this.typ = "lądowo-wodna"; break;
            case 1: this.typ = "wodno-powietrzna"; break;
        }
    }
}
