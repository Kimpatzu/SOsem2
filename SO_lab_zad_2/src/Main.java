import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static Request[] generateRequests(int trackNumber, int requestNumber, int averageBurstTime, int deadlineFactor, int maxApperanceTimeDiff){
        Request[] requests = new Request[requestNumber];
        Random random = new Random();
        int lastArrivalTime = random.nextInt((3*maxApperanceTimeDiff)/2-1)+1;
        int lastBurstTime = random.nextInt(averageBurstTime) + averageBurstTime/2;
        for (int i=0; i< requests.length; i++){
            requests[i] = new Request(random.nextInt(trackNumber-1)+1,lastArrivalTime,lastBurstTime,deadlineFactor);
            lastArrivalTime = random.nextInt(maxApperanceTimeDiff-1) + lastArrivalTime + 1;
            lastBurstTime = random.nextInt(averageBurstTime-1) + 1 + averageBurstTime/2;
        }
        return requests;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int trackNumber = 100;
        int requestNumber = 1500;
        int averageBurstTime = 20;
        int deadlineFactor = 10;
        int maxApperanceTimeDiff = 10;
        Request[] requests = generateRequests(trackNumber,requestNumber,averageBurstTime,deadlineFactor,maxApperanceTimeDiff);
        boolean running = true;
        while (running){
            System.out.println("1. Zastosuj Algorytmy\n" +
                    "2.  Wyświetl wygenerowane żądania\n" +
                    "3.  Wygeneruj żądania\n" +
                    "4.  Ustaw liczbę bloków dysku\n" +
                    "5. Ustaw liczbę żądań" +
                    "6. Ustaw średni czas wykonania żądania\n" +
                    "7. Ustaw średni czas pomiędzy przybyciem kolejnych żądań\n" +
                    "8. Ustaw współczynnik średniego czasu deadline'u\n" +
                    "9. Zakończ działanie programu");
            int menuChoice = scanner.nextInt();
            switch (menuChoice){
                case 1:
                    FCFS fcfs = new FCFS(requests);
                    System.out.println("FCFS Liczba skoków: " + fcfs.Symulacja());
                    SSTF sstf = new SSTF(requests);
                    System.out.println("SSTF Liczba skoków: " + sstf.Symulacja());
                    break;
                case 2:
                    for (int i=0; i<requests.length; i++){
                        System.out.println(requests[i]);
                    }
                    break;
                case 3:
                    requests = generateRequests(trackNumber,requestNumber,averageBurstTime,deadlineFactor,maxApperanceTimeDiff);
                    break;
                case 4:
                    trackNumber = scanner.nextInt();
                    break;
                case 5:
                    requestNumber = scanner.nextInt();
                    break;
                case 6:
                    averageBurstTime = scanner.nextInt();
                    break;
                case 7:
                    maxApperanceTimeDiff = scanner.nextInt();
                case 8:
                    deadlineFactor = scanner.nextInt();
                    break;
                case 9:
                    running = false;
                    break;
            }
        }
    }
}
