package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TaskPlanner {
	private List<Task> tasks;
	private static final String FILE_NAME = "tasks.dat";

	public TaskPlanner() {
		this.tasks = loadTasks();
	}

	public void addTask(Task task) {
		tasks.add(task);
		saveTasks();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void saveTasks() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(tasks);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Task> loadTasks() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			return (List<Task>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return new ArrayList<>();
		}
	}

	public Task getTaskByName(String name) {
		for (Task task : tasks) {
			if (task.getTitle().equalsIgnoreCase(name)) {
				return task;
			}
		}
		return null;
	}

	public void removeTask(Task task) {
		tasks.remove(task);
		saveTasks();
	}

	public void sortTasks() {
		tasks.sort((task1, task2) -> {
			int dateComparison = task1.getDueDate().compareTo(task2.getDueDate());
			if (dateComparison == 0) {
				return Integer.compare(task2.getPriority().ordinal(), task1.getPriority().ordinal());
			}
			return dateComparison;
		});
	}
	
	public List<Task> getTasksForDate(Date date) {
        List<Task> tasksForDate = new ArrayList<>();
        for (Task task : tasks) {
            if (isSameDay(task.getDueDate(), date)) {
                tasksForDate.add(task);
            }
        }
        return tasksForDate;
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}