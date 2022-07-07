public class PrzydzialRowny {
    private int[][] ciagiOdwolan;
    private int liczbaRamek;

    public PrzydzialRowny(int[][] ciagiOdwolan, int liczbaRamek) {
        this.ciagiOdwolan = ciagiOdwolan;
        this.liczbaRamek = liczbaRamek;
    }

    public void Symulation(){
        this.Symulation(getLiczbaRamek(), getCiagiOdwolan());
    }

    public static void Symulation(int liczbaRamek, int[][] ciagiOdwolan){
        int[][] przydzialRamek = new int[ciagiOdwolan.length][liczbaRamek/(ciagiOdwolan.length)];       //ciagiOdwolan.length  = ilosc procesow
        int[] liczbaBledowStrony = new int[ciagiOdwolan.length];                                //[ilosc procesow]
        int[][] znacznikiCzasu = new int[ciagiOdwolan.length][przydzialRamek[0].length];        //[ilosc procesow][ilosc ramek w przydziale]
        for (int n=0; n< ciagiOdwolan[0].length; n++) {                         //dla każdego odwołania
            for (int i = 0; i < ciagiOdwolan.length; i++) {                 //dla każdego procesu
                boolean znajdujeSieWPamieci = false;
                for(int j=0; j<przydzialRamek[i].length; j++){              //sprawdza przydzielone procesowi ramki
                    if (przydzialRamek[i][j]==ciagiOdwolan[i][n]){          //sprawdza czy w przydziolonej ramce znajduje sie juz nastepne odwolanie tego procesu
                        znajdujeSieWPamieci = true;
                        break;
                    }
                }
                if(!znajdujeSieWPamieci){       //jeżeli strona nie jest już w ramce
                    for(int j=0; j<przydzialRamek[i].length; j++){
                        if (przydzialRamek[i][j]==0){           //jeżeli któraś z przydzielonych ramek jest pusta
                            przydzialRamek[i][j] = ciagiOdwolan[i][n];      //to w ramce umieszcza strone dla tego procesu
                            znacznikiCzasu[i][j] = n;           //znacznik czasu dla danej ramki z przeedzialu jest równy indexowi odwołania do strony
                            znajdujeSieWPamieci = true;
                            liczbaBledowStrony[i]++;    //łączna liczba błędów strony dla danego procesu
                            break;
                        }
                    }
                }
                if(!znajdujeSieWPamieci){       //jeżeli żadna z dostępnych ramek nie była pusta lub jeżeli strona już się nie znajdowywała w jednej z nich
                    int max = -10;
                    int maxIndex = -1;
                    for (int j=0; j<przydzialRamek[i].length; j++){    //szuka dla przydzielonych ramek ramkę z największym znacznikiem czasu
                        if(n-znacznikiCzasu[i][j]>max){
                            max = n - znacznikiCzasu[i][j];
                            maxIndex = j;
                        }
                    }
                    przydzialRamek[i][maxIndex] = ciagiOdwolan[i][n];   //umieszcza w stronę w ramce, która była najdłużej nieużywana
                    znacznikiCzasu[i][maxIndex] = n;
                    liczbaBledowStrony[i]++;
                    znajdujeSieWPamieci = true;
                }
            }
            String output = "";                                             //część wyświetlająca działanie programu
            if(n==0){                                                       //jeżeli program dopiero się zaczął to wyświetla nagłówek
                for (int i=0; i<ciagiOdwolan.length; i++){
                    output += "Proces " + (i+1);
                    for (int j=0; j<liczbaRamek/ciagiOdwolan.length; j++){
                        output += " ";
                    }
                }
                output +="\n";
            }
            for (int i=0; i<ciagiOdwolan.length; i++){                      //wyświetlanie aktualnego stanu ramek
                for (int j=0; j<przydzialRamek[i].length; j++){
                    output+= "|" + przydzialRamek[i][j];
                }
                output +="|";
                output +="-";
            }
            for (int i=0; i<ciagiOdwolan.length; i++){                      //wyświetlanie aktualnych odwołań
                output += "::" + ciagiOdwolan[i][n];
            }
            output += "::\n";
            if(n==ciagiOdwolan[0].length-1){                                                       //wyświetlenie liczby błedów strony pod koniec
                for (int i=0; i<ciagiOdwolan.length; i++){
                    output += "Page error " + (1+i) + " :" + liczbaBledowStrony[i] + "\n";
                }
                int sumaBledowStrony =0;
                for (int i=0; i<ciagiOdwolan.length; i++){
                    sumaBledowStrony =+ liczbaBledowStrony[i];
                }
                output+="\n Suma błędów strony wszystkich procesów: " + sumaBledowStrony;
            }
            System.out.println(output);
        }
    }

    public int[][] getCiagiOdwolan() {
        return ciagiOdwolan;
    }

    public void setCiagiOdwolan(int[][] ciagiOdwolan) {
        this.ciagiOdwolan = ciagiOdwolan;
    }

    public int getLiczbaRamek() {
        return liczbaRamek;
    }

    public void setLiczbaRamek(int liczbaRamek) {
        this.liczbaRamek = liczbaRamek;
    }
}
