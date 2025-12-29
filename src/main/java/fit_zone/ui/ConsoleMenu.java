package fit_zone.ui;

import fit_zone.dao.ClientDAOImpl;
import fit_zone.dao.IClientDAO;
import fit_zone.entities.Client;

import java.util.List;
import java.util.Scanner;

/**
 * Console-based user interface for managing clients in the Fit Zone system.
 *
 * <p>This class provides a text-based menu that allows users to perform
 * CRUD operations on {@link Client} entities. It acts as the presentation
 * layer of the application and delegates all data operations to the
 * {@link IClientDAO} implementation.</p>
 *
 * <p>This class does not contain any database or SQL logic.
 * Its sole responsibility is handling user interaction.</p>
 */
public class ConsoleMenu {

    /** Data Access Object used to manage client persistence */
    private final IClientDAO clientDAO;

    /** Scanner used to read user input from the console */
    private final Scanner scanner;

    /**
     * Creates a new {@code ConsoleMenu} instance.
     *
     * <p>Initializes the DAO implementation and the scanner
     * used for reading console input.</p>
     */
    public ConsoleMenu() {
        this.clientDAO = new ClientDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the console menu loop.
     *
     * <p>This method displays the menu options, reads the user's choice
     * and executes the corresponding operation until the user chooses
     * to exit the application.</p>
     */
    public void start() {
        int option;

        do {
            printMenu();
            option = readInt("Choose an option: ");

            switch (option) {
                case 1 -> listClients();
                case 2 -> findClientById();
                case 3 -> addClient();
                case 4 -> updateClient();
                case 5 -> deleteClient();
                case 0 -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid option. Please try again.");
            }

        } while (option != 0);
    }

    /**
     * Prints the main menu options to the console.
     */
    private void printMenu() {
        System.out.println("\n=== FIT ZONE - CLIENT MENU ===");
        System.out.println("[1] List clients");
        System.out.println("[2] Find client by ID");
        System.out.println("[3] Add client");
        System.out.println("[4] Update client");
        System.out.println("[5] Delete client");
        System.out.println("[0] Exit");
    }

    /**
     * Reads an integer value from the console.
     *
     * <p>This method keeps prompting the user until a valid
     * integer is entered, preventing input mismatch errors.</p>
     *
     * @param message the message displayed before reading input
     * @return a valid integer entered by the user
     */
    private int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Displays all clients stored in the database.
     *
     * <p>If no clients are found, an informational message
     * is shown to the user.</p>
     */
    private void listClients() {
        System.out.println("\n--- Client List ---");
        List<Client> clients = clientDAO.findAll();

        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            clients.forEach(System.out::println);
        }
    }

    /**
     * Searches for a client by its identifier and displays the result.
     */
    private void findClientById() {
        int id = readInt("Enter client ID: ");

        Client client = clientDAO.findById(id);
        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("Client not found.");
        }
    }

    /**
     * Reads client data from the console and saves a new client.
     */
    private void addClient() {
        scanner.nextLine(); // clear buffer

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        int membershipCode = readInt("Enter membership code: ");

        Client client = new Client(name, surname, membershipCode);

        boolean saved = clientDAO.save(client);
        System.out.println(saved ? "Client added successfully." : "Error adding client.");
    }

    /**
     * Updates an existing client using data provided by the user.
     */
    private void updateClient() {
        int id = readInt("Enter client ID to update: ");
        scanner.nextLine(); // clear buffer

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new surname: ");
        String surname = scanner.nextLine();

        int membershipCode = readInt("Enter new membership code: ");

        Client client = new Client(id, name, surname, membershipCode);

        boolean updated = clientDAO.update(client);
        System.out.println(updated ? "Client updated successfully." : "Client not found.");
    }

    /**
     * Deletes a client identified by its ID.
     */
    private void deleteClient() {
        int id = readInt("Enter client ID to delete: ");

        boolean deleted = clientDAO.delete(id);
        System.out.println(deleted ? "Client deleted successfully." : "Client not found.");
    }
}
