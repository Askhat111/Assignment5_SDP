import Facade.CheckoutFacade;
import User.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Register username:");
        String username = sc.next();
        System.out.print("Register password:");
        String password = sc.next();
        User user = new User(username, password);
        
        CheckoutFacade facade = new CheckoutFacade();
        facade.mainMenu(user);
    }
}


