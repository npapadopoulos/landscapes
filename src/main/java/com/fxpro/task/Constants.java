package com.fxpro.task;

public enum Constants {
    ;
    public static final int MAX_POSITION = 32000;
    public static final int MAX_HEIGHT = MAX_POSITION;
    public static final int MIN_HEIGHT = 0;

    public static String usage() {
        return String.format("Usage: java -jar landscapes.jar {p1,p2,p3,p4,p5,...}" +
                        "\n\tMin height: %d" +
                        "\n\tMax height: %d" +
                        "\n\tMax position: %d\n",
                MIN_HEIGHT, MAX_HEIGHT, MAX_POSITION);
    }
}
