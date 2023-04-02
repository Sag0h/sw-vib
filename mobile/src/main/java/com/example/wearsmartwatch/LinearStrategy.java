package com.example.wearsmartwatch;

public abstract class LinearStrategy implements FormatStrategy {
    private long period;
    private int vibrations;
    private long pauseTime;

    public LinearStrategy(long period, int vibrations, long pause){
        this.period = period;
        this.vibrations = vibrations;
        this.pauseTime = pause;
    }

    public long[] getPattern(){
        while (period <= pauseTime*vibrations){
            pauseTime -= 50;
        }
        long totalVibrationTime =  period - (long)(pauseTime * vibrations);
        long[] pattern = new long[(vibrations*2) +1];
        pattern[0] = pauseTime;
        for(int i=1; i<pattern.length; i++){
            if(i%2 == 0){
                pattern[i] = pauseTime;
            }else{
                pattern[i] = totalVibrationTime/vibrations;
                totalVibrationTime -= totalVibrationTime/vibrations;
            }
        }
        pattern[(vibrations*2)] = pauseTime;
        return pattern;
    }

}
