import java.util.ArrayList;
import java.util.List;

public class TaskManager {


    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if(index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void updateTaskDescription(int index, String newDescription) {
        if(index >= 0 && index < tasks.size()) {
            tasks.get(index).setDescription(newDescription);
        }
    }

    public void setTaskCompleted(int index, boolean completed) {
        if(index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(completed);
        }
    }
}
