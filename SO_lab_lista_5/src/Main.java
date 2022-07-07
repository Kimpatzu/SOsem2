import java.util.Scanner;
import java.util.Random;

public class Main {

    public static Proces[] generateIncomingTasks(int liczbaProcesow, int wspolczynnikCzestotliwosci, int wspolczynnikCPUUsage,int wspolczynnikTaskLenght){
        Proces[] tasks = new Proces[liczbaProcesow];
        Random random = new Random();
        int currentAt = 1;
        int currentCPUUsage = 0;
        int currentTaskLenght = 0;
        int currentCPUUsageRandomFactor= 0;
        int currentAtRandomFactor = 0;
        int currentTaskLenghtRandomFactor = 0;
        for(int i=0; i<tasks.length; i++){
            currentCPUUsageRandomFactor = random.nextInt(4)-2;
            currentCPUUsage = wspolczynnikCPUUsage + currentCPUUsageRandomFactor;        //cpu usage ma wartość oscylującą -1, 0, +1 wokół wartości współczynnika
            currentAtRandomFactor = random.nextInt(2)-1;
            currentAt += wspolczynnikCzestotliwosci + currentAtRandomFactor;    //currentAt może mieć jedynie wartości wyższe niż wcześniej, zależne od współczynnika AT
            currentTaskLenghtRandomFactor = random.nextInt(wspolczynnikTaskLenght/4)-wspolczynnikTaskLenght/8;
            currentTaskLenght = wspolczynnikTaskLenght + currentTaskLenghtRandomFactor;  // tak jak z cpu usage
            tasks[i] = new Proces(currentAt, currentCPUUsage, currentTaskLenght);
        }
        return tasks;
    }
    public static Proces[] copyTasks(Proces[] tasks){
        Proces[] copy = new Proces[tasks.length];
        for (int i=0; i<tasks.length;i++){
            copy[i]=tasks[i];
        }
        return copy;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int liczbaProcesorowN = 10;
        int liczbaProcesow = 500;
        int wspolczynnikCzestotliwosci = 2;
        int wspolczynnikCPUUsage = 7;
        int wspolczynnikTaskLenght = 100;
        int maxProgP = 40;
        int minProgR = 10;
        int maxLiczbaZapytanZ = 50;
        Proces[] tasks = generateIncomingTasks(liczbaProcesow,wspolczynnikCzestotliwosci,wspolczynnikCPUUsage,wspolczynnikTaskLenght);
        boolean running = true;
        while (running){
            System.out.println("1. Zastosuj pierwszą strategię\n" +
                    "2. Zastosuj drugą strategię\n" +
                    "3. Zastosuj trzecią strategię\n" +
                    "4. Wyświetl ciąg przychodzących procesów\n" +
                    "5. Wyświetl aktualne ustawienia\n" +
                    "6. Wyświetl aktualny ciąg procesów\n" +
                    "7. Wygeneruj nowy ciąg procesów\n" +
                    "8. Zmień liczbę procesorów\n" +
                    "9. Zmień liczbę procesów\n" +
                    "10. Zmień współczynnik częstotliwości\n" +
                    "11. Zmień współczynnik wykorzystania procesora przez procesy\n" +
                    "12. Zmień współczynnik czasu trwania procesów\n" +
                    "13. Zmień wartość maksymalnego progu\n" +
                    "14. Zmień wartość minimalnego progu\n" +
                    "15. Zmień maksymalną liczbę zapytań\n" +
                    "16. Zakończ program\n");
            int mainMenuChoice = scanner.nextInt();
            switch(mainMenuChoice){
                case 1:
                    StrategyOne strategyOne = new StrategyOne(generateIncomingTasks(liczbaProcesow,wspolczynnikCzestotliwosci,wspolczynnikCPUUsage,wspolczynnikTaskLenght),liczbaProcesorowN,maxProgP,maxLiczbaZapytanZ);
                    System.out.println(strategyOne.Symulation());
                    break;
                case 2:
                    StrategyTwo strategyTwo = new StrategyTwo(generateIncomingTasks(liczbaProcesow,wspolczynnikCzestotliwosci,wspolczynnikCPUUsage,wspolczynnikTaskLenght),liczbaProcesorowN,maxProgP);
                    System.out.println(strategyTwo.Symulation());
                    break;
                case 3:
                    StrategyThree strategyThree = new StrategyThree(generateIncomingTasks(liczbaProcesow,wspolczynnikCzestotliwosci,wspolczynnikCPUUsage,wspolczynnikTaskLenght),liczbaProcesorowN,maxProgP,minProgR);
                    System.out.println(strategyThree.Symulation());
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("\nAktualne ustawienia:\n");
                    System.out.println("Liczba procesorów: " + liczbaProcesorowN);
                    System.out.println("Liczba procesów: " + liczbaProcesow);
                    System.out.println("Współczynnik częstotliwości: " + wspolczynnikCzestotliwosci);
                    System.out.println("Współczynnik wykorzystania procesora przez procesy: " + wspolczynnikCPUUsage);
                    System.out.println("Współczynnik czasu trwania procesów: " + wspolczynnikTaskLenght);
                    System.out.println("Maksymalny próg obciążenia p: " + maxProgP);
                    System.out.println("Minimalny próg obciążenia r: " + minProgR);
                    System.out.println("Maksymalna liczba zapytań: " + maxLiczbaZapytanZ);
                    break;
                case 6:
                    System.out.println("\nCiąg procesów:\n");
                    for (int i =0 ; i< tasks.length; i++){
                        System.out.println("At: " +  tasks[i].getArrivalTime() + " CPUUsage: " + tasks[i].getCpuUsage() + "% TaskLenght: " + tasks[i].getWorkTime());
                    }
                    break;
                case 7:
                    tasks = generateIncomingTasks(liczbaProcesow,wspolczynnikCzestotliwosci,wspolczynnikCPUUsage,wspolczynnikTaskLenght);
                    break;
                case 8:
                    System.out.println("Podaj liczbę procesorów N: ");
                    liczbaProcesorowN = scanner.nextInt();
                    break;
                case 9:
                    System.out.println("Podaj liczbę procesów: ");
                    liczbaProcesow = scanner.nextInt();
                    break;
                case 10:
                    System.out.println("Podaj współczynnik częstotliwości: ");
                    wspolczynnikCzestotliwosci = scanner.nextInt();
                    break;
                case 11:
                    System.out.println("Podaj współczynnik wykorzystania procesora przez procesy");
                    wspolczynnikCPUUsage = scanner.nextInt();
                    break;
                case 12:
                    System.out.println("Podaj współczynnik czasu trwania procesów");
                    wspolczynnikTaskLenght = scanner.nextInt();
                    break;
                case 13:
                    System.out.println("Podaj wartość progu maksymalnego p: ");
                    maxProgP = scanner.nextInt();
                    break;
                case 14:
                    System.out.println("Podaj wartość progu minimalnego r: ");
                    minProgR = scanner.nextInt();
                    break;
                case 15:
                    System.out.println("Podaj maksymalną liczbę zapytań z: ");
                    maxLiczbaZapytanZ = scanner.nextInt();
                    break;
                case 16:
                    running = false;
                    break;
            }
        }
    }
}
