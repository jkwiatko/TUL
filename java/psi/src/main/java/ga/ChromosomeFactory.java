package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChromosomeFactory {

    public static final Random random  = new Random();

    public static List<Chromosome> generateChromosomes(int listSize, int maxBinaryValue) {
        List<Chromosome> chromosomes = new ArrayList<>();

        for(int i=0; i<listSize; i++) {
            Chromosome chromosome = new Chromosome();
            chromosome.setBinaryValue(generateRandomBinaryString(1, maxBinaryValue));
            chromosome.setValue(getIntegerFromBinaryString(chromosome.getBinaryValue()));
            chromosome.setAdaptationValue(Ga.fitnessFunction(chromosome.getValue()));
            chromosomes.add(chromosome);
        }

        return chromosomes;
    }

    public static String generateRandomBinaryString(int minBinaryValue, int maxBinaryValue) {
        String binaryString = Integer.toBinaryString(random.nextInt((maxBinaryValue - minBinaryValue) + 1) + minBinaryValue);
        if(binaryString.length() != Chromosome.NUMBER_OF_BINARY_DIGITS) {
            int missingDigits = Chromosome.NUMBER_OF_BINARY_DIGITS - binaryString.length();
            StringBuilder stringBuilder = new StringBuilder();

            for(int i=0; i<missingDigits; i++) {
                stringBuilder.append("0");
            }

            binaryString = stringBuilder.toString() + binaryString;
        }
        return binaryString;
    }

    public static int getIntegerFromBinaryString(String s) {
        return Integer.parseInt(s, 2);
    }


}
