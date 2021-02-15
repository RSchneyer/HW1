public class Location {
    public static final double RELATIVE_COST_OF_POSTCARD = 0.05;

    private String locationName;
    private int lodgingCost;

    public Location(String givenName, int givenLodgingCost){
        locationName = givenName;
        lodgingCost = givenLodgingCost;
    }

    public String getName(){
        return locationName;
    }

    public int getLodgingCost(){
        return lodgingCost;
    }

    public int costToSendPostcard(){
        //Updated based on hw1 doc, tip #5
        return (int) Math.round((RELATIVE_COST_OF_POSTCARD * lodgingCost));
    }

    public int maxLengthOfStay(int funds){
        return funds / lodgingCost;
    }

    public int maxNumberOfPostcards(int funds){
        return funds / (costToSendPostcard());
    }

}
