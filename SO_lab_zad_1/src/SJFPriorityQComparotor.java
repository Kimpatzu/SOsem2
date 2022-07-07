import java.util.Comparator;

public class SJFPriorityQComparotor implements Comparator<Proces> {
    @Override
    public int compare(Proces p1, Proces p2) {
        return Integer.compare(p1.getBt(), p2.getBt());
    }
}
