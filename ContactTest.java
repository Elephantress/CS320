package project1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    private Contact validContact() {
        return new Contact(
                "C12345", "John", "Smith",
                "1234567890", "123 Main Street");
    }

    @Test
    void testValidContactCreation() {
        Contact contact = validContact();

        assertEquals("C12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "John", "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testContactIdMaximumLengthAccepted() {
        Contact contact = new Contact(
                "1234567890", "John", "Smith",
                "1234567890", "123 Main Street");

        assertEquals(10, contact.getContactId().length());
    }

    @Test
    void testContactIdOverMaximumLengthRejected() {
        String id = "1".repeat(11);
        assertEquals(11, id.length());

        assertThrows(IllegalArgumentException.class, () ->
                new Contact(id, "John", "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testContactIdCannotBeUpdated() {
        Contact contact = validContact();

        assertEquals("C12345", contact.getContactId());
        // No setContactId method exists.
    }

    @Test
    void testFirstNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", null, "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testFirstNameLengthBoundaries() {
        String tenCharacters = "a".repeat(10);
        assertEquals(10, tenCharacters.length());

        Contact contact = new Contact(
                "C1", tenCharacters, "Smith",
                "1234567890", "123 Main Street");

        assertEquals(tenCharacters, contact.getFirstName());

        String elevenCharacters = "a".repeat(11);
        assertEquals(11, elevenCharacters.length());

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", elevenCharacters, "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testLastNameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", null,
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testLastNameCannotExceedTenCharacters() {
        String name = "a".repeat(11);

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", name,
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testPhoneCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith",
                        null, "123 Main Street"));
    }

    @Test
    void testPhoneMustBeExactlyTenDigits() {
        String[] invalidPhones = {
                "123456789",
                "12345678901",
                "12345abc90",
                "123-456-7890",
                ""
        };

        for (String phone : invalidPhones) {
            assertThrows(IllegalArgumentException.class, () ->
                    new Contact("C1", "John", "Smith",
                            phone, "123 Main Street"));
        }
    }

    @Test
    void testAddressCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith",
                        "1234567890", null));
    }

    @Test
    void testAddressLengthBoundaries() {
        String thirtyCharacters = "a".repeat(30);
        assertEquals(30, thirtyCharacters.length());

        Contact contact = new Contact(
                "C1", "John", "Smith",
                "1234567890", thirtyCharacters);

        assertEquals(thirtyCharacters, contact.getAddress());

        String thirtyOneCharacters = "a".repeat(31);
        assertEquals(31, thirtyOneCharacters.length());

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith",
                        "1234567890", thirtyOneCharacters));
    }

    @Test
    void testFirstNameCanBeUpdated() {
        Contact contact = validContact();

        contact.setFirstName("Jane");

        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    void testInvalidFirstNameUpdateRejected() {
        Contact contact = validContact();

        assertThrows(IllegalArgumentException.class, () ->
                contact.setFirstName(null));

        assertThrows(IllegalArgumentException.class, () ->
                contact.setFirstName("a".repeat(11)));

        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testLastNameCanBeUpdated() {
        Contact contact = validContact();

        contact.setLastName("Jones");

        assertEquals("Jones", contact.getLastName());
    }

    @Test
    void testInvalidLastNameUpdateRejected() {
        Contact contact = validContact();

        assertThrows(IllegalArgumentException.class, () ->
                contact.setLastName(null));

        assertThrows(IllegalArgumentException.class, () ->
                contact.setLastName("a".repeat(11)));

        assertEquals("Smith", contact.getLastName());
    }

    @Test
    void testPhoneCanBeUpdated() {
        Contact contact = validContact();

        contact.setPhone("0987654321");

        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    void testInvalidPhoneUpdateRejected() {
        Contact contact = validContact();

        assertThrows(IllegalArgumentException.class, () ->
                contact.setPhone(null));

        assertThrows(IllegalArgumentException.class, () ->
                contact.setPhone("123"));

        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testAddressCanBeUpdated() {
        Contact contact = validContact();

        contact.setAddress("456 Oak Street");

        assertEquals("456 Oak Street", contact.getAddress());
    }

    @Test
    void testInvalidAddressUpdateRejected() {
        Contact contact = validContact();

        assertThrows(IllegalArgumentException.class, () ->
                contact.setAddress(null));

        assertThrows(IllegalArgumentException.class, () ->
                contact.setAddress("a".repeat(31)));

        assertEquals("123 Main Street", contact.getAddress());
    }
}