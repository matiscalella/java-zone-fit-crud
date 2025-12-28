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
    @Override
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();

        String sql = """
        SELECT id, name, surname, membership_code
        FROM clients
        ORDER BY id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
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
        return null;
    }

    @Override
    public boolean save(Client client) {
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
        System.out.println("*** findAll Test ***");
        IClientDAO clientDAO = new ClientDAOImpl();
        List<Client> clients = clientDAO.findAll();
        clients.forEach(System.out::println);
    }
}
