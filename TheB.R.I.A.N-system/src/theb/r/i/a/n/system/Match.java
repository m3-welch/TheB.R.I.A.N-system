package theb.r.i.a.n.system;

import java.util.ArrayList;
import java.util.List;

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
        // Find out how many sets the homeTeam has won
        for(int i = 0; i < 5; i++){
            if(sets.get(i).winner == homeTeam){
                home++;
            }
        }

        // If the homeTeam has won 3 or more sets, then set the homeTeam as the winner and work out the overall scores
        // of the match, otherwise set the winner to the awayTeam and calculate the final scores
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
