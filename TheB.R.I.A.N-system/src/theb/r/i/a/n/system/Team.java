package theb.r.i.a.n.system;


import java.util.ArrayList;
import java.util.List;

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
