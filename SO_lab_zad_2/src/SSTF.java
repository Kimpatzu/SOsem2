import java.util.ArrayList;
import java.util.Arrays;

public class SSTF {
    private Request[] requests;

    public SSTF(Request[] requests){
        this.requests = requests;
    }

    public int Symulacja(){
        ArrayList<Request> requestPriorityQ = new ArrayList<>();
        int time =requests[0].getArrivalTime();
        int i=1;
        int jumpCounter = 0;
        requestPriorityQ.add(requests[0]);
        int requestPQSize = 5;
        while (requestPQSize!=1){
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
                requestPriorityQ = sort(requestPriorityQ);
            }
            jumpCounter += modulusFromSubtrraction(requestPriorityQ.get(0).getTrack(), requestPriorityQ.get(1).getTrack());
            requestPriorityQ.remove(0);
            requestPQSize = requestPriorityQ.size();
        }
        return jumpCounter;
    }

    private ArrayList<Request> sort(ArrayList<Request> requestQ){
        Request[] sortedRequests = requestQ.toArray(new Request[0]);
        int currentRequestTrack = sortedRequests[0].getTrack();
        int shortestSeekTime = modulusFromSubtrraction(currentRequestTrack, sortedRequests[1].getTrack());
        int sSTRequestIndex = 1;
        for (int i=1; i<sortedRequests.length; i++){
            for(int j=0; i+j<sortedRequests.length; j++){
                if(modulusFromSubtrraction(currentRequestTrack,sortedRequests[i+j].getTrack())<shortestSeekTime){
                    shortestSeekTime = modulusFromSubtrraction(currentRequestTrack, sortedRequests[i+j].getTrack());
                    sSTRequestIndex = i+j;
                }
                Request pom = sortedRequests[i];
                sortedRequests[i] = sortedRequests[sSTRequestIndex];
                sortedRequests[sSTRequestIndex] = pom;
            }
        }
        ArrayList<Request> output = new ArrayList<>();
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
