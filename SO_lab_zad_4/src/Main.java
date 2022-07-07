import java.util.Scanner;
import java.util.Random;

public class Main {

    public static int[][] generujCiagiOdwolan(int liczbaProcesow, int zakresStronNaProces, int liczbaOdowlań, int szansaLokalnosci){
        int[][] ciagiOdwolan = new int[liczbaProcesow][liczbaOdowlań];
        Random random = new Random();
        for (int i=0; i<ciagiOdwolan.length; i++){
            int odwolanie = random.nextInt(zakresStronNaProces)+1+(i*10);
            for(int j=0; j<ciagiOdwolan[i].length; j++){
                ciagiOdwolan[i][j] = odwolanie;
                int prawdopodobienstwoLokalnosci  = random.nextInt(99)+1;
                if(prawdopodobienstwoLokalnosci>szansaLokalnosci){
                    odwolanie = random.nextInt(zakresStronNaProces-1)+1+(i*10);
                } else {
                    boolean prawidloweOdwolanie = false;
                    while(!prawidloweOdwolanie){
                        int lokalnosc = random.nextInt(6) -3;
                        odwolanie = lokalnosc +  random.nextInt(zakresStronNaProces-1)+1+(i*10);
                        if(odwolanie>zakresStronNaProces*i && odwolanie<=zakresStronNaProces*(i+1)){
                            prawidloweOdwolanie = true;
                        }
                    }
                }
            }
        }
        return ciagiOdwolan;
    }
    public static void main(String[] args) {
        int liczbaProcesow = 10;
        int liczbaRamek = 45;
        int zakresStronNaProces = 10;
        int liczbaOdwolan = 100;
        int szansaLokalnosci = 95;          //95%
        int czestoscSprawdzaniaBledowStrony = 4;
        int dlugoscOknaCzasowego = 6;
        int[][] ciagiOdwolan = generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("1. Zastosuj przydział równy\n" +
                    "2. Zastosuj przydział proporcjonalny\n" +
                    "3. Zastosuj sterowanie częstością błędów strony\n" +
                    "4. Zastosuj model strefowy\n" +
                    "5. Zastosuj wszystkie 4 algorytmy na raz\n" +
                    "6. Wyswietl ciagi odwołań\n" +
                    "7. Wygeneruj ciagi odwołań\n" +
                    "8. Zmień liczbę procesów\n" +
                    "9. Zmień liczbę ramek\n" +
                    "10. Zmień zakres stron na proces\n" +
                    "11. Zmień liczbę odwołań do stron\n" +
                    "12. Zmień szansę lokalności\n" +
                    "13. Zmień częstość sprawdzania błedów strony\n" +
                    "14. Zmień długość okna czasowego\n" +
                    "15. Wyświetl aktualne ustawienia\n" +
                    "16. Zakończ działanie programu\n");
            int mainMenuChoice = scanner.nextInt();
            switch (mainMenuChoice){
                case 1:
                    PrzydzialRowny pr = new PrzydzialRowny(generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci), liczbaRamek);
                    pr.Symulation();
                    break;
                case 2:
                    break;
                case 3:
                    SterowanieCzestosciaBledowStrony scbs = new SterowanieCzestosciaBledowStrony(generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci),liczbaRamek,czestoscSprawdzaniaBledowStrony);
                    scbs.Symulation();
                    break;
                case 4:
                    ModelStrefowy ms = new ModelStrefowy(generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci), liczbaRamek, dlugoscOknaCzasowego);
                    ms.Symulation();
                    break;
                case 5:
                    System.out.println("Przydział równy: \n");
                    PrzydzialRowny pr2 = new PrzydzialRowny(generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci), liczbaRamek);
                    pr2.Symulation();
                    System.out.println("\nSterowanie częstością błędów strony: \n");
                    SterowanieCzestosciaBledowStrony scbs2 = new SterowanieCzestosciaBledowStrony(generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci),liczbaRamek,czestoscSprawdzaniaBledowStrony);
                    scbs2.Symulation();
                    System.out.println("\nModel Strefowy: \n");
                    ModelStrefowy ms2 = new ModelStrefowy(generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci), liczbaRamek, dlugoscOknaCzasowego);
                    ms2.Symulation();
                    break;
                case 6:
                    for (int i=0; i<ciagiOdwolan.length; i++){
                        System.out.println("Ciąg odwołań dla " + (i+1) + " procesu:");
                        for (int j=0; j<ciagiOdwolan[i].length; j++){
                            System.out.println("Nr. Odwołania: " + (j+1) + " Nr. Strony " + ciagiOdwolan[i][j]);
                        }
                    }
                    break;
                case 7:
                    ciagiOdwolan = generujCiagiOdwolan(liczbaProcesow,zakresStronNaProces,liczbaOdwolan,szansaLokalnosci);
                    System.out.println("Nowe ciągi odwołań zostały wygenrowane");
                    break;
                case 8:
                    System.out.println("Podaj nową liczbę procesów: ");
                    liczbaProcesow = scanner.nextInt();
                    break;
                case 9:
                    System.out.println("Podaj nową liczbę ramek: ");
                    liczbaRamek = scanner.nextInt();
                    break;
                case 10:
                    System.out.println("Podaj nowy zakres stron: ");
                    zakresStronNaProces = scanner.nextInt();
                    break;
                case 11:
                    System.out.println("Podaj nową liczbę odwołań: ");
                    liczbaOdwolan = scanner.nextInt();
                    break;
                case 12:
                    System.out.println("Podaj nową szansę lokalności: ");
                    szansaLokalnosci = scanner.nextInt();
                    break;
                case 13:
                    System.out.println("Podaj nową częstość sprawdzania błędów strony: ");
                    czestoscSprawdzaniaBledowStrony = scanner.nextInt();
                    break;
                case 14:
                    System.out.println("Podaj nową długość okna czasowego: ");
                    dlugoscOknaCzasowego = scanner.nextInt();
                    break;
                case 15:
                    System.out.println("Liczba procesów: " + liczbaProcesow);
                    System.out.println("Liczba ramek: " + liczbaRamek);
                    System.out.println("Zakres stron na proces: " + zakresStronNaProces);
                    System.out.println("Liczba odwołań: " + liczbaOdwolan);
                    System.out.println("Szansa lokalności: " + szansaLokalnosci + "(%)");
                    System.out.println("Częstość sprawdzania błędów strony: " + czestoscSprawdzaniaBledowStrony);
                    System.out.println("Długość okna czasowego: " + dlugoscOknaCzasowego);
                    break;
                case 16:
                    running = false;
                    break;
            }
        }
    }
}
