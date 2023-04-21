import javax.management.monitor.Monitor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//======================================================================================================================
//                                                      ZMIENNE
//======================================================================================================================
        Map<Osoba, MagazynOsoby> osoby_magazynyOsob = new LinkedHashMap<>();
        List<MagazynOsoby> magazynyOsob = new LinkedList<>();
        MagazynOsoby magazyn1 = new MagazynOsoby(960);
        magazynyOsob.add(magazyn1);
        MagazynOsoby magazyn2 = new MagazynOsoby(1290);
        magazynyOsob.add(magazyn2);
        MagazynOsoby magazyn3 = new MagazynOsoby(840);
        magazynyOsob.add(magazyn3);
        MagazynOsoby magazyn4 = new MagazynOsoby(1156);
        magazynyOsob.add(magazyn4);
        MagazynOsoby magazyn5 = new MagazynOsoby(1087);
        magazynyOsob.add(magazyn5);

        Map<Osoba, MagazynSerwisu> osoby_magazynySerwisu = new LinkedHashMap<>();
        List<MagazynSerwisu> magazynySerwisu = new LinkedList<>();
        MagazynSerwisu magazynSerwisu1 = new MagazynSerwisu(560);
        magazynySerwisu.add(magazynSerwisu1);
        MagazynSerwisu magazynSerwisu2 = new MagazynSerwisu(723);
        magazynySerwisu.add(magazynSerwisu2);
        MagazynSerwisu magazynSerwisu3 = new MagazynSerwisu(543);
        magazynySerwisu.add(magazynSerwisu3);
        MagazynSerwisu magazynSerwisuIndywidualnego = new MagazynSerwisu(1253);
        magazynySerwisu.add(magazynSerwisuIndywidualnego);

        Map<Osoba, MiejsceParkingowe> osoby_miejscaParkingowe = new LinkedHashMap<>();
        List<MiejsceParkingowe> miejscaParkingowe = new LinkedList<>();
        MiejsceParkingowe miejsceParkingowe1 = new MiejsceParkingowe(234);
        miejscaParkingowe.add(miejsceParkingowe1);
        MiejsceParkingowe miejsceParkingowe2 = new MiejsceParkingowe(275);
        miejscaParkingowe.add(miejsceParkingowe2);
        MiejsceParkingowe miejsceParkingowe3 = new MiejsceParkingowe(295);
        miejscaParkingowe.add(miejsceParkingowe3);
        MiejsceParkingowe miejsceParkingowe4 = new MiejsceParkingowe(269);
        miejscaParkingowe.add(miejsceParkingowe4);

        SamochodMiejski samochodMiejski = new SamochodMiejski();
        Motocykl motocykl = new Motocykl();
        Amfibia amfibia = new Amfibia();
        SamochodTerenowy samochodTerenowy = new SamochodTerenowy();

        Przedmiot przedmiot1 = new Przedmiot(0);
        Przedmiot przedmiot2 = new Przedmiot(1);
        Przedmiot przedmiot3 = new Przedmiot(2);
        Przedmiot przedmiot4 = new Przedmiot(3);
        Przedmiot przedmiot5 = new Przedmiot(4);
        Przedmiot przedmiot6 = new Przedmiot(5);

        List<Osoba> osoby = new LinkedList<>();
        Osoba osoba1 = new Osoba("Jan", "Kowalski", "123456789", "ul.Bursztynowa 2", "01.01.1987");
        osoby.add(osoba1);
        Osoba osoba2 = new Osoba("Piotr", "Chmura", "987654321", "ul.Szkolna 12", "14.05.1989");
        osoby.add(osoba2);
        Osoba osoba3 = new Osoba("Jacek", "Mak", "123789456", "ul.Serocka 5", "15.06.1997");
        osoby.add(osoba3);
        Osoba osoba4 = new Osoba("Roman", "Kasztan", "789456123", "ul.Kościelna 7", "06.02.1994");
        osoby.add(osoba4);
        Osoba osoba5 = new Osoba("Franek", "Saszyński", "789123456", "ul.Wesoła 3", "26.11.1991");
        osoby.add(osoba5);
//======================================================================================================================
        try {
            magazyn1.wynajemMagazynuOsoby(osoba1);
            osoby_magazynyOsob.put(osoba1, magazyn1);
            magazyn3.wynajemMagazynuOsoby(osoba3);
            osoby_magazynyOsob.put(osoba3, magazyn3);
            magazyn1.dodanieUprawnienDoMagazynu(osoba3);
            magazyn1.dodanieUprawnienDoMagazynu(osoba4);
            magazyn1.zaparkujSamochod(samochodMiejski, osoba1);
            magazyn3.zaparkujSamochod(motocykl, osoba3);
            magazynSerwisu1.wynajemSerwisuMechanik(osoba5);
            osoby_magazynySerwisu.put(osoba5, magazynSerwisu1);
            miejsceParkingowe1.wynajemMiejscaParkingowego(osoba4);
            osoby_miejscaParkingowe.put(osoba4, miejsceParkingowe1);
        } catch (NeverRentException e) {
            e.printStackTrace();
        } catch (ProblematicTenantExcpetion problematicTenantExcpetion) {
            problematicTenantExcpetion.printStackTrace();
        } catch (TenantAlert tenantAlert) {
            tenantAlert.printStackTrace();
        }
//======================================================================================================================
//                                                      WĄTKI
//======================================================================================================================
        Date kalendarz = new Date();
        String monitor = new String();
        synchronized (monitor) {
            Thread watek1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    kalendarz.setDate(kalendarz.getDay() + 1);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
            Thread watek2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (Map.Entry<Osoba, MagazynOsoby> entry : osoby_magazynyOsob.entrySet()) {
                            if (entry.getValue().dataZakonczeniaNajmu.before(kalendarz)) {
                                entry.getValue().oproznienieMagazynuOsoby(entry.getKey());
                            }
                        }
                        for (Map.Entry<Osoba, MagazynSerwisu> entry : osoby_magazynySerwisu.entrySet()) {
                            if (entry.getValue().dataZakonczeniaNajmu.before(kalendarz)) {
                                entry.getValue().oproznienieMagazynuSeriwsu(entry.getKey());
                            }
                        }
                        for (Map.Entry<Osoba, MiejsceParkingowe> entry : osoby_miejscaParkingowe.entrySet()) {
                            if (entry.getValue().dataZakonczeniaNajmu.before(kalendarz)) {
                                entry.getValue().oproznienieMiejscaParkingowego(entry.getKey());
                            }
                        }
                    } catch (TenantAlert tenantAlert) {
                        tenantAlert.printStackTrace();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });
        }
//======================================================================================================================
//                                                      MENU
//======================================================================================================================
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zaloguj sie podajac pesel: ");
        String pesel = scanner.nextLine();
        Osoba osoba = null;
        boolean menu = false;
        switch (pesel) {
            case "123456789":
                osoba = osoba1;
                menu = true;
                break;
            case "987654321":
                osoba = osoba2;
                menu = true;
                break;
            case "123789456":
                osoba = osoba3;
                menu = true;
                break;
            case "789456123":
                osoba = osoba4;
                menu = true;
                break;
            case "789123456":
                osoba = osoba5;
                menu = true;
                break;
            default:
                System.out.println("Nie ma takiej osoby - podaj inny pesel!");
                break;
        }
        if (menu) {
            boolean wyjscie = false;
            while (!wyjscie) {
                System.out.println();
                System.out.println("---=Menu=---");
                System.out.println("0: zakoncz program");
                System.out.println("1: wypisz swoje dane łącznie z wynajętymi pomieszczeniami");
                System.out.println("2: wyświetl wolne pomieszczenia");
                System.out.println("3: wynajmij nowe pomieszczenie, po uprzednim jego wybraniu");
                System.out.println("4: wyswietl zawartosc pomieszczen, ktore zostanie wybrane");
                System.out.println("5: zaparkuj nowy pojazd lub wloz nowy przedmiot");
                System.out.println("6: wyjedz pojazdem lub wyjmij przedmiot");
                System.out.println("7: rozpocznij zgloszenie serwisowe (samodzielna lub przez mechanika)");
                System.out.println("8: wynajmij miejsce parkingowe");
                System.out.println("9: rozpocznij naprawe");
                System.out.println("10: zapisz aktualny stan aplikacji do pliku");
                System.out.println();
                System.out.print("Podaj numer, ktory chcesz wykonac: ");
                int tmp = scanner.nextInt();
                System.out.println();
                try {
                    switch (tmp) {
                        case 0:
                            wyjscie = true;
                            System.out.println("Zamykam program [...]");
                            break;
                        case 1:
                            System.out.println(osoba.toString());
                            System.out.println("Magazyny " + osoba.getImie() + " " + osoba.getNazwisko() + ": ");
                            for (MagazynOsoby magazynOsoby : magazynyOsob) {
                                magazynOsoby.showWynajeteMagazyny(osoba);
                            }
                            for (MagazynSerwisu magazynSerwisu : magazynySerwisu) {
                                magazynSerwisu.showWynajeteMagazyny(osoba);
                            }
                            for (MiejsceParkingowe miejsceParkingowe : miejscaParkingowe) {
                                miejsceParkingowe.showWynajeteMagazyny(osoba);
                            }
                            break;
                        case 2:
                            for (MagazynOsoby magazynOsoby : magazynyOsob) {
                                magazynOsoby.showMagazynyOsob();
                            }
                            for (MagazynSerwisu magazynSerwisu : magazynySerwisu) {
                                magazynSerwisu.showMagazynySerwisowe();
                            }
                            for (MiejsceParkingowe miejsceParkingowe : miejscaParkingowe) {
                                miejsceParkingowe.showMiejscaParkingowe();
                            }
                            break;
                        case 3:
                            System.out.print("Wybierz pomieszczenie (id): ");
                            String idMiejsca1 = scanner.next();
                            switch (idMiejsca1) {
                                case "1MO":
                                    magazyn1.wynajemMagazynuOsoby(osoba);
                                    osoby_magazynyOsob.put(osoba, magazyn1);
                                    break;
                                case "2MO":
                                    magazyn2.wynajemMagazynuOsoby(osoba);
                                    osoby_magazynyOsob.put(osoba, magazyn2);
                                    break;
                                case "3MO":
                                    magazyn3.wynajemMagazynuOsoby(osoba);
                                    osoby_magazynyOsob.put(osoba, magazyn3);
                                    break;
                                case "4MO":
                                    magazyn4.wynajemMagazynuOsoby(osoba);
                                    osoby_magazynyOsob.put(osoba, magazyn4);
                                    break;
                                case "5MO":
                                    magazyn5.wynajemMagazynuOsoby(osoba);
                                    osoby_magazynyOsob.put(osoba, magazyn5);
                                    break;
                                default:
                                    System.out.println("Nie ma takiego pomieszczenia!");
                                    break;
                            }
                            break;
                        case 4:
                            magazyn1.showWynajeteMagazyny(osoba);
                            System.out.print("Wybierz pomieszczenie, ktorego zawartosc chcesz obejrzec (id): ");
                            String idMiejsca2 = scanner.next();
                            switch (idMiejsca2) {
                                case "1MO":
                                    magazyn1.showZawartosc(osoba);
                                    break;
                                case "2MO":
                                    magazyn2.showZawartosc(osoba);
                                    break;
                                case "3MO":
                                    magazyn3.showZawartosc(osoba);
                                    break;
                                case "4MO":
                                    magazyn4.showZawartosc(osoba);
                                    break;
                                case "5MO":
                                    magazyn5.showZawartosc(osoba);
                                    break;
                                case "1MS":
                                    magazynSerwisu1.showZawartosc();
                                    break;
                                case "2MS":
                                    magazynSerwisu2.showZawartosc();
                                    break;
                                case "3MS":
                                    magazynSerwisu3.showZawartosc();
                                    break;
                                case "4MS":
                                    magazynSerwisuIndywidualnego.showZawartosc();
                                case "1MP":
                                    miejsceParkingowe1.showZawartosc();
                                    break;
                                case "2MP":
                                    miejsceParkingowe2.showZawartosc();
                                    break;
                                case "3MP":
                                    miejsceParkingowe3.showZawartosc();
                                    break;
                                case "4MP":
                                    miejsceParkingowe4.showZawartosc();
                                    break;
                                default:
                                    System.out.println("Nie ma takiego pomieszczenia!");
                                    break;
                            }
                            break;
                        case 5:
                            magazyn1.showWynajeteMagazyny(osoba);
                            magazynSerwisu1.showWynajeteMagazyny(osoba);
                            miejsceParkingowe1.showWynajeteMagazyny(osoba);
                            System.out.print("Do ktorego pomieszczenia chcesz zaparkowac/wlozyc pojazd/przedmiot: ");
                            String pomieszczenie1 = scanner.next();
                            MagazynOsoby magazyn_1 = null;
                            MagazynSerwisu magazyn_3 = null;
                            MiejsceParkingowe miejsceParkingowe_1 = null;
                            switch (pomieszczenie1) {
                                case "1MO":
                                    magazyn_1 = magazyn1;
                                    break;
                                case "2MO":
                                    magazyn_1 = magazyn2;
                                    break;
                                case "3MO":
                                    magazyn_1 = magazyn3;
                                    break;
                                case "4MO":
                                    magazyn_1 = magazyn4;
                                    break;
                                case "5MO":
                                    magazyn_1 = magazyn5;
                                    break;
                                case "1MS":
                                    magazyn_3 = magazynSerwisu1;
                                    break;
                                case "2MS":
                                    magazyn_3 = magazynSerwisu2;
                                    break;
                                case "3MS":
                                    magazyn_3 = magazynSerwisu3;
                                    break;
                                case "4MS":
                                    magazyn_3 = magazynSerwisuIndywidualnego;
                                case "1MP":
                                    miejsceParkingowe_1 = miejsceParkingowe1;
                                    break;
                                case "2MP":
                                    miejsceParkingowe_1 = miejsceParkingowe2;
                                    break;
                                case "3MP":
                                    miejsceParkingowe_1 = miejsceParkingowe3;
                                    break;
                                case "4MP":
                                    miejsceParkingowe_1 = miejsceParkingowe4;
                                    break;
                                default:
                                    System.out.println("Nie ma takiego pomieszczenia!");
                                    break;
                            }
                            System.out.print("Co chcesz zaparkowac/wlozyc do pomieszczenia (pojazd/przedmiot): ");
                            String temp1 = scanner.next();
                            if (temp1.equals("pojazd")) {
                                System.out.print("Podaj rodzaj pojazdu, ktorym chcesz wjechac (Terenowy/Miejski/Amfibia/Motocykl): ");
                                String pojazd1 = scanner.next();
                                switch (pojazd1) {
                                    case "Terenowy":
                                        if (!(magazyn_1 == null))
                                            magazyn_1.zaparkujSamochod(samochodTerenowy, osoba);
                                        else if (!(magazyn_3 == null))
                                            magazyn_3.wjedzSamochodem(samochodTerenowy, osoba);
                                        else
                                            miejsceParkingowe_1.stanSamochodem(samochodTerenowy, osoba);
                                        break;
                                    case "Miejski":
                                        if (!(magazyn_1 == null))
                                            magazyn_1.zaparkujSamochod(samochodMiejski, osoba);
                                        else if (!(magazyn_3 == null))
                                            magazyn_3.wjedzSamochodem(samochodMiejski, osoba);
                                        else
                                            miejsceParkingowe_1.stanSamochodem(samochodMiejski, osoba);
                                        break;
                                    case "Amfibia":
                                        if (!(magazyn_1 == null))
                                            magazyn_1.zaparkujSamochod(amfibia, osoba);
                                        else if (!(magazyn_3 == null))
                                            magazyn_3.wjedzSamochodem(amfibia, osoba);
                                        else
                                            miejsceParkingowe_1.stanSamochodem(amfibia, osoba);
                                        break;
                                    case "Motocykl":
                                        if (!(magazyn_1 == null))
                                            magazyn_1.zaparkujSamochod(motocykl, osoba);
                                        else if (!(magazyn_3 == null))
                                            magazyn_3.wjedzSamochodem(motocykl, osoba);
                                        else
                                            miejsceParkingowe_1.stanSamochodem(motocykl, osoba);
                                        break;
                                    default:
                                        System.out.println("Nie ma takiego pojazdu!");
                                        break;
                                }
                            } else if ((!(magazyn_1 == null) || !(magazyn_3 == null)) && (temp1.equals("przedmiot"))) {
                                System.out.println("Podaj nazwe przedmiotu, ktore chcesz wlozyc (Klucz/Kabel/Skrzynka/Narzedzia/Wiertarka/Opony): ");
                                String nazwa1 = scanner.next();
                                switch (nazwa1) {
                                    case "Klucz":
                                        magazyn_1.wlozPrzedmiot(przedmiot1, osoba);
                                        break;
                                    case "Kabel":
                                        magazyn_1.wlozPrzedmiot(przedmiot2, osoba);
                                        break;
                                    case "Skrzynka":
                                        magazyn_1.wlozPrzedmiot(przedmiot3, osoba);
                                        break;
                                    case "Narzedzia":
                                        magazyn_1.wlozPrzedmiot(przedmiot4, osoba);
                                        break;
                                    case "Wiertarka":
                                        magazyn_1.wlozPrzedmiot(przedmiot5, osoba);
                                        break;
                                    case "Opony":
                                        magazyn_3.skladowanieOpon(przedmiot6, osoba);
                                        break;
                                    default:
                                        System.out.println("Nie ma takiego przedmiotu");
                                        break;
                                }
                            } else
                                System.out.println("Tego nie mozna wlozyc/zaparkowac!");
                            break;
                        case 6:
                            magazynSerwisu1.showWynajeteMagazyny(osoba);
                            magazyn1.showWynajeteMagazyny(osoba);
                            miejsceParkingowe1.showWynajeteMagazyny(osoba);
                            System.out.print("Z ktorego pomieszczenia chcesz wyjechac/wyjac pojazd/przedmiot: ");
                            String pomieszczenie2 = scanner.next();
                            MagazynOsoby magazyn_2 = null;
                            MagazynSerwisu magazyn_4 = null;
                            MiejsceParkingowe miejsceParkingowe_2 = null;
                            switch (pomieszczenie2) {
                                case "1MO":
                                    magazyn_2 = magazyn1;
                                    break;
                                case "2MO":
                                    magazyn_2 = magazyn2;
                                    break;
                                case "3MO":
                                    magazyn_2 = magazyn3;
                                    break;
                                case "4MO":
                                    magazyn_2 = magazyn4;
                                    break;
                                case "5MO":
                                    magazyn_2 = magazyn5;
                                    break;
                                case "1MS":
                                    magazyn_4 = magazynSerwisu1;
                                    break;
                                case "2MS":
                                    magazyn_4 = magazynSerwisu2;
                                    break;
                                case "3MS":
                                    magazyn_4 = magazynSerwisu3;
                                    break;
                                case "4MS":
                                    magazyn_4 = magazynSerwisuIndywidualnego;
                                case "1MP":
                                    miejsceParkingowe_2 = miejsceParkingowe1;
                                    break;
                                case "2MP":
                                    miejsceParkingowe_2 = miejsceParkingowe2;
                                    break;
                                case "3MP":
                                    miejsceParkingowe_2 = miejsceParkingowe3;
                                    break;
                                case "4MP":
                                    miejsceParkingowe_2 = miejsceParkingowe4;
                                    break;
                                default:
                                    System.out.println("Nie ma takiego pomieszczenia!");
                                    break;
                            }
                            magazyn_2.showZawartosc(osoba);
                            System.out.print("Czym/Co chcesz wyjechac/wyjac z pomieszczenia (pojazd/przedmiot): ");
                            String temp2 = scanner.next();
                            if (!(magazyn_2 == null) || !(miejsceParkingowe_2 == null) && (temp2.equals("pojazd"))) {
                                System.out.print("Podaj, ktorym pojazdem chcesz wyjechac (Terenowy/Miejski/Amfibia/Motocykl): ");
                                String pojazd2 = scanner.next();
                                switch (pojazd2) {
                                    case "Terenowy":
                                        if (!(magazyn_2 == null))
                                            magazyn_2.wyjedzSamochodem(samochodTerenowy, osoba);
                                        else
                                            miejsceParkingowe_2.ruszSamochodem(samochodTerenowy, osoba);
                                        break;
                                    case "Miejski":
                                        if (!(magazyn_2 == null))
                                            magazyn_2.wyjedzSamochodem(samochodMiejski, osoba);
                                        else
                                            miejsceParkingowe_2.ruszSamochodem(samochodMiejski, osoba);
                                        break;
                                    case "Amfibia":
                                        if (!(magazyn_2 == null))
                                            magazyn_2.wyjedzSamochodem(amfibia, osoba);
                                        else
                                            miejsceParkingowe_2.ruszSamochodem(amfibia, osoba);
                                        break;
                                    case "Motocykl":
                                        if (!(magazyn_2 == null))
                                            magazyn_2.wyjedzSamochodem(motocykl, osoba);
                                        else
                                            miejsceParkingowe_2.ruszSamochodem(motocykl, osoba);
                                        break;
                                    default:
                                        System.out.println("Nie ma takiego pojazdu!");
                                        break;
                                }
                            } else if (temp2.equals("przedmiot")) {
                                System.out.println("Podaj nazwe przedmiotu, ktore chcesz wyjac (Klucz/Kabel/Skrzynka/Narzedzia/Wiertarka/Opony): ");
                                String nazwa2 = scanner.next();
                                switch (nazwa2) {
                                    case "Klucz":
                                        magazyn_2.wyjmijPrzedmiot(przedmiot1, osoba);
                                        break;
                                    case "Kabel":
                                        magazyn_2.wyjmijPrzedmiot(przedmiot2, osoba);
                                        break;
                                    case "Skrzynka":
                                        magazyn_2.wyjmijPrzedmiot(przedmiot3, osoba);
                                        break;
                                    case "Narzedzia":
                                        magazyn_2.wyjmijPrzedmiot(przedmiot4, osoba);
                                        break;
                                    case "Wiertarka":
                                        magazyn_2.wyjmijPrzedmiot(przedmiot5, osoba);
                                        break;
                                    case "Opony":
                                        magazyn_4.wyjmowanieOpon(przedmiot6, osoba);
                                    default:
                                        System.out.println("Nie ma takiego przedmiotu");
                                        break;
                                }
                            } else
                                System.out.println("Tego nie mozna wlozyc/zaparkowac!");
                            break;
                        case 7:
                            System.out.print("Czy chcesz serwisowac u Mechanika czy Samodzielnie (mechanik/samodzielnie): ");
                            String opcja = scanner.next();
                            if (opcja.equals("mechanik")) {
                                for (MagazynSerwisu magazynSerwisu : magazynySerwisu) {
                                    magazynSerwisu.showMagazynySerwisowe();
                                }
                                System.out.print("W ktorym pomieszczeniu chcesz serwisowac: ");
                                String pomieszczenie3 = scanner.next();
                                MagazynSerwisu magazynSerwisu_1 = null;
                                switch (pomieszczenie3) {
                                    case "1MS":
                                        magazynSerwisu_1 = magazynSerwisu1;
                                        break;
                                    case "2MS":
                                        magazynSerwisu_1 = magazynSerwisu2;
                                        break;
                                    case "3MS":
                                        magazynSerwisu_1 = magazynSerwisu3;
                                        break;
                                    default:
                                        System.out.println("Nie ma takiego pomieszczenia!");
                                        break;
                                }
                                magazynSerwisu_1.wynajemSerwisuMechanik(osoba);
                                osoby_magazynySerwisu.put(osoba, magazynSerwisu_1);
                            } else if (opcja.equals("samodzielnie")) {
                                magazynSerwisu1.showMagazynySerwisowe();
                                magazynSerwisuIndywidualnego.wynajemSerwisuSamodzielnego(osoba);
                                osoby_magazynySerwisu.put(osoba, magazynSerwisuIndywidualnego);
                            } else
                                System.out.println("Nie ma takiej opcji serwisowania!");
                            break;
                        case 8:
                            for (MiejsceParkingowe miejsceParkingowe : miejscaParkingowe) {
                                miejsceParkingowe.showMiejscaParkingowe();
                            }
                            System.out.print("Ktore miejsce parkingowe chcesz wynajc (id): ");
                            String id_miejscaParkingowego = scanner.next();
                            switch (id_miejscaParkingowego) {
                                case "1MP":
                                    miejsceParkingowe1.wynajemMiejscaParkingowego(osoba);
                                    osoby_miejscaParkingowe.put(osoba, miejsceParkingowe1);
                                    break;
                                case "2MP":
                                    miejsceParkingowe2.wynajemMiejscaParkingowego(osoba);
                                    osoby_miejscaParkingowe.put(osoba, miejsceParkingowe2);
                                    break;
                                case "3MP":
                                    miejsceParkingowe3.wynajemMiejscaParkingowego(osoba);
                                    osoby_miejscaParkingowe.put(osoba, miejsceParkingowe3);
                                    break;
                                case "4MP":
                                    miejsceParkingowe4.wynajemMiejscaParkingowego(osoba);
                                    osoby_miejscaParkingowe.put(osoba, miejsceParkingowe4);
                                    break;
                                default:
                                    System.out.println("Nie ma takiego miejsca parkingowego!");
                                    break;
                            }
                            break;
                        case 9:
                            System.out.println("Miejsca serwisowe zajete przez ciebie: ");
                            for (MagazynSerwisu magazynSerwisu : magazynySerwisu) {
                                magazynSerwisu.showWynajeteMagazyny(osoba);
                            }
                            System.out.println("W ktorym miejscu chcesz wykonac naprawe: ");
                            String id_miejscaSerwisowego = scanner.next();
                            MagazynSerwisu magazynSerwisu_2 = null;
                            switch (id_miejscaSerwisowego) {
                                case "1MS":
                                    magazynSerwisu_2 = magazynSerwisu1;
                                    break;
                                case "2MS":
                                    magazynSerwisu_2 = magazynSerwisu2;
                                    break;
                                case "3MS":
                                    magazynSerwisu_2 = magazynSerwisu3;
                                    break;
                                case "4MS":
                                    magazynSerwisu_2 = magazynSerwisuIndywidualnego;
                                default:
                                    System.out.println("Nie ma takiego pomieszczenia!");
                                    break;
                            }
                            System.out.println("Jaki pojazd naprawiasz (Terenowy/Miejski/Amfibia/Motocykl): ");
                            String pojazd3 = scanner.next();
                            switch (pojazd3) {
                                case "Terenowy":
                                    magazynSerwisu_2.napraw(samochodTerenowy, osoba);
                                    break;
                                case "Miejski":
                                    magazynSerwisu_2.napraw(samochodMiejski, osoba);
                                    break;
                                case "Amfibia":
                                    magazynSerwisu_2.napraw(amfibia, osoba);
                                    break;
                                case "Motocykl":
                                    magazynSerwisu_2.napraw(motocykl, osoba);
                                    break;
                                default:
                                    System.out.println("Nie ma takiego pojazdu!");
                                    break;
                            }
                            break;
                        case 10:
                            System.out.println("Zapisuje aktualny stan aplikacji [...]");
                            zapisz(magazynyOsob, magazynySerwisu, miejscaParkingowe);
                            break;
                    }
                } catch (NeverRentException e) {
                    e.printStackTrace();
                } catch (ProblematicTenantExcpetion problematicTenantExcpetion) {
                    problematicTenantExcpetion.printStackTrace();
                } catch (TenantAlert tenantAlert) {
                    tenantAlert.printStackTrace();
                }
            }
        }
        System.out.println("Zamykam program [...]");
    }
//======================================================================================================================
//                                                      ZAPIS
//======================================================================================================================
    public static void zapisz(List<MagazynOsoby> magazynyOsob, List<MagazynSerwisu> magazynySerwisu, List<MiejsceParkingowe> miejscaParkingowe) {
        String path1 = "./src/Magazyny.txt";
        String path2 = "./src/Serwis.txt";

        try {
            FileOutputStream Magazyny = new FileOutputStream(path1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Plik Magazyny.txt już istnieje.");
        }
        try {
            FileOutputStream Serwis = new FileOutputStream(path2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Plik Seris.txt juz istnieje.");
        }
        try {
            FileWriter zapis = new FileWriter(path1);
            zapis.write("Magazyny osob: " + "\n");
            for (MagazynOsoby magazynOsoby : magazynyOsob) {
                zapis.write(magazynOsoby.showMagazynyOsobZapis() + "\n");
                zapis.write(magazynOsoby.showZawartoscZapis() + "\n");
            }
            zapis.write("Magazyny serwisowe: " + "\n");
            for (MagazynSerwisu magazynSerwisu : magazynySerwisu) {
                zapis.write(magazynSerwisu.showMagazynySerwisoweZapis() + "\n");
                zapis.write(magazynSerwisu.showZawartoscZapis() + "\n");
            }
            zapis.write("Miejsca Parkingowe: " + "\n");
            for (MiejsceParkingowe miejsceParkingowe : miejscaParkingowe) {
                zapis.write(miejsceParkingowe.showMiejscaParkingoweZapis() + "\n");
                zapis.write(miejsceParkingowe.showZawartoscZapis() + "\n");
            }
            zapis.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Wystąpił problem przy zapisywaniem do pliku Magazyny.txt.");
        }
        try {
            FileWriter zapis = new FileWriter(path2);
            zapis.write("Magazyny serwisowe: " + "\n");
            for (MagazynSerwisu magazynSerwisu : magazynySerwisu) {
                zapis.write(magazynSerwisu.showMagazynySerwisoweZapis() + "\n");
                zapis.write(magazynSerwisu.showZawartoscZapis() + "\n");
                if(magazynSerwisu.dataWynajmu!=null)
                    zapis.write(magazynSerwisu.dataWynajmu.toString() + "\n");
                if(magazynSerwisu.dataZakonczeniaNajmu!=null)
                    zapis.write(magazynSerwisu.dataZakonczeniaNajmu.toString() + "\n");
            }
            zapis.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Wystąpił problem przy zapisywaniem do pliku Seris.txt.");
        }
    }
}



