package theb.r.i.a.n.system;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// Create a class that can handle the viewing of the statistics.
abstract class ViewStats{

    // Display the stats popup window
    public static void display(){

        // Set the properties for the popup login window
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("View Statistics");

        // Create the grid pane for the login window and set its properties
        GridPane stats = new GridPane();
        stats.setVgap(4);
        stats.setHgap(4);
        stats.setPadding(new Insets(5, 5, 5, 5));

        // Create the labels for the top row of the statistics table
        stats.add(new Label("Matches Played"), 1, 0);
        stats.add(new Label("Matches Won"), 2, 0);
        stats.add(new Label("Sets Won"), 3, 0);

        // For each row in the statistics table...
        for(int i = 0; i < TheBRIANSystem.teamsArray.size(); i++){
            // Print the name of the team first..
            stats.add(new Label(TheBRIANSystem.teamsArray.get(i).getName()), 0, i + 1);
            // Then for each cell in the row...
            for(int c = 0; c < 3; c++){
                // Print the stats in the correct place
                stats.add(new Label(TheBRIANSystem.statisticsArray.get(i)[c]), c + 1, i + 1);
            }
        }

        // Set the scene and display the popup window
        Scene statsscene = new Scene(stats, 300, 200);
        popupwindow.setScene(statsscene);
        popupwindow.showAndWait();
    }
}