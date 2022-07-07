import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ModelStrefowy {
    private int[][] ciagiOdwolan;
    private int liczbaRamek;
    private int dlugoscOknaCzasowego;

    public ModelStrefowy(int[][] ciagiOdwolan, int liczbaRamek, int dlugoscOknaCzasowego) {
        this.ciagiOdwolan = ciagiOdwolan;
        this.liczbaRamek = liczbaRamek;
        this.dlugoscOknaCzasowego = dlugoscOknaCzasowego;
    }

    public void Symulation(){
        this.Symulation(getLiczbaRamek(), getCiagiOdwolan(), getDlugoscOknaCzasowego());
    }

    public static void Symulation(int liczbaRamek, int[][] ciagiOdwolan, int dlugoscOknaCzasowego){
        int[][] przydzialRamek = new int[ciagiOdwolan.length][liczbaRamek/(ciagiOdwolan.length)];       //ciagiOdwolan.length  = ilosc procesow , na początku ramki są przydzielane po równo
        int liczbaZajetychRamek = 0;
        for (int i=0; i<przydzialRamek.length; i++){
            liczbaZajetychRamek+=przydzialRamek[i].length;
        }
        int[][] znacznikiCzasu = new int[ciagiOdwolan.length][liczbaRamek/(ciagiOdwolan.length)];       //tabela znaczników czasów, tych samych rozmiarów co przydział ramek
        int[] calkowitaLiczbaBledowStrony = new int[ciagiOdwolan.length];
        int[][] ciagOdwolanWOknieCzasowym = new int[ciagiOdwolan.length][dlugoscOknaCzasowego];
        for (int n=0; n< ciagiOdwolan[0].length; n++){
            for (int i = 0; i < ciagiOdwolan.length; i++) {                 //dla każdego procesu
                ciagOdwolanWOknieCzasowym[i][n%dlugoscOknaCzasowego] = ciagiOdwolan[i][n];
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
                    calkowitaLiczbaBledowStrony[i]++;
                    znajdujeSieWPamieci = true;
                }
            }
            if (n>=dlugoscOknaCzasowego-1){             //jeżeli nie jest to sam początek działania programu
                LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(); //hashSet do stworzenia zbioru roboczego
                ArrayList<Integer> zbiorRoboczy = new ArrayList<>();
                for (int i=0; i<ciagiOdwolan.length; i++){      //dla każdego z procesów
                    for (int j=0; j<ciagOdwolanWOknieCzasowym[i].length; j++){
                        if (hashSet.add(ciagOdwolanWOknieCzasowym[i][j])){
                            zbiorRoboczy.add(ciagOdwolanWOknieCzasowym[i][j]);
                        }
                    }
                }
                for (int i=0; i<ciagiOdwolan.length; i++) {
                    if (zbiorRoboczy.size() < przydzialRamek[i].length){            //jeżeli zbiór roboczy jest mniejszy niż aktualna liczba ramek procesu
                        przydzialRamek[i] = zmienLiczbeRamekProcesu(przydzialRamek[i], przydzialRamek[i].length-zbiorRoboczy.size());       //zabierz procesowi ramki i daj je do puli wolnych ramek
                        znacznikiCzasu[i] = zmienLiczbeRamekProcesu(znacznikiCzasu[i], przydzialRamek[i].length-zbiorRoboczy.size());
                        liczbaZajetychRamek-=przydzialRamek[i].length-zbiorRoboczy.size();
                    } else if(zbiorRoboczy.size() > przydzialRamek[i].length){     //jeżeli zbiór roboczy jest większy niż aktualna liczba ramek
                        if(zbiorRoboczy.size()-przydzialRamek[i].length<=liczbaRamek-liczbaZajetychRamek){
                            przydzialRamek[i] = zmienLiczbeRamekProcesu(przydzialRamek[i], zbiorRoboczy.size()-przydzialRamek[i].length);
                            znacznikiCzasu[i] = zmienLiczbeRamekProcesu(znacznikiCzasu[i], zbiorRoboczy.size()-przydzialRamek[i].length);
                            liczbaZajetychRamek+=zbiorRoboczy.size()-przydzialRamek[i].length;
                        } else if(liczbaRamek-liczbaZajetychRamek>0){
                            przydzialRamek[i] = zmienLiczbeRamekProcesu(przydzialRamek[i], liczbaRamek-liczbaZajetychRamek);
                            znacznikiCzasu[i] = zmienLiczbeRamekProcesu(znacznikiCzasu[i], liczbaRamek-liczbaZajetychRamek);
                            liczbaZajetychRamek+=liczbaRamek-liczbaZajetychRamek;
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

    public int getDlugoscOknaCzasowego() {
        return dlugoscOknaCzasowego;
    }

    public void setDlugoscOknaCzasowego(int dlugoscOknaCzasowego) {
        this.dlugoscOknaCzasowego = dlugoscOknaCzasowego;
    }
}
