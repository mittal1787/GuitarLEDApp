package com.example.guitarledapp;

public class Note {
    private String name;
    private boolean sharp;
    private boolean flat;
    private double frequency;

    public Note(String name, boolean sharp, boolean flat, double frequency) {
        this.name = name;
        this.sharp = sharp;
        this.flat = flat;
        this.frequency = frequency;
    }

    /**
     * Returns whether note is sharp or not
     *
     * @return sharp -- Whether or not the note is sharp
     */
    public boolean isSharp()
    {
        return sharp;
    }

    /**
     * Returns whether or not note is sharp or not
     *
     * @return flat -- whether or not the note is flat
     */
    public boolean isFlat()
    {
        return flat;
    }
}
