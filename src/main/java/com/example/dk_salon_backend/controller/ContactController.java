package com.example.dk_salon_backend.controller;
import com.example.dk_salon_backend.models.Contact;
import com.example.dk_salon_backend.service.ContactService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin(
        origins = "https://dksalon.vercel.app",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}
)
public class ContactController {

    private ContactService contactService;

    public ContactController(@Qualifier("selfStoreContactService") ContactService contactService) {
        this.contactService = contactService;
    }

    // Basic Api
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Contact Information is running!";
    }

    // Get Single Contact
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable("id") long id) {
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    // Get All Contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = contactService.getContacts();
        return ResponseEntity.ok(contacts);
    }

    // Create Contact
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        return ResponseEntity.status(201).body(createdContact);
    }

    // Delete Contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}