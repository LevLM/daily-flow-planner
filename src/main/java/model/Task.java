package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {

	public enum TaskPriority {
		LOW, MEDIUM, HIGH
	}

	public enum TaskStatus {
		PENDING, IN_PROGRESS, COMPLETED
	}

	private String title;
	private String description;
	private Date dueDate;
	private TaskPriority priority;
	private TaskStatus status;

	public Task(String title, String description, Date dueDate, TaskPriority priority, TaskStatus status) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getFormattedDueDate() {
        if (dueDate == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(dueDate);
    }
	
	@Override
	public String toString() {
		return getFormattedDueDate() + " - " + title +  " - " + "[" + priority + "] " + " - " + " (" + status + ")";
	}
}