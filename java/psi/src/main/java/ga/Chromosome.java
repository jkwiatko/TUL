package ga;

public class Chromosome {

    public static int NUMBER_OF_BINARY_DIGITS = 7;

    private String binaryValue;
    private int value;
    private int adaptationValue;
    private double survivalChance;

    public String getBinaryValue() {
        return binaryValue;
    }

    public void setBinaryValue(String binaryValue) {
        this.binaryValue = binaryValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAdaptationValue() {
        return adaptationValue;
    }

    public void setAdaptationValue(int adaptationValue) {
        this.adaptationValue = adaptationValue;
    }

    public double getSurvivalChance() {
        return survivalChance;
    }

    public void setSurvivalChance(double survivalChance) {
        this.survivalChance = survivalChance;
    }

    @Override
    public String toString() {
       return Integer.toString(value);
    }
}
