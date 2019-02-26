package theb.r.i.a.n.system;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract class ViewFixturesPopup{

    // Display the stats popup window
    public static void display(){

        GenerateFixtures.fixtures_generation();
        // Set the properties for the popup login window
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("View Fixtures");

        // Create the grid pane for the login window and set its properties
        GridPane fixts = new GridPane();
        fixts.setVgap(4);
        fixts.setHgap(4);
        fixts.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane root = new GridPane();
        root.setVgap(4);
        root.setHgap(4);
        root.setPadding(new Insets(5, 5, 5, 5));

        // print the team names across the top of the fixtures table
        for(int i = 0; i < TheBRIANSystem.teamsArray.size(); i++){
            fixts.add(new Label(TheBRIANSystem.teamsArray.get(i).getName()), 0, i + 1);
        }

        // print the teams names down the left of the fixtures table
        for(int i = 0; i < TheBRIANSystem.teamsArray.size(); i++){
            fixts.add(new Label(TheBRIANSystem.teamsArray.get(i).getName()),i + 1, 0);
        }

        // add the actual fixtures to the table
        for(int i = 0; i < TheBRIANSystem.teamsArray.size(); i++){
            for(int c = 0; c < TheBRIANSystem.teamsArray.size(); c++){
                fixts.add(new Label(TheBRIANSystem.fixtures.get(i)[c]), c + 1, i + 1);
            }
        }

        fixts.setBorder(TheBRIANSystem.thinBorder);
        
        Label homeTeamLabel = new Label("Home Team");
        Label awayTeamLabel = new Label("Away Team");
        root.setHalignment(homeTeamLabel, HPos.CENTER);
        root.setHalignment(awayTeamLabel, HPos.CENTER);
        
        root.add(fixts, 1, 1);
        root.add(homeTeamLabel, 0, 1);
        root.add(awayTeamLabel, 1, 0);
        
        // Set the scene and display the popup window
        Scene fixtsscene = new Scene(root, 300, 200);
        popupwindow.setScene(fixtsscene);
        popupwindow.showAndWait();
    }
}
