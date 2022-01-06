package com.example.monstergame;

/**
 * Node that stores data in a linked list created with the StorageList class.
 * Generic type used so that anything can be stored in the StorageNode.
 *
 * @param <E> The generic type of data stored in StorageNode.
 *
 * @author Simon Powley
 * @version 0.9
 */
public class StorageNode<E> implements java.io.Serializable {
    /** The next {@link StorageNode} in the chain. */
    private StorageNode<E> next;

    /** The previous {@link StorageNode} in the chain. */
    private StorageNode<E> prev;

    /** The element to associate (or store) with the {@link StorageNode}. */
    private E data;

    private String name;

    /**
     * Creates a new unlinked {@link StorageNode}.
     * @param data The data to store in new {@link StorageNode}.
     */
    public StorageNode(String name, E data) {
        this.next    = null;
        this.prev    = null;
        this.name = name;
        this.data = data;
    }

    /**
     * Retrieves the "next" {@link StorageNode} which this {@link StorageNode} references.
     *
     * @return The "next" {@link StorageNode} which this {@link StorageNode} references, or {@code null} if this {@link StorageNode}
     *         doesn't reference another {@link StorageNode}.
     */
    public StorageNode<E> getNext() {
        return this.next;
    }

    /**
     * Sets the "next" {@link StorageNode} to which this {@link StorageNode} references.
     *
     * @param next The "next" {@link StorageNode} to which this {@link StorageNode} references.  Pass {@code null} to de-link this
     *             {@link StorageNode} from another.
     */
    public void setNext(StorageNode<E> next) {
        this.next = next;
    }

    /**
     * Retrieves the "previous" {@link StorageNode} which this {@link StorageNode} references.
     *
     * @return The "previous" {@link StorageNode} which this {@link StorageNode} references, or {@code null} if this {@link StorageNode}
     *         doesn't reference another {@link StorageNode}.
     */
    public StorageNode<E> getPrev() {
        return this.prev;
    }

    /**
     * Sets the "previous" {@link StorageNode} to which this {@link StorageNode} references.
     *
     * @param prev The "previous" {@link StorageNode} to which this {@link StorageNode} references.  Pass {@code null} to de-link
     *             this {@link StorageNode} from another.
     */
    public void setPrev(StorageNode<E> prev) {
        this.prev = prev;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the data that is associated with this {@link StorageNode}.
     *
     * @return The data that is associated with this {@link StorageNode}, or {@code null} if no data is associated.
     */
    public E getData() {
        return this.data;
    }

    /**
     * Sets the data that is associated with this {@link StorageNode}.
     *
     * @param data The data to associate with this {@link StorageNode}.
     */
    public void setData(E data) {
        this.data = data;
    }
}
