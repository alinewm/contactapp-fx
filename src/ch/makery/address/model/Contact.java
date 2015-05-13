package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Contact {

    private final StringProperty name;
    private final StringProperty meetingLoc;
    private final StringProperty school;
    private final StringProperty city;
    //new ones
    private final StringProperty company;
    private final StringProperty email;
    private final IntegerProperty phone;
    private final StringProperty notes;
    
    private final ObjectProperty<LocalDate> lastContacted;

    /**
     * Default constructor.
     */
    public Contact() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Contact(String name) {
        this.name = new SimpleStringProperty(name);

        // Some initial dummy data, just for convenient testing.
        this.meetingLoc = new SimpleStringProperty("some place");
        this.school = new SimpleStringProperty("some school");
        this.city = new SimpleStringProperty("some city");
        this.lastContacted = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        
        //new ones for convenient testing
        this.company = new SimpleStringProperty("some company");
        this.email = new SimpleStringProperty("abc@xyz.com");
        this.phone = new SimpleIntegerProperty(1234567890); //how? integer?
        this.notes = new SimpleStringProperty("some note");
    }

    public String getName() {
        return name.get();
    }

    public void setName(String firstName) {
        this.name.set(firstName);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getMeetingLoc() {
        return meetingLoc.get();
    }

    public void setMeetingLoc(String place) {
        this.meetingLoc.set(place);
    }

    public StringProperty meetingLocProperty() {
        return meetingLoc;
    }

    public String getSchool() {
        return school.get();
    }

    public void setSchool(String school) {
        this.school.set(school);
    }

    public StringProperty schoolProperty() {
        return school;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public LocalDate getLastContacted() {
        return lastContacted.get();
    }

    public void setLastContacted(LocalDate lastContacted) {
        this.lastContacted.set(lastContacted);
    }

    public ObjectProperty<LocalDate> lastContactedProperty() {
        return lastContacted;
    }
    
    //new ones
    public String getCompany() {
        return company.get();
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public StringProperty companyProperty() {
        return company;
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }
    public Integer getPhone() {
        return phone.get();
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public IntegerProperty phoneProperty() {
        return phone;
    }
    
    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public StringProperty notesProperty() {
        return notes;
    }
}
