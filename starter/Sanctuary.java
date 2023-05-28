/*
 * Name: Farris Danish
 * PID: A17401247
 * email: fbinsyahrilakmar@ucsd.edu
 * Sources used: Write-up, JDK 17 documentation
 *
 * This file contains the implementation of Hashset for the application of
 * enrollment of classes
 */

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the implementation of hashset for a wildlife sanctuary
 * to keep track of its animals
 */
public class Sanctuary {

    private static final String LESS_THAN_ZERO_MESSAGE =
        "Arguments cannot be less than zero";
    private static final String NULL_ARG_MESSAGE = "Arguments cannot be null";
    private static final String NUM_MORE_THAN_COUNT =
        "Num cannot be more than the number of animals of the species";
    private static final String SPECIES_NOT_IN_SANCTUARY =
        "Species is not in sanctuary";
    private static final String SPECIES_LARGER_THAN_ANIMAL_MESSAGE =
        "Max species cannot be more than max animals";
    /* Instance variables */
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    /* Class methods */
    /**
     * The constructor for the sanctuary. Args have to be more than zero
     * @param maxAnimals the maximum number of animals
     * @param maxSpecies the maximum number of species
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals <= 0 || maxSpecies <= 0) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_MESSAGE);
        }
        if (maxSpecies > maxAnimals) {
            throw new IllegalArgumentException(
                SPECIES_LARGER_THAN_ANIMAL_MESSAGE
            );
        }
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
        this.sanctuary = new HashMap<>(maxSpecies);
    }

    /**
     * This method returns the number of the specified species in the santuary
     * @param species the species of animal in question
     * @return the number of animals of that species
     */
    public int countForSpecies(String species) {
        if (species == null) {
            throw new IllegalArgumentException(NULL_ARG_MESSAGE);
        }
        if (!this.sanctuary.containsKey(species)) return 0;
        return this.sanctuary.get(species);
    }

    /**
     * This method returns the total number of animals of all species
     * in the sanctuary
     * @return the total number of animals in the sanctuary
     */
    public int getTotalAnimals() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : this.sanctuary.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    /**
     * This method returns the total number of different species
     * in the sanctuary
     * @return the number of different species in the sanctuary
     */
    public int getTotalSpecies() {
        return this.sanctuary.size();
    }

    /**
     * This method returns the max number of animals that the santuary can house
     * @return the maximum number of animals the sanctuary can keep
     */
    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    /**
     * This method returns the max number of different species that
     * the sanctuary can keep
     * @return the max number of species the sanctuary can house
     */
    public int getMaxSpecies() {
        return this.maxSpecies;
    }

    /**
     * This method adds the specified number of that species to the sanctuary
     * @param species the species to add
     * @param num the number of animals to add
     * @return the number of animals that could not be rescued
     */
    public int rescue(String species, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_MESSAGE);
        }
        if (species == null) {
            throw new IllegalArgumentException(NULL_ARG_MESSAGE);
        }
        if (getTotalAnimals() == getMaxAnimals()) {
            return num;
        } else if ( //have space for new species and can accomodate all addition
            getTotalAnimals() + num <= getMaxAnimals() &&
            getTotalSpecies() + 1 <= getMaxSpecies()
        ) {
            this.sanctuary.put(species, num + countForSpecies(species));
            return 0;
        } else if ( // if have space for new species but not all additions
            getTotalSpecies() + 1 <= getMaxSpecies() &&
            getTotalAnimals() + num > getMaxAnimals()
        ) {
            int diff = getMaxAnimals() - getTotalAnimals();
            this.sanctuary.put(species, countForSpecies(species) + diff);
            return num - diff;
        } else if ( // the rescued species is not new and can be added
            this.sanctuary.containsKey(species) &&
            getTotalAnimals() + num <= getMaxAnimals()
        ) {
            this.sanctuary.put(species, countForSpecies(species) + num);
            return 0;
        } else if ( // if species is not new but not all can be added
            this.sanctuary.containsKey(species) &&
            getTotalAnimals() + num > getTotalAnimals()
        ) {
            int diff = getMaxAnimals() - getTotalAnimals();
            this.sanctuary.put(species, countForSpecies(species) + diff);
            return num - diff;
        } else {
            return num;
        }
    }

    /**
     * This method removes the specified number of animals from the sanctuary
     * @param species the species of animal to remove
     * @param num the number of animals to remove
     */
    public void release(String species, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_MESSAGE);
        }
        if (num > countForSpecies(species)) {
            throw new IllegalArgumentException(NUM_MORE_THAN_COUNT);
        }
        if (species == null) {
            throw new IllegalArgumentException(NULL_ARG_MESSAGE);
        }
        if (!this.sanctuary.containsKey(species)) {
            throw new IllegalArgumentException(SPECIES_NOT_IN_SANCTUARY);
        }

        this.sanctuary.put(species, countForSpecies(species) - num);
        if (this.sanctuary.get(species) == 0) {
            this.sanctuary.remove(species);
        }
    }
}
