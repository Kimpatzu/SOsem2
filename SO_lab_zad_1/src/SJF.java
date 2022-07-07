import java.util.ArrayList;

public class SJF {
    private ArrayList<Proces> procesyNiegotowe;
    private ArrayList<Proces> procesyWykonane = new ArrayList<>();

    public SJF(ArrayList<Proces> procesy) {
        this.procesyNiegotowe = procesy;
        this.Sort();
    }

    public void Symulacja() {
        ArrayList<Proces> procesyGotowe = new ArrayList<>();
        procesyGotowe.add(procesyNiegotowe.get(0));
        int t = procesyGotowe.get(0).getAt();
        while(!procesyGotowe.isEmpty()){
            t+=procesyGotowe.get(0).getBt();
            procesyGotowe.get(0).setWt(t-procesyGotowe.get(0).getBt()-procesyGotowe.get(0).getAt());
            procesyWykonane.add(new Proces(procesyGotowe.get(0)));
            procesyGotowe.remove(0);
            if (!procesyNiegotowe.isEmpty()){
                for(int i=0; i<procesyNiegotowe.size(); i++){
                    if(procesyNiegotowe.get(i).getAt()<=t){
                        if(procesyNiegotowe.get(i).getWt()==-1) {
                            procesyGotowe.add(new Proces(procesyNiegotowe.get(i)));
                            procesyNiegotowe.get(i).setWt(0);
                        }
                    }

                }
                procesyGotowe.sort(new SJFPriorityQComparotor());
            }

        }
        int cwt =0;
        for (Proces p: procesyWykonane) {
            System.out.println(p + " wt: " + p.getWt());
            cwt+=p.getWt();
        }
        System.out.println("Całkowity czas oczekiwania: " + cwt);
        System.out.println("średni czas oczekiwania: " + (float)cwt/(float)procesyWykonane.size());


    }

    private void Sort() {
        procesyNiegotowe.sort(new FCFSComparator());
    }

}
