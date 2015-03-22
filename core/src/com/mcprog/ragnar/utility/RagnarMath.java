package com.mcprog.ragnar.utility;

/**
 * Created by Michael on 3/21/2015.
 */
public class RagnarMath {

    public static float minLength (float a, float b) {
        float absA = Math.abs(a);
        float absB = Math.abs(b);
        return absA < absB ? a : b;
    }
}
