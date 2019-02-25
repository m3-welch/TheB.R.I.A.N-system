package theb.r.i.a.n.system;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// A class to handle selecting two teams
abstract class TeamSelector{

    static Match match;
    static boolean match_found = false;

    // Define a function that will handle the sumbission of the two teams and the closing of the window
    static private void submitAndClose(Stage window, String homeTeam, String awayTeam){

        TheBRIANSystem.matchPlayed = false;

        // Try and find if the match between the two teams exists
        for(int i = 0; i < TheBRIANSystem.matchesArray.size(); i++){
            if((TheBRIANSystem.matchesArray.get(i).getHomeTeam().getName().equals(homeTeam)) && TheBRIANSystem.matchesArray.get(i).getAwayTeam().getName().equals(awayTeam)){
                match = TheBRIANSystem.matchesArray.get(i);
                match_found = true;
            }
        }

        // If the match between the two teams does exist, set the global variable of the selectMatch to the found match
        // and set the global variable of matchPlayed to true
        if(match_found){
            TheBRIANSystem.selectedMatch = match;
            TheBRIANSystem.matchPlayed = true;
            match_found = false;
        }

        window.close();
    }

    public static void display(){
        // Set the properties for the popup team selector window
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Team Selector");
        Button submit = new Button("Submit");

        //Create an array for the team names
        String[] teamNames = new String[TheBRIANSystem.teamsArray.size()];
        for (int i = 0; i < TheBRIANSystem.teamsArray.size(); i++){
            teamNames[i] = TheBRIANSystem.teamsArray.get(i).getName();
        }

        // Create the combo boxes
        // Home team combo box
        ComboBox comboHomeTeam = new ComboBox();
        comboHomeTeam.setMinWidth(100);
        comboHomeTeam.getItems().addAll(teamNames);

        // Away team combo box
        ComboBox comboAwayTeam = new ComboBox();
        comboAwayTeam.setMinWidth(100);
        comboAwayTeam.getItems().addAll(teamNames);

        // Make the grid pane and add the features in place
        GridPane teams = new GridPane();
        teams.add(new Label("Select the home and away team:"), 0, 0);
        teams.add(new Label("Home Team:"), 0, 1);
        teams.add(comboHomeTeam, 1, 1);
        teams.add(new Label("Away Team:"), 0, 2);
        teams.add(comboAwayTeam, 1, 2);
        teams.add(submit, 0, 3);

        // Handle the button press by calling the submitAndClose functiom
        submit.setOnAction(e-> submitAndClose(popupwindow, comboHomeTeam.getValue().toString(), comboAwayTeam.getValue().toString()));

        // Set the scene and display
        Scene teamScene = new Scene(teams, 300, 200);
        popupwindow.setScene(teamScene);
        popupwindow.showAndWait();
    }
}