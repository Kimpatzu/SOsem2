public class LRU {
    private int[] ciagOdwolan;
    private int liczbaRamek;

    public LRU(int[] ciagOdwolan, int liczbaRamek) {
        this.ciagOdwolan = ciagOdwolan;
        this.liczbaRamek = liczbaRamek;
    }

    public int Symulacja(){
        int liczbaBledowStrony = 0;
        int[] ramki = new int[liczbaRamek];
        int[] znacznikiCzasu = new int[liczbaRamek];
        int i=0;
        while(i<ciagOdwolan.length){
            boolean znajdujeSieWPamieci = false;
            for (int j=0; j<ramki.length; j++){
                if (ramki[j]==ciagOdwolan[i]){
                    znajdujeSieWPamieci = true;
                    break;
                }
            }
            if (!znajdujeSieWPamieci) {
                for (int j=0;  j<ramki.length; j++){
                    if (ramki[j]==0){
                        ramki[j] = ciagOdwolan[i];
                        znacznikiCzasu[j] = i;
                        znajdujeSieWPamieci=true;
                        liczbaBledowStrony++;
                        break;
                    }
                }
            }
            if(!znajdujeSieWPamieci){
                int max = -10;
                int maxIndex = -1;
                for(int j=0; j<ramki.length ; j++){
                    if(i-znacznikiCzasu[j]>max){
                        max = i-znacznikiCzasu[j];
                        maxIndex = j;
                    }
                }
                ramki[maxIndex] = ciagOdwolan[i];
                znacznikiCzasu[maxIndex] = i;
                liczbaBledowStrony++;
                znajdujeSieWPamieci = true;
            }
            if(!znajdujeSieWPamieci){
                return -1;
            }
            i++;
        }
        return liczbaBledowStrony;
    }

    public int[] getCiagOdwolan() {
        return ciagOdwolan;
    }

    public void setCiagOdwolan(int[] ciagOdwolan) {
        this.ciagOdwolan = ciagOdwolan;
    }

    public int getLiczbaRamek() {
        return liczbaRamek;
    }

    public void setLiczbaRamek(int liczbaRamek) {
        this.liczbaRamek = liczbaRamek;
    }
}
