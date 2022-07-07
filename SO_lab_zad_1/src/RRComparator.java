import java.util.Comparator;

public class RRComparator implements Comparator<Proces> {

    @Override
    public int compare(Proces p1, Proces p2) {
        boolean p1wywlaszczony = !(p1.getBt()==p1.getRt());
        boolean p2wywlaszczony = !(p2.getBt()==p2.getRt());
        if (p1wywlaszczony){
            if(p2wywlaszczony){
                return Integer.compare(p1.getAt(), p2.getAt());
            } else {
                return 1;
            }
        } else if (p2wywlaszczony){
            return -1;
        } else {
            return Integer.compare(p1.getAt(), p2.getAt());
        }
    }
}
