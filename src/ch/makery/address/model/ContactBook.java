package ch.makery.address.model;

/**
 * Created by Sam Voigt on 5/6/15.
 */

import java.util.*;
import java.io.*;
import java.lang.Integer;

public class ContactBook<T> {

    private Hashtable<String, Contact> searchTable;

    //empty constructor used first time using the program
    public ContactBook() {
        searchTable = new Hashtable<String, Contact>(); // create a blank hashtable
    }

    /* constructor that takes in a file of information
     * makes contact objects
     * should be called once the program is initialized to read previous user data
     */
    public ContactBook(String srcFile) {
        searchTable = new Hashtable<String, Contact>();
        try {
            Scanner scan = new Scanner(new File(srcFile));
            while(scan.hasNextLine()) {
                String line = scan.nextLine(); // one line represents a single contact
                String[] fields = line.split("#"); // line is split up by # symbols
                Calendar dateContacted = Calendar.getInstance(); // sets up a calendar with default date/month/year (today)
                try {
                    setCalFromString(fields[7], dateContacted); // if there is a date set, update the calendar using the helper method to the given date/month/year of last contact
                } catch (ArrayIndexOutOfBoundsException ex) { } // don't do anything -- keep the default date/month/year Calendar
                Contact newPerson = new Contact(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], dateContacted);
                searchTable.put(newPerson.getName(), newPerson); // add new contact to the hashtable
            }
            scan.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // helper method that takes a typical "mm/dd/yyyy" formatted String and saves that information into a given Calendar object
    private void setCalFromString(String mmddyyyy, Calendar cal) {
        String[] dateArray = mmddyyyy.split("/");
        int month = Integer.parseInt(dateArray[0])-1;
        int date = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        cal.set(year, month, date);
    }

    public LinkedList<T> contactBookSearch(String category, String specificCriteria) {
        return null;
    }

    public LinkedList<T> getAllNames() {
        return null;
    }

    public void addContact(Contact person) {
        // TODO
    }

    public String toString() {
        return searchTable.toString();
    }
}
