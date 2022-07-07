import java.util.ArrayList;

public class FCFS {
    private ArrayList<Proces> procesy;
    public FCFS(ArrayList<Proces> procesy) {
        this.procesy = procesy;
        this.Sort();
    }

    public void Symulacja(){
        int t = procesy.get(0).getAt();
        for (Proces p: procesy) {
            t+=p.getBt();
            p.setWt(t-p.getBt()-p.getAt());
        }
        int cwt = 0;
        for (Proces p: procesy){
            System.out.println(p + " wt: " + p.getWt());
            cwt+=p.getWt();
        }
        System.out.println("Całkowity czas oczekiwania: " + cwt);
        System.out.println("średni czas oczekiwania: " + (float)cwt/(float)procesy.size());
    }

    private void Sort(){
        procesy.sort(new FCFSComparator());
    }

}
