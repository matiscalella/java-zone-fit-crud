package fit_zone.dao;

import fit_zone.connection.DatabaseConnection;
import fit_zone.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements IClientDAO{

    private static final String SELECT_ALL =
            "SELECT id, name, surname, membership_code FROM clients ORDER BY id";

    private static final String SELECT_BY_ID =
            "SELECT id, name, surname, membership_code FROM clients WHERE id = ?";

    private static final String INSERT =
            "INSERT INTO clients (name, surname, membership_code) VALUES (?, ?, ?)";

    private static final String UPDATE =
            "UPDATE clients SET name = ?, surname = ?, membership_code = ? WHERE id = ?";

    private static final String DELETE =
            "DELETE FROM clients WHERE id = ?";

    @Override
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setSurname(rs.getString("surname"));
                client.setMembershipCode(rs.getInt("membership_code"));
                clients.add(client);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving clients: " + e.getMessage());
        }

        return clients;
    }

    @Override
    public Client findById(int id) {
        Client client = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setName(rs.getString("name"));
                    client.setSurname(rs.getString("surname"));
                    client.setMembershipCode(rs.getInt("membership_code"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving client with id: " + id + " " + e.getMessage());
        }

        return client;
    }

    @Override
    public boolean save(Client client) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getMembershipCode());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error saving client: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public static void main(String[] args) {
        // Client listing test
        /*
        System.out.println("*** findAll Test ***");
        IClientDAO clientDAO = new ClientDAOImpl();
        List<Client> clients = clientDAO.findAll();
        clients.forEach(System.out::println);


        // findById Test
        IClientDAO clientDAO = new ClientDAOImpl();

        System.out.println("*** findById Test ***");
        Client client = clientDAO.findById(2);

        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("Client not found");
        }

        // save Test
        IClientDAO clientDAO = new ClientDAOImpl();
        Client client = new Client("Nicolas", "Camps", 555);
        boolean addedSuccessfully = clientDAO.save(client);
        if (addedSuccessfully) {
            System.out.println("New client added.");
            List<Client> clients = clientDAO.findAll();
            clients.forEach(System.out::println);
        } else {
            System.out.println("Error adding new client.");
        }
        */
    }
}
