public class NeverRentException extends Exception{
    public NeverRentException(){
        super("Ta osoba jeszcze niczego nie wynajęła.");
    }
}
