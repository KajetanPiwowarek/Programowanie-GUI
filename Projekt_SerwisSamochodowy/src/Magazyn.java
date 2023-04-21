import javax.swing.*;
import java.util.*;

public class Magazyn {
    public String idMiejsca;
    public double powierzchnia;
    public Date dataWynajmu;
    public Date dataZakonczeniaNajmu;
}
//======================================================================================================================
class MagazynOsoby extends Magazyn {
    public static int counter = 1;
    public Map<Osoba, String> wynajmowaneMagazyny = new LinkedHashMap<>();
    public List<Osoba> osobyDostep;
    public List<Pojazd> pojazdyWMagazynie;
    public List<Przedmiot> przedmiotyWMagazynie;
    public Map<String, String> magazynyOsob = new LinkedHashMap<>();
    public boolean czyZajety = false;
    public MagazynOsoby(double powierzchnia){
        this.powierzchnia = powierzchnia;
        this.idMiejsca = "" + counter++ + "MO";
        osobyDostep = new LinkedList<>();
        magazynyOsob.put(this.idMiejsca, "nie zajety");
    }
    public void showMagazynyOsob(){
            for (Map.Entry<String, String> entry : magazynyOsob.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
    }
    public String showMagazynyOsobZapis(){
        for (Map.Entry<String, String> entry : magazynyOsob.entrySet()) {
             return entry.getKey() + " - " + entry.getValue();
        }
        return "";
    }
    public void oproznienieMagazynuOsoby(Osoba osoba) throws TenantAlert {
        if(czyZajety){
            if(wynajmowaneMagazyny.keySet().equals(osoba)){
                wynajmowaneMagazyny.remove(osoba, this.idMiejsca);
                this.czyZajety = false;
                magazynyOsob.replace(this.idMiejsca, "zajety", "nie zajety");
                this.dataZakonczeniaNajmu = null;
                osoba.opis = "W związku z przedawnieniem się terminu wynajmu zabieramy Pani/Panu magazyn!";
                throw new TenantAlert();
            }
        }
        else
            System.out.println("Ten magazyn jest pusty!");
    }
    public void wynajemMagazynuOsoby(Osoba osoba) throws NeverRentException, ProblematicTenantExcpetion {
        if(!this.czyZajety){
            if(osoba.zadluzenia<=3) {
                pojazdyWMagazynie = new LinkedList<>();
                przedmiotyWMagazynie = new LinkedList<>();
                wynajmowaneMagazyny.put(osoba, this.idMiejsca);
                this.osobyDostep.add(osoba);
                this.czyZajety = true;
                magazynyOsob.replace(this.idMiejsca,"nie zajety","zajety");
                if(osoba.getPieniadze() > 250) {
                    osoba.pieniadze -= 250;
                }
                else{
                    osoba.pieniadzeZadluzenie -= 250;
                    osoba.zadluzenia++;
                }
                this.dataWynajmu = new Date();
                this.dataZakonczeniaNajmu = new Date();
                dataZakonczeniaNajmu.setDate(dataWynajmu.getDate() + 30);
                if (osoba.getDataPierwszegoNajmu().isEmpty())
                    osoba.dataPierwszegoNajmu = dataWynajmu.toString();
            }
            else
                throw new ProblematicTenantExcpetion("Osoba " + osoba + " posiada juz " + osoba.zadluzenia +
                        " wynajetych pomieszczen i jest zadluzona na " + osoba.pieniadzeZadluzenie);
        }
    }
    public void showWynajeteMagazyny(Osoba osoba){
        for (Map.Entry<Osoba, String> entry : wynajmowaneMagazyny.entrySet()) {
            if(entry.getKey().equals(osoba))
                System.out.println("-->" + entry.getValue());
        }
    }
    public void przedluzenieWynajmu(Osoba osoba) throws ProblematicTenantExcpetion {
        if(osoba.zadluzenia<=3) {
            if (osoba.getPieniadze() > 50) {
                osoba.pieniadze -= 50;
                this.dataZakonczeniaNajmu.setDate(30);
            } else {
                osoba.pieniadzeZadluzenie -= 50;
                osoba.zadluzenia++;
            }
        }
        else
            throw new ProblematicTenantExcpetion("Osoba " + osoba + " posiada juz " + osoba.zadluzenia +
                    " wynajetych pomieszczen i jest zadluzona na " + osoba.pieniadzeZadluzenie);
    }
    public void dodanieUprawnienDoMagazynu(Osoba osoba){
        if(!osobyDostep.equals(osoba))
            this.osobyDostep.add(osoba);
    }
    public void odjecieUprawnienDoMagazynu(Osoba osoba){
        if(osobyDostep.equals(osoba))
            this.osobyDostep.remove(osoba);
    }
    public void showOsobyUprawnioneDoMagazynu(){
        for(Osoba o : this.osobyDostep)
            System.out.println("Osoby uprawnione: " + "[" + o.getImie() + " " + o.getNazwisko() + "]");
    }
    public void zaparkujSamochod(Pojazd pojazd, Osoba osoba){
        if((czyZajety)&&(osobyDostep.contains(osoba))){
            if(getPowierzchnia()>pojazd.getObjetoscPojazdu()){
                this.powierzchnia -= pojazd.getObjetoscPojazdu();
                pojazdyWMagazynie.add(pojazd);
            }
            else
                System.out.println("Nie ma miejsca!");
        }
        else
            System.out.println("Ten magazyn nie jest wynajmowany przez ta osobe!");
    }
    public void wyjedzSamochodem(Pojazd pojazd, Osoba osoba){
        if((czyZajety)&&(osobyDostep.contains(osoba))){
            if(pojazdyWMagazynie.contains(pojazd)){
                pojazdyWMagazynie.remove(pojazd);
                this.powierzchnia += pojazd.getObjetoscPojazdu();
            }
            else
                System.out.println("Nie ma takiego samochodu!");
        }
        else
            System.out.println("Ten magazyn nie jest wynajmowany przez ta osobe!");
    }
    public void wlozPrzedmiot(Przedmiot przedmiot, Osoba osoba){
        if(osobyDostep.contains(osoba)){
            if(getPowierzchnia()>przedmiot.getObjetosc()){
                this.powierzchnia -= przedmiot.getObjetosc();
                przedmiotyWMagazynie.add(przedmiot);
            }
            else
                System.out.println("Nie ma miejsca!");
        }
        else
            System.out.println("Osoba nie ma uprawnien!");
    }
    public void wyjmijPrzedmiot(Przedmiot przedmiot, Osoba osoba){
        if(osobyDostep.contains(osoba)){
            if(przedmiotyWMagazynie.contains(przedmiot)){
                przedmiotyWMagazynie.remove(przedmiot);
                this.powierzchnia += przedmiot.getObjetosc();
            }
            else
                System.out.println("Nie ma takiego przedmiotu!");
        }
        else
            System.out.println("Osoba nie ma uprawnien!");
    }
    public void showZawartosc(Osoba osoba){
        if(osobyDostep.contains(osoba)){
            for(Przedmiot przedmiot : przedmiotyWMagazynie){
                System.out.println(przedmiot.toString());
            }
            for(Pojazd pojazd : pojazdyWMagazynie){
                System.out.println(pojazd.toString());
            }
        }
        else
            System.out.println("To nie twoje pomieszczenie!");
    }
    public String showZawartoscZapis(){
        if (przedmiotyWMagazynie!=null) {
            for (Przedmiot przedmiot : przedmiotyWMagazynie) {
                if (przedmiot != null)
                    return przedmiot.toString() + "\n";
            }
        }
        else
            return "Nie ma przedmiotow w magazynie.";
        if (pojazdyWMagazynie!=null) {
            for (Pojazd pojazd : pojazdyWMagazynie) {
                if (pojazd != null)
                    return pojazd.toString() + "\n";
            }
        }
        else
            return "Nie ma pojazdow w magazynie.";
        return "";
    }
    public double getPowierzchnia(){ return powierzchnia;}
}
//======================================================================================================================
class MagazynSerwisu extends Magazyn {
    public static int counter = 1;
    public Map<Osoba, String> wynajmowaneMagazynySerwisowe = new LinkedHashMap<>();
    public boolean czyZajety = false;
    public Map<String, String> magazynySerwisowe = new LinkedHashMap<>();
    public List<Osoba> poczekalnia = new LinkedList<>();
    public List<Pojazd> pojazdyWMagazynieSerwisowym;
    public List<Przedmiot> przedmiotyWMagazynieSerwisowym;
    public MagazynSerwisu(double powierzchnia){
        this.powierzchnia = powierzchnia;
        this.idMiejsca = "" + counter++ + "MS";
        magazynySerwisowe.put(this.idMiejsca, "nie zajety");
    }
    public void wynajemSerwisuMechanik(Osoba osoba) throws NeverRentException, ProblematicTenantExcpetion {
        if(!this.czyZajety){
            if(osoba.zadluzenia<=3) {
                wynajmowaneMagazynySerwisowe.put(osoba, this.idMiejsca);
                this.czyZajety = true;
                magazynySerwisowe.replace(this.idMiejsca,"nie zajety","zajety");
                pojazdyWMagazynieSerwisowym = new LinkedList<>();
                przedmiotyWMagazynieSerwisowym = new LinkedList<>();
                if(osoba.getPieniadze() > 150) {
                    osoba.pieniadze -= 150;
                }
                else{
                    osoba.pieniadzeZadluzenie -= 150;
                    osoba.zadluzenia++;
                }
                this.dataWynajmu = new Date();
                this.dataZakonczeniaNajmu = new Date();
                dataZakonczeniaNajmu.setDate(dataWynajmu.getDate() + ((int) (Math.random() * 5) + 1));
            }
            else
                throw new ProblematicTenantExcpetion("Osoba " + osoba + " posiada juz " + osoba.zadluzenia +
                        " wynajetych pomieszczen i jest zadluzona na " + osoba.pieniadzeZadluzenie);
        }
        else {
            poczekalnia.add(osoba);
            sprawdzanieWolnychMiejsc(osoba);
        }
    }
    public String showZawartoscZapis(){
        if (przedmiotyWMagazynieSerwisowym!=null) {
            for (Przedmiot przedmiot : przedmiotyWMagazynieSerwisowym) {
                if (przedmiot != null)
                    return przedmiot.toString() + "\n";
            }
        }
        else
            return "Nie ma opon w magazynie.";
        if (pojazdyWMagazynieSerwisowym!=null) {
            for (Pojazd pojazd : pojazdyWMagazynieSerwisowym) {
                if (pojazd != null)
                    return pojazd.toString() + "\n";
            }
        }
        else
            return "Nie ma pojazdow w serwisie.";
        return "";
    }
    public void showZawartosc(){
        for(Przedmiot przedmiot : przedmiotyWMagazynieSerwisowym){
            System.out.println(przedmiot.toString());
        }
        for(Pojazd pojazd : pojazdyWMagazynieSerwisowym){
            System.out.println(pojazd.toString());
        }
    }
    public void wjedzSamochodem(Pojazd pojazd, Osoba osoba){
        if(czyZajety){
            if(getPowierzchnia()>pojazd.getObjetoscPojazdu()){
                this.powierzchnia -= pojazd.getObjetoscPojazdu();
                pojazdyWMagazynieSerwisowym.add(pojazd);
            }
            else
                System.out.println("Nie ma miejsca!");
        }
        else
            System.out.println("Ten magazyn nie jest wynajmowany!");
    }
    public void napraw(Pojazd pojazd,Osoba osoba){
        if(czyZajety){
            if(wynajmowaneMagazynySerwisowe.keySet().equals(osoba)){
                wynajmowaneMagazynySerwisowe.remove(osoba, this.idMiejsca);
                pojazdyWMagazynieSerwisowym.remove(pojazd);
                this.czyZajety = false;
                magazynySerwisowe.replace(this.idMiejsca, "zajety", "nie zajety");
                this.dataZakonczeniaNajmu = null;
            }
        }
        else
            System.out.println("Nic tu sie nie naprawia!");
    }
    public void oproznienieMagazynuSeriwsu(Osoba osoba) throws TenantAlert {
        if(czyZajety){
            if(wynajmowaneMagazynySerwisowe.keySet().equals(osoba)){
                wynajmowaneMagazynySerwisowe.remove(osoba, this.idMiejsca);
                this.czyZajety = false;
                magazynySerwisowe.replace(this.idMiejsca, "zajety", "nie zajety");
                this.dataZakonczeniaNajmu = null;
                osoba.opis = "W związku z przedawnieniem się terminu wynajmu zabieramy Pani/Panu magazyn!";
                throw new TenantAlert();
            }
        }
        else
            System.out.println("Ten magazyn jest pusty!");
    }
    private void sprawdzanieWolnychMiejsc(Osoba osoba) throws NeverRentException, ProblematicTenantExcpetion {
        int counter = 0;
        for (Map.Entry<String, String> entry : magazynySerwisowe.entrySet()) {
            if(entry.getValue() == "nie zajety"){
                counter++;
            }
        }
        if(counter > 0) {
            System.out.println("Sa wolne miejsca Serwisowe!");
            poczekalnia.remove(osoba);
        }
        else
            System.out.println("Nie ma zadnych wolnych miejsc - mozesz za darmo udac sie na miejsce parkingowe!");
    }

    public void wynajemSerwisuSamodzielnego(Osoba osoba) throws NeverRentException, ProblematicTenantExcpetion {
        if (!this.czyZajety){
            if (osoba.zadluzenia <= 3) {
                wynajmowaneMagazynySerwisowe.put(osoba, this.idMiejsca);
                this.czyZajety = true;
                magazynySerwisowe.replace(this.idMiejsca,"nie zajety","zajety");
                if(osoba.getPieniadze() > 50) {
                    osoba.pieniadze -= 50;
                }
                else{
                    osoba.pieniadzeZadluzenie -= 50;
                    osoba.zadluzenia++;
                }
                this.dataWynajmu = new Date();
                this.dataZakonczeniaNajmu = new Date();
                dataZakonczeniaNajmu.setDate(dataWynajmu.getDate() + ((int) (Math.random() * 5) + 1));
            }
            else
                throw new ProblematicTenantExcpetion("Osoba " + osoba + " posiada juz " + osoba.zadluzenia +
                        " wynajetych pomieszczen i jest zadluzona na " + osoba.pieniadzeZadluzenie);
        }
        else
            System.out.println("Pomieszczenie jest zajete!");
    }
    public void skladowanieOpon(Przedmiot przedmiot,Osoba osoba){
        if(osoba.getPieniadze() > 25) {
            osoba.pieniadze -= 25;
            przedmiotyWMagazynieSerwisowym.add(przedmiot);
        }
        else{
            System.out.println("Nie masz wystarczajaco pieniedzy na skladowanie opon u nas w Serwisie!");
        }
    }
    public void wyjmowanieOpon(Przedmiot przedmiot,Osoba osoba){
        if(przedmiotyWMagazynieSerwisowym.contains(przedmiot)) {
            osoba.pieniadze += 25;
            przedmiotyWMagazynieSerwisowym.remove(przedmiot);
        }
        else{
            System.out.println("Nie masz skladowanych opon!");
        }
    }
    public void showMagazynySerwisowe(){
        for (Map.Entry<String, String> entry : magazynySerwisowe.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
    public String showMagazynySerwisoweZapis(){
        for (Map.Entry<String, String> entry : magazynySerwisowe.entrySet()) {
            return entry.getKey() + " - " + entry.getValue();
        }
        return "";
    }
    public void showWynajeteMagazyny(Osoba osoba){
        for (Map.Entry<Osoba, String> entry : wynajmowaneMagazynySerwisowe.entrySet()) {
            if(entry.getKey().equals(osoba))
                System.out.println("-->" + entry.getValue());

        }
    }
    public double getPowierzchnia(){ return powierzchnia;}
}
//======================================================================================================================
class MiejsceParkingowe extends Magazyn {
    public static int counter = 1;
    public Map<Osoba, String> wynajmowaneMiejscaParkingowe = new LinkedHashMap<>();
    public boolean czyZajety = false;
    public Map<String, String> miejscaParkingowe = new LinkedHashMap<>();
    public List<Pojazd> pojazdyNaParkingu;
    public MiejsceParkingowe(double powierzchnia){
        this.powierzchnia = powierzchnia;
        this.idMiejsca = "" + counter++ + "MP";
        miejscaParkingowe.put(this.idMiejsca, "nie zajety");
    }
    public void wynajemMiejscaParkingowego(Osoba osoba) throws NeverRentException, ProblematicTenantExcpetion, TenantAlert {
        if (!this.czyZajety){
            if (osoba.zadluzenia <= 3) {
                wynajmowaneMiejscaParkingowe.put(osoba, this.idMiejsca);
                this.czyZajety = true;
                miejscaParkingowe.replace(this.idMiejsca,"nie zajety","zajety");
                pojazdyNaParkingu = new LinkedList<>();
                if(osoba.getPieniadze() > 50) {
                    osoba.pieniadze -= 50;
                }
                else{
                    osoba.pieniadzeZadluzenie -= 50;
                    osoba.zadluzenia++;
                }
                this.dataWynajmu = new Date();
                this.dataZakonczeniaNajmu = new Date();
                dataZakonczeniaNajmu.setDate(dataWynajmu.getDate() + 14);
            }
            else
                throw new ProblematicTenantExcpetion("Osoba " + osoba + " posiada juz " + osoba.zadluzenia +
                        " wynajetych pomieszczen i jest zadluzona na " + osoba.pieniadzeZadluzenie);
        }
        else{
            wynajmowaneMiejscaParkingowe.remove(this.idMiejsca);
            this.czyZajety = false;
            miejscaParkingowe.replace(this.idMiejsca, "zajety", "nie zajety");
            osoba.opis = "W związku z przyśpieszoną decyzją administracyjną, pojazd zostaje odholowany na specjalnie do tego wyznaczony płatny parking strzeżony!\n";
            throw new TenantAlert();
        }
    }
    public void oproznienieMiejscaParkingowego(Osoba osoba) throws TenantAlert {
        if(czyZajety){
            if(wynajmowaneMiejscaParkingowe.keySet().equals(osoba)){
                wynajmowaneMiejscaParkingowe.remove(osoba, this.idMiejsca);
                this.czyZajety = false;
                miejscaParkingowe.replace(this.idMiejsca, "zajety", "nie zajety");
                this.dataZakonczeniaNajmu = null;
                osoba.opis = "W związku z przedawnieniem się terminu wynajmu zabieramy Pani/Panu miejsce parkingowe!";
                throw new TenantAlert();
            }
        }
        else
            System.out.println("To miejsce parkingowe jest puste!");
    }
    public void stanSamochodem(Pojazd pojazd, Osoba osoba){
        if(czyZajety){
            if(getPowierzchnia()>pojazd.getObjetoscPojazdu()){
                this.powierzchnia -= pojazd.getObjetoscPojazdu();
                pojazdyNaParkingu.add(pojazd);
            }
            else
                System.out.println("Nie ma miejsca!");
        }
        else
            System.out.println("Ten magazyn nie jest wynajmowany!");
    }
    public void ruszSamochodem(Pojazd pojazd, Osoba osoba){
        if(czyZajety){
            if(wynajmowaneMiejscaParkingowe.keySet().equals(osoba)){
                wynajmowaneMiejscaParkingowe.remove(osoba, this.idMiejsca);
                pojazdyNaParkingu.remove(pojazd);
                this.czyZajety = false;
                miejscaParkingowe.replace(this.idMiejsca, "zajety", "nie zajety");
                this.dataZakonczeniaNajmu = null;
            }
        }
        else
            System.out.println("Nic tu nie stoi!");
    }
    public void wynajemMiejscaParkingowegoZaDarmo(Osoba osoba) throws NeverRentException, ProblematicTenantExcpetion {
        if (!this.czyZajety){
                wynajmowaneMiejscaParkingowe.put(osoba, this.idMiejsca);
                this.czyZajety = true;
                pojazdyNaParkingu = new LinkedList<>();
                miejscaParkingowe.replace(this.idMiejsca,"nie zajety","zajety");
                this.dataWynajmu = new Date();
                this.dataZakonczeniaNajmu = new Date();
                dataZakonczeniaNajmu.setDate(dataWynajmu.getDate() + 14);
            }
    }
    public String showZawartoscZapis(){
        if (pojazdyNaParkingu!=null) {
            for (Pojazd pojazd : pojazdyNaParkingu) {
                if (pojazd != null)
                    return pojazd.toString() + "\n";
            }
        }
        else
            return "Nie ma pojazdow na parkingu.";
        return "";
    }
    public void showZawartosc(){
        for(Pojazd pojazd : pojazdyNaParkingu){
            System.out.println(pojazd.toString());
        }
    }
    public void showMiejscaParkingowe(){
        for (Map.Entry<String, String> entry : miejscaParkingowe.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
    public String showMiejscaParkingoweZapis(){
        for (Map.Entry<String, String> entry : miejscaParkingowe.entrySet()) {
            return entry.getKey() + " - " + entry.getValue();
        }
        return "";
    }
    public void showWynajeteMagazyny(Osoba osoba){
        for (Map.Entry<Osoba, String> entry : wynajmowaneMiejscaParkingowe.entrySet()) {
            if(entry.getKey().equals(osoba))
                System.out.println("-->" + entry.getValue());
        }
    }
    public Map<String, String> getMiejscaParkingowe(){
        return miejscaParkingowe;
    }
    public double getPowierzchnia(){ return powierzchnia;}
}
