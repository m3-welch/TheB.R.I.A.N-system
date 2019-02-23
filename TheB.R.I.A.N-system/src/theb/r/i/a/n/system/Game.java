package theb.r.i.a.n.system;

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

    // Return a string version of the scores for a game by joining homeScore and awayScore with a ':'
    @Override
    public String toString(){
        return homeScore + ":" + awayScore;
    }

}