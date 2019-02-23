package theb.r.i.a.n.system;


import java.util.ArrayList;
import java.util.List;

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

        // Calculate how many games the homeTeam has won and set the winner of each game to the appropriate team
        for(int i = 0; i < 3; i++){
            if(games.get(i).homeScore > games.get(i).awayScore){
                home++;
                games.get(i).setWinner(homeTeam);
            }
            else{
                games.get(i).setWinner(awayTeam);
            }
        }

        // If teh homeTeam has won 2 or more games (e.g. best of 3: winning the set), set them to the set winner,
        // otherwise, set the set winner to awayTeam
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