interface CustomerRepository {
    String findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        if (id == 1) {
            return "Alice Smith";
        } else if (id == 2) {
            return "Bob Johnson";
        } else {
            return "Unknown Customer";
        }
    }
}

class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void printCustomerDetails(int id) {
        String customerName = repository.findCustomerById(id);
        System.out.println("-> Customer Record: " + customerName + " (ID: " + id + ")");
    }
}

public class DependencyInjectionExample {
    public static void main(String[] args) {
        System.out.println("--- Customer Management System ---\n");

        CustomerRepository database = new CustomerRepositoryImpl();

        CustomerService service = new CustomerService(database);

        service.printCustomerDetails(1);
        service.printCustomerDetails(2);
        service.printCustomerDetails(99);
    }
}