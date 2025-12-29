package fit_zone.dao;

import fit_zone.connection.DatabaseConnection;
import fit_zone.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of the {@link IClientDAO} interface.
 *
 * <p>This class provides concrete implementations of CRUD operations
 * for {@link Client} entities using plain JDBC and a MySQL database.</p>
 *
 * <p>All SQL statements are defined as constants and executed using
 * {@link PreparedStatement} to ensure security and performance.</p>
 */
public class ClientDAOImpl implements IClientDAO {

    /** SQL query to retrieve all clients ordered by id */
    private static final String SELECT_ALL =
            "SELECT id, name, surname, membership_code FROM clients ORDER BY id";

    /** SQL query to retrieve a client by its identifier */
    private static final String SELECT_BY_ID =
            "SELECT id, name, surname, membership_code FROM clients WHERE id = ?";

    /** SQL query to insert a new client */
    private static final String INSERT =
            "INSERT INTO clients (name, surname, membership_code) VALUES (?, ?, ?)";

    /** SQL query to update an existing client */
    private static final String UPDATE =
            "UPDATE clients SET name = ?, surname = ?, membership_code = ? WHERE id = ?";

    /** SQL query to delete a client by its identifier */
    private static final String DELETE =
            "DELETE FROM clients WHERE id = ?";

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of {@link Client} objects, or an empty list if none are found
     */
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

    /**
     * Retrieves a client by its unique identifier.
     *
     * @param id the client identifier
     * @return the {@link Client} if found, or {@code null} otherwise
     */
    @Override
    public Client findById(int id) {

        Client client = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {

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
            System.out.println("Error retrieving client with id " + id + ": " + e.getMessage());
        }

        return client;
    }

    /**
     * Saves a new client in the database.
     *
     * @param client the client to be saved
     * @return {@code true} if the client was successfully saved,
     *         {@code false} otherwise
     */
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
            return false;
        }
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client the client containing updated data
     * @return {@code true} if the client was successfully updated,
     *         {@code false} otherwise
     */
    @Override
    public boolean update(Client client) {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setInt(3, client.getMembershipCode());
            ps.setInt(4, client.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a client from the database using its identifier.
     *
     * @param id the client identifier
     * @return {@code true} if the client was successfully deleted,
     *         {@code false} otherwise
     */
    @Override
    public boolean delete(int id) {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
            return false;
        }
    }
}
