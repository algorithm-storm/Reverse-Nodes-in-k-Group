/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class Solution {

    static public void main(String []args){

        Solution s = new Solution();

        String a = "1->2->3->4->5";

        String [] values = a.split("->");

        ListNode head = new ListNode(Integer.parseInt(values[0]));

        ListNode cur = head;

        for(int i = 1 ; i < values.length; i++){
            ListNode node = new ListNode(Integer.parseInt(values[i]));
            cur.next = node;
            cur = node;
        }

        cur.next = null;

        s.printNode(head);

        ListNode newList = s.reverseKGroup(head,3);

        s.printNode(newList);

    }


    public void printNode(ListNode node){

        System.out.print(node.val);
        node = node.next;
        while(node != null){
            System.out.print("->"+node.val);
            node = node.next;
        }
        System.out.println();

    }

    /*
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null){
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while(prev != null){
            prev = reverseKNode(prev,k);
        }

        return dummy.next;

    }

    // head->n1->n2....nk->nk+1
    // head->nk->nk-1....n1->nk+1

    private ListNode reverseKNode(ListNode prevNode, int k){

        ListNode n1 = prevNode.next; //record original head

        ListNode curt = prevNode;
        for(int i = 0 ; i < k ; i++){
            curt = curt.next;
            if(curt == null){
                return null;
            }
        }

        ListNode nk = curt; //record the original tail
        ListNode nkplus = curt.next; //record the original tail.next

        //re-run the nodes from head
        ListNode prev = prevNode;
        ListNode cur = prevNode.next;
        while (cur != nkplus){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        prevNode.next = nk;
        n1.next = nkplus;

        return n1; //return prev ; n1 is the next prev node
    }

}