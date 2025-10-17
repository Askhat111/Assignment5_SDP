import PaymentTypes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Register username: ");
        String username = sc.next();
        System.out.print("Register password: ");
        String password = sc.next();
        User user = new User(username, password, 0);
        
        CheckoutFacade facade = new CheckoutFacade();
        facade.runCheckout(user);
    }
}


