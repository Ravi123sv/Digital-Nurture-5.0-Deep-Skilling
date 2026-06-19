class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskLinkedList {
    private Node head;

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void traverse() {
        Node current = head;
        while (current != null) {
            System.out.println("Task ID: " + current.task.taskId + " | Name: " + current.task.taskName + " | Status: " + current.task.status);
            current = current.next;
        }
    }

    public Node search(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void delete(int taskId) {
        if (head == null) return;

        if (head.task.taskId == taskId) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.task.taskId != taskId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        } else {
            System.out.println("Error: Task ID " + taskId + " not found.");
        }
    }

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addTask(new Task(1, "Fix Login Bug", "In Progress"));
        taskList.addTask(new Task(2, "Update Database Schema", "Pending"));
        taskList.addTask(new Task(3, "Write Unit Tests", "Not Started"));

        System.out.println("--- All Tasks ---");
        taskList.traverse();

        System.out.println("\n--- Searching for Task 2 ---");
        Node found = taskList.search(2);
        if (found != null) {
            System.out.println("Found: " + found.task.taskName);
        }

        System.out.println("\n--- Deleting Task 2 ---");
        taskList.delete(2);
        taskList.traverse();
    }
}