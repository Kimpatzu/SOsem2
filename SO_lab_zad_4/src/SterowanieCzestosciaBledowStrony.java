public class SterowanieCzestosciaBledowStrony {
    private int[][] ciagiOdwolan;
    private int liczbaRamek;
    private int czestoscSprawdzaniaBledowStrony;

    public SterowanieCzestosciaBledowStrony(int[][] ciagiOdwolan, int liczbaRamek, int czestoscSprawdzaniaBledowStrony) {
        this.ciagiOdwolan = ciagiOdwolan;
        this.liczbaRamek = liczbaRamek;
        this.czestoscSprawdzaniaBledowStrony = czestoscSprawdzaniaBledowStrony;
    }

    public void Symulation(){
        this.Symulation(getLiczbaRamek(), getCiagiOdwolan(), getCzestoscSprawdzaniaBledowStrony());
    }

    public static void Symulation(int liczbaRamek, int[][] ciagiOdwolan, int czestoscSprawdzaniaBledowStrony){
        int[][] przydzialRamek = new int[ciagiOdwolan.length][liczbaRamek/(ciagiOdwolan.length)];       //ciagiOdwolan.length  = ilosc procesow , na początku ramki są przydzielane po równo
        int liczbaZajetychRamek = 0;
        for (int i=0; i<przydzialRamek.length; i++){
            liczbaZajetychRamek+=przydzialRamek[i].length;
        }
        int[][] znacznikiCzasu = new int[ciagiOdwolan.length][liczbaRamek/(ciagiOdwolan.length)];       //tabela znaczników czasów, tych samych rozmiarów co przydział ramek
        int[][] liczbaBledowStrony = new int[ciagiOdwolan.length][czestoscSprawdzaniaBledowStrony];
        int[] calkowitaLiczbaBledowStrony = new int[ciagiOdwolan.length];
        for (int n=0; n< ciagiOdwolan[0].length; n++){
            for (int i = 0; i < ciagiOdwolan.length; i++) {                 //dla każdego procesu
                boolean znajdujeSieWPamieci = false;
                for(int j=0; j<przydzialRamek[i].length; j++){              //sprawdza przydzielone procesowi ramki
                    if (przydzialRamek[i][j]==ciagiOdwolan[i][n]){          //sprawdza czy w przydziolonej ramce znajduje sie juz nastepne odwolanie tego procesu
                        znajdujeSieWPamieci = true;
                        liczbaBledowStrony[i][n%czestoscSprawdzaniaBledowStrony]=0; //nie pojawił się błąd strony
                        break;
                    }
                }
                if(!znajdujeSieWPamieci){       //jeżeli strona nie jest już w ramce
                    for(int j=0; j<przydzialRamek[i].length; j++){
                        if (przydzialRamek[i][j]==0){           //jeżeli któraś z przydzielonych ramek jest pusta
                            przydzialRamek[i][j] = ciagiOdwolan[i][n];      //to w ramce umieszcza strone dla tego procesu
                            znacznikiCzasu[i][j] = n;           //znacznik czasu dla danej ramki z przeedzialu jest równy indexowi odwołania do strony
                            znajdujeSieWPamieci = true;
                            liczbaBledowStrony[i][n%czestoscSprawdzaniaBledowStrony]=1;    //czy wystąpił w bład strony
                            calkowitaLiczbaBledowStrony[i]++;           //łączna liczba błędów strony dla danego procesu
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
                    liczbaBledowStrony[i][n%czestoscSprawdzaniaBledowStrony]=1;
                    calkowitaLiczbaBledowStrony[i]++;
                    znajdujeSieWPamieci = true;
                }
            }
            if(n>=czestoscSprawdzaniaBledowStrony-1){   //jeżeli nie jest to sam początek działania programu
                int[] tabelaIlosciOstatnichBledowStrony = new int[ciagiOdwolan.length];
                for(int i=0; i<tabelaIlosciOstatnichBledowStrony.length; i++){                  //obliczanie ilosci ostatnich błędów strony
                    for(int j=0; j<liczbaBledowStrony[i].length; j++){
                        tabelaIlosciOstatnichBledowStrony[i]+=liczbaBledowStrony[i][j];
                    }
                }
                for(int i=0; i<tabelaIlosciOstatnichBledowStrony.length; i++){
                    if(tabelaIlosciOstatnichBledowStrony[i]==0){                                //jeżeli ostatnio nie było błędu strony
                        przydzialRamek[i] = zmienLiczbeRamekProcesu(przydzialRamek[i], -1);         //to odbiera procesowy jedną ramkę
                        znacznikiCzasu[i] = zmienLiczbeRamekProcesu(znacznikiCzasu[i], -1);
                        liczbaZajetychRamek--;
                    }
                }
                for(int i=0; i<tabelaIlosciOstatnichBledowStrony.length; i++){
                    if(tabelaIlosciOstatnichBledowStrony[i]==czestoscSprawdzaniaBledowStrony){        //jeżeli błąd strony występuje za każdym razem
                        if(liczbaRamek-liczbaZajetychRamek>0){                                         //i jest co najmniej jedna wolna ramka
                            przydzialRamek[i] = zmienLiczbeRamekProcesu(przydzialRamek[i], 1);      //przydziela procesowi jedną ramkę więcej
                            znacznikiCzasu[i] = zmienLiczbeRamekProcesu(znacznikiCzasu[i], 1);
                            liczbaZajetychRamek++;
                        }
                    }
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
                    output += "Page error " + (1+i) + " :" + calkowitaLiczbaBledowStrony[i] + "\n";
                }
                int sumaBledowStrony =0;
                for (int i=0; i<ciagiOdwolan.length; i++){
                    sumaBledowStrony =+ calkowitaLiczbaBledowStrony[i];
                }
                output+="\n Suma błędów strony wszystkich procesów: " + sumaBledowStrony;
            }
            System.out.println(output);
            System.out.println(liczbaRamek-liczbaZajetychRamek);

        }
    }

    private static int[] zmienLiczbeRamekProcesu(int[] przydzialRamekProcesu, int wspolczynnikNowejLiczbyRamek){
        if(wspolczynnikNowejLiczbyRamek ==-1){
            int[] nowaTabRamek = new int[przydzialRamekProcesu.length-1];
            for(int i=0; i<nowaTabRamek.length; i++){
                nowaTabRamek[i] = przydzialRamekProcesu[i];
            }
            return nowaTabRamek;
        } else {
            int[] nowaTabRamek = new int[przydzialRamekProcesu.length+1];
            for(int i=0; i<przydzialRamekProcesu.length; i++){
                nowaTabRamek[i] = przydzialRamekProcesu[i];
            }
            return nowaTabRamek;
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

    public int getCzestoscSprawdzaniaBledowStrony() {
        return czestoscSprawdzaniaBledowStrony;
    }

    public void setCzestoscSprawdzaniaBledowStrony(int czestoscSprawdzaniaBledowStrony) {
        this.czestoscSprawdzaniaBledowStrony = czestoscSprawdzaniaBledowStrony;
    }
}
