/**
 * Represents a node in a linked list of character data objects.
 * A node has a pointer to a CharData object,
 * and a pointer to another node.
 * f.x: Node n = new Node(new CharData('c'));
 */
public class Node {
   CharData cd; // a pointer to a CharData object
   Node next; // pointer to another node in the list (null if there is no such node)
   // pointer to the previous node in the list (null if there is no such node)

   /**
    * Constructs a node with the given CharData object.
    * The new node will point to the given next node.
    */
   public Node(CharData cd, Node next) {
      this.cd = cd;
      this.next = next;
   }

   /**
    * Constructs a node with the given CharData object.
    * The new node will point to null.
    */
   public Node(CharData cd) {
      this(cd, null);
   }

   // method to get the last node in the list
   public Node getLastNode(List list, Node first) {
      Node current = list.first;
      while (current.next != null) {
         current = current.next;
      }
      return current;
   }

   /** Textual representation of this node. */
   public String toString() {
      return "" + cd;
   }
}