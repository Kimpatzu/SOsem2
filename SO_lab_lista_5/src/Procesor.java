import java.util.ArrayList;

public class Procesor {
    public ArrayList<Proces> tasks;

    public Procesor() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Proces> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Proces> tasks) {
        this.tasks = tasks;
    }

    public int getCurrentCPUUsage() {
        if (tasks.isEmpty()){
            return 0;
        } else {
            int usage = 0;
            for (int i =0; i<tasks.size(); i++){
                usage += tasks.get(i).getCpuUsage();
            }
            return usage;
        }
    }

}
