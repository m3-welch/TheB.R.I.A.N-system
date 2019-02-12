/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theb.r.i.a.n.system;


//import all the necessary modules
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert.AlertType;


// Create a class to represent the game data struture. Has the appropriate get 
// and set methods
class Game{
    int homeScore;
    int awayScore;
    Team winner;
    
    public int getHomeScore(){
        return homeScore;
    }
    
    public void setHomeScore(int score){
        homeScore = score;
    }
    
    public int getAwayScore(){
        return awayScore;
    }
    
    public void setAwayScore(int score){
        awayScore = score;
    }
    
    public Team getWinner(){
        return winner;
    }
    
    public void setWinner(Team team){
        winner = team;
    }
    
    @Override
    public String toString(){
        return homeScore + ":" + awayScore;
    }
    
}


// Create a class to represent the set data structure. Has the appropriate get
// and set methods
class Set{
    Team homeTeam;
    Team awayTeam;
    List<Game> games = new ArrayList<>();
    Team winner;
    
    public Team getHomeTeam(){
        return homeTeam;
    }
    
    public void setHomeTeam(Team team){
        homeTeam = team;
    }
    
    public Team getAwayTeam(){
        return awayTeam;
    }
    
    public void setAwayTeam(Team team){
        awayTeam = team;
    }
    
    public List<Game> getGames(){
        return games;
    }
    
    public void setGames(List<Game> games){
        this.games = games;
    }
 
    // Calculate the winner by finding out how many games the home team won, and
    // performing calculations on that number.
    public void calculateWinner(){
        int home = 0;
        for(int i = 0; i < 3; i++){
            if(games.get(i).homeScore > games.get(i).awayScore){
                home++;
                games.get(i).setWinner(homeTeam);
            }
            else{
                games.get(i).setWinner(awayTeam);
            }
        }
        if(home >= 2){
            winner = homeTeam;
        }
        else{
            winner = awayTeam;
        }
    }
    
    public Team getWinner(){
        return winner;
    }
}


// Create a class to represent the match data structure. Has the appropriate get
// and set methods.
class Match{
    int matchID;
    Team homeTeam;
    Team awayTeam;
    Player homePlayer1;
    Player homePlayer2;
    Player awayPlayer1;
    Player awayPlayer2;
    List<Set> sets = new ArrayList<>();
    int homeScore;
    int awayScore;
    Team winner;
    String fileName;
    
    public Team getHomeTeam(){
        return homeTeam;
    }
    
    public void setHomeTeam(Team team){
        homeTeam = team;
    }
    
    public Team getAwayTeam(){
        return awayTeam;
    }
    
    public void setAwayTeam(Team team){
        awayTeam = team;
    }
    
    public Player getHomePlayer1(){
        return homePlayer1;
    }
    
    public Player getHomePlayer2(){
        return homePlayer2;
    }
    
    public Player getAwayPlayer1(){
        return awayPlayer1;
    }
        
    public Player getAwayPlayer2(){
        return awayPlayer2;
    }
    
    public void setHomePlayer1(Player player){
        homePlayer1 = player;
    }
    
    public void setHomePlayer2(Player player){
        homePlayer2 = player;
    }
    
    public void setAwayPlayer1(Player player){
        awayPlayer1 = player;
    }
    
    public void setAwayPlayer2(Player player){
        awayPlayer2 = player;
    }
    
    public List<Set> getSets(){
        return sets;
    }
    
    public void setSets(List<Set> sets){
        this.sets = sets;
    }
    
    // Calculate winner by working out how many sets the home team has one and
    // performing the calculations on that number
    public void calculateWinner(){
        int home = 0;
        for(int i = 0; i < 5; i++){
            if(sets.get(i).winner == homeTeam){
                home++;
            }
        }
        if(home >= 3){
            homeScore = home;
            awayScore = 5 - home;
            winner = homeTeam;
        }
        else{
            awayScore = 5 - home;
            homeScore = home;
            winner = awayTeam;
        }
    }
    
    public Team getWinner(){
        return winner;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public int getHomeScore(){
        return homeScore;
    }
    
    public int getAwayScore(){
        return awayScore;
    }
    
    public int getMatchID(){
        return matchID;
    }
    
    public void setMatchID(int matchID){
        this.matchID = matchID;
    }
}


// Create a class to represent the player data structure. Has the appropriate 
// get and set methods.
class Player{
    String firstName;
    String lastName;
    
    public Player(String first, String last){
        firstName = first;
        lastName = last;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String name){
        firstName = name;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String name){
        lastName = name;
    }
    
    public String getFullName(){
        return firstName + " " + lastName;
    }
}

// Create a class to represent the team data structure. Has the appropriate get
// and set methods.
class Team{
    String name;
    List<Player> players = new ArrayList<>();
    int matchesPlayed;
    int matchesWon;
    int setsWon;
    
    public Team(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getMatchesPlayed(){
        return matchesPlayed;
    }
    
    public void setMatchesPlayed(int matches){
        matchesPlayed = matches;
    }
    
    public void addMatchPlayed(){
        matchesPlayed++;
    }
    
    public int getMatchesWon(){
        return matchesWon;
    }
    
    public void setMatchesWon(int wins){
        matchesWon = wins;
    }
    
    public void addMatchWin(){
        matchesWon++;
    }
    
    public int getSetsWon(){
        return setsWon;
    }
    
    public void setSetsWon(int sets){
        setsWon = sets;
    }
    
    public void addSetWin(){
        setsWon++;
    }
    
    public List<Player> getPlayers(){
        return players;
    }
    
    public void setPlayers(ArrayList players){
        this.players = players;
    }
    
    public void addPlayer(Player player){
        players.add(player);
    }
    
    public void removePlayer(Player player){
        players.remove(player);
    }
}

// A class to handle selecting two teams
class TeamSelector{
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
        //Home team combo box
        ComboBox comboHomeTeam = new ComboBox();
        comboHomeTeam.setMinWidth(150);
        comboHomeTeam.getItems().addAll(teamNames);
        
        //Away team combo box
        ComboBox comboAwayTeam = new ComboBox();
        comboAwayTeam.setMinWidth(150);
        comboAwayTeam.getItems().addAll(teamNames);
        
        //Make the grid pane and add the features in place
        GridPane teams = new GridPane();
        teams.add(new Label("Select the home and away team:"), 0, 0);
        teams.add(new Label("Home Team:"), 0, 1);
        teams.add(comboHomeTeam, 1, 1);
        teams.add(new Label("Away Team:"), 0, 2);
        teams.add(comboAwayTeam, 1, 2);
        teams.add(submit, 0, 3);
        
        // Set the scene and display
        Scene teamScene = new Scene(teams, 300, 200);
        popupwindow.setScene(teamScene);
        popupwindow.showAndWait();
        
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //DO STUFF
            }
        });
    }
}

// Create a class that can handle the login for the admin page.
class Login{
    static private void submitAndClose(Stage window, String password){
        // If the password is correct, set the authorisation to true and close
        // the login window
        if(password.equals("admin")){
            TheBRIANSystem.auth = true;
            window.close();
        }
        else{
            // If the password is not correct, alert the user the password is 
            // wrong and allow them to re-enter the password
            Alert alert = new Alert(AlertType.WARNING);
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

// Create a class to be a second thread in the program that will update the 
// statistics every 100 seconds
class Statistics implements Runnable {
    // Create a function to update the statistics
    static public void updateStats(){
        System.out.println("UPDATE STATS");
    }
    
    @Override
    public void run(){
        // Set the start time as the current time
        long start = (System.currentTimeMillis() / 1000);
        long current = start;
        long elapsed = current;
        boolean reset = false;

        //while the thread is running, do this loop
        while(true){
            // Set the current time to the current time
            current = (System.currentTimeMillis() / 1000);
            // Set the elapsed time as the current time minus the start time
            elapsed = current - start;
            
            // If the reset flag is true (e.g. after 100 seconds have elapsed)
            if (reset){
                //set the start time to the current time and set the reset flag
                // to false
                start = (System.currentTimeMillis() / 1000);
                reset = false;
            }
            // If 100 seconds have elapsed
            if(elapsed == 100){
                // Update the statistics and set the reset flag to true
                updateStats();
                reset = true;
            }
        }
    }
    
}

// Create the main application class
public class TheBRIANSystem extends Application {
    
    // Create the arrays that will hold the teams and matches
    public static List<Match> matchesArray = new ArrayList<>();
    public static List<Team> teamsArray = new ArrayList<>();
    
    // Create a function that will handle the initialisation of files
    public void initialSetup(){
        // Create the array of the list of files in the matches folder
        File matchesPath = new File("./matches");
        File [] matchFiles = matchesPath.listFiles();
        for(int i = 0; i<matchFiles.length; i++){
            if(!matchFiles[i].isFile()){
                matchFiles[i].delete();
            }
        }
        
        // Create the array of the list of files in the teams folder
        File teamsPath = new File("./teams");
        File [] teamFiles = teamsPath.listFiles();
        for(int i = 0;i<teamFiles.length;i++){
            if(!teamFiles[i].isFile()){
                teamFiles[i].delete();
            }
        }
        
        try{
            // For each team in the teams folder
            for(int f = 0;f<teamFiles.length;f++){
                // Create a new scanner for the file
                Scanner teamScanner = new Scanner(teamFiles[f]);
                // While the file has more information, do this
                while(teamScanner.hasNextLine()){
                    // Create a new team with the name of the team
                    Team team = new Team(teamScanner.next());
                    // Make an array of players from the list of players in the
                    // file
                    String players = teamScanner.next();
                    String[] playersArray = players.split(",");
                    for(int i = 0;i < playersArray.length;i++){
                        Player player = new Player(playersArray[i], playersArray[i+1]);
                        i++;
                        team.addPlayer(player);
                    }   
                    
                    // Set the team's matches played, matches won and sets won
                    team.matchesPlayed = Integer.parseInt(teamScanner.next());
                    team.matchesWon = Integer.parseInt(teamScanner.next());
                    team.setsWon = Integer.parseInt(teamScanner.next());
                    // Add the team to the team array
                    teamsArray.add(team);
                }
                System.out.println("Loaded initial team data");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Team file not found");
        }
        
        try{
            // For each match in the matches folder
            for(int f = 0; f<matchFiles.length;f++){
                // Create a new scanner for that file
                Scanner matchScanner = new Scanner(matchFiles[f]);
                // While the file has another line, do this
                while(matchScanner.hasNextLine()){
                    // Create a new match
                    Match match = new Match();
                    // Set the filename of the match to the filename
                    match.setFileName(matchFiles[f].getName());
                    
                    // Set the match's home team
                    String homeTeam = matchScanner.next();
                    for(int i=0;i<teamsArray.size();i++){
                        if(teamsArray.get(i).name.equals(homeTeam)){
                            match.setHomeTeam(teamsArray.get(i));
                        }
                    }
                    
                    // Set the match's away team
                    String awayTeam = matchScanner.next();
                    for(int i=0;i<teamsArray.size();i++){
                        if(teamsArray.get(i).name.equals(awayTeam)){
                            match.setAwayTeam(teamsArray.get(i));
                        }
                    }

                    // Add the homePlayer1 to the match
                    String homePlayer1FullName = matchScanner.next();
                    homePlayer1FullName = homePlayer1FullName.replace(",", " ");
                    for(int i = 0;i<match.homeTeam.players.size();i++){
                        if(match.homeTeam.players.get(i).getFullName().equals(homePlayer1FullName)){
                            match.setHomePlayer1(match.homeTeam.players.get(i));                    }
                    }

                    // Add the homePlayer2 to the match
                    String homePlayer2FullName = matchScanner.next();
                    homePlayer2FullName = homePlayer2FullName.replace(",", " ");
                    for(int i = 0;i<match.homeTeam.players.size();i++){
                        if(match.homeTeam.players.get(i).getFullName().equals(homePlayer2FullName)){
                            match.setHomePlayer2(match.homeTeam.players.get(i));                    }
                    }

                    // Add the awayPlayer1 to the match
                    String awayPlayer1FullName = matchScanner.next();
                    awayPlayer1FullName = awayPlayer1FullName.replace(",", " ");
                    for(int i = 0;i<match.awayTeam.players.size();i++){
                        if(match.awayTeam.players.get(i).getFullName().equals(awayPlayer1FullName)){
                            match.setAwayPlayer1(match.awayTeam.players.get(i));                    }
                    }

                    // Add the awayPlayer2 to the match
                    String awayPlayer2FullName = matchScanner.next();
                    awayPlayer2FullName = awayPlayer2FullName.replace(",", " ");
                    for(int i = 0;i<match.awayTeam.players.size();i++){
                        if(match.awayTeam.players.get(i).getFullName().equals(awayPlayer2FullName)){
                            match.setAwayPlayer2(match.awayTeam.players.get(i));                    }
                    }
                    
                    // Create the list of sets to add the match
                    List<Set> sets = new ArrayList<Set>();
                    for(int i = 0;i<5;i++){
                        Set set = new Set();
                        set.homeTeam = match.homeTeam;
                        set.awayTeam = match.awayTeam;
                        String scores[] = matchScanner.next().split(",");
                        List<Game> games = new ArrayList<Game>();
                        for(int c = 0; c < 6;c++){
                           Game game = new Game();
                           game.setHomeScore(Integer.parseInt(scores[c]));
                           game.setAwayScore(Integer.parseInt(scores[c + 1]));
                           games.add(game);
                           c++;
                        }
                        set.setGames(games);
                        sets.add(set);
                    }
                    for(int i = 0;i<sets.size();i++){
                        sets.get(i).calculateWinner();
                    }
                    match.setSets(sets);
                    
                    // Add the match to the matches array
                    matchesArray.add(match);
                    
                    // Calculate the winner for each match
                    for(int i = 0; i<matchesArray.size();i++){
                        matchesArray.get(i).calculateWinner();
                    }

                }
                System.out.println("Loaded initial scoresheets");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Match files not found");
        }
    }
    
    // Set the auth flag to false
    static public boolean auth = false;
    
    @Override
    public void start(Stage primaryStage){
       
        // Run the initial setup function that loads in data
        initialSetup();
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
        admin.setVgap(4);
        admin.setHgap(4);
        admin.setPadding(new Insets(5, 5, 5, 5));
        
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
        newTeamGrid.setHgap(4);
        newTeamGrid.setPadding(new Insets(5, 5, 5, 5));
        newTeam.setText("Enter a new team");
        
        //create the button and textfield for the user inputs
        TextField newTeamName = new TextField("Enter a new team name");
        Button submitTeamName = new Button("Add Team");
        
        // Create a listener for the new team button
        submitTeamName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String teamName = newTeamName.getText();
                System.out.println(teamName + " has been added");
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
        newPlayer.setText("Enter a new player");
        
        //create a textfield, a dropdown for teams and a button for the new 
        //player
        TextField playerName = new TextField("John");
        Button addPlayer = new Button("Add Player");        
        ComboBox teamList = new ComboBox();
        ///////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////
        //////                 -TODO-                    //////
        ////// This needs to add team names using a loop //////
        //////                 -TODO-                    //////
        ///////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////
        
        // Create a handler for the add player button click
        addPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String newPlayerName = playerName.getText();
                System.out.println(newPlayerName + " has been added");
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
                System.out.println("--GENERATING FIXTURES--");
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
        viewer.setGridLinesVisible(true);
        GridPane buttons = new GridPane();
        buttons.setGridLinesVisible(true);
        GridPane text = new GridPane();
        text.setGridLinesVisible(true);
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        buttons.setVgap(150);
        buttons.setHgap(6);
        buttons.setPadding(new Insets(5, 5, 5, 5));
        text.setVgap(6);
        text.setHgap(6);
        text.setPadding(new Insets(5, 5, 5, 5));
        root.setVgap(6);
        root.setHgap(200);
        root.setPadding(new Insets(5, 5, 5, 5));
        
        //add the nodes to the viewer grid pane
        Button viewFixtures = new Button("View Fixture and Result Chart");
        Button teamStats = new Button("Show All Team Stats");
        Button teamRanks = new Button("Show All Team Rankings");
        Button viewScores = new Button("View A Match Scores");
        viewFixtures.setMinWidth(300);
        teamStats.setMinWidth(300);
        teamRanks.setMinWidth(300);
        viewScores.setMinWidth(300);
        
        // Set the preferences of the nodes
        viewFixtures.setPrefWidth(205);
        Label textArea = new Label();
        textArea.setPrefSize(400, 800);
        textArea.setWrapText(true);
        textArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        textArea.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        textArea.setAlignment(Pos.TOP_LEFT);
        
        // Create a handler for the view fixtures button
        viewFixtures.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("--VIEWING FIXTURES AND RESULT CHART--");
                System.out.println("--VIEW FIXTURES AND RESULTS--");
            }
        });
        
        // Create a handler for the team stats button
        teamStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("--VIEWING TEAM STATISTICS--");
                System.out.println("--VIEWING STATISTICS--");
            }
        });
        
        // Create a handler for the team ranks button
        teamRanks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("--VIEWING TEAM RANKINGS--");
                System.out.println("--VIEWING RANKINGS--");
            }
        });
        
        // Create a handler for the view scores button
        viewScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Create popup to select home and away team
                TeamSelector.display();
                
                textArea.setText("--VIEWING SCORES--");
                System.out.println("--VIEWING SCORES--");
            }
        });
        
        //add the nodes to the viewer grid pane
        buttons.add(viewFixtures, 0, 0);
        buttons.add(teamStats, 0, 1);
        buttons.add(teamRanks, 0, 2);
        buttons.add(viewScores, 0, 3);
        text.add(textArea, 0, 0);
        root.add(buttons, 0, 0);
        root.add(text, 1, 0);
        
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
        homeTeam.setMinWidth(150);
        ComboBox awayTeam = new ComboBox();
        awayTeam.setMinWidth(150);
        Label singleSets = new Label("Single Sets");
        Label doubleSetLabel = new Label("Double Set");
        ComboBox awayPlayer1 = new ComboBox();
        awayPlayer1.setMinWidth(150);
        ComboBox awayPlayer2 = new ComboBox();
        awayPlayer2.setMinWidth(150);
        ComboBox homePlayer1 = new ComboBox();
        homePlayer1.setMinWidth(100);
        ComboBox homePlayer2 = new ComboBox();
        homePlayer2.setMinWidth(100);
        TextField finalTeamScores = new TextField("Final Team Scores");
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
            }
        });
        
        // Create a handler for the modify sheet button
        modifySheet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--SELECTING A SCORESHEET TO MODIFY--");
                // Let the user choose a file from the matches folder
                FileChooser fileChooser = new FileChooser();
                File startFolder = new File("./matches");
                fileChooser.setInitialDirectory(startFolder);
                File selectedFile = fileChooser.showOpenDialog(null);
                
                // For each of the items in the matches array
                for(int i = 0;i<matchesArray.size();i++){
                    // If the filename matches the filename the user has chosen,
                    // do this
                    if(matchesArray.get(i).getFileName().equals(selectedFile.getName())){
                        // Set the home team combo box to the home team name
                        homeTeam.setValue(matchesArray.get(i).getHomeTeam().getName());
                        // Set the away team combo box to the away team name
                        awayTeam.setValue(matchesArray.get(i).getAwayTeam().getName());
                        // Set the homePlayer1 combo box to the homePlayer1
                        // first name
                        homePlayer1.setValue(matchesArray.get(i).getHomePlayer1().getFirstName());
                        // Set the homePlayer2 combo box to the homePlayer2
                        // first name
                        homePlayer2.setValue(matchesArray.get(i).getHomePlayer2().getFirstName());
                        // Set the awayPlayer1 combo box to the awayPlayer1
                        // first name
                        awayPlayer1.setValue(matchesArray.get(i).getAwayPlayer1().getFirstName());
                        // Set the awayPlayer2 combo box to the awayPlayer2
                        // first name
                        awayPlayer2.setValue(matchesArray.get(i).getAwayPlayer2().getFirstName());
                        
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
                System.out.println("--CALCULATING--");
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
        Scene scene = new Scene(tabs, 800, 600);
        
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
