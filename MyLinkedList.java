public class MyLinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        public ListNode() {}
        public ListNode(int val) {
            this.val = val;
        }
        public ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    int size;
    ListNode head;
    ListNode tail;

    public MyLinkedList() {
        head = new ListNode();
        tail = head;
        size = 0;
    }

    private ListNode getNode(int index) {
        ListNode cur = head;
        while (index-- >= 0) {
            cur = cur.next;
        }
        return cur;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        return getNode(index).val;
    }

    public void addAtHead(int val) { // push functionality for a stack implementation
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        tail.next = new ListNode(val);
        tail.next.prev = tail;
        tail = tail.next;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        ListNode newNode = new ListNode(val);
        ListNode target = getNode(index);
        newNode.prev = target.prev;
        newNode.next = target;
        target.prev.next = newNode;
        target.prev = newNode;
        size++;
    }

    public void addInOrder(int value){
        if(head == null){
            head = new ListNode(value);
        }else{
            if(head.val > value){ // value is the smallest in the list
                head = new ListNode(value, head);
            }else{
                ListNode ref = head;
                int index = 0;
                while(ref != null && ref.val <= value){
                    ref = ref.next;
                    index++;
                }
                addAtIndex(index, value);
            }
        }
    }

    public ListNode removeFromHead(){ // pop functionality for a stack implementation
        ListNode target = getNode(0);
        target.prev.next = target.next;
        if (target == tail) {
            tail = tail.prev;
        } else {
            target.next.prev = target.prev;
        }
        size--;
        return target;
    }

    public void removeValue(int value){
        if(head != null){
            if(head.val == value){
                removeAtIndex(0);
            }
        }else{
            ListNode ref = head;
            int index = 0;
            while( ref.val != value && ref != null){
                ref = ref.next;
                index++;
            }
            if( ref != null){
                removeAtIndex(index);
            }
        }
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode target = getNode(index);
        target.prev.next = target.next;
        if (target == tail) {
            tail = tail.prev;
        } else {
            target.next.prev = target.prev;
        }
        size--;
    }

    public void printList(){
        ListNode cur = head;
        while(cur != tail){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(55);
        list.addAtHead(54);
        list.addAtIndex(0, 2);
        list.printList();
    }
}

