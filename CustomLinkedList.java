import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList {
    private Node head; // Head of the linked list

    // Inserts a new node with the given data at the end of the list
    public void insert(int data) {
        Node newNode = new Node(data); // Create a new node
        if (head == null) { // Check if the list is empty
            head = newNode; // List is empty
        } else {
            Node current = head; // Start from the head
            while (current.next != null) { // Check if the current node is the last node
                current = current.next; // Traverse to the end of the list
            }
            current.next = newNode; // Insert at the end
        }
    }

    // Deletes the first occurrence of a node with the given data
    public void delete(int data) { 
        if (head == null) { // Check if the list is empty
            return; // List is empty
        }
        if (head.data == data) { // Check if the head node has the data
            head = head.next; // Delete head
            return; 
        }
        Node current = head; // Start from the head
        while (current.next != null && current.next.data != data) { // Check if the next node has the data
            current = current.next; // Traverse the list
        }
        if (current.next != null) { // Check if the next node is not null
            current.next = current.next.next; // Delete node
        }
    }

    public Iterator<Integer> iterator() { // Returns an iterator for the linked list
        return new LinkedListIterator(); // Return a new instance of the iterator
    }

    private class Node { 
        int data; // Data of the node
        Node next; // Reference to the next node

        Node(int data) { // Constructor to create a new node
            this.data = data; // Initialize the data
            this.next = null; // Initialize the next reference
        }
    }

    private class LinkedListIterator implements Iterator<Integer> { // Iterator for the linked list
        private Node current = head; // Current node

        @Override 
        public boolean hasNext() { // Check if there is a next element
            return current != null; // Return true if the current node is not null
        }

        @Override
        public Integer next() { // Get the next element
            if (!hasNext()) { // Check if there is a next element
                throw new NoSuchElementException(); // Throw an exception
            }
            int data = current.data; // Get the data of the current node
            current = current.next; // Move to the next node
            return data; // Return the data
        }
    }
}

class Main { // Main class to demonstrate the linked list
    public static void main(String[] args) { // Main method
        CustomLinkedList linkedList = new CustomLinkedList(); // Create a new linked list

        // Read integers from a text file and insert them into the linked list
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) { // Open the file
            String line; // Line read from the file
            while ((line = br.readLine()) != null) { // Read each line
                try { // Handle NumberFormatException
                    int data = Integer.parseInt(line.trim()); // Parse the integer
                    linkedList.insert(data); // Insert the integer into the linked list
                } catch (NumberFormatException e) { // Catch NumberFormatException
                    System.err.println("Invalid integer in file: " + line); // Print an error message
                }
            }
        } catch (IOException e) { // Catch IOException
            e.printStackTrace(); // Print the stack trace
        }

        // Demonstrate deletion
        linkedList.delete(2); // Deletes the first occurrence of 2

        // Iterate and display elements
        Iterator<Integer> iterator = linkedList.iterator(); // Get an iterator
        System.out.print("LinkedList Elements: "); // Print a message
        while (iterator.hasNext()) { // Check if there is a next element
            System.out.print(iterator.next() + " "); // Print the next element
        }
    }
}
