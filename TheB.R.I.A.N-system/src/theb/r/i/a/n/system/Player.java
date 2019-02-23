package theb.r.i.a.n.system;


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