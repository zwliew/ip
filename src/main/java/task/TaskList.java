package task;

import misc.PeepoException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int idx) throws PeepoException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new PeepoException("The task number is out of range.");
        }
        return tasks.get(idx);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task remove(int idx) throws PeepoException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new PeepoException("The task number is out of range.");
        }
        return tasks.remove(idx);
    }

    public Task markAsDone(int idx) throws PeepoException {
        final var task = get(idx);
        task.markAsDone();
        return task;
    }

    public Task markAsUndone(int idx) throws PeepoException {
        final var task = get(idx);
        task.markAsUndone();
        return task;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < tasks.size();
            }

            @Override
            public Task next() {
                return tasks.get(idx++);
            }
        };
    }

    public List<Task> find(String term) {
        return tasks.stream().filter(task -> task.description.contains(term)).collect(Collectors.toList());
    }
}
