package project1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private Contact createContact(String id) {
        return new Contact(
                id, "John", "Smith",
                "1234567890", "123 Main Street");
    }

    @Test
    void testAddAndRetrieveContact() {
        ContactService service = new ContactService();
        Contact contact = createContact("C1");

        service.addContact(contact);

        assertSame(contact, service.getContact("C1"));
    }

    @Test
    void testMultipleContactsCanBeAdded() {
        ContactService service = new ContactService();

        service.addContact(createContact("C1"));
        service.addContact(createContact("C2"));

        assertEquals("C1", service.getContact("C1").getContactId());
        assertEquals("C2", service.getContact("C2").getContactId());
    }

    @Test
    void testDuplicateContactIdRejected() {
        ContactService service = new ContactService();

        service.addContact(createContact("C1"));

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(createContact("C1")));
    }

    @Test
    void testNullContactRejected() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(null));
    }

    @Test
    void testDeleteContact() {
        ContactService service = new ContactService();
        service.addContact(createContact("C1"));

        service.deleteContact("C1");

        assertThrows(IllegalArgumentException.class, () ->
                service.getContact("C1"));
    }

    @Test
    void testDeleteNullIdRejected() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
                service.deleteContact(null));
    }

    @Test
    void testDeleteMissingContactRejected() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
                service.deleteContact("missing"));
    }

    @Test
    void testGetNullIdRejected() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
                service.getContact(null));
    }

    @Test
    void testGetMissingContactRejected() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
                service.getContact("missing"));
    }

    @Test
    void testUpdateFirstName() {
        ContactService service = new ContactService();
        service.addContact(createContact("C1"));

        service.updateFirstName("C1", "Jane");

        assertEquals("Jane", service.getContact("C1").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        ContactService service = new ContactService();
        service.addContact(createContact("C1"));

        service.updateLastName("C1", "Jones");

        assertEquals("Jones", service.getContact("C1").getLastName());
    }

    @Test
    void testUpdatePhone() {
        ContactService service = new ContactService();
        service.addContact(createContact("C1"));

        service.updatePhone("C1", "0987654321");

        assertEquals("0987654321", service.getContact("C1").getPhone());
    }

    @Test
    void testUpdateAddress() {
        ContactService service = new ContactService();
        service.addContact(createContact("C1"));

        service.updateAddress("C1", "456 Oak Street");

        assertEquals("456 Oak Street", service.getContact("C1").getAddress());
    }

    @Test
    void testUpdatesRejectMissingContact() {
        ContactService service = new ContactService();

        assertThrows(IllegalArgumentException.class, () ->
                service.updateFirstName("missing", "Jane"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updateLastName("missing", "Jones"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updatePhone("missing", "0987654321"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updateAddress("missing", "456 Oak Street"));
    }

    @Test
    void testInvalidFieldUpdatesRejected() {
        ContactService service = new ContactService();
        service.addContact(createContact("C1"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updateFirstName("C1", null));

        assertThrows(IllegalArgumentException.class, () ->
                service.updateLastName("C1", "a".repeat(11)));

        assertThrows(IllegalArgumentException.class, () ->
                service.updatePhone("C1", "123"));

        assertThrows(IllegalArgumentException.class, () ->
                service.updateAddress("C1", "a".repeat(31)));
    }
}