package pl.dzielins42.dmtools.util;

public class UnitsUtils {

    private static final double ACRE_TO_SQ_METERS = 4046.85642d;

    public static double acrsToSquareMeters(double acres) {
        return acres * ACRE_TO_SQ_METERS;
    }

    public static double squareMetersToAcres(double sqMeters) {
        return sqMeters / ACRE_TO_SQ_METERS;
    }

}