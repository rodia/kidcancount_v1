package com.rodia.develop.kidcancount.model;

/**
 * Model for Stage. This class define the play moment.
 */
public class StageModel {

    /**
     * Make only test output.
     */
    private static final String LOG_CLASS = "StageModel";

    /**
     *
     * @param total_for_count
     * @return
     */
    public String [][] valuesForDefault(int total_for_count) {
        String[][] values = new String[total_for_count][3];
        for (int i = 0; i < total_for_count; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    values[i][j] = "0";
                } else {
                    values[i][j] = "";
                }
            }
        }
        return values;
    }
}
