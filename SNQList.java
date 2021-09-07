public class SNQList {
    class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int value) {
            this.val = value;
            this.next = null;
        }

        public Node(int value, Node nextNode) {
            this.val = value;
            this.next = nextNode;
        }

        int getVal() {
            return val;
        }

        Node getNext() {
            return next;
        }

        void setVal(int value) {
            this.val = value;
        }

        void setNext(Node nextNode) {
            this.next = nextNode;
        }
    }

    Node head = new Node();
    Node tail = head;

    public SNQList() {
    }

    boolean isEmpty() {
        return (head == tail);
    }

    void addAtHead(int value) {
        head = new Node(value, head);
    }

    void addAtTail(int value) {
        Node cur = new Node(value);
        tail.setNext(cur);
        tail = tail.getNext();

    }

    Node removeFromHead() {
        if (!(head.getNext() == null)) {
            Node cur = head;
            head = head.getNext();
            return cur;
        } else {
            return null;
        }
    }

    void clear() {
        //TODO: fill out
    }

    void print() {
        Node cur = head;
        System.out.print("Values in Linked List: ");
        while (cur != null) {
            System.out.print(cur.getVal() + " ");
            cur = cur.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SNQList queue = new SNQList();
        SNQList stack = new SNQList();

//        stack.print();
//        stack.addAtHead("A");
//        stack.print();
//        stack.addAtHead("B");
//        stack.print();
//
//        stack.clear();
//        stack.print();
//
//        stack.addAtHead("B");
//        stack.addAtHead("C");
//        stack.addAtHead("D");
//        stack.print();
//
//        System.out.println("Removed: " + stack.removeFromHead().getVal() + " from stack.");
//        System.out.println("Removed: " + stack.removeFromHead().getVal() + " from stack.");
//        System.out.println("Removed: " + stack.removeFromHead().getVal() + " from stack.");
//        stack.print();

        queue.addAtTail(1);
        queue.addAtTail(2);
        queue.addAtTail(3);
        queue.addAtTail(4);
        queue.print();

        queue.addAtTail(5);
        queue.print();

        System.out.println("Removed: " + queue.removeFromHead().getVal() + " from queue.");
        System.out.println("Removed: " + queue.removeFromHead().getVal() + " from queue.");
        System.out.println("Removed: " + queue.removeFromHead().getVal() + " from queue.");
        queue.print();
    }
}
