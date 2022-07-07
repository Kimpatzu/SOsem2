public class Proces {
    private int bt;
    private int at;
    private int indeksProcesu;
    private int wt=-1;
    private int rt;

    public Proces(int bt, int at, int indeksProcesu) {
        this.bt = bt;
        this.at = at;
        this.indeksProcesu = indeksProcesu;
        this.rt=this.bt;
    }

    public Proces(Proces proces){
        this.at = proces.getAt();
        this.bt = proces.getBt();
        this.indeksProcesu = proces.getIndeksProcesu();
        this.wt = proces.getWt();
        this.rt=this.bt;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getWt() {
        return wt;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }

    public int getBt() {
        return bt;
    }

    public int getAt() {
        return at;
    }

    public void setBt(int bt) {
        this.bt = bt;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getIndeksProcesu() {
        return indeksProcesu;
    }

    public void setIndeksProcesu(int indeksProcesu) {
        this.indeksProcesu = indeksProcesu;
    }

    public String toString(){
        return "Nr. " + indeksProcesu + " at: "+ at + " bt: " + bt;
    }
}
