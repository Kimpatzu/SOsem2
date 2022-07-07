import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int[] generujCiagOdwolan(int liczbaOdwolan, int wspolczynnikLokalnosci, int zakresNumerowStron){
        int[] ciagOdwolan = new int[liczbaOdwolan];
        Random random = new Random();
        int odwolanie = random.nextInt(zakresNumerowStron);
        for (int i=0; i<ciagOdwolan.length; i++){
            ciagOdwolan[i] = odwolanie;
            if (wspolczynnikLokalnosci>=zakresNumerowStron){
                odwolanie = random.nextInt(zakresNumerowStron-1)+1;
            } else if (odwolanie-wspolczynnikLokalnosci<=0){
                odwolanie = random.nextInt(odwolanie+wspolczynnikLokalnosci-1) + 1;
            } else if (odwolanie+wspolczynnikLokalnosci>=zakresNumerowStron){
                odwolanie = random.nextInt(zakresNumerowStron-1-odwolanie+wspolczynnikLokalnosci) + odwolanie-wspolczynnikLokalnosci;
            } else {
                odwolanie = random.nextInt(2*wspolczynnikLokalnosci) + odwolanie-wspolczynnikLokalnosci;
            }
        }
        return ciagOdwolan;
    }
    public static void main(String[] args) {
        int liczbaRamek=5;
        int liczbaOdwolan=10000;
        int zakresNumerowStron = 100;
        int wspolczynnikLokalnosci = 10;
        int[] ciagOdwolan = generujCiagOdwolan(liczbaOdwolan,wspolczynnikLokalnosci,zakresNumerowStron);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("1.  Zastosuj algorytm FIFO\n" +
                    "2.  Zastosuj algorytm OPT\n" +
                    "3.  Zastosuj algorytm LRU\n" +
                    "4.  Zastosuj aproksymowanu LRU\n" +
                    "5.  Zastosuj algorytm RAND\n" +
                    "6.  Wygeneruj nowy ciąg odwołań\n" +
                    "7.  Wyświetl ciąg odwołań\n" +
                    "8.  Zmień liczbę ramek\n" +
                    "9.  Zmień liczbę odwołań do stron\n" +
                    "10. Zmień zakres numerów stron\n" +
                    "11. Zmień współczynnik lokalności odwołań\n" +
                    "12. Zakończ działanie programu\n" +
                    "13. Zastosuj wszystkie algorytmy na raz\n");
            int wyborMainMenu = scanner.nextInt();
            switch (wyborMainMenu) {
                case 1:
                    FIFO fifo = new FIFO(ciagOdwolan,liczbaRamek);
                    System.out.println("Liczba błędów stron FIFO: " + fifo.Symulacja());
                    break;
                case 2:
                    OPT opt = new OPT(ciagOdwolan,liczbaRamek,zakresNumerowStron);
                    System.out.println("Liczba błedów stron OPT: " + opt.Symulacja());
                    break;
                case 3:
                    LRU lru = new LRU(ciagOdwolan,liczbaRamek);
                    System.out.println("Liczba błędów stron LRU: " + lru.Symulacja());
                    break;
                case 4:
                    break;
                case 5:
                    RR rr = new RR(ciagOdwolan, liczbaRamek);
                    System.out.println("Liczba błedow stron RR: " + rr.Symulacja());
                    break;
                case 6:
                    ciagOdwolan = generujCiagOdwolan(liczbaOdwolan, wspolczynnikLokalnosci, zakresNumerowStron);
                    System.out.println("Nowy ciąg odwołań został wygenorowany");
                    break;
                case 7:
                    for (int i = 0; i < ciagOdwolan.length; i++) {
                        System.out.println("Nr. Odwołania: " + (i + 1) + "  Nr. strony: " + ciagOdwolan[i]);
                    }
                    break;
                case 8:
                    System.out.println("Podaj nową liczbę ramek: ");
                    liczbaRamek = scanner.nextInt();
                    break;
                case 9:
                    System.out.println("Podaj nową liczbę odwołań: ");
                    liczbaOdwolan = scanner.nextInt();
                    break;
                case 10:
                    System.out.println("Podaj nowy zakres numerów stron");
                    zakresNumerowStron = scanner.nextInt();
                    break;
                case 11:
                    System.out.println("Podaj nowy zakres numerów stron: ");
                    wspolczynnikLokalnosci = scanner.nextInt();
                    break;
                case 12:
                    running = false;
                    break;
                case 13:
                    FIFO fifo1 = new FIFO(ciagOdwolan,liczbaRamek);
                    System.out.println("Liczba błędów stron FIFO: " + fifo1.Symulacja());
                    OPT opt1 = new OPT(ciagOdwolan,liczbaRamek,zakresNumerowStron);
                    System.out.println("Liczba błedów stron OPT: " + opt1.Symulacja());
                    LRU lru1 = new LRU(ciagOdwolan,liczbaRamek);
                    System.out.println("Liczba błędów stron LRU: " + lru1.Symulacja());
                    RR rr1 = new RR(ciagOdwolan, liczbaRamek);
                    System.out.println("Liczba błedow stron RR: " + rr1.Symulacja());
            }
        }
    }
}
