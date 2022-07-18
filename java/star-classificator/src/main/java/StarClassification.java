import java.math.BigDecimal;
import java.util.*;

public class StarClassification {

    int k;
    Map<EStar, Integer> classesCount = new HashMap<>();
    public List<Star> stars;

    StarClassification(int k) {
        this.k = k;
        stars = new ArrayList<>();
        stars.add(new Star("Rigel"                ,new BigDecimal(120000)     ,new BigDecimal(12100) , EStar.SuperGiant));
        stars.add(new Star("Antares"              ,new BigDecimal(97700)      ,new BigDecimal(3570)  , EStar.SuperGiant));
        stars.add(new Star("Betelgeuse"           ,new BigDecimal(90000)      ,new BigDecimal(3590)  , EStar.SuperGiant));
        stars.add(new Star("Aledebaran"           ,new BigDecimal(518)        ,new BigDecimal(3910)  , EStar.Giant));
        stars.add(new Star("Hamal"                ,new BigDecimal(91)         ,new BigDecimal(4480)  , EStar.Giant));
        stars.add(new Star("Pollux"               ,new BigDecimal(43)         ,new BigDecimal(4666)  , EStar.Giant));
        stars.add(new Star("Kappa Persei"         ,new BigDecimal(39.8)       ,new BigDecimal(4857)  , EStar.Giant));
        stars.add(new Star("Andromedae"           ,new BigDecimal(880)        ,new BigDecimal(3400)  , EStar.Giant));
        stars.add(new Star("Beta Centauri"        ,new BigDecimal(41700)      ,new BigDecimal(25000) , EStar.MainSequenceStar));
        stars.add(new Star("Bellatrix"            ,new BigDecimal(9211)       ,new BigDecimal(22000) , EStar.MainSequenceStar));
        stars.add(new Star("Achernar"             ,new BigDecimal(3150)       ,new BigDecimal(15000) , EStar.MainSequenceStar));
        stars.add(new Star("Alfa Centauri A"      ,new BigDecimal(1.519)      ,new BigDecimal(5790)  , EStar.MainSequenceStar));
        stars.add(new Star("Wolf 359"             ,new BigDecimal(0.0014)     ,new BigDecimal(2800)  , EStar.MainSequenceStar));
        stars.add(new Star("Gliese 752 A"         ,new BigDecimal(0.0326 )    ,new BigDecimal(3240)  , EStar.MainSequenceStar));
        stars.add(new Star("Ross 128"             ,new BigDecimal(0.00362)    ,new BigDecimal(3192)  , EStar.MainSequenceStar));
        stars.add(new Star("Vega"                 ,new BigDecimal(40.12)      ,new BigDecimal(9602)  , EStar.MainSequenceStar));
        stars.add(new Star("Sun"                  ,new BigDecimal(1)          ,new BigDecimal(5772)  , EStar.MainSequenceStar));
        stars.add(new Star("Tau Ceti"             ,new BigDecimal(0.52)       ,new BigDecimal(5344)  , EStar.MainSequenceStar));
        stars.add(new Star("61 Cygani A"          ,new BigDecimal(0.153)      ,new BigDecimal(4526)  , EStar.MainSequenceStar));
        stars.add(new Star("61 Cygani B"          ,new BigDecimal(0.085)      ,new BigDecimal(4077)  , EStar.MainSequenceStar));
        stars.add(new Star("Procyon"              ,new BigDecimal(6.93)       ,new BigDecimal(7740)  , EStar.MainSequenceStar));
        stars.add(new Star("Sirius"               ,new BigDecimal(25.4)       ,new BigDecimal(9940)  , EStar.MainSequenceStar));
        stars.add(new Star("DX Cancri"            ,new BigDecimal(0.00065)    ,new BigDecimal(2840)  , EStar.MainSequenceStar));
        stars.add(new Star("Procyon B"            ,new BigDecimal(0.00049)    ,new BigDecimal(7740)  , EStar.WhiteDwarf));
        stars.add(new Star("L 97-12"              ,new BigDecimal(0.013)      ,new BigDecimal(5700)  , EStar.WhiteDwarf));
        stars.add(new Star("LP 145"               ,new BigDecimal(0.0005)     ,new BigDecimal(8500)  , EStar.WhiteDwarf));
        stars.add(new Star("40 Erdiani B"         ,new BigDecimal(0.013)      ,new BigDecimal(16500) , EStar.WhiteDwarf));
        stars.add(new Star("Van Maanen 2"         ,new BigDecimal(0.00017)    ,new BigDecimal(6220)  , EStar.WhiteDwarf));
        stars.add(new Star("Alfa Canis Minoris B" ,new BigDecimal(0.00049)    ,new BigDecimal(7440)  , EStar.WhiteDwarf));
    }


    public EStar classificate(Star classifiedStar, List<Star> learningSet) {
        List<BigDecimal> distances                   = new ArrayList<>();
        Map<BigDecimal, Star> classAndDistances      = new HashMap<>();
        prepareCounters(classesCount);
        if(learningSet.isEmpty()) {
            learningSet = stars;
        }

        for(Star star : learningSet) {
            BigDecimal distanceBetweenStars = getDistanceBetween(star, classifiedStar);
            distances.add(distanceBetweenStars);
            classAndDistances.put(distanceBetweenStars, star);
        }

        distances.sort(BigDecimal::compareTo);
        List<BigDecimal> kList = distances.subList(0,k);

        for(BigDecimal kej : kList){
            switch(classAndDistances.get(kej).clazz){
                case WhiteDwarf:
                    classesCount.replace(EStar.WhiteDwarf, classesCount.get(EStar.WhiteDwarf) + 1);
                    break;
                case MainSequenceStar:
                    classesCount.replace(EStar.MainSequenceStar, classesCount.get(EStar.MainSequenceStar) + 1);
                    break;
                case Giant:
                    classesCount.replace(EStar.Giant, classesCount.get(EStar.Giant) + 1);
                    break;
                case SuperGiant:
                    classesCount.replace(EStar.SuperGiant, classesCount.get(EStar.SuperGiant) + 1);
                    break;
            }
        }

        return getMaximum(classesCount);
    }


    public double crossValidate(int setSize) {
        int i;
        double mean = 0;
        Collections.shuffle(stars);

        for(i = 0; i <= stars.size()/setSize; ++i) {
            int counter = 0;
            List<Star> starsSublist = new ArrayList<>();
            List<Star> testingList = new ArrayList<>(stars);

            for(int j = 0; j < setSize && i*setSize+j < stars.size(); ++j) {
                starsSublist.add(stars.get(i*setSize+j));
                testingList.remove(stars.get(i*setSize+j));
            }

            for(Star star : starsSublist) {
                EStar eStar = classificate(star,testingList);
                if(eStar == star.clazz) {
                    counter++;
                }
            }
            mean += (double) counter/setSize;
        }
        return mean/i*100;
    }

    private EStar getMaximum(Map<EStar, Integer> counterMap) {
        Map.Entry<EStar, Integer> maxEntry = null;
        for (Map.Entry<EStar, Integer> entry : counterMap.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        if (maxEntry.getKey() != null) {
            return maxEntry.getKey();
        }
        return EStar.NaN;
    }

    private void prepareCounters(Map<EStar, Integer> counterMap) {
        counterMap.put(EStar.WhiteDwarf, 0);
        counterMap.put(EStar.MainSequenceStar, 0);
        counterMap.put(EStar.Giant, 0);
        counterMap.put(EStar.SuperGiant, 0);
    }


    private BigDecimal getDistanceBetween(Star a, Star b) {
        BigDecimal x1 = a.luminosity;
        BigDecimal x2 = b.luminosity;
        BigDecimal y1 = a.surfaceTemperature;
        BigDecimal y2 = b.surfaceTemperature;

        BigDecimal term1    =  x2.subtract(x1).multiply(x2.subtract(x1));
        BigDecimal term2    =  y2.subtract(y1).multiply(y2.subtract(y1));
        BigDecimal termSUM  =  term1.add(term2);
        return new BigDecimal(Math.abs(Math.sqrt(termSUM.doubleValue())));
    }
}
