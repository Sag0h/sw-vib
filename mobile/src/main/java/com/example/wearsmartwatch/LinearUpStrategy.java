package com.example.wearsmartwatch;

public class LinearUpStrategy extends LinearStrategy{


    public LinearUpStrategy(long period, int vibrations, long pause){
        super(period, vibrations, pause);
    }

    @Override
    public long[] getPattern() {
        long[] pattern = super.getPattern();

        return reverseSwap(pattern);
    }

    private long[] reverseSwap(long[] p){
        long[] reversePattern = new long[p.length];

        for (int i = 0; i < p.length; i++) {
            reversePattern[p.length - 1 - i] = p[i];
        }

        return reversePattern;
    }

}
