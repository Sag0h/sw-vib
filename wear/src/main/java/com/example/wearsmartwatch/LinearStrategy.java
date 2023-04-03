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

    public boolean checkData(){
        return (period > 0) && (vibrations > 0) && (period <= (pauseTime*vibrations));
    }

    private long assignValues(int index, long totalVibrationTime, long[] pattern){
        if(index%2 == 0){
            pattern[index] = pauseTime;
        }else{
            pattern[index] = totalVibrationTime/vibrations;
            return totalVibrationTime/vibrations;
        }
        return 0;
    }
    public long[] getPattern(){
        if(this.checkData()){
            long totalVibrationTime =  period - (long)(pauseTime * vibrations);
            long[] pattern = new long[(vibrations*2) +1];
            pattern[0] = pauseTime;
            for(int i=1; i<pattern.length; i++){
                totalVibrationTime -= this.assignValues(i, totalVibrationTime, pattern);
            }
            return pattern;
        }
        return new long[0];
    }

}
