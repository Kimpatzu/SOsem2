public class Proces {
    private int arrivalTime;
    private int cpuUsage;
    private int workTime;

    public Proces(int arrivalTime, int cpuUsage, int workTime) {
        this.arrivalTime = arrivalTime;
        this.cpuUsage = cpuUsage;
        this.workTime = workTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(int cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }
}
