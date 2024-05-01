import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook {
    private Map<String, Contact> contactList;

    public PhoneBook() {
        this.contactList = new HashMap<>();
    }

    public void addNewContact(Contact contact) {
        getPrimaryKey(contact);
        if (contactList.containsKey(getPrimaryKey(contact))) {
            System.out.println("Contact already exists!");
            return;
        }
        contactList.put(getPrimaryKey(contact), contact);
        System.out.println("Contact added successfully");
    }

    public void displayAllContacts() {

        System.out.println("\n\nAll Contacts present in PhoneBook:-\n");
        for (Map.Entry<String, Contact> entry : contactList.entrySet()) {
            System.out.println("Primary key: " + entry.getKey() + "  ==>  " + "Value: " + entry.getValue());
        }
    }

    public void updateContactDetails(String name, LocalDate dob, Scanner sc) {

        var contact = getContactFromList(name, dob);
        if (contact == null) {
            System.out.println("There is no contact with given credentials, please try again!!!");
            return;
        }
        boolean exitLoop = false;

        System.out.println("What would you like to update for this contact ==> " + contact);
        while (!exitLoop) {

            System.out.println("Update Instructions: \n1. PhoneNumber\n2. Email\n3. Stop updating");

            switch (sc.nextInt()) {
                case 1:
                    System.out.print("Enter PhoneNumber: ");
                    if (updateNumber(sc.next(), contact)) {
                        System.out.println("Number successfully updated!!!");
                    } else System.out.println("Couldn't update phone number");
                    break;
                case 2:
                    System.out.println("Enter Email: ");
                    contact.setEmail(sc.next());
                    System.out.println("Email successfully updated!!!");
                    break;
                case 3:
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Enter valid input!!!");
            }

            if (exitLoop) break;
        }

    }

    public void removeAll80Above() {
        Iterator<Map.Entry<String, Contact>> iterator = contactList.entrySet().iterator();
        LocalDate currentDate = LocalDate.now();

        while (iterator.hasNext()) {
            Map.Entry<String, Contact> contactEntry = iterator.next();

//            My Implementation:-
//            if (ChronoUnit.YEARS.between(contactEntry.getValue().getDob(), LocalDate.now()) >= 80) {
//                iterator.remove();
//            }

//          CoPilot's Implementation:-
            LocalDate dob = contactEntry.getValue().getDob();
            long ageInYears = ChronoUnit.YEARS.between(dob, currentDate);
            long ageInMonths = ChronoUnit.MONTHS.between(dob.plusYears(ageInYears), currentDate);
            long ageInDays = ChronoUnit.DAYS.between(dob.plusYears(ageInYears).plusMonths(ageInMonths), currentDate);

            if (ageInYears >= 80) {
                iterator.remove(); // Use iterator's remove() method
            }
        }

        System.out.println("All contacts above 80 age are removed!!!");


//        for (Map.Entry<String, Contact> contact : contactList.entrySet()) {
//            if (ChronoUnit.YEARS.between(contact.getValue().getDob(), LocalDate.now()) >= 80)
//                contactList.remove(contact.getKey());
//        }

//        Exception in thread "main" java.util.ConcurrentModificationException
//        at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1605)
//        at java.base/java.util.HashMap$EntryIterator.next(HashMap.java:1638)
//        at java.base/java.util.HashMap$EntryIterator.next(HashMap.java:1636)
//        at PhoneBook.removeAll80Above(PhoneBook.java:67)
//        at Main.main(Main.java:36)

//        The issue you’re encountering is due to concurrent modification of the contactList while iterating over it. When you remove an entry from the map during iteration, it causes a ConcurrentModificationException.
//        To avoid this, you can use an iterator explicitly and remove entries using the iterator’s remove() method. Here’s the updated removeAll80Above() method:
    }


    private boolean updateNumber(String number, Contact contact) {
        if (Contact.isPhoneNumberRegistered(number)) {
            System.out.println("This Phone is already registered with a contact, Cannot give same number to more than one contact!!!");
            return false;
        }
        if (contact.getPhoneNumberSet().contains(number))
            return false;
        return contact.getPhoneNumberSet().add(number);
    }

    private Contact getContactFromList(String name, LocalDate dob) {
        return contactList.get(getPrimaryKey(name, dob));
    }

    private String getPrimaryKey(String name, LocalDate dob) {
        return new StringBuilder().append(name).append("---").append(dob.toString()).toString();
    }

    private String getPrimaryKey(Contact contact) {
        return new StringBuilder().append(contact.getName()).append("---").append(contact.getDob().toString()).toString();
    }
}
