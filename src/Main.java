import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        var pb = new PhoneBook();

        System.out.println("Welcome to phoneBook app:- ");
        boolean exit = false;

        while (!exit) {

            System.out.println("\nInstructions:-\n" +
                    "1. Add a new contact\n" +
                    "2. Display all contacts in the phoneBook\n" +
                    "3. Update contact details\n" +
                    "4. Remove all contacts who are above 80 years\n" +
                    "5. Exit\n");

            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Enter values: ");
                    System.out.println("name phoneNumber dob(yyyy-mm-dd) email");
                    pb.addNewContact(new Contact(sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.next()));
                    break;
                case 2:
                    pb.displayAllContacts();
                    break;
                case 3:
                    System.out.println("Enter the name and dob(yyyy-mm-dd) for which you want to update details: ");
                    pb.updateContactDetails(sc.next(), LocalDate.parse(sc.next()), sc);
                    break;
                case 4:
                    pb.removeAll80Above();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Enter a valid option!!!");

            }
        }
    }
}