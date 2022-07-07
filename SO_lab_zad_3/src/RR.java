import java.util.Random;

public class RR {
    private int[] ciagOdwolan;
    private int liczbaRamek;

    public RR(int[] ciagOdwolan, int liczbaRamek) {
        this.ciagOdwolan = ciagOdwolan;
        this.liczbaRamek = liczbaRamek;
    }

    public int Symulacja(){
        int liczbaBledowStrony = 0;
        int[] ramki = new int[liczbaRamek];
        int i=0;
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
            if(!znajdujeSieWPamieci){
                Random random = new Random();
                ramki[random.nextInt(ramki.length)] = ciagOdwolan[i];
                liczbaBledowStrony++;
                znajdujeSieWPamieci= true;
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
