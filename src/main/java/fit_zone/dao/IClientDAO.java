package fit_zone.dao;

import fit_zone.entities.Client;
import java.util.List;

/**
 * Data Access Object (DAO) interface for {@link Client} entities.
 *
 * <p>This interface defines the standard CRUD operations
 * for managing clients in the persistence layer.</p>
 *
 * <p>Implementations of this interface are responsible for
 * interacting with the database using JDBC.</p>
 */
public interface IClientDAO {

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of clients
     */
    List<Client> findAll();

    /**
     * Retrieves a client by its unique identifier.
     *
     * @param id the client identifier
     * @return the client if found, or {@code null} otherwise
     */
    Client findById(int id);

    /**
     * Saves a new client in the database.
     *
     * @param client the client to be saved
     * @return {@code true} if the client was successfully saved,
     *         {@code false} otherwise
     */
    boolean save(Client client);

    /**
     * Updates an existing client in the database.
     *
     * @param client the client with updated data
     * @return {@code true} if the client was successfully updated,
     *         {@code false} otherwise
     */
    boolean update(Client client);

    /**
     * Deletes a client from the database using its identifier.
     *
     * @param id the client identifier
     * @return {@code true} if the client was successfully deleted,
     *         {@code false} otherwise
     */
    boolean delete(int id);
}
