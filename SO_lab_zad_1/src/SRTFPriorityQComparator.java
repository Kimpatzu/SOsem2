import java.util.Comparator;

public class SRTFPriorityQComparator implements Comparator<Proces> {
    @Override
    public int compare(Proces p1, Proces p2) {
        return Integer.compare(p1.getRt(), p2.getRt());
    }
}
