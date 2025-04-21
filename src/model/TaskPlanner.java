package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
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
		return null; // Если задача не найдена
	}

	public void removeTask(Task task) {
	    tasks.remove(task);  // tasks — это список задач
	}
	
	private void saveTasksToFile() {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.dat"))) {
	        oos.writeObject(tasks);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
//	public void sortTasks() {
//	    tasks.sort(Comparator.comparing(Task::getDueDate)
//	              .thenComparing(task -> task.getPriority().ordinal()));
//	}
	
	public void sortTasks() {
	    tasks.sort((task1, task2) -> {
	        int dateComparison = task1.getDueDate().compareTo(task2.getDueDate());
	        if (dateComparison == 0) {
	        	return Integer.compare(task2.getPriority().ordinal(), task1.getPriority().ordinal());
	        }
	        return dateComparison;
	    });
	}
}