package org.samarBg.models;

import javax.persistence.*;
import java.time.Instant;

/**
 * Base class for all entities in the system.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    protected Instant created;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    protected Instant modified;

    /**
     * Getter for the ID of the entity.
     *
     * @return The ID of the entity.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the ID of the entity.
     *
     * @param id The ID to set for the entity.
     * @return The updated BaseEntity instance.
     */
    public BaseEntity setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for the creation timestamp of the entity.
     *
     * @return The creation timestamp.
     */
    public Instant getCreated() {
        return created;
    }

    /**
     * Setter for the creation timestamp of the entity.
     *
     * @param created The creation timestamp to set.
     * @return The updated BaseEntity instance.
     */
    public BaseEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    /**
     * Getter for the last modified timestamp of the entity.
     *
     * @return The last modified timestamp.
     */
    public Instant getModified() {
        return modified;
    }

    /**
     * Setter for the last modified timestamp of the entity.
     *
     * @param modified The last modified timestamp to set.
     * @return The updated BaseEntity instance.
     */
    public BaseEntity setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    /**
     * Generates a string representation of the BaseEntity.
     *
     * @return A string representation containing the ID, creation timestamp, and last modified timestamp.
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
