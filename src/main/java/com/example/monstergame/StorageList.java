package com.example.monstergame;

import java.util.Comparator;
import java.util.Objects;

/**
 * Doubly linked list that stores nodes created with the StorageNode class.
 * Generic type used so that anything can be stored in the StorageList.
 *
 * @param <E> The generic type of data stored in StorageList.
 *
 * @author Simon Powley
 * @version 0.9
 */
public class StorageList<E> implements java.io.Serializable {

    //  initialize head and tail nodes and list size
    StorageNode<E> head;
    StorageNode<E> tail;
    int size;

    public StorageList() {
        this.head = new StorageNode<>("Head", null);
        this.tail = new StorageNode<>("Tail", null);
        this.size = 0;
    }

    //  return size of list
    public int size() {
        return this.size;
    }

    //  return true if list is empty, false otherwise
    public boolean isEmpty() {
        return this.size == 0;
    }

    //  return true if element is in list, false otherwise
    public boolean contains(E o) {
        StorageNode<E> currNode = this.head.getNext();
        for (int i = 0; i < this.size(); i++) {
            if (currNode.getData() == o) {
                return true;
            }
            currNode = currNode.getNext();
        }
        return false;
    }

    //  return true if element successfully added to end of list, false otherwise
    public void add(String name, E o) {
        //  create new node to add to list
        StorageNode<E> newNode = new StorageNode<>(name, o);
        newNode.setData(o);
        //  if list is empty
        if (this.isEmpty()) {
            //  add new node to list, update node pointers, update list size
            this.head.setNext(newNode);
            this.tail.setPrev(newNode);
            newNode.setPrev(this.head);
            newNode.setNext(this.tail);
            this.size++;
        }
        //  if list is not empty
        else if (!this.isEmpty()) {
            //  add new node to list, update node pointers, update list size
            newNode.setPrev(this.tail.getPrev());
            newNode.setNext(this.tail);
            newNode.getPrev().setNext(newNode);
            this.tail.setPrev(newNode);
            this.size++;
        }
    }

    //  return true if element successfully removed from list, false otherwise
    public void remove(E o) {
        StorageNode<E> currNode = this.head.getNext();
        for (int i = 0; i < this.size(); i++) {
            if (currNode.getData() == o) {
                //  if list has one node
                if (this.size() == 1) {
                    this.head.setNext(this.tail);
                    this.tail.setPrev(this.head);
                    this.size--;
                    return;
                }
                //  if removing first element
                else if (currNode.getPrev() == this.head) {
                    currNode.getNext().setPrev(this.head);
                    this.head.setNext(currNode.getNext());
                    this.size--;
                    return;
                }
                //  if removing last element
                else if (currNode.getNext() == this.tail) {
                    currNode.getPrev().setNext(this.tail);
                    this.tail.setPrev(currNode.getPrev());
                    this.size--;
                    return;
                }
                else {
                    //  remove node from list, update list size
                    currNode.getPrev().setNext(currNode.getNext());
                    currNode.getNext().setPrev(currNode.getPrev());
                    this.size--;
                    return;
                }
            }
            currNode = currNode.getNext();
        }
    }

    //  remove all elements from list
    public void clear() {
        //  update head and tail pointers and list size
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.size = 0;
    }

    //  return element at specified index
    public E get(int index) {
        StorageNode<E> currNode = this.head.getNext();
        for (int i = 0; i < this.size(); i++) {
            //  if specified index is reached, return element
            if (i == index) {
                return currNode.getData();
            }
            currNode = currNode.getNext();
        }
        return null;
    }

    //  replace element at specified index with specified element, return element previously at specified index
    public void set(int index, E element) {
        if (index < 0 || index > this.size()-1) {
            throw new IndexOutOfBoundsException("index must be in range: " + 0 + "-" + (this.size()-1));
        }   else {
            StorageNode<E> currNode = this.head.getNext();
            for (int i = 0; i < this.size(); i++) {
                //  if specified index reached, return replaced element
                if (i == index) {
                    currNode.setData(element);
                    return;
                }
                currNode = currNode.getNext();
            }
        }
    }

    //  insert specified element at specified index, shift elements at specified index to the right
    public void add(int index, String name, E element) {
        if (index < 0 || index > this.size()-1) {
            throw new IndexOutOfBoundsException("index must be in range: " + 0 + "-" + (this.size()-1));
        }   else {
            //  create new node to add to list
            StorageNode<E> newNode = new StorageNode<>(name, element);
            newNode.setData(element);
            StorageNode<E> currNode = this.head.getNext();
            for (int i = 0; i < this.size(); i++) {
                //  if specified index reached
                if (i == index) {
                    //  add new element, update list size
                    newNode.setPrev(currNode.getPrev());
                    newNode.setNext(currNode);
                    //  shift elements right
                    newNode.getNext().setPrev(newNode);
                    newNode.getPrev().setNext(newNode);
                    this.size++;
                }
                currNode = currNode.getNext();
            }
        }
    }

    //  remove element from specified index, shift elements after specified index left, return removed element
    public void remove(int index) {
        if (index < 0 || index > this.size()-1) {
            throw new IndexOutOfBoundsException("index must be in range: " + 0 + "-" + (this.size()-1));
        }   else {
            StorageNode<E> currNode = this.head.getNext();
            for (int i = 0; i < this.size(); i++) {
                if (i == index) {
                    //  if node is first in list
                    if (currNode.getPrev() == this.head) {
                        //  if node is only 1 node
                        if (currNode.getNext() == this.tail) {
                            this.head.setNext(this.tail);
                            this.tail.setPrev(this.head);
                        }
                        //  else
                        else {
                            this.head.setNext(currNode.getNext());
                            currNode.getNext().setPrev(this.head);
                        }
                        this.size--;
                        return;
                    }
                    //  if node is last in list
                    else if (currNode.getNext() == this.tail) {
                        this.tail.setPrev(currNode.getPrev());
                        currNode.getPrev().setNext(this.tail);
                        this.size--;
                        return;
                    } else {
                        //  remove element, update list size
                        currNode.getPrev().setNext(currNode.getNext());
                        currNode.getNext().setPrev(currNode.getPrev());
                        this.size--;
                        return;
                    }
                }
                currNode = currNode.getNext();
            }
        }
    }

    //  return the index of the first occurrence of specified element, otherwise return -1
    public int indexOf(String name) {
        StorageNode<E> currNode = this.head.getNext();
        for (int i = 0; i < this.size(); i++) {
            if (Objects.equals(currNode.getName(), name)) {
                return i;
            }
            currNode = currNode.getNext();
        }
        return -1;
    }

    //  return true if sorting method is implemented, false otherwise
    public boolean isSortingImplemented() {
        return true;
    }

    //  merge sort function
    public void sort(Comparator<E> comparator) {
        //  if list has 1 or less element
        if (this.size() <= 1) {
            return;
        }
        //  get middle point, initialize left and right sub lists
        int mid = this.size() / 2;
        StorageList<E> left = new StorageList<>();
        StorageList<E> right = new StorageList<>();
        //  split list into left and right sub lists
        for (int i = 0; i < mid; i++) {
            left.add("NAME", this.get(i));
        }
        for (int i = mid; i < this.size(); i++) {
            right.add("NAME", this.get(i));
        }
        //  sort left and right sub lists
        left.sort(comparator);
        right.sort(comparator);
        //  merge left and right sub lists
        this.merge(comparator, left, right);
    }

    //  merge left and right sub lists for merge sort
    public void merge(Comparator<E> comparator, StorageList<E> left, StorageList<E> right) {
        //  initial indexes for list and sub lists
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            //  compare left and right sub lists, merge sub lists based on comparator
            if (comparator.compare(left.get(i), right.get(j)) < 1) {
                this.set(k, left.get(i));
                i++;
            }   else {
                this.set(k, right.get(j));
                j++;
            }
            k++;
        }
        //  add any remaining elements of left and right sub lists to merged list
        while (i < left.size()) {
            this.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < right.size()) {
            this.set(k, right.get(j));
            j++;
            k++;
        }
    }
}