import java.util.Comparator;

public class FCFC_Comparator implements Comparator<Request> {

    @Override
    public int compare(Request r1, Request r2) {
        if(r1.getArrivalTime()>r2.getArrivalTime()){
            return 1;
        } if (r1.getArrivalTime() == r2.getArrivalTime()){
            return 0;
        } else return -1;
    }
}
