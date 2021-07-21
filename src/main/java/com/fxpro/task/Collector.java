package com.fxpro.task;

public interface Collector {
    /**
     * Calculates amount of collected water for the given landscape using two pointers approach in order to process in one iteration.
     *
     * @param landscape a Collection of heights
     * @return The number of collected water squares
     */
    default long calculateWaterAmount(int[] landscape) {
        long lmax = 0;
        long rmax = 0;
        long result = 0;

        var l = 0;
        var r = landscape.length - 1;

        while (l < r) {
            if (landscape[l] < landscape[r]) { //compare the heights of the current positions on the left and the right sides
                if (landscape[l] >= lmax) { //reset the left max height position, otherwise add the difference of left max height with the current height on the left to the result
                    lmax = landscape[l];
                } else {
                    result = result + (lmax - landscape[l]); //other
                }
                ++l; //increase the left index as we move to the right
            } else {
                if (landscape[r] >= rmax) { //reset the right max height position, otherwise add the difference of right max height with the current height on the right to the result
                    rmax = landscape[r];
                } else {
                    result = result + (rmax - landscape[r]);
                }
                --r; //decrease the right index as we move to the left
            }
        }
        return result;
    }
}
