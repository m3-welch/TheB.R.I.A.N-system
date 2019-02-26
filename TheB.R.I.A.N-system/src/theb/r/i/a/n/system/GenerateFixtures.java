/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theb.r.i.a.n.system;

import static theb.r.i.a.n.system.TheBRIANSystem.fixtures;
import static theb.r.i.a.n.system.TheBRIANSystem.matchesArray;
import static theb.r.i.a.n.system.TheBRIANSystem.teamsArray;

/**
 *
 * @author m3-welch
 */
public class GenerateFixtures {
    public static void fixtures_generation(){
        String[][] matches = new String[teamsArray.size()][teamsArray.size()];
        
        fixtures.clear();

        String homeScore;
        String awayScore;
        boolean matchFound = false;
        Match match = new Match();

        //loop through all the teams (e.g. top left to bottom right of the fixtures table)
        for (int i = 0; i < teamsArray.size(); i++){
            for(int n = 0; n < teamsArray.size(); n++){
                // if the teams are the same, print '---' as a team cannot play against themselves
                if (n == i){
                    matches[i][n] = ("---");
                }
                else{
                   matchFound = false; 
                    // try and see if the match between the two teams exists
                    for(int c = 0; c < matchesArray.size(); c++){
                        if(matchesArray.get(c).getHomeTeam().getName().equals(teamsArray.get(i).getName())){
                            if(matchesArray.get(c).getAwayTeam().getName().equals(teamsArray.get(n).getName())){
                                match = matchesArray.get(c);
                                matchFound = true;
                            }
                        }
                    }

                    // if the match between the two teams does exists, print the match scores in the fixtures table
                    if((matchFound)){ 
                        homeScore = Integer.toString(match.homeScore);
                        awayScore = Integer.toString(match.awayScore);
                        matches[i][n] = homeScore + ":" + awayScore;
                    }
                    else{
                        // print np (not played) if the match between the two teams has not been played
                        matches[i][n] = ("np");
                    }
                }
            }
            // add the row for that team to the fixtures table
            fixtures.add(matches[i]);
        }      
    }
}
