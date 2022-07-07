import java.util.ArrayList;

public class SRTF {
    private ArrayList<Proces> procesyNiegotowe;
    private ArrayList<Proces> procesyWykonane = new ArrayList<>();
    private int kwantCzasu;

    public SRTF(ArrayList<Proces> proces,int kwant){
        this.procesyNiegotowe = proces;
        this.kwantCzasu = kwant;
        this.Sort();
    }

    public void Symulacja(){
        ArrayList<Proces> procesyGotowe = new ArrayList<>();
        procesyGotowe.add(procesyNiegotowe.get(0));
        procesyNiegotowe.get(0).setWt(0);
        int t = procesyGotowe.get(0).getAt();
        while(!procesyGotowe.isEmpty()){
            if (procesyGotowe.get(0).getRt()>kwantCzasu){
                procesyGotowe.get(0).setRt(procesyGotowe.get(0).getRt()-kwantCzasu);
                t+=kwantCzasu;
            } else if (procesyGotowe.get(0).getRt()<=kwantCzasu){
                t+=procesyGotowe.get(0).getRt();
                procesyGotowe.get(0).setWt(t-procesyGotowe.get(0).getBt()-procesyGotowe.get(0).getAt());
                procesyWykonane.add(new Proces(procesyGotowe.get(0)));
                procesyGotowe.remove(0);
            }
            if (!procesyNiegotowe.isEmpty()){
                for(int i=0; i<procesyNiegotowe.size(); i++){
                    if(procesyNiegotowe.get(i).getAt()<=t){
                        if(procesyNiegotowe.get(i).getWt()==-1) {
                            procesyGotowe.add(new Proces(procesyNiegotowe.get(i)));
                            procesyNiegotowe.get(i).setWt(0);
                        }
                    }

                }
                procesyGotowe.sort(new SRTFPriorityQComparator());
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

    private void Sort(){
        procesyNiegotowe.sort(new FCFSComparator());
    }
}
