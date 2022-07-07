public class PrzydzialProporcjonalny {
    private int[][] ciagiOdwolan;
    private int liczbaRamek;

    public PrzydzialProporcjonalny(int[][] ciagiOdwolan, int liczbaRamek) {
        this.ciagiOdwolan = ciagiOdwolan;
        this.liczbaRamek = liczbaRamek;
    }

    public void Symulation(){
        this.Symulation(getLiczbaRamek(), getCiagiOdwolan());
    }

    public static void Symulation(int liczbaRamek, int[][] ciagiOdwolan){
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
