package it.amrito.utils;

public class StopWatch2 {

    private long startTs;
    private long deltaTs = 0;

    public StopWatch2(){
        this.start();
    }

    public long getDeltaTimeInMillis(){
        long deltaTmp = System.currentTimeMillis();
        long diff = deltaTmp - deltaTs;
        deltaTs = deltaTmp;
        return diff;
    }

    public long getTotalTimeInMillis(){
        return System.currentTimeMillis() - startTs;
    }

    public void start(){
        startTs = System.currentTimeMillis();
        deltaTs = startTs;
    }
}
