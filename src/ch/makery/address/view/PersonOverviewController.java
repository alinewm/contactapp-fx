package ch.makery.address.view;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import ch.makery.address.MainApp;
import ch.makery.address.model.Contact;
import ch.makery.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Contact> personTable;
    @FXML
    private TableColumn<Contact, String> nameColumn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label meetingLocLabel;
    @FXML
    private Label schoolLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label lastContactedLabel;
    
    //new ones
    @FXML
    private Label companyLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label notesLabel;

    @FXML
    private ComboBox<String> searchCriteria;
    private ObservableList<String> searchCriteriaData = FXCollections.observableArrayList();

    @FXML
    private TextField searchDetail;
    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    	searchCriteria = new ComboBox<String>();
    	searchCriteriaData.addAll(
    			"Name",
    			"Email",
    			"Meeting Location",
    			"School",
    			"Company",
    			"Email",
    			"Phone",
    			"Last Contacted",
    			"Notes");
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//Adds searchCriteriaData to the combobox
		searchCriteria.setItems(searchCriteriaData);
		searchCriteria.setValue("Select criteria");
		
		//Define rendering of the list of values in ComboBox drop down. - ????
		searchCriteria.setCellFactory((comboBox) -> {
		    return new ListCell<String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
		                setText(null);
		            } else {
		                setText(item);
		            }
		        }
		    };
		});
		
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.searchResult);
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Contact person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            nameLabel.setText(person.getName());
            //lastNameLabel.setText(person.getLastName());
            meetingLocLabel.setText(person.getMeetingLoc());
            schoolLabel.setText(person.getSchool());
            cityLabel.setText(person.getCity());
            lastContactedLabel.setText(DateUtil.format(person.getLastContacted()));
            //new ones
            companyLabel.setText(person.getCompany());
            emailLabel.setText(person.getEmail());
            phoneLabel.setText(Integer.toString(person.getPhone())); //???
            notesLabel.setText(person.getNotes());
        } else {
            // Person is null, remove all the text.
            nameLabel.setText("");
            //lastNameLabel.setText("");
            meetingLocLabel.setText("");
            schoolLabel.setText("");
            cityLabel.setText("");
            lastContactedLabel.setText("");
            //new ones
            companyLabel.setText("");
            emailLabel.setText("");
            phoneLabel.setText("");
            notesLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Contact tempPerson = new Contact();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Contact selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleFind() { //STUBBBB - CHANGE THIS!
    	String selectedCriteria = searchCriteria.getSelectionModel().getSelectedItem();
    	String selectedDetail = searchDetail.getText();
    	mainApp.searchBy(selectedCriteria, selectedDetail);
    	System.out.println("find: " + selectedCriteria + " " + selectedDetail);
    }
}