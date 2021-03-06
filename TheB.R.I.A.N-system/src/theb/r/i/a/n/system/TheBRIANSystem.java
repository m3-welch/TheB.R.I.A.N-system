package theb.r.i.a.n.system;


//import all the necessary modules
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


// Create the main application class
public class TheBRIANSystem extends Application {
    
    // Create the arrays that will hold the teams and matches
    public static List<Match> matchesArray = new ArrayList<Match>();
    public static List<Team> teamsArray = new ArrayList<Team>();
    
    public static List<String[]> statisticsArray = new ArrayList<String[]>();
    
    public static List<String[]> fixtures = new ArrayList<String[]>();
    public static int first_fixture = 0;
    
    // Obserable list vairables of the teams to be used in combo boxes
    static ObservableList<String> teamObservableList = FXCollections.observableArrayList();
    ObservableList<String> homePlayers = FXCollections.observableArrayList();
    ObservableList<String> awayPlayers = FXCollections.observableArrayList();
   
    // Boolean variables to be used throguh out the code
    public static Match selectedMatch = new Match();
    public static boolean matchPlayed = false;
    
    //Create border variables to be used
    public static Border thickBorder = new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2)));
    public static Border thinBorder = new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(1)));
      
    
    
    // Set the auth flag to false
    static public boolean auth = false;
    
    int matchID;
    
    boolean modify;
    
    @Override
    public void start(Stage primaryStage){
       
        // Run the initial setup function that loads in data
        InitialSetup.initialSetup();
        GenerateFixtures.fixtures_generation();
        //create the main tab pane that will be the basis of the whole UI
        TabPane tabs = new TabPane();
        
        //create the 3 tabs to be used with their appropriate titles
        Tab adminPage = new Tab("Admin");
        Tab viewerPage = new Tab("Viewer");
        Tab scoresheets = new Tab("Scoresheets");
        
        // Get the selection model for the tabs
        SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
        
        // Add a listener for the tab being changed
        tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                // If the new tab is the admin page and the user is not 
                // authorised
                if(newTab == adminPage && !auth) {
                    // Display the login popup window
                    Login.display();
                }
                
                // If the user is not authorised and the new tab is not the 
                // admin page
                if(!auth && (newTab == scoresheets || newTab == viewerPage)){
                    // Send the user to the new tab
                    selectionModel.select(newTab);
                }
                
                // If the user is not authorised and the page is the admin page
                if(!auth && newTab == adminPage){
                    // Send the user to the viewer page
                    selectionModel.select(viewerPage);
                }
            }
            });
        
        // Stop the user from being able to manually close any of the tabs
        adminPage.setClosable(false);
        viewerPage.setClosable(false);
        scoresheets.setClosable(false);
        
        ////////////////////
        ///              ///
        ///    -ADMIN-   ///
        ///              ///
        ////////////////////
        
        //create a grid pane for the admin tab and set all the preferences
        GridPane admin = new GridPane();
        admin.setVgap(10);
        admin.setHgap(4);
        admin.setPadding(new Insets(50, 50, 50, 50));
        
        ////////////////////////////////
        ///                          ///
        ///       -NEW TEAM-         ///
        ///                          ///
        ////////////////////////////////
        
        //create a titled pane that will hold the new team option and make it 
        //non collapsible 
        TitledPane newTeam = new TitledPane();
        newTeam.setCollapsible(false);
        
        //create a grid pane to hold the nodes for the new team pane and set
        //preferences
        GridPane newTeamGrid = new GridPane();
        newTeamGrid.setVgap(4);
        newTeamGrid.setHgap(10);
        newTeamGrid.setPadding(new Insets(5, 5, 5, 5));
        newTeam.setText("Enter a new team");
        
        //create the button and textfield for the user inputs
        TextField newTeamName = new TextField();
        newTeamName.setPromptText("Team Name");
        Button submitTeamName = new Button("Add Team");
        
        // Create a listener for the new team button
        submitTeamName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get the text from the box
                String teamName = newTeamName.getText();
                // Create a team with this name
                Team addTeam = new Team (teamName);
                // Add the team to the arrays
                teamsArray.add(addTeam);
                teamObservableList.add(addTeam.getName());
                System.out.println(addTeam.getName() + " has been added");
                
                // Update the fixtures and stats
                first_fixture = matchesArray.size();
                GenerateFixtures.fixtures_generation();
                Statistics.updateStats();
            }
        });
        
        //add the nodes to the new team grid pane
        newTeamGrid.add(newTeamName, 0, 0);
        newTeamGrid.add(submitTeamName, 1, 0);
        
        //set the content of the new team title pane to the new team grid pane
        newTeam.setContent(newTeamGrid);
        
        //add the new team title pane to the main admin grid pane
        admin.add(newTeam, 0, 0);
        
        /////////////////////////////////
        ///                           ///
        ///       -NEW PLAYER-        ///
        ///                           ///
        /////////////////////////////////
        
        //create a titled pane that will hold the new player option and make it 
        //non collapsible 
        TitledPane newPlayer = new TitledPane();
        newPlayer.setCollapsible(false);
        
        //create a grid pane to hold the nodes for the new player and set
        //preferences
        GridPane newPlayerGrid = new GridPane();
        newPlayerGrid.setVgap(4);
        newPlayerGrid.setHgap(4);
        newPlayerGrid.setPadding(new Insets(5, 5, 5, 5));
        newPlayer.setText("Enter a new player's full name");
        
        //create a textfield, a dropdown for teams and a button for the new 
        //player
        TextField playerName = new TextField();
        playerName.setPromptText("John Smith");
        Button addPlayer = new Button("Add Player");        
        ComboBox teamList = new ComboBox();
        teamList.setItems(teamObservableList);
        teamList.getSelectionModel().selectFirst();
        
        // Create a handler for the add player button click
        addPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Get player name from the text box
                    String newPlayerName = playerName.getText();
                    //Split the name into first and last name
                    String[] splitName = newPlayerName.split(" ");
                    String forename = splitName[0];
                    String surname = splitName[1];

                    // Get the team name from the combo box
                    String selectedTeamName = teamList.getValue().toString();

                    System.out.println(forename + " " + surname + " has been added to " + selectedTeamName);

                    // add the player to the selected team
                    for (int i = 0; i < teamsArray.size(); i++) {
                        if (teamsArray.get(i).getName().equals(selectedTeamName)) {
                            teamsArray.get(i).addPlayer(new Player(forename, surname));
                        }
                    }

                }
                // Make sure the user enters the name in the correct format
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ERROR! WRONG NAME FORMAT!");
                    alert.setHeaderText("The player name must be in the format of firstname lastname (e.g. John Smith).");
                    alert.showAndWait();
                }
            }
        });
        
        //Add the nodes to the new player grid pane
        newPlayerGrid.add(playerName, 0, 0);
        newPlayerGrid.add(teamList, 1, 0);
        newPlayerGrid.add(addPlayer, 2, 0);
        
        //add the nodes to the new player titled pane
        newPlayer.setContent(newPlayerGrid);
        
        //add the new player titled pane to the admin grid pane
        admin.add(newPlayer, 0, 1);
        
        ///////////////////////////////////////
        ///                                 ///
        ///       -GENERATE FIXTURES-       ///
        ///                                 ///
        ///////////////////////////////////////
        
        //create a new titled pane that will hold the option to generate
        //fixtures
        TitledPane generateFixtures = new TitledPane();
        generateFixtures.setCollapsible(false);
        
        //create a grid pane to hold the nodes for the generate fixtures box
        //and set preferences
        GridPane generateFixturesGrid = new GridPane();
        generateFixturesGrid.setVgap(4);
        generateFixturesGrid.setHgap(4);
        generateFixturesGrid.setPadding(new Insets(5, 5, 5, 5));
        generateFixtures.setText("Generate Fixtures");
        
        //create the nodes that will be contained in the generate fixtures box
        Label description = new Label();
        Label warning = new Label();
        description.setText("This will generate a match between all teams.");
        warning.setText("Warning: All pre-existing match information \n"
                + "will be removed.");
        Button generateFixturesButton = new Button("Generate Fixtures");
        
        // Create a handler for the generate fixtures button
        generateFixturesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                first_fixture = matchesArray.size();
                GenerateFixtures.fixtures_generation();
                System.out.println("FIXTURES GENERATED");
            }
        });
        

        //add the nodes to the generate fixtures grid
        generateFixturesGrid.add(description, 0, 0);
        generateFixturesGrid.add(warning, 0, 1);
        generateFixturesGrid.add(generateFixturesButton, 1, 1);
        
        //add the nodes to the titled pane
        generateFixtures.setContent(generateFixturesGrid);
        
        //add the title pan to the admin grid
        admin.add(generateFixtures, 0, 2);
        
        ///////////////////////////////////////
        ///                                 ///
        ///         -GENERATE STATS-        ///
        ///                                 ///
        ///////////////////////////////////////
        
        //create a new titled pane that will hold the option to generate
        //fixtures
        TitledPane generateStats = new TitledPane();
        generateStats.setCollapsible(false);
        
        //create a grid pane to hold the nodes for the generate fixtures box
        //and set preferences
        GridPane generateStatsGrid = new GridPane();
        generateStatsGrid.setVgap(4);
        generateStatsGrid.setHgap(4);
        generateStatsGrid.setPadding(new Insets(5, 5, 5, 5));
        generateStats.setText("Generate Statistics");
        
        //create the nodes that will be contained in the generate fixtures box
        Label auto = new Label();
        Label info = new Label();
        auto.setText("The stats report will be generatred automatically \n"
                + "every 100 seconds.");
        info.setText("You can also generate it by clicking the button \n"
                + " on the right.");
        Button generateStatsButton = new Button("Generate Statistics");
        
        // Create a handler for the generate statisctics button
        generateStatsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Statistics.updateStats();
            }
        });

        //add the nodes to the generate fixtures grid
        generateStatsGrid.add(auto, 0, 0);
        generateStatsGrid.add(info, 0, 1);
        generateStatsGrid.add(generateStatsButton, 1, 1);
        
        //add the nodes to the titled pane
        generateStats.setContent(generateStatsGrid);
        
        //add the title pan to the admin grid
        admin.add(generateStats, 0, 3);
        
        //set the admin page contents to the admin grid pane
        adminPage.setContent(admin);
        
        
        /////////////////////
        ///               ///
        ///   -Viewer-    ///
        ///               ///
        /////////////////////
        
        
        
        //create a grid pane for the viewer tab and set all the preferences
        GridPane viewer = new GridPane();
        viewer.setVgap(6);
        viewer.setHgap(6);
        viewer.setPadding(new Insets(5, 5, 5, 5));
        GridPane buttons = new GridPane();
        GridPane root = new GridPane();
        buttons.setVgap(100);
        buttons.setHgap(100);
        buttons.setPadding(new Insets(50, 50, 50, 50));
        root.setVgap(6);
        root.setHgap(6);
        root.setPadding(new Insets(5, 5, 5, 5));
        
        //add the nodes to the viewer grid pane
        Button viewFixtures = new Button("View Fixture and Result Chart");
        Button teamStats = new Button("Show All Team Stats");
        Button teamRanks = new Button("Show All Team Rankings");
        Button viewScores = new Button("View A Match Scores");
        viewFixtures.setMinWidth(200);
        teamStats.setMinWidth(200);
        teamRanks.setMinWidth(200);
        viewScores.setMinWidth(200);
        viewFixtures.setMinHeight(50);
        teamStats.setMinHeight(50);
        teamRanks.setMinHeight(50);
        viewScores.setMinHeight(50);
        viewFixtures.wrapTextProperty().setValue(true);
        teamStats.wrapTextProperty().setValue(true);
        teamRanks.wrapTextProperty().setValue(true);
        viewScores.wrapTextProperty().setValue(true);
        viewFixtures.textAlignmentProperty().set(TextAlignment.CENTER);
        teamStats.textAlignmentProperty().set(TextAlignment.CENTER);
        teamRanks.textAlignmentProperty().set(TextAlignment.CENTER);
        viewScores.textAlignmentProperty().set(TextAlignment.CENTER);

        // Set the preferences of the nodes
        viewFixtures.setPrefWidth(205);
        
        // Create a handler for the view fixtures button
        viewFixtures.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewFixturesPopup.display();
                               
                System.out.println("--VIEW FIXTURES AND RESULTS--");
            }
        });

        
        // Create a handler for the team stats button
        teamStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ViewStats.display();
                System.out.println("--VIEWING STATISTICS--");
            }
        });
        
        // Create a handler for the team ranks button
        teamRanks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--VIEWING RANKINGS--");
                
                // Create a popup to view the team rankings
                Stage rankPopup = new Stage();
                rankPopup.initModality(Modality.APPLICATION_MODAL);
                rankPopup.setTitle("Team Rankings");
                
                //Create the pane layout for the popup
                GridPane ranks = new GridPane();
                ranks.setVgap(4);
                ranks.setHgap(4);
                ranks.setPadding(new Insets(5, 5, 5, 5));

                // add the labels along the top of the rankings chart
                ranks.add(new Label("Rank "), 0, 0);
                ranks.add(new Label("Team Names "), 1, 0);
                ranks.add(new Label("Matches won"), 2, 0);
                
                int maxWins = 0;
                List<Integer> winOrder = new ArrayList<Integer>();

                // Find the highest number of matches won
                for (int i = 0; i < teamsArray.size(); i++){
                    if(teamsArray.get(i).getMatchesWon() > maxWins){
                        maxWins = teamsArray.get(i).getMatchesWon();
                    }
                }

                // Find the index of each team in the teamsArray in score order
                while(winOrder.size() < teamsArray.size()){
                    for(int j = 0; j < teamsArray.size(); j++){
                        if(teamsArray.get(j).getMatchesWon() == maxWins){
                            winOrder.add(0, j);
                        }
                    }
                    maxWins--;
                }

                // Add the teams to the popup window in the correct order
                int teamIndex;
                int popupIndex = 1;
                while(winOrder.size() > 0){
                    teamIndex = winOrder.get(winOrder.size() - 1);
                    ranks.add(new Label(Integer.toString(popupIndex)), 0, popupIndex);
                    ranks.add(new Label(teamsArray.get(teamIndex).getName()), 1, popupIndex);
                    ranks.add(new Label(Integer.toString(teamsArray.get(teamIndex).getMatchesWon())), 2, popupIndex);
                    popupIndex++;
                    winOrder.remove(winOrder.size() - 1);
                }

                // set the scene and show the window
                Scene ranksScene = new Scene(ranks, 200, 200);
                rankPopup.setScene(ranksScene);
                rankPopup.showAndWait();
            }
        });
        
        // Create a handler for the view scores button
        viewScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Create popup to select home and away team
                matchPlayed = false;
                TeamSelector.display();
                if(matchPlayed){  // If the selected match has been played
                    System.out.println("--VIEWING SCORES--");
                    Match match = selectedMatch;
                    
                    // Create a popup to display the match details
                    Stage matchPopup = new Stage();
                    matchPopup.initModality(Modality.APPLICATION_MODAL);
                    matchPopup.setTitle("Match viewer");
                    
                    //Create the layout for the match popup
                    GridPane scoresheet = new GridPane();
                    scoresheet.setVgap(4);
                    scoresheet.setHgap(4);
                    scoresheet.setPadding(new Insets(5, 5, 5, 5));

                    GridPane sides = new GridPane();
                    sides.setVgap(4);
                    sides.setHgap(4);
                    sides.setPadding(new Insets(5, 5, 5, 5));

                    GridPane scoreroot = new GridPane();
                    scoreroot.setVgap(4);
                    scoreroot.setHgap(4);
                    scoreroot.setPadding(new Insets(5, 5, 5, 5));

                    GridPane grid = new GridPane();
                    grid.setVgap(4);
                    grid.setHgap(4);
                    grid.setPadding(new Insets(5, 5, 5, 5));

                    GridPane set1 = new GridPane();
                    set1.setVgap(4);
                    set1.setHgap(4);
                    set1.setPadding(new Insets(5, 5, 5, 5));

                    GridPane set2 = new GridPane();
                    set2.setVgap(4);
                    set2.setHgap(4);
                    set2.setPadding(new Insets(5, 5, 5, 5));

                    GridPane set3 = new GridPane();
                    set3.setVgap(4);
                    set3.setHgap(4);
                    set3.setPadding(new Insets(5, 5, 5, 5));

                    GridPane set4 = new GridPane();
                    set4.setVgap(4);
                    set4.setHgap(4);
                    set4.setPadding(new Insets(5, 5, 5, 5));

                    GridPane doubleSet = new GridPane();
                    doubleSet.setVgap(4);
                    doubleSet.setHgap(4);
                    doubleSet.setPadding(new Insets(5, 5, 5, 5));
                    
                    //Create the labels for the display
                    Label homeTeamLabel = new Label("Home Team: " + match.getHomeTeam().getName());
                    Label awayTeamLabel = new Label("Away Team: " + match.getAwayTeam().getName());
                    
                    Label singleSets = new Label("Single Sets");
                    Label doubleSetLabel = new Label("Double Set");
                    
                    //Get the player's names
                    Label awayPlayer1 = new Label(match.awayPlayer1.getFullName());
                    Label awayPlayer2 = new Label(match.awayPlayer2.getFullName());
                    Label homePlayer1 = new Label(match.homePlayer1.getFullName());
                    Label homePlayer2 = new Label(match.homePlayer2.getFullName());
                    
                    //Create the labels which display each set
                    Label set11 = new Label(Integer.toString(match.sets.get(0).games.get(0).getHomeScore()) + ":" + Integer.toString(match.sets.get(0).games.get(0).getAwayScore()));
                    Label set12 = new Label(Integer.toString(match.sets.get(0).games.get(1).getHomeScore()) + ":" + Integer.toString(match.sets.get(0).games.get(1).getAwayScore()));
                    Label set13 = new Label(Integer.toString(match.sets.get(0).games.get(2).getHomeScore()) + ":" + Integer.toString(match.sets.get(0).games.get(2).getAwayScore()));
                    
                    Label set21 = new Label(Integer.toString(match.sets.get(1).games.get(0).getHomeScore()) + ":" + Integer.toString(match.sets.get(1).games.get(0).getAwayScore()));
                    Label set22 = new Label(Integer.toString(match.sets.get(1).games.get(1).getHomeScore()) + ":" + Integer.toString(match.sets.get(1).games.get(1).getAwayScore()));
                    Label set23 = new Label(Integer.toString(match.sets.get(1).games.get(2).getHomeScore()) + ":" + Integer.toString(match.sets.get(1).games.get(2).getAwayScore()));
                    
                    Label set31 = new Label(Integer.toString(match.sets.get(2).games.get(0).getHomeScore()) + ":" + Integer.toString(match.sets.get(2).games.get(0).getAwayScore()));
                    Label set32 = new Label(Integer.toString(match.sets.get(2).games.get(1).getHomeScore()) + ":" + Integer.toString(match.sets.get(2).games.get(1).getAwayScore()));
                    Label set33 = new Label(Integer.toString(match.sets.get(2).games.get(2).getHomeScore()) + ":" + Integer.toString(match.sets.get(2).games.get(2).getAwayScore()));
                    
                    Label set41 = new Label(Integer.toString(match.sets.get(3).games.get(0).getHomeScore()) + ":" + Integer.toString(match.sets.get(3).games.get(0).getAwayScore()));
                    Label set42 = new Label(Integer.toString(match.sets.get(3).games.get(1).getHomeScore()) + ":" + Integer.toString(match.sets.get(3).games.get(1).getAwayScore()));
                    Label set43 = new Label(Integer.toString(match.sets.get(3).games.get(2).getHomeScore()) + ":" + Integer.toString(match.sets.get(3).games.get(2).getAwayScore()));
                    
                    Label setd1 = new Label(Integer.toString(match.sets.get(4).games.get(0).getHomeScore()) + ":" + Integer.toString(match.sets.get(4).games.get(0).getAwayScore()));
                    Label setd2 = new Label(Integer.toString(match.sets.get(4).games.get(1).getHomeScore()) + ":" + Integer.toString(match.sets.get(4).games.get(1).getAwayScore()));
                    Label setd3 = new Label(Integer.toString(match.sets.get(4).games.get(2).getHomeScore()) + ":" + Integer.toString(match.sets.get(4).games.get(2).getAwayScore()));
                    
                    // Create the last label for the final result
                    match.calculateWinner();
                    Label finalTeamScores = new Label("Final Score:\n" + Integer.toString(match.getHomeScore()) + ":" + Integer.toString(match.getAwayScore()));
                    
                    // Add the nodes
                    set1.add(set11, 0, 0);
                    set1.add(set12, 0, 1);
                    set1.add(set13, 0, 2);
                    set2.add(set21, 0, 0);
                    set2.add(set22, 0, 1);
                    set2.add(set23, 0, 2);
                    set3.add(set31, 0, 0);
                    set3.add(set32, 0, 1);
                    set3.add(set33, 0, 2);
                    set4.add(set41, 0, 0);
                    set4.add(set42, 0, 1);
                    set4.add(set43, 0, 2);
                    doubleSet.add(setd1, 0, 0);
                    doubleSet.add(setd2, 0, 1);
                    doubleSet.add(setd3, 0, 2);

                    //add the nodes to the gridpane
                    sides.add(homeTeamLabel, 0, 0);
                    sides.add(awayTeamLabel, 2, 0);
                    grid.add(singleSets, 0, 0);
                    grid.add(awayPlayer1, 1, 0);
                    grid.add(awayPlayer2, 2, 0);
                    grid.add(homePlayer1, 0, 1);
                    grid.add(homePlayer2, 0, 2);
                    grid.add(doubleSetLabel, 0, 3);
                    grid.add(finalTeamScores, 2, 3);
                    grid.add(set1, 1, 1);
                    grid.add(set2, 2, 1);
                    grid.add(set3, 1, 2);
                    grid.add(set4, 2, 2);
                    grid.add(doubleSet, 1, 3);
                    
                    // Add panes to the root
                    scoreroot.add(scoresheet, 0, 0);
                    scoreroot.add(sides, 0, 1);
                    scoreroot.add(grid, 0, 2);
                    
                    // Create a border for the grid
                    sides.setBorder(thickBorder);
                    grid.setBorder(thickBorder);
                    
                    set1.setBorder(thinBorder);
                    set2.setBorder(thinBorder);
                    set3.setBorder(thinBorder);
                    set4.setBorder(thinBorder);
                    doubleSet.setBorder(thinBorder);

                    // Assign the scene and display it
                    Scene teamScene = new Scene(scoreroot, 300, 320);
                    matchPopup.setScene(teamScene);
                    matchPopup.showAndWait();
                }
                else{  // If the game hasn't been played yet
                    System.out.println("--MATCH NOT PLAYED--");
                }
            }
        });
        
        //add the nodes to the viewer grid pane
        buttons.add(viewFixtures, 0, 0);
        buttons.add(teamStats, 0, 1);
        buttons.add(teamRanks, 1, 0);
        buttons.add(viewScores, 1, 1);
        root.add(buttons, 0, 0);
        
        //add the grid pane to the viewer tab
        viewerPage.setContent(root);
        
        /////////////////////////////////////////
        ///// scoresheets tabs ////////////////
        ///////////////////////////////////////
        
        //create grid panes for the scoresheet tab and set all the preferences
        GridPane scoresheet = new GridPane();
        scoresheet.setVgap(4);
        scoresheet.setHgap(4);
        scoresheet.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane sides = new GridPane();
        sides.setVgap(4);
        sides.setHgap(4);
        sides.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane scoreroot = new GridPane();
        scoreroot.setVgap(4);
        scoreroot.setHgap(4);
        scoreroot.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane set1 = new GridPane();
        set1.setVgap(4);
        set1.setHgap(4);
        set1.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane set2 = new GridPane();
        set2.setVgap(4);
        set2.setHgap(4);
        set2.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane set3 = new GridPane();
        set3.setVgap(4);
        set3.setHgap(4);
        set3.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane set4 = new GridPane();
        set4.setVgap(4);
        set4.setHgap(4);
        set4.setPadding(new Insets(5, 5, 5, 5));
        
        GridPane doubleSet = new GridPane();
        doubleSet.setVgap(4);
        doubleSet.setHgap(4);
        doubleSet.setPadding(new Insets(5, 5, 5, 5));
        
        //Create nodes
        Button newSheet = new Button("New Sheet");
        newSheet.setMinWidth(300);
        Button modifySheet = new Button("Modify Sheet");
        modifySheet.setMinWidth(300);
        Label homeTeamLabel = new Label("Home Team");
        Label awayTeamLabel = new Label("Away Team");
        
        ComboBox homeTeam = new ComboBox();
        ComboBox awayTeam = new ComboBox();

        Label singleSets = new Label("Single Sets");
        Label doubleSetLabel = new Label("Double Set");
        
        homeTeam.setItems(teamObservableList);
        homeTeam.setMinWidth(150);
        homeTeam.getSelectionModel().selectFirst();
        for(int player = 0;  player < teamsArray.get(0).players.size(); player++)
            homePlayers.add(teamsArray.get(0).players.get(player).getFullName());
        
        //Add a listener for when the combo box is changed
        homeTeam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            //A method to check if the value has been changed or not
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldTeam, String newTeam) {
                if(!(oldTeam.equals(newTeam))){  // If the combo box has been changed
                    homePlayers.clear();  // Clear the players array
                    for(int teamNum = 0; teamNum < teamsArray.size(); teamNum++) {
                        // Find which team we are now looking at
                        if(teamsArray.get(teamNum).getName().equals(newTeam)){
                            // When found -> add players to observable list
                            for(int player = 0;  player < teamsArray.get(teamNum).players.size(); player++)
                                homePlayers.add(teamsArray.get(teamNum).players.get(player).getFullName());
                        }
                    }
                }
            }
        });
        
        awayTeam.setItems(teamObservableList);
        awayTeam.setMinWidth(150);
        awayTeam.getSelectionModel().selectFirst();
        for(int player = 0;  player < teamsArray.get(0).players.size(); player++)
            awayPlayers.add(teamsArray.get(0).players.get(player).getFullName());
        
        //Add a listener for when the combo box is changed
        awayTeam.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            //A method to check if the value has been changed or not
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldTeam, String newTeam) {
                if(!(oldTeam.equals(newTeam))){  // If the combo box has been changed
                    awayPlayers.clear();  // Clear the players array
                    for(int teamNum = 0; teamNum < teamsArray.size(); teamNum++) {
                        // Find which team we are now looking at
                        if(teamsArray.get(teamNum).getName().equals(newTeam)){
                            // When found -> add players to observable list
                            for(int player = 0;  player < teamsArray.get(teamNum).players.size(); player++)
                                awayPlayers.add(teamsArray.get(teamNum).players.get(player).getFullName());
                        }
                    }
                }
            }
        });
       
        
        ComboBox awayPlayer1 = new ComboBox();
        awayPlayer1.setItems(awayPlayers);
        awayPlayer1.setMinWidth(150);
        
        ComboBox awayPlayer2 = new ComboBox();
        awayPlayer2.setItems(awayPlayers);
        awayPlayer2.setMinWidth(150);
        
        ComboBox homePlayer1 = new ComboBox();
        homePlayer1.setItems(homePlayers);
        homePlayer1.setMinWidth(100);
        
        ComboBox homePlayer2 = new ComboBox();
        homePlayer2.setItems(homePlayers);
        homePlayer2.setMinWidth(100);
        
        homeTeam.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           if(newValue.equals(awayTeam.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR! Please try again.");
                alert.setHeaderText("ERROR! SAME TEAM SELECTED TWICE");
                alert.showAndWait();
           }
        }
        ); 
        
        awayTeam.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           if(newValue.equals(homeTeam.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR! Please try again.");
                alert.setHeaderText("ERROR! SAME TEAM SELECTED TWICE");
                alert.showAndWait();
           }
        }
        ); 
        
        homePlayer1.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           if(newValue.equals(homePlayer2.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR! Please try again.");
                alert.setHeaderText("ERROR! SAME PLAYER SELECTED TWICE");
                alert.showAndWait();
           }
        }
        ); 
        
        homePlayer2.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           if(newValue.equals(homePlayer1.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR! Please try again.");
                alert.setHeaderText("ERROR! SAME PLAYER SELECTED TWICE");
                alert.showAndWait();
           }
        }
        ); 
        
        awayPlayer1.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           if(newValue.equals(awayPlayer2.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR! Please try again.");
                alert.setHeaderText("ERROR! SAME PLAYER SELECTED TWICE");
                alert.showAndWait();
           }
        }
        ); 
        
        awayPlayer2.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
           if(newValue.equals(awayPlayer1.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR! Please try again.");
                alert.setHeaderText("ERROR! SAME PLAYER SELECTED TWICE");
                alert.showAndWait();
           }
        }
        ); 
        
        TextField finalTeamScores = new TextField("Final Team Scores");
        finalTeamScores.setEditable(false);
        TextField set11 = new TextField("0:0");
        TextField set12 = new TextField("0:0");
        TextField set13 = new TextField("0:0");
        TextField set21 = new TextField("0:0");
        TextField set22 = new TextField("0:0");
        TextField set23 = new TextField("0:0");
        TextField set31 = new TextField("0:0");
        TextField set32 = new TextField("0:0");
        TextField set33 = new TextField("0:0");
        TextField set41 = new TextField("0:0");
        TextField set42 = new TextField("0:0");
        TextField set43 = new TextField("0:0");
        TextField setd1 = new TextField("0:0");
        TextField setd2 = new TextField("0:0");
        TextField setd3 = new TextField("0:0");
        Button calculate = new Button("Calculate and Submit scores");
        calculate.setMinWidth(500);
        
        // Create a handler for the new sheet button
        newSheet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--CREATING A NEW SCORESHEET--");
                int tempMatchID = 0;
                int newMatchID = 0;
                for(int i = 0; i < matchesArray.size(); i++){
                    if(matchesArray.get(i).getMatchID() > tempMatchID){
                        tempMatchID = matchesArray.get(i).getMatchID();
                    }
                }
                newMatchID = tempMatchID + 1;
                matchID = newMatchID;
                modify = false;
                set11.setText("0:0");
                set12.setText("0:0");
                set13.setText("0:0");
                set21.setText("0:0");
                set22.setText("0:0");
                set23.setText("0:0");
                set31.setText("0:0");
                set32.setText("0:0");
                set33.setText("0:0");
                set41.setText("0:0");
                set42.setText("0:0");
                set43.setText("0:0");
                setd1.setText("0:0");
                setd2.setText("0:0");
                setd3.setText("0:0");
            }
        });

        
        // Create a handler for the modify sheet button
        modifySheet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TeamSelector.display();
                Match match = selectedMatch;
                
                // For each of the items in the matches array
                for(int i = 0;i<matchesArray.size();i++){
                    // If the match ID matches the match ID the user has chosen,
                    // do this
                    if(matchesArray.get(i).getMatchID() == match.getMatchID()){
                        // Set a flag to true to show a file has been modified
                        // rather than a new file being created
                        modify = true;
                        // Set the global matchID to make saving easier
                        matchID = matchesArray.get(i).getMatchID();
                        // Set the home team combo box to the home team name
                        homeTeam.setValue(matchesArray.get(i).getHomeTeam().getName());
                        // Set the away team combo box to the away team name
                        awayTeam.setValue(matchesArray.get(i).getAwayTeam().getName());
                        // Set the homePlayer1 combo box to the homePlayer1
                        // first name
                        homePlayer1.setValue(matchesArray.get(i).getHomePlayer1().getFullName());
                        // Set the homePlayer2 combo box to the homePlayer2
                        // first name
                        homePlayer2.setValue(matchesArray.get(i).getHomePlayer2().getFullName());
                        // Set the awayPlayer1 combo box to the awayPlayer1
                        // first name
                        awayPlayer1.setValue(matchesArray.get(i).getAwayPlayer1().getFullName());
                        // Set the awayPlayer2 combo box to the awayPlayer2
                        // first name
                        awayPlayer2.setValue(matchesArray.get(i).getAwayPlayer2().getFullName());
                        
                        // Set all of the text fields to their appropriate scores
                        set11.setText(Integer.toString(matchesArray.get(i).sets.get(0).games.get(0).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(0).games.get(0).getAwayScore()));
                        set12.setText(Integer.toString(matchesArray.get(i).sets.get(0).games.get(1).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(0).games.get(1).getAwayScore()));
                        set13.setText(Integer.toString(matchesArray.get(i).sets.get(0).games.get(2).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(0).games.get(2).getAwayScore()));
                        
                        set21.setText(Integer.toString(matchesArray.get(i).sets.get(1).games.get(0).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(1).games.get(0).getAwayScore()));
                        set22.setText(Integer.toString(matchesArray.get(i).sets.get(1).games.get(1).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(1).games.get(1).getAwayScore()));
                        set23.setText(Integer.toString(matchesArray.get(i).sets.get(1).games.get(2).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(1).games.get(2).getAwayScore()));
                        
                        set31.setText(Integer.toString(matchesArray.get(i).sets.get(2).games.get(0).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(2).games.get(0).getAwayScore()));
                        set32.setText(Integer.toString(matchesArray.get(i).sets.get(2).games.get(1).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(2).games.get(1).getAwayScore()));
                        set33.setText(Integer.toString(matchesArray.get(i).sets.get(2).games.get(2).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(2).games.get(2).getAwayScore()));
                        
                        set41.setText(Integer.toString(matchesArray.get(i).sets.get(3).games.get(0).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(3).games.get(0).getAwayScore()));
                        set42.setText(Integer.toString(matchesArray.get(i).sets.get(3).games.get(1).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(3).games.get(1).getAwayScore()));
                        set43.setText(Integer.toString(matchesArray.get(i).sets.get(3).games.get(2).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(3).games.get(2).getAwayScore()));
                        
                        setd1.setText(Integer.toString(matchesArray.get(i).sets.get(4).games.get(0).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(4).games.get(0).getAwayScore()));
                        setd2.setText(Integer.toString(matchesArray.get(i).sets.get(4).games.get(1).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(4).games.get(1).getAwayScore()));
                        setd3.setText(Integer.toString(matchesArray.get(i).sets.get(4).games.get(2).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).sets.get(4).games.get(2).getAwayScore()));
                   
                        // Calculate the winner of the matches
                        matchesArray.get(i).calculateWinner();
                        
                        // Set the final scores text field
                        finalTeamScores.setText(Integer.toString(matchesArray.get(i).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).getAwayScore()));
                    }
                }
            }
        });
        
        // Create a handler for the calculate and submit button
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--CALCULATING AND SUBMITTING--");

                // If the scoresheet being submitted is a previous one being modified...
                if(modify){
                    modify = false;
                    // find the right match..
                    for(int i = 0; i < matchesArray.size(); i++){
                        if(matchID == matchesArray.get(i).getMatchID()){

                            // Set all of the home and away scores for each game
                            String game11 = set11.getText();
                            String[] scores11 = game11.split(":");
                            int game11HomeScore = Integer.parseInt(scores11[0]);
                            int game11AwayScore = Integer.parseInt(scores11[1]);
                            matchesArray.get(i).sets.get(0).games.get(0).setHomeScore(game11HomeScore);
                            matchesArray.get(i).sets.get(0).games.get(0).setAwayScore(game11AwayScore);
                            
                            String game12 = set12.getText();
                            String[] scores12 = game12.split(":");
                            int game12HomeScore = Integer.parseInt(scores12[0]);
                            int game12AwayScore = Integer.parseInt(scores12[1]);
                            matchesArray.get(i).sets.get(0).games.get(1).setHomeScore(game12HomeScore);
                            matchesArray.get(i).sets.get(0).games.get(1).setAwayScore(game12AwayScore);
                            
                            String game13 = set13.getText();
                            String[] scores13 = game13.split(":");
                            int game13HomeScore = Integer.parseInt(scores13[0]);
                            int game13AwayScore = Integer.parseInt(scores13[1]);
                            matchesArray.get(i).sets.get(0).games.get(2).setHomeScore(game13HomeScore);
                            matchesArray.get(i).sets.get(0).games.get(2).setAwayScore(game13AwayScore);
                            
                            String game21 = set21.getText();
                            String[] scores21 = game21.split(":");
                            int game21HomeScore = Integer.parseInt(scores21[0]);
                            int game21AwayScore = Integer.parseInt(scores21[1]);
                            matchesArray.get(i).sets.get(1).games.get(0).setHomeScore(game21HomeScore);
                            matchesArray.get(i).sets.get(1).games.get(0).setAwayScore(game21AwayScore);
                            
                            String game22 = set22.getText();
                            String[] scores22 = game22.split(":");
                            int game22HomeScore = Integer.parseInt(scores22[0]);
                            int game22AwayScore = Integer.parseInt(scores22[1]);
                            matchesArray.get(i).sets.get(1).games.get(1).setHomeScore(game22HomeScore);
                            matchesArray.get(i).sets.get(1).games.get(1).setAwayScore(game22AwayScore);
                            
                            String game23 = set23.getText();
                            String[] scores23 = game23.split(":");
                            int game23HomeScore = Integer.parseInt(scores23[0]);
                            int game23AwayScore = Integer.parseInt(scores23[1]);
                            matchesArray.get(i).sets.get(1).games.get(2).setHomeScore(game23HomeScore);
                            matchesArray.get(i).sets.get(1).games.get(2).setAwayScore(game23AwayScore);
                            
                            String game31 = set31.getText();
                            String[] scores31 = game31.split(":");
                            int game31HomeScore = Integer.parseInt(scores31[0]);
                            int game31AwayScore = Integer.parseInt(scores31[1]);
                            matchesArray.get(i).sets.get(2).games.get(0).setHomeScore(game31HomeScore);
                            matchesArray.get(i).sets.get(2).games.get(0).setAwayScore(game31AwayScore);
                            
                            String game32 = set32.getText();
                            String[] scores32 = game32.split(":");
                            int game32HomeScore = Integer.parseInt(scores32[0]);
                            int game32AwayScore = Integer.parseInt(scores32[1]);
                            matchesArray.get(i).sets.get(2).games.get(1).setHomeScore(game32HomeScore);
                            matchesArray.get(i).sets.get(2).games.get(1).setAwayScore(game32AwayScore);
                            
                            String game33 = set33.getText();
                            String[] scores33 = game33.split(":");
                            int game33HomeScore = Integer.parseInt(scores33[0]);
                            int game33AwayScore = Integer.parseInt(scores33[1]);
                            matchesArray.get(i).sets.get(2).games.get(2).setHomeScore(game33HomeScore);
                            matchesArray.get(i).sets.get(2).games.get(2).setAwayScore(game33AwayScore);
                            
                            String game41 = set41.getText();
                            String[] scores41 = game41.split(":");
                            int game41HomeScore = Integer.parseInt(scores41[0]);
                            int game41AwayScore = Integer.parseInt(scores41[1]);
                            matchesArray.get(i).sets.get(3).games.get(0).setHomeScore(game41HomeScore);
                            matchesArray.get(i).sets.get(3).games.get(0).setAwayScore(game41AwayScore);
                            
                            String game42 = set42.getText();
                            String[] scores42 = game42.split(":");
                            int game42HomeScore = Integer.parseInt(scores42[0]);
                            int game42AwayScore = Integer.parseInt(scores42[1]);
                            matchesArray.get(i).sets.get(3).games.get(1).setHomeScore(game42HomeScore);
                            matchesArray.get(i).sets.get(3).games.get(1).setAwayScore(game42AwayScore);
                            
                            String game43 = set43.getText();
                            String[] scores43 = game43.split(":");
                            int game43HomeScore = Integer.parseInt(scores43[0]);
                            int game43AwayScore = Integer.parseInt(scores43[1]);
                            matchesArray.get(i).sets.get(3).games.get(2).setHomeScore(game43HomeScore);
                            matchesArray.get(i).sets.get(3).games.get(2).setAwayScore(game43AwayScore);
                            
                            String gamed1 = setd1.getText();
                            String[] scoresd1 = gamed1.split(":");
                            int gamed1HomeScore = Integer.parseInt(scoresd1[0]);
                            int gamed1AwayScore = Integer.parseInt(scoresd1[1]);
                            matchesArray.get(i).sets.get(4).games.get(0).setHomeScore(gamed1HomeScore);
                            matchesArray.get(i).sets.get(4).games.get(0).setAwayScore(gamed1AwayScore);
                            
                            String gamed2 = setd2.getText();
                            String[] scoresd2 = gamed2.split(":");
                            int gamed2HomeScore = Integer.parseInt(scoresd2[0]);
                            int gamed2AwayScore = Integer.parseInt(scoresd2[1]);
                            matchesArray.get(i).sets.get(4).games.get(1).setHomeScore(gamed2HomeScore);
                            matchesArray.get(i).sets.get(4).games.get(1).setAwayScore(gamed2AwayScore);
                            
                            String gamed3 = setd3.getText();
                            String[] scoresd3 = gamed3.split(":");
                            int gamed3HomeScore = Integer.parseInt(scoresd3[0]);
                            int gamed3AwayScore = Integer.parseInt(scoresd3[1]);
                            matchesArray.get(i).sets.get(4).games.get(2).setHomeScore(gamed3HomeScore);
                            matchesArray.get(i).sets.get(4).games.get(2).setAwayScore(gamed3AwayScore);

                            // Calculate the set winners
                            for(int s = 0; s < 5; s++){
                                matchesArray.get(i).sets.get(s).calculateWinner();
                            }

                            // calculate the mactch winner
                            matchesArray.get(i).calculateWinner();

                            //display the final scores and generate fixtures for those scores
                            finalTeamScores.setText(Integer.toString(matchesArray.get(i).getHomeScore()) + ":" + Integer.toString(matchesArray.get(i).getAwayScore()));
                            GenerateFixtures.fixtures_generation();
                        }
                    }
                }

                // if the socresheet being submitted is not a previous one being modified (e.g. new scoresheet)
                else if(!modify){
                    // Create a new match and set it to the next matchID
                    Match match = new Match();
                    match.setMatchID(matchID);
                    List<Set> sets = new ArrayList<>();

                    // set the games and sets to be what the user has inputted
                    String game11 = set11.getText();
                    String[] scores11 = game11.split(":");
                    int game11HomeScore = Integer.parseInt(scores11[0]);
                    int game11AwayScore = Integer.parseInt(scores11[1]);
                    Game newGame11 = new Game();
                    newGame11.setHomeScore(game11HomeScore);
                    newGame11.setAwayScore(game11AwayScore);
                    
                    String game12 = set12.getText();
                    String[] scores12 = game12.split(":");
                    int game12HomeScore = Integer.parseInt(scores12[0]);
                    int game12AwayScore = Integer.parseInt(scores12[1]);
                    Game newGame12 = new Game();
                    newGame12.setHomeScore(game12HomeScore);
                    newGame12.setAwayScore(game12AwayScore);
                    
                    String game13 = set13.getText();
                    String[] scores13 = game13.split(":");
                    int game13HomeScore = Integer.parseInt(scores13[0]);
                    int game13AwayScore = Integer.parseInt(scores13[1]);
                    Game newGame13 = new Game();
                    newGame13.setHomeScore(game13HomeScore);
                    newGame13.setAwayScore(game13AwayScore);
                    
                    String game21 = set21.getText();
                    String[] scores21 = game21.split(":");
                    int game21HomeScore = Integer.parseInt(scores21[0]);
                    int game21AwayScore = Integer.parseInt(scores21[1]);
                    Game newGame21 = new Game();
                    newGame21.setHomeScore(game21HomeScore);
                    newGame21.setAwayScore(game21AwayScore);
                    
                    String game22 = set22.getText();
                    String[] scores22 = game22.split(":");
                    int game22HomeScore = Integer.parseInt(scores22[0]);
                    int game22AwayScore = Integer.parseInt(scores22[1]);
                    Game newGame22 = new Game();
                    newGame22.setHomeScore(game22HomeScore);
                    newGame22.setAwayScore(game22AwayScore);
                    
                    String game23 = set23.getText();
                    String[] scores23 = game23.split(":");
                    int game23HomeScore = Integer.parseInt(scores23[0]);
                    int game23AwayScore = Integer.parseInt(scores23[1]);
                    Game newGame23 = new Game();
                    newGame23.setHomeScore(game23HomeScore);
                    newGame23.setAwayScore(game23AwayScore);
                    
                    String game31 = set31.getText();
                    String[] scores31 = game31.split(":");
                    int game31HomeScore = Integer.parseInt(scores31[0]);
                    int game31AwayScore = Integer.parseInt(scores31[1]);
                    Game newGame31 = new Game();
                    newGame31.setHomeScore(game31HomeScore);
                    newGame31.setAwayScore(game31AwayScore);
                    
                    String game32 = set32.getText();
                    String[] scores32 = game32.split(":");
                    int game32HomeScore = Integer.parseInt(scores32[0]);
                    int game32AwayScore = Integer.parseInt(scores32[1]);
                    Game newGame32 = new Game();
                    newGame32.setHomeScore(game32HomeScore);
                    newGame32.setAwayScore(game32AwayScore);
                    
                    String game33 = set33.getText();
                    String[] scores33 = game33.split(":");
                    int game33HomeScore = Integer.parseInt(scores33[0]);
                    int game33AwayScore = Integer.parseInt(scores33[1]);
                    Game newGame33 = new Game();
                    newGame33.setHomeScore(game33HomeScore);
                    newGame33.setAwayScore(game33AwayScore);
                    
                    String game41 = set41.getText();
                    String[] scores41 = game41.split(":");
                    int game41HomeScore = Integer.parseInt(scores41[0]);
                    int game41AwayScore = Integer.parseInt(scores41[1]);
                    Game newGame41 = new Game();
                    newGame41.setHomeScore(game41HomeScore);
                    newGame41.setAwayScore(game41AwayScore);
                    
                    String game42 = set42.getText();
                    String[] scores42 = game42.split(":");
                    int game42HomeScore = Integer.parseInt(scores42[0]);
                    int game42AwayScore = Integer.parseInt(scores42[1]);
                    Game newGame42 = new Game();
                    newGame42.setHomeScore(game42HomeScore);
                    newGame42.setAwayScore(game42AwayScore);
                    
                    String game43 = set43.getText();
                    String[] scores43 = game43.split(":");
                    int game43HomeScore = Integer.parseInt(scores43[0]);
                    int game43AwayScore = Integer.parseInt(scores43[1]);
                    Game newGame43 = new Game();
                    newGame43.setHomeScore(game43HomeScore);
                    newGame43.setAwayScore(game43AwayScore);
                    
                    String gamed1 = setd1.getText();
                    String[] scoresd1 = gamed1.split(":");
                    int gamed1HomeScore = Integer.parseInt(scoresd1[0]);
                    int gamed1AwayScore = Integer.parseInt(scoresd1[1]);
                    Game newGamed1 = new Game();
                    newGamed1.setHomeScore(gamed1HomeScore);
                    newGamed1.setAwayScore(gamed1AwayScore);
                    
                    String gamed2 = setd2.getText();
                    String[] scoresd2 = gamed2.split(":");
                    int gamed2HomeScore = Integer.parseInt(scoresd2[0]);
                    int gamed2AwayScore = Integer.parseInt(scoresd2[1]);
                    Game newGamed2 = new Game();
                    newGamed2.setHomeScore(gamed2HomeScore);
                    newGamed2.setAwayScore(gamed2AwayScore);
                    
                    String gamed3 = setd3.getText();
                    String[] scoresd3 = gamed3.split(":");
                    int gamed3HomeScore = Integer.parseInt(scoresd3[0]);
                    int gamed3AwayScore = Integer.parseInt(scoresd3[1]);
                    Game newGamed3 = new Game();
                    newGamed3.setHomeScore(gamed3HomeScore);
                    newGamed3.setAwayScore(gamed3AwayScore);

                    // add the games to the set and set the home and away team for each set
                    Set set1 = new Set();
                    List<Game> games1 = new ArrayList<>();
                    games1.add(newGame11);
                    games1.add(newGame12);
                    games1.add(newGame13);
                    set1.setGames(games1);
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(homeTeam.getValue())){
                            set1.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(awayTeam.getValue())){
                            set1.setAwayTeam(teamsArray.get(i));
                        }
                    }
                    
                    sets.add(set1);
                    
                    Set set2 = new Set();
                    List<Game> games2 = new ArrayList<>();
                    games2.add(newGame21);
                    games2.add(newGame22);
                    games2.add(newGame23);
                    set2.setGames(games2);
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(homeTeam.getValue())){
                            set2.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(awayTeam.getValue())){
                            set2.setAwayTeam(teamsArray.get(i));
                        }
                    }
                    
                    sets.add(set2);
                    
                    Set set3 = new Set();
                    List<Game> games3 = new ArrayList<>();
                    games3.add(newGame31);
                    games3.add(newGame32);
                    games3.add(newGame33);
                    set3.setGames(games3);
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(homeTeam.getValue())){
                            set3.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(awayTeam.getValue())){
                            set3.setAwayTeam(teamsArray.get(i));
                        }
                    }
                    
                    sets.add(set3);
                    
                    Set set4 = new Set();
                    List<Game> games4 = new ArrayList<>();
                    games4.add(newGame41);
                    games4.add(newGame42);
                    games4.add(newGame43);
                    set4.setGames(games4);
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(homeTeam.getValue())){
                            set4.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(awayTeam.getValue())){
                            set4.setAwayTeam(teamsArray.get(i));
                        }
                    }
                    
                    sets.add(set4);
                    
                    Set setd = new Set();
                    List<Game> gamesd = new ArrayList<>();
                    gamesd.add(newGamed1);
                    gamesd.add(newGamed2);
                    gamesd.add(newGamed3);
                    setd.setGames(gamesd);
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(homeTeam.getValue())){
                            setd.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(awayTeam.getValue())){
                            setd.setAwayTeam(teamsArray.get(i));
                        }
                    }
                    
                    sets.add(setd);

                    // add the sets to the match
                    match.setSets(sets);


                    // set the match home and away teams
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(homeTeam.getValue())){
                            match.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    for(int i = 0; i < teamsArray.size(); i++){
                        if(teamsArray.get(i).getName().equals(awayTeam.getValue())){
                            match.setAwayTeam(teamsArray.get(i));
                        }
                    }

                    // create the players and set them to the correct players for the match
                    Player matchHomePlayer1 = new Player("","");
                    Player matchHomePlayer2 = new Player("","");
                    Player matchAwayPlayer1 = new Player("","");
                    Player matchAwayPlayer2 = new Player("","");
                    
                    for(int i = 0; i < match.getHomeTeam().players.size(); i++){
                        if(match.getHomeTeam().players.get(i).getFullName().equals(homePlayer1.getValue())){
                            matchHomePlayer1 = match.getHomeTeam().players.get(i);
                        }
                    }
                    
                    match.setHomePlayer1(matchHomePlayer1);
                    
                    for(int i = 0; i < match.getHomeTeam().players.size(); i++){
                        if(match.getHomeTeam().players.get(i).getFullName().equals(homePlayer2.getValue())){
                            matchHomePlayer2 = match.getHomeTeam().players.get(i);
                        }
                    }
                    
                    match.setHomePlayer2(matchHomePlayer2);
                    
                    for(int i = 0; i < match.getAwayTeam().players.size(); i++){
                        if(match.getAwayTeam().players.get(i).getFullName().equals(awayPlayer1.getValue())){
                            matchAwayPlayer1 = match.getAwayTeam().players.get(i);
                        }
                    }
                    
                    match.setAwayPlayer1(matchAwayPlayer1);
                    
                    for(int i = 0; i < match.getAwayTeam().players.size(); i++){
                        if(match.getAwayTeam().players.get(i).getFullName().equals(awayPlayer2.getValue())){
                            matchAwayPlayer2 = match.getAwayTeam().players.get(i);
                        }
                    }
                    
                    match.setAwayPlayer2(matchAwayPlayer2);

                    // calculate the winner of each set
                    for(int s = 0; s < 5; s++){
                        match.sets.get(s).calculateWinner();
                    }

                    // calculate the match winner
                    match.calculateWinner();
                    // Display the final scores
                    finalTeamScores.setText(Integer.toString(match.getHomeScore()) + ":" + Integer.toString(match.getAwayScore()));

                    // add the match to the matches array and generate fixtures with the new match
                    matchesArray.add(match);
                    GenerateFixtures.fixtures_generation();
                }
            }
        });
        
        // Add the nodes
        set1.add(set11, 0, 0);
        set1.add(set12, 0, 1);
        set1.add(set13, 0, 2);
        set2.add(set21, 0, 0);
        set2.add(set22, 0, 1);
        set2.add(set23, 0, 2);
        set3.add(set31, 0, 0);
        set3.add(set32, 0, 1);
        set3.add(set33, 0, 2);
        set4.add(set41, 0, 0);
        set4.add(set42, 0, 1);
        set4.add(set43, 0, 2);
        doubleSet.add(setd1, 0, 0);
        doubleSet.add(setd2, 0, 1);
        doubleSet.add(setd3, 0, 2);
        
        
        //add the nodes to the gridpane
        scoresheet.add(newSheet, 0, 0);
        scoresheet.add(modifySheet, 1, 0);
        sides.add(homeTeamLabel, 0, 0);
        sides.add(homeTeam, 1, 0);
        sides.add(awayTeamLabel, 2, 0);
        sides.add(awayTeam, 3, 0);
        grid.add(singleSets, 0, 0);
        grid.add(awayPlayer1, 1, 0);
        grid.add(awayPlayer2, 2, 0);
        grid.add(homePlayer1, 0, 1);
        grid.add(homePlayer2, 0, 2);
        grid.add(doubleSetLabel, 0, 3);
        grid.add(finalTeamScores, 2, 3);
        grid.add(set1, 1, 1);
        grid.add(set2, 2, 1);
        grid.add(set3, 1, 2);
        grid.add(set4, 2, 2);
        grid.add(doubleSet, 1, 3);
        scoreroot.add(calculate, 0, 3);
        
        
        //add panes to the root
        scoreroot.add(scoresheet, 0, 0);
        scoreroot.add(sides, 0, 1);
        scoreroot.add(grid, 0, 2);
        
        //set the scoresheet tabs content
        scoresheets.setContent(scoreroot);
        
        //add the 4 tabs to the tab pane
        tabs.getTabs().add(viewerPage);
        tabs.getTabs().add(scoresheets);
        tabs.getTabs().add(adminPage);
        
        //set the tabs to be down the left hand side
        tabs.setSide(Side.LEFT);
        
        //set the scene with the window dimensions
        Scene scene = new Scene(tabs, 650, 500);
        
        //set the title and show
        primaryStage.setTitle("TheB.R.I.A.N. system");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        // Create an instance of the statistics class
        Statistics stats = new Statistics();
        // Create a new thread of the statistics class
        Thread statsThread = new Thread(stats);
        // Set the thread to close on program shutdown
        statsThread.setDaemon(true);
        // Start the statistics thread
        statsThread.start();
        // Launch the program
        launch(args);
    }
    
}
