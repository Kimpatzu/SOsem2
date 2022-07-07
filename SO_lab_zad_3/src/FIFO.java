public class FIFO {
    private int[] ciagOdwolan;
    private int liczbaRamek;

    public FIFO(int[] ciagOdwolan, int liczbaRamek) {
        this.ciagOdwolan = ciagOdwolan;
        this.liczbaRamek = liczbaRamek;
    }

    public int Symulacja(){
        int liczbaBledowStrony = 0;
        int[] ramki = new int[liczbaRamek];
        int i=0;
        int j=0;
        while (i<ciagOdwolan.length) {
            boolean znajdujeSieWPamieci = false;
            for (int k=0; k<ramki.length; k++){
                if(ciagOdwolan[i]==ramki[k]){
                    znajdujeSieWPamieci = true;
                    break;
                }
            }
            if(!znajdujeSieWPamieci){
                for (int k=0; k<ramki.length; k++){
                    if(ramki[k]==0){
                        znajdujeSieWPamieci = true;
                        ramki[k]=ciagOdwolan[i];
                        liczbaBledowStrony++;
                        break;
                    }
                }
            }
            if(!znajdujeSieWPamieci){
                ramki[j] = ciagOdwolan[i];
                liczbaBledowStrony++;
                znajdujeSieWPamieci = true;
            }
            if(!znajdujeSieWPamieci){
                return -1;
            }
            i++;
            j++;
            if (j==ramki.length){
                j=0;
            }
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
