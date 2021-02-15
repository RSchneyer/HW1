import javax.tools.JavaFileManager.Location;

public class Backpacker {
    public static final int SYMPATHY_FACTOR = 30;

    private int currFunds;
    private Location currLocation;
    private String journal;
    private int totalNightsInTrainStation;
    private int amountOfPostcardsSent;

    public Backpacker(int initialFunds, Location initialLocation){
        nightsInTrainStation = 0;
        amountOfPostcardsSent = 0;
        currFunds = initialFunds;
        currLocation = initialLocation;
        journal = initialLocation.getName() + "(start)";
    }

    public String getCurrentLocation(){
        return currLocation.getName();
    }

    public int getCurrentFunds(){
        return currFunds;
    }

    public String getJournal(){
        return journal;
    }

    public boolean isSOL(){
        //Probably how they'd want you to do it
        return funds < currLocation.costToSendPostcard();
    }

    public int getTotalNightsInTrainStation(){
        return totalNightsInTrainStation;
    }

    public void visit(Location c, int numNights){
        // Update current location instance variable
        currLocation = c;

        // Update the journal with the string ", locationname(numNights)" 
        journal += (", "+ c.getName() + "("+numNights+")");

        // The max amount of nights we don't have to stay at a train station.
        // If we can afford all nights, the var == numNights
        // If we can't afford all nights, the var == the max amount of nights we can stay
        int maxAmountOfLodgedNights = Math.min(numNights, c.maxLengthOfStay(currFunds));
        
        // Subtract money from our current funds based on how many nights we're not in a train station
        currFunds -= (maxAmountOfLodgedNights * c.getLodgingCost());

        // Update the total amount of nights we've spend in a train station
        // If numNights == maxAmountOfLodgedNights, we add zero
        totalNightsInTrainStation += (numNights - maxAmountOfLodgedNights);
    }

    public void sendPostcardsHome(int howMany){
        int actualSentAmount = Math.min(howMany, currLocation.maxNumberOfPostcards(currFunds));
        funds -= (currLocation.costToSendPostcard() * actualSentAmount);
        amountOfPostcardsSent += actualSentAmount;
    }

    public void callHomeForMoney(){
        currFunds += SYMPATHY_FACTOR * amountOfPostcardsSent;
        amountOfPostcardsSent = 0;
    }


}
