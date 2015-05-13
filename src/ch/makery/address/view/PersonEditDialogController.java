package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Contact;
import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField meetingLocField;
    @FXML
    private TextField schoolField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField lastContactedField;
    
    //new ones
    @FXML
    private TextField companyField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField notesField;

    private Stage dialogStage;
    private Contact person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Contact person) {
        this.person = person;

        nameField.setText(person.getName());
        meetingLocField.setText(person.getMeetingLoc());
        schoolField.setText(person.getSchool());
        cityField.setText(person.getCity());
        lastContactedField.setText(DateUtil.format(person.getLastContacted()));
        lastContactedField.setPromptText("dd.mm.yyyy");
        //new ones
        companyField.setText(person.getCompany());
        emailField.setText(person.getEmail());
        phoneField.setText(Integer.toString(person.getPhone()));
        notesField.setText(person.getNotes());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setName(nameField.getText());
            person.setMeetingLoc(meetingLocField.getText());
            person.setSchool(schoolField.getText());
            person.setCity(cityField.getText());
            person.setLastContacted(DateUtil.parse(lastContactedField.getText()));

            //new ones
            person.setCompany(companyField.getText());
            person.setEmail(emailField.getText());
            person.setPhone(Integer.parseInt(phoneField.getText())); //integer?
            person.setNotes(notesField.getText());
            
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
    	//GOTTA FILL THIS IN WITH NEW VARIABLES!!!!
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (meetingLocField.getText() == null || meetingLocField.getText().length() == 0) {
            errorMessage += "No valid place!\n"; 
        }

        if (schoolField.getText() == null || schoolField.getText().length() == 0) {
            errorMessage += "No valid school!\n"; 
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (lastContactedField.getText() == null || lastContactedField.getText().length() == 0) {
            errorMessage += "No valid lastContacted!\n";
        } else {
            if (!DateUtil.validDate(lastContactedField.getText())) {
                errorMessage += "No valid lastContacted. Use the format dd.mm.yyyy!\n";
            }
        }
        
        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid school!\n"; 
        } else {
            // try to parse the phone input into an int.
            try {
                Integer.parseInt(phoneField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
