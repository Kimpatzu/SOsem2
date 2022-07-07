import java.util.Comparator;

public class FCFSComparator implements Comparator<Proces> {
    @Override
    public int compare(Proces p1, Proces p2) {
        return Integer.compare(p1.getAt(), p2.getAt());
    }
}
