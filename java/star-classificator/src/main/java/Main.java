import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        Map<Star, EStar> testStars = new HashMap<>();
        testStars.put(new Star("Deneb"                ,new BigDecimal(196000)     ,new BigDecimal(8525) ,null), EStar.SuperGiant);
        testStars.put(new Star("Canopus"              ,new BigDecimal(10700)      ,new BigDecimal(6998) ,null), EStar.SuperGiant);
        testStars.put(new Star("Arcotrus"             ,new BigDecimal(170)        ,new BigDecimal(4286) ,null), EStar.Giant);
        testStars.put(new Star("Spica"                ,new BigDecimal(20512)      ,new BigDecimal(25300),null), EStar.MainSequenceStar);
        testStars.put(new Star("Epsilon Eridani"      ,new BigDecimal(0.34)       ,new BigDecimal(5084) ,null), EStar.MainSequenceStar);
        testStars.put(new Star("Syrius B"             ,new BigDecimal(0.056)      ,new BigDecimal(25000),null), EStar.WhiteDwarf);
        testStars.put(new Star("Lacaile 9352"         ,new BigDecimal(0.033)      ,new BigDecimal(3625) ,null), EStar.MainSequenceStar);
        testStars.put(new Star("Proxmia Centauri"     ,new BigDecimal(0.0017)     ,new BigDecimal(3042) ,null), EStar.MainSequenceStar);

        StarClassification starClassification = new StarClassification(1);
        System.out.println("cross validation: " + starClassification.crossValidate(9) + "%");
        test(testStars, starClassification);
    }

    private static void test(Map<Star, EStar> testStars, StarClassification starClassification) {
        int correctGuessCounter = 0;
            for(Map.Entry<Star, EStar> testStar : testStars.entrySet()) {
                EStar clazz = starClassification.classificate(testStar.getKey(), Collections.emptyList());
                if(clazz == testStar.getValue()) {
                    correctGuessCounter++;
                }
                else {
                    System.out.println(testStar.getKey().name + " classified as: " + starClassification.classificate(testStar.getKey(), Collections.emptyList())) ;
                }
            }

        System.out.println("Percent of accuracy:" + (double)correctGuessCounter/testStars.size()*100 + "%\n");
    }
}
