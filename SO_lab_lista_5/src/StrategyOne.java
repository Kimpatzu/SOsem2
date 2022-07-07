import java.util.ArrayList;
import java.util.Random;

public class StrategyOne {
    private Procesor[] procesors;
    private Proces[] incomingTasks;
    private int maxProgP;
    private int maxLZ;

    public StrategyOne(Proces[] incomingTasks, int liczbaProcesorow, int maxProgP, int maxLZ) {
        this.procesors = procesorsSetup(liczbaProcesorow);
        this.incomingTasks = incomingTasks;
        this.maxProgP = maxProgP;
        this.maxLZ = maxLZ;
    }

    public String Symulation(){
        String output = "";
        Random random = new Random();
        int zapytaniaCounter = 0;
        int migracjeCounter = 0;
        int t = 0;
        int i = 0;
        float sumOfAverageCPUUsage =0;
        float sumOfAverageCPUUsageDiff = 0;
        while(i<incomingTasks.length){
            output+= incomingTasks[i].getArrivalTime() + " " + i + " " + t + " ";
            if(incomingTasks[i].getArrivalTime() < t){    //jeżeli przyszedł proces
                migracjeCounter ++;
                boolean assigned = false;
                int numberofTries = 0;
                int arrivalCoreNumber = random.nextInt(procesors.length-1);
                while(!assigned && numberofTries<maxLZ){       //jeżeli proces nie został umieszczony lub nie przekroczył liczbę prób
                    int randomeCoreNumber = arrivalCoreNumber;
                    while(randomeCoreNumber==arrivalCoreNumber){    //upewnienie się, że procesor nie będzie pytał samego siebie
                        randomeCoreNumber = random.nextInt(procesors.length-1);
                    }
                    if (procesors[randomeCoreNumber].getCurrentCPUUsage()<maxProgP){    //jeżeli obciążenie jest mniejsze niż maxProgP
                       procesors[randomeCoreNumber].tasks.add(incomingTasks[i]);        //Procesor przyjmuje proces
                       assigned = true;
                       numberofTries++;
                       migracjeCounter++;
                       i++;
                    } else {
                        numberofTries++;
                    }
                }
                if(!assigned){      //jeżeli nie udało się znaleźć procesora z obciążeniem poniżej progu, to przyjmij proces na prcoesorzem na którym proces się pojawił
                    procesors[arrivalCoreNumber].tasks.add(incomingTasks[i]);
                    i++;
                }
                zapytaniaCounter += numberofTries;
            }
            int sumCPUUsage = 0;
            int[] currentCPUUsages = new int[procesors.length];
            for (int j=0; j<procesors.length; j++){     //przegląda wszystkie procesory
                currentCPUUsages[j] = procesors[j].getCurrentCPUUsage();     //zapamiętuje obecne obciążenia na procesorach
                sumCPUUsage += procesors[j].getCurrentCPUUsage();    //podlicza obecne zużycie procesorów
                if(!procesors[j].tasks.isEmpty()){      //jeżeli jakiś procesor ma nasobie proces
                    for(int k=0; k<procesors[j].tasks.size(); k++){     //przegląda wszystkie procesy procesora
                        procesors[j].tasks.get(k).setWorkTime(procesors[j].tasks.get(k).getWorkTime()-1);   //zmniejsza workTime - pozostały czas wykonywania procesu o 1 dla wszystkich procesów na wszystki procesorach
                        if(procesors[j].tasks.get(k).getWorkTime()==0){     //jeżeli na proceserze j, proces k ma 0 czasu do zakonczenia, to proces jest usuwany z procesora
                            procesors[j].tasks.remove(k);
                        }
                    }

                }
            }
            float averageCPUUsage = (float) sumCPUUsage/procesors.length;    //średnia zużycia procesora
            sumOfAverageCPUUsage += averageCPUUsage;    //podlicza sumę średnich zużyć procesora na podrzeby wyświetlenia na sam koniec
            float sumofCPUUsageDiff = 0;
            for (int j=0; j<procesors.length; j++){     //dla każdego procesora
                if (averageCPUUsage>currentCPUUsages[j]) {          //poodlicza wartość bezwględną z różnicy między średnim obciążeniem a obciążeniem na danym procesorze
                    sumofCPUUsageDiff += averageCPUUsage - currentCPUUsages[j];
                } else {
                    sumofCPUUsageDiff += currentCPUUsages[j] - averageCPUUsage;
                }
            }
            float averageCPUUsageDiff = (float) sumofCPUUsageDiff/procesors.length;
            sumOfAverageCPUUsageDiff = averageCPUUsageDiff;
            output += "t: " + t + " średnie obciążenie: " + averageCPUUsage + " średnie odchylenie: " + averageCPUUsageDiff + "\n";
            t++;
        }
        output += "\nPODSUMOWANIE\nWartość jednostki czasu: " + t + " średnie obciążenie procesorów w całym czasie działania: " + sumOfAverageCPUUsage/t + " średnie odchylenie w całym czasie działania: " + sumOfAverageCPUUsageDiff/t;
        output += "\nIlość zapytań: " + zapytaniaCounter + " Ilość migracji: " + migracjeCounter;
        return output;
    }

    public Procesor[] procesorsSetup(int liczbaProcesorow){
        Procesor[] procesors = new Procesor[liczbaProcesorow];
        for(int i=0; i<procesors.length; i++){
            procesors[i]= new Procesor();
        }
        return procesors;
    }
}
