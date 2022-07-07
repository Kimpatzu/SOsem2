public class Request {
    private int track;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int deadlineTime;

    public Request(int track, int arrivalTime, int burstTime, int deadlineFactor){
        this.track = track;
        this.arrivalTime =  arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.deadlineTime = burstTime*deadlineFactor;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(int deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String toString(){
        return "Track: " + track + " At: " + arrivalTime + " Bt: " + burstTime + " Dt: " + deadlineTime;
    }
}
