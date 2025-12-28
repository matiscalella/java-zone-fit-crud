package fit_zone.entities;

import java.util.Objects;

/**
 * Represents a client of the Fit Zone system.
 *
 * <p>This entity models a gym client, storing personal information
 * and a numeric membership code associated with the client.</p>
 *
 * <p>This class is typically used as a domain entity and can be
 * persisted to a database table (e.g. {@code clients}).</p>
 */
public class Client {

    /** Unique identifier of the client */
    private int id;

    /** Client's first name */
    private String name;

    /** Client's last name (surname) */
    private String surname;

    /** Numeric code representing the client's membership */
    private int membershipCode;

    /**
     * Default constructor.
     * <p>
     * Required for frameworks, serialization and manual object creation.
     * </p>
     */
    public Client() {
    }

    /**
     * Creates a client with only an identifier.
     *
     * @param id the unique client identifier
     */
    public Client(int id) {
        this.id = id;
    }

    /**
     * Creates a client without an identifier.
     * <p>
     * This constructor is typically used before persisting
     * the client in the database.
     * </p>
     *
     * @param name           the client's first name
     * @param surname        the client's last name
     * @param membershipCode the membership code
     */
    public Client(String name, String surname, int membershipCode) {
        this.name = name;
        this.surname = surname;
        this.membershipCode = membershipCode;
    }

    /**
     * Creates a fully initialized client.
     *
     * @param id             the unique client identifier
     * @param name           the client's first name
     * @param surname        the client's last name
     * @param membershipCode the membership code
     */
    public Client(int id, String name, String surname, int membershipCode) {
        this(name, surname, membershipCode);
        this.id = id;
    }

    /**
     * Returns the client identifier.
     *
     * @return the client id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the client identifier.
     *
     * @param id the client id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the client's first name.
     *
     * @return the first name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the client's first name.
     *
     * @param name the first name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the client's last name.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the client's last name.
     *
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the membership code.
     *
     * @return the membership code
     */
    public int getMembershipCode() {
        return membershipCode;
    }

    /**
     * Sets the membership code.
     *
     * @param membershipCode the membership code to set
     */
    public void setMembershipCode(int membershipCode) {
        this.membershipCode = membershipCode;
    }

    /**
     * Returns a string representation of the client.
     *
     * @return a formatted string containing client data
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", membershipCode=" + membershipCode +
                '}';
    }

    /**
     * Compares this client with another object for equality.
     *
     * @param o the object to compare with
     * @return {@code true} if both objects represent the same client,
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                membershipCode == client.membershipCode &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname);
    }

    /**
     * Returns the hash code of the client.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, membershipCode);
    }
}
