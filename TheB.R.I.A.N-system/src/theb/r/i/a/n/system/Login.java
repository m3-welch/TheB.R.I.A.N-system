package theb.r.i.a.n.system;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// Create a class that can handle the login for the admin page.
abstract class Login{

    // Define a function that handles the submission of the password and the closing of the window
    static private void submitAndClose(Stage window, String password){

        // If the password is correct, set the authorisation to true and close
        // the login window
        if(password.equals("admin")) {
            TheBRIANSystem.auth = true;
            window.close();
        }
        else{
            // If the password is not correct, alert the user the password is
            // wrong and allow them to re-enter the password
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INCORRECT PASSWORD!");
            alert.setHeaderText("Re-enter the password to try again");
            alert.showAndWait();
        }
    }

    // Display the login popup window
    public static void display(){

        // Set the properties for the popup login window
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Admin Login");

        // Create the label, password field and button nodes
        Label enter_password = new Label("Enter the admin password: ");
        PasswordField password = new PasswordField();
        Button submit = new Button("Submit");
        submit.setOnAction(e-> submitAndClose(popupwindow, password.getText()));

        // Allow the user to press enter while focused on the passwordfield to submit
        password.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent keyEvent){
                if(keyEvent.getCode() == KeyCode.ENTER){
                    submitAndClose(popupwindow, password.getText());
                }
            }
        });

        // Create the grid pane for the login window and set its properties
        GridPane login = new GridPane();
        login.setVgap(4);
        login.setHgap(4);
        login.setPadding(new Insets(5, 5, 5, 5));

        // Add the nodes to the login popup window
        login.add(enter_password, 0, 0);
        login.add(password, 0, 1);
        login.add(submit, 1, 1);

        // Set the scene and display the popup window
        Scene loginscene = new Scene(login, 300, 200);
        popupwindow.setScene(loginscene);
        popupwindow.showAndWait();
    }
}
