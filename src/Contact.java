import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Contact {
    private String name;
    private static Set<String> allPhoneNumbers = new HashSet<>();
    private Set<String> phoneNumber;
    private LocalDate dob;
    private String email;

    public Contact(String name, String number, LocalDate dob, String email) {
        phoneNumber = new HashSet<>();
        this.name = name;
        this.dob = dob;
        this.email = email;
        if (!isPhoneNumberRegistered(number))
            setPhoneNumber(number);
        else
            System.out.println("Phone number already registered with a contact, can not give same number to more than one contact");
    }

    public static boolean isPhoneNumberRegistered(String number) {
        return allPhoneNumbers.contains(number);
    }

    public Set<String> getPhoneNumberSet() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.add(phoneNumber);
        allPhoneNumbers.add(phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
