package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ga {

    public static double CROSS_PROBABILITY = 0.75;
    public static double MUTATION_PROBABILITY = 0.1;


    public static void main(String[] args) {
        List<Chromosome> chromosomesAfterSelection = null;

        //create population
        List<Chromosome> chromosomes = ChromosomeFactory.generateChromosomes(100, 127);
        applySurvivalChance(chromosomes);
        System.out.println(chromosomes);

        for(int i=0; i<100000; i++) {
            //select best chromosomes
            chromosomesAfterSelection = applySelection(chromosomes);

            //connect chromosomes in pairs and cross them
            List<List<Chromosome>> listOfPairs = new ArrayList<>();
            List<Chromosome> children = new ArrayList<>();
            Collections.shuffle(chromosomesAfterSelection);
            for (int j=0; j<chromosomesAfterSelection.size()-1; j+=2) {
                List<Chromosome> pair = new ArrayList<>();
                pair.add(chromosomesAfterSelection.get(j));
                pair.add(chromosomesAfterSelection.get(j + 1));
                listOfPairs.add(pair);
            }
            for(List<Chromosome> pair : listOfPairs) {
                if(ChromosomeFactory.random.nextDouble() < CROSS_PROBABILITY) {
                    children.addAll(cross(pair));
                }
            }

            //mutate children
            chromosomesAfterSelection.addAll(children);
            for(Chromosome chromosome : children) {
                if(ChromosomeFactory.random.nextDouble() < MUTATION_PROBABILITY) {
                    mutate(chromosome);
                }
            }
            chromosomesAfterSelection.addAll(children);
        }
        chromosomesAfterSelection = applySelection(chromosomes);
        System.out.println(chromosomesAfterSelection +" "+ (int) chromosomesAfterSelection.stream().filter(c -> c.getValue() == 127).count());
    }

    private static List<Chromosome> generateChromosomes(int amount, int bound) {
        List<Chromosome> chromosomes = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            Chromosome chromosome = new Chromosome();
            chromosome.setBinaryValue(ChromosomeFactory.generateRandomBinaryString(1, bound));
            chromosome.setValue(ChromosomeFactory.getIntegerFromBinaryString(chromosome.getBinaryValue()));
            chromosome.setAdaptationValue(fitnessFunction(chromosome.getValue()));
            chromosomes.add(chromosome);
        }

        return chromosomes;
    }

    private static void applySurvivalChance(List<Chromosome> chromosomes) {
        int populationAdaptionValue = chromosomes.stream()
                .map(Chromosome::getAdaptationValue)
                .reduce(Integer::sum)
                .orElse(0);

        chromosomes.forEach(chromosome -> chromosome
                .setSurvivalChance((double) (chromosome.getAdaptationValue()) / populationAdaptionValue));
    }

    private static List<Chromosome> applySelection(List<Chromosome> chromosomes) {
        List<Chromosome> chromosomesAfterRoulette = new ArrayList<>();

        for(int i=0; i<chromosomes.size(); i++) {
            double rouletteSpin = ChromosomeFactory.random.nextDouble();
            double chromosomeBound = 0;

            for(Chromosome chromosome : chromosomes) {
                chromosomeBound += chromosome.getSurvivalChance();
                if(rouletteSpin < chromosomeBound) {
                    chromosomesAfterRoulette.add(chromosome);
                    break;
                }
            }
        }

        return chromosomesAfterRoulette;
    }

    private static List<Chromosome> cross(List<Chromosome> pair) {
        Chromosome child;
        ArrayList<Chromosome> children = new ArrayList<>();
        int lokus = ChromosomeFactory.random.nextInt(Chromosome.NUMBER_OF_BINARY_DIGITS - 1) + 1;

        Chromosome father = pair.get(0);
        String fatherPrefix = father.getBinaryValue().substring(0, lokus);
        String fatherSuffix = father.getBinaryValue().substring(lokus);

        Chromosome mother = pair.get(1);
        String motherPrefix = mother.getBinaryValue().substring(0, lokus);
        String motherSuffix = mother.getBinaryValue().substring(lokus);

        child = new Chromosome();
        child.setBinaryValue(fatherPrefix + motherSuffix);
        child.setValue(ChromosomeFactory.getIntegerFromBinaryString(fatherPrefix + motherSuffix));
        child.setAdaptationValue(fitnessFunction(child.getValue()));
        children.add(child);
        child.setBinaryValue(motherPrefix + fatherSuffix);
        child.setValue(ChromosomeFactory.getIntegerFromBinaryString(motherPrefix + fatherSuffix));
        child.setAdaptationValue(fitnessFunction(child.getValue()));
        children.add(child);

        return children;
    }

    private static void mutate(Chromosome chromosome) {
        int bit = ChromosomeFactory.random.nextInt(Chromosome.NUMBER_OF_BINARY_DIGITS);
        char[] genotype = chromosome.getBinaryValue().toCharArray();
        genotype[bit] = genotype[bit] == '0' ? '1' : '0';
        chromosome.setBinaryValue(String.valueOf(genotype));
    }

    public static int fitnessFunction(int chromosomeValue) {
        return (int)(2 * (Math.pow(chromosomeValue, 2) + 1));
    }

}
