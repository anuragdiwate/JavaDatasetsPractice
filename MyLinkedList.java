public class MyLinkedList {
        class SinglyListNode{
            int val;
            SinglyListNode next;
            SinglyListNode prev;
            public SinglyListNode(){}
            public SinglyListNode (int x){ this.val = x;}
        }
        SinglyListNode init, tail;
        int ListSize;


        /** Initialize your data structure here. */
        public MyLinkedList() {
            ListSize = 0;
            init = new SinglyListNode();
            tail = init;
        }

    private SinglyListNode getNode(int index) {
        SinglyListNode cur = init;
            while (index-- >= 0) {
                cur = cur.next;
            }
        return cur;
    }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index < 0 || index >= ListSize){
                System.out.println("Invalid index value to fetch.");
                return -1;
            }
            return getNode(index).val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {

        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            tail.next = new SinglyListNode(val);
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            SinglyListNode hold = getNode(index).next;
            getNode(index).next = new SinglyListNode(val);
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {

        }

        public void printList() {

        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */

