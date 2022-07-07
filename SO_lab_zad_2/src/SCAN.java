import java.util.ArrayList;

public class SCAN {
    private Request[] requests;
    private int trackNumber;

    public SCAN(Request[] requests, int trackNumber){
        this.requests = requests;
        this.trackNumber = trackNumber;
    }

    public int Symulacja(){
        ArrayList<Request> requestPriorityQ = new ArrayList<>();
        int time =requests[0].getArrivalTime();
        int i=1;
        int jumpCounter = 0;
        requestPriorityQ.add(requests[0]);
        int requestDoneCounter = 0;
        while (requestDoneCounter!=requests.length-1){
            time += requestPriorityQ.get(0).getBurstTime();
            if(i<requests.length) {
                for (int j = 0; j < 10; j++) {
                    if(i+j<requests.length) {
                        if (requests[i+j].getArrivalTime() <= time) {
                            requestPriorityQ.add(requests[i]);
                            i++;
                        }
                    }
                }
            }
        }
        return jumpCounter;
    }

    private ArrayList<Request> sort(ArrayList<Request> requestQ, boolean decreasing){
        Request[] sortedRequests = requestQ.toArray(new Request[0]);
        ArrayList<Request> output = new ArrayList<>();
        int  currentTrack =sortedRequests[0].getTrack();
        int closestTrack = sortedRequests[1].getTrack();
        int closestIndex=-1;
        if(decreasing){
            for (int i=1; i<sortedRequests.length; i++){
                for (int j=0; j+i<sortedRequests.length; j++){
                    if (sortedRequests[i+j].getTrack()<currentTrack){

                    }
                }
            }
        }
        for (int i=0; i<sortedRequests.length; i++){
            output.add(sortedRequests[i]);
        }
        return output;
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
