public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(5,"Hrani");
        bst.put(1,"Vas");
        bst.put(2,"Gospod'");
        bst.put(4,"Jesus");
        bst.put(6,"Vas");
        bst.put(0,"A");
        bst.put(3,"B");
        bst.put(8,"C");
        bst.put(7,"D");
        bst.put(9,"E");
        bst.put(10,"F");
        System.out.println(bst.inOrder());

        System.out.println(bst.get(4));
        System.out.println(bst.get(8));

        bst.delete(1);
        System.out.println(bst.inOrder());
        for(Integer a: bst){
            System.out.print(a + " ");
        }
    }
}