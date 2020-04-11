package neu.kaishen.connecteddevices.labs.module07;

class test {
    public static void main(String[] args) {
        Node node= new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;

        //Node temp = node;
        //while (temp.next != null) {
        while (node.next != null) {
            node = node.next;
        }
       node.next = new Node(10);
        System.out.println(node2.next.next.t);
    }
}
class Node<T> {
    Node next;
    T t;

    Node(T t) {
        this.t = t;
    }
}