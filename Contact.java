package project1;

import java.util.regex.Pattern;

public class Contact {

    private static final int MAX_ID_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_ADDRESS_LENGTH = 30;
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("\\d{10}");

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName,
                   String lastName, String phone, String address) {

        validateContactId(contactId);
        validateName(firstName, "First name");
        validateName(lastName, "Last name");
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    private static void validateContactId(String contactId) {
        if (contactId == null || contactId.length() > MAX_ID_LENGTH) {
            throw new IllegalArgumentException(
                    "Contact ID must be non-null and 10 characters or less.");
        }
    }

    private static void validateName(String name, String fieldName) {
        if (name == null || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    fieldName + " must be non-null and 10 characters or less.");
        }
    }

    private static void validatePhone(String phone) {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException(
                    "Phone must be exactly 10 digits.");
        }
    }

    private static void validateAddress(String address) {
        if (address == null || address.length() > MAX_ADDRESS_LENGTH) {
            throw new IllegalArgumentException(
                    "Address must be non-null and 30 characters or less.");
        }
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateName(lastName, "Last name");
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }
}