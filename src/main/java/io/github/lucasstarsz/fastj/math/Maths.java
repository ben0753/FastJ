package io.github.lucasstarsz.fastj.math;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Secondary mathematics class to provide useful utility methods dealing with {@code float}s.
 *
 * @author Andrew Dey
 * @version 1.0.0
 */
public class Maths {

    public static final float FloatPrecision = 0.000001f;

    /**
     * Generates a random number within the specified min and max limits.
     *
     * @param min The minimum number possible.
     * @param max The maximum number possible.
     * @return Randomized float value within the range of the specified parameters.
     */
    public static float random(float min, float max) {
        if (min >= max) {
            throw new IllegalArgumentException("The minimum must be less than the maximum.");
        }

        return ThreadLocalRandom.current().nextFloat() * (max - min) + min;
    }

    /**
     * Generates a random boolean value.
     *
     * @return The randomized boolean value.
     */
    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Generates a random number within the specified range, then snaps it to the edge it is closest to.
     *
     * @param leftEdge  The leftmost edge.
     * @param rightEdge The rightmost edge.
     * @return The edge that the random number is closest to.
     */
    public static float randomAtEdge(float leftEdge, float rightEdge) {
        if (leftEdge >= rightEdge) {
            throw new IllegalArgumentException("The left edge must be less than the right edge.");
        }

        return ThreadLocalRandom.current().nextBoolean() ? leftEdge : rightEdge;
    }

    /**
     * Snaps the specified number to the edge it is closest to.
     *
     * If the two edges are equidistant from the {@code num}, then the right edge will be returned.
     *
     * @param num       The number to be compared.
     * @param leftEdge  The leftmost edge.
     * @param rightEdge The rightmost edge.
     * @return The edge that the number is closest to.
     */
    public static float snap(float num, float leftEdge, float rightEdge) {
        if (leftEdge >= rightEdge) {
            throw new IllegalArgumentException("The left edge must be less than the right edge.");
        }

        return ((num - leftEdge) < (rightEdge - num)) ? leftEdge : rightEdge;
    }

    /**
     * Finds the magnitude of the specified {@code x} and {@code y} values.
     *
     * @param x The x value.
     * @param y The y value.
     * @return The magnitude, as a {@code float} value.
     */
    public static float magnitude(float x, float y) {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Finds the magnitude of the {@code Pointf} based on its {@code x} and {@code y} coordinates.
     *
     * @param p The {@code Pointf} to find the magnitude of.
     * @return The magnitude, as a {@code float} value.
     */
    public static float magnitude(Pointf p) {
        return (float) Math.sqrt(p.x * p.x + p.y * p.y);
    }

    /**
     * Finds the magnitude of the {@code Point} based on its {@code x} and {@code y} coordinates.
     *
     * @param p The {@code Point} to find the magnitude of.
     * @return The magnitude, as a {@code float} value.
     */
    public static float magnitude(Point p) {
        return (float) Math.sqrt(p.x * p.x + p.y * p.y);
    }

    /**
     * Checks for 'equality' between two floating point values through ensuring their difference is less than the
     * defined float precision value {@link #FloatPrecision}.
     *
     * @param a The first {@code float}.
     * @param b The second {@code float}.
     * @return Whether the two are 'equal' (if their difference is less than {@link #FloatPrecision}).
     */
    public static boolean floatEquals(float a, float b) {
        return Math.abs(a - b) < FloatPrecision;
    }
}
