public class OPT {
    private int[] ciagOdwolan;
    private int liczbaRamek;
    private int zakresNumerowStron;

    public OPT(int[] ciagOdwolan, int liczbaRamek, int zakresNumerowStron) {
        this.ciagOdwolan = ciagOdwolan;
        this.liczbaRamek = liczbaRamek;
        this.zakresNumerowStron = zakresNumerowStron;
    }

    public int Symulacja(){
        int liczbaBledowStrony = 0;
        int[] ramki = new int[liczbaRamek];
        int i=0;
        int j=0;
        while(i<ciagOdwolan.length){
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
            if (!znajdujeSieWPamieci){
                int maxOdleglosc = 0;
                int maxNrRamki = 0;
                for (int k=0; k<ramki.length; k++){
                    int l=0;
                    while(l+i< ciagOdwolan.length){
                        if (ciagOdwolan[l+i]==ramki[k]){
                            if(maxOdleglosc < l){
                                l = maxOdleglosc;
                                maxNrRamki = k;
                            }
                            break;
                        }
                        l++;
                    }
                }
                ramki[maxNrRamki] = ciagOdwolan[i];
                liczbaBledowStrony++;
                i++;
                znajdujeSieWPamieci = true;
            }
            if(!znajdujeSieWPamieci){
                ramki[j] = ciagOdwolan[i];
                znajdujeSieWPamieci =true;
                liczbaBledowStrony++;
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

    public int getZakresNumerowStron() {
        return zakresNumerowStron;
    }

    public void setZakresNumerowStron(int zakresNumerowStron) {
        this.zakresNumerowStron = zakresNumerowStron;
    }
}
