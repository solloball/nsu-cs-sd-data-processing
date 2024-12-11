package org.example;

public class Node {
    private Node next;
    private String value = "value";

    synchronized Node add(String value) {
       var res = new Node();
       res.value = value;
       res.next = this;
       return res;
    }

    synchronized void print() {
        System.out.println(value);
        if (next != null) {
            next.print();
        }
    }

    int getLength() {
        int len = 0;
        Node curr = this;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return len;
    }

    synchronized Node bubbleSort() {
        var head = this;
        int len = getLength();
        int itr = 0;
        boolean swapped;

        // Iterating over the whole linked list
        while (itr < len) {
            Node traverseNode = head;
            Node prevNode = head;
            swapped = false;

            while (traverseNode.next != null) {

                // Temporary pointer to store the next
                // pointer of traverseNode
                Node ptr = traverseNode.next;
                if (traverseNode.value.compareTo(ptr.value) > 0) {
                    swapped = true;
                    if (traverseNode == head) {

                        // Performing swap operations and
                        // updating the head of the linked
                        // list
                        traverseNode.next = ptr.next;
                        ptr.next = traverseNode;
                        prevNode = ptr;
                        head = prevNode;
                    }
                    else {
                        // Performing swap operation
                        traverseNode.next = ptr.next;
                        ptr.next = traverseNode;
                        prevNode.next = ptr;
                        prevNode = ptr;
                    }
                    continue;
                }
                prevNode = traverseNode;
                traverseNode = traverseNode.next;
            }

            // If no swap occurred, break the loop
            if (!swapped) {
                break;
            }

            itr++;
        }
        return head;
    }
}
