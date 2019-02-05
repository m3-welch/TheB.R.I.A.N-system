/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theb.r.i.a.n.system;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import java.util.*;
import java.lang.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Modality;

/**
 *
 * @author morga
 */

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
    
    public String toString(){
        return homeScore + ":" + awayScore;
    }
    
}

class Set{
    Team homeTeam;
    Team awayTeam;
    List<Game> games = new ArrayList<Game>();
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
    
    public void setGames(ArrayList games){
        this.games = games;
    }
 
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

class Match{
    Team homeTeam;
    Team awayTeam;
    Player homePlayer1;
    Player homePlayer2;
    Player awayPlayer1;
    Player awayPlayer2;
    List<Set> sets = new ArrayList<Set>();
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
    
    public void calculateWinner(){
        int home = 0;
        for(int i = 0; i < 5; i++){
            if(sets.get(i).winner == homeTeam){
                home++;
            }
        }
        if(home >= 3){
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
}

class Team{
    String name;
    List<Player> players = new ArrayList<Player>();
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

class Login{
    static private void submitAndClose(Stage window, String password){
        if(password.equals("admin")){
            TheBRIANSystem.auth = true;
            window.close();
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("INCORRECT PASSWORD!");
            alert.setHeaderText("Re-enter the password to try again");
            alert.showAndWait();
        }
    }
    
    public static void display(){
    Stage popupwindow = new Stage();
    popupwindow.initModality(Modality.APPLICATION_MODAL);
    popupwindow.setTitle("Admin Login");
    
    Label enter_password = new Label("Enter the admin password: ");
    PasswordField password = new PasswordField();
    Button submit = new Button("Submit");
    submit.setOnAction(e-> submitAndClose(popupwindow, password.getText()));
    
    GridPane login = new GridPane();
    login.setVgap(4);
    login.setHgap(4);
    login.setPadding(new Insets(5, 5, 5, 5));
    
    login.add(enter_password, 0, 0);
    login.add(password, 0, 1);
    login.add(submit, 1, 1);
    
    Scene loginscene = new Scene(login, 300, 200);
    popupwindow.setScene(loginscene);
    popupwindow.showAndWait();
    }
}

class Statistics implements Runnable {
    
    static public void updateStats(){
        System.out.println("UPDATE STATS");
    }
    
    @Override
    public void run(){
    long start = (System.currentTimeMillis() / 1000);
    long current = start;
    long elapsed = current;
    boolean reset = false;
    long elapsed_temp = 0;
    
    while(true){
        current = (System.currentTimeMillis() / 1000);
        elapsed = current - start;

        if (reset){
            start = (System.currentTimeMillis() / 1000);
            reset = false;
        }
        if(elapsed == 100){
            updateStats();
            reset = true;
        }
        
        if(elapsed != elapsed_temp){
            System.out.println(elapsed);
        }
        
        elapsed_temp = elapsed;
    }
}
    
}
public class TheBRIANSystem extends Application {
    static public boolean auth = false;
    
    @Override
    public void start(Stage primaryStage){
        
        //create the main tab pane that will be the basis of the whole UI
        TabPane tabs = new TabPane();
        
        //create the 3 tabs to be used with their appropriate titles
        Tab adminPage = new Tab("Admin");
        Tab viewerPage = new Tab("Viewer");
        Tab scoresheets = new Tab("Scoresheets");
        
        SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
        
        tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if(newTab == adminPage && !auth) {
                    Login.display();
                }
                if(!auth && (newTab == scoresheets || newTab == viewerPage)){
                    selectionModel.select(newTab);
                }
                if(!auth && newTab == adminPage){
                    selectionModel.select(viewerPage);
                }
            }
            });
        
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
        
        viewFixtures.setPrefWidth(205);
        Label textArea = new Label();
        textArea.setPrefSize(400, 800);
        textArea.setWrapText(true);
        textArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        textArea.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        textArea.setAlignment(Pos.TOP_LEFT);
        
        viewFixtures.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("--VIEWING FIXTURES AND RESULT CHART--");
                System.out.println("--VIEW FIXTURES AND RESULTS--");
            }
        });
        teamStats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("--VIEWING TEAM STATISTICS--");
                System.out.println("--VIEWING STATISTICS--");
            }
        });
        teamRanks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText("--VIEWING TEAM RANKINGS--");
                System.out.println("--VIEWING RANKINGS--");
            }
        });
        viewScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
        
        //create a grid pane for the scoresheet tab amd sey all the preferences
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
        set11.setEditable(false);
        set12.setEditable(false);
        set13.setEditable(false);
        set21.setEditable(false);
        set22.setEditable(false);
        set23.setEditable(false);
        set31.setEditable(false);
        set32.setEditable(false);
        set33.setEditable(false);
        set41.setEditable(false);
        set42.setEditable(false);
        set43.setEditable(false);
        setd1.setEditable(false);
        setd2.setEditable(false);
        setd3.setEditable(false);
        Button calculate = new Button("Calculate and Submit scores");
        calculate.setMinWidth(500);
        
        newSheet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--CREATING A NEW WORKSHEET--");
            }
        });
        modifySheet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--SELECTING A WORKSHEET TO MODIFY--");
            }
        });
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("--CALCULATING--");
            }
        });
        
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
        Statistics stats = new Statistics();
        Thread statsThread = new Thread(stats);
        statsThread.setDaemon(true);
        statsThread.start();
        launch(args);
    }
    
}
