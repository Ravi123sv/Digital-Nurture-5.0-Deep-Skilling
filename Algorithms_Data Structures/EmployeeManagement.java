class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
}

public class EmployeeManagement {
    private Employee[] employees;
    private int count;

    public EmployeeManagement(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public void addEmployee(Employee e) {
        if (count < employees.length) {
            employees[count] = e;
            count++;
        }
    }

    public void traverse() {
        for (int i = 0; i < count; i++) {
            System.out.println("ID: " + employees[i].employeeId + ", Name: " + employees[i].name);
        }
    }

    public int search(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) return i;
        }
        return -1;
    }

    public void delete(int id) {
        int index = search(id);
        if (index != -1) {
            for (int i = index; i < count - 1; i++) {
                employees[i] = employees[i + 1];
            }
            count--;
        }
    }

    public static void main(String[] args) {
        EmployeeManagement system = new EmployeeManagement(5);
        system.addEmployee(new Employee(1, "Alice", "Developer", 80000));
        system.addEmployee(new Employee(2, "Bob", "Manager", 90000));

        System.out.println("--- Employee List ---");
        system.traverse();

        system.delete(1);
        System.out.println("\n--- After Deleting Employee 1 ---");
        system.traverse();
    }
}