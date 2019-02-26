/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theb.r.i.a.n.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static theb.r.i.a.n.system.TheBRIANSystem.matchesArray;
import static theb.r.i.a.n.system.TheBRIANSystem.teamsArray;

/**
 *
 * @author m3-welch
 */
public class InitialSetup {
    // Create a function that will handle the initialisation of files
    static public void initialSetup(){
        //initial generation of fixtures
        GenerateFixtures.fixtures_generation();
        
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
                    TheBRIANSystem.teamObservableList.add(team.getName());
                }
                System.out.println("Loaded initial team data");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Team file not found");
        }
        
        try{
            int matchID;
            // For each match in the matches folder
            for(int f = 0; f<matchFiles.length;f++){
                // Create a new scanner for that file
                Scanner matchScanner = new Scanner(matchFiles[f]);
                // While the file has another line, do this
                while(matchScanner.hasNextLine()){
                    // Create a new match
                    Match match = new Match();
                    // Set the matchID to the first line in the file
                    matchID = Integer.parseInt(matchScanner.next());
                    
                    match.setMatchID(matchID);
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
        Statistics.updateStats();
    }
}
