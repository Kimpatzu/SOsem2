import java.util.ArrayList;

public class FCFS {
    private Request[] requests;

    public FCFS(Request[] requests){
        this.requests = requests;
    }

    public int  Symulacja(){
        ArrayList<Request> requestsQ = new ArrayList<>();
        for (int i=0; i<requests.length; i++){
            requestsQ.add(requests[i]);
        }
        int jumpCounter = requestsQ.get(0).getTrack();
        requestsQ.sort(new FCFC_Comparator());
        for (int i=1; i<requestsQ.size(); i++){
            jumpCounter += modulusFromSubtrraction(requestsQ.get(i-1).getTrack(),requestsQ.get(i).getTrack());
        }
        return jumpCounter;
    }

    private int modulusFromSubtrraction(int n1, int n2){
        if(n1==n2){
            return 0;
        } if (n1>n2) {
            return n1-n2;
        } else {
            return n2-n1;
        }
    }
}
