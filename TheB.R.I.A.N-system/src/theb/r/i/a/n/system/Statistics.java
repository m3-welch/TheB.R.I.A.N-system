package theb.r.i.a.n.system;


// Create a class to be a second thread in the program that will update the
// statistics every 100 seconds
class Statistics implements Runnable {

    // Create a function to update the statistics
    static public void updateStats(){
        TheBRIANSystem.statisticsArray.clear();
        for(int i = 0; i < TheBRIANSystem.teamsArray.size(); i++){
            TheBRIANSystem.statisticsArray.add(new String[] {Integer.toString(TheBRIANSystem.teamsArray.get(i).getMatchesPlayed()), Integer.toString(TheBRIANSystem.teamsArray.get(i).getMatchesWon()), Integer.toString(TheBRIANSystem.teamsArray.get(i).getSetsWon())});
        }
    }

    // Overriden run functions that allows the class to be run simultaneously with the main class
    @Override
    public void run(){
        // Set the start time as the current time
        long start = (System.currentTimeMillis() / 1000);
        long current;
        long elapsed;
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