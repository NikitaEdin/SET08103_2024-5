package com.napier.sem;

import java.text.DecimalFormat;

/**
 * Represents the spoken language, containing the amount of speakers and percentage within world
 */
public class Language {
    /** The spoken lanauge */
    private final String language;
    /**  Total amount of speakers */
    private final double speakers;
    /**  Percentage of speakers in the world */
    private final double percentage;

    /**  Constructs a new Lanauge object of the spoken language and its speakers */
    public Language(String language, double speakers, double percentage) {
        this.language = language;
        this.speakers = speakers;

        // Normalise percentage into bounds
        if (Double.isInfinite(percentage) || Double.isNaN(percentage)){
            percentage = 0;
        } else if (percentage > 100) {
            percentage = 100;
        } else if (percentage < 0) {
            percentage = 0;
        }

        this.percentage = percentage;
    }

    /**
     * Get a readable string of the language and its speaker stats
     * @return Returns a string containing the language and its speakers
     */
    @Override
    public String toString() {
        // Format for display
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedSpeakers = df.format(speakers);
        String formattedPercentage = String.format("%.2f", percentage);

        // Display results
        return ("Language: " + language +
                ", Speakers: " + formattedSpeakers +
                ", Percentage of World: " + formattedPercentage + "%");
    }

    /**
     * Get the Language
     * @return Returns the spoken language name
     */
    public String getLanguage() { return language; }

    /**
     * Get the speakers
     * @return returns the number of speakers in double
     */
    public double getSpeakers() { return speakers; }

    /**
     * Get the percentage of speakers compared to world population
     * @return returns the percentage of speakers within world
     */
    public double getPercentage() { return percentage; }
}
