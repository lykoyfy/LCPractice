package fIrstPractice;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k){
        if(k == 1){
            return nums;
        }

        Node root = new Node();
        Node tail = new Node();
        root.next = tail;
        tail.before = root;

        int[] ret = new int[nums.length - k + 1];
        for(int i=0; i<k; i++){
            Node n = new Node();
            n.index = i; n.value = nums[i];
            insertNode(tail, n);
        }

        for (int i=k; i<nums.length; i++){
            Node n = new Node();
            n.index = i; n.value = nums[i];
            removeNode(root, i-k);
            insertNode(root, n);
            ret[i-k] = root.next.value;

        }

        return ret;
    }

    void insertNode(Node tail, Node newNode){
        Node current = tail;
        while(current.before != null){
            if(current.before.value < newNode.value){
                current.before.before.next = current;
                current.before = current.before.before;
            }else {
                current.before.next = newNode;
                newNode.before = current.before;
                current.before = newNode;
                newNode.next = current;
            }
        }
        current.next = newNode;
    }

    void  removeNode(Node root, int index){
        while (root.next != null){
            if (root.next.index<index){
                root.next.next.before = root;
                root.next = root.next.next;
            }else {
                break;
            }
        }
    }

    class Node{
        int index;
        int value;
        Node next;
        Node before;
    }
}
