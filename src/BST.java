import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<Pair<K, V>> {
    private Node root;
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        private int size;
        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }
        public Node(K key, V val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public void put(K key, V val){
        root = privatePut(key, val, root, 0);
    }
    private Node privatePut(K key, V value, Node root, int size){
        if(root == null){
            return new Node(key, value, size);
        }
        if(root.key.compareTo(key) > 0){
            root.left = privatePut(key, value, root.left, size + 1);
        }
        else root.right = privatePut(key, value, root.right, size + 1);

        return root;
    }

    public V get(K key){
        Node res = levelOrder(key, root);
        if(res == null) return null;
        return res.val;
    }
    public Node levelOrder(K key, Node root){
        if(root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Node node = q.poll();
                if(node.key.equals(key)) return node;
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
        }

        return null;
    }
    public void delete(K key){
        root = privateDelete(key, root);
    }
    private Node privateDelete(K key, Node root){
        if(root == null) return root;

        if(root.key.compareTo(key) > 0){
            root.left = privateDelete(key, root.left);
        }
        else if(root.key.compareTo(key) < 0){
            root.right = privateDelete(key, root.right);
        }
        else {
            if(root.left == null) {
                root.right.size = root.size - 1;
                return root.right;
            }
            else if(root.right == null) {
                root.left.size = root.size - 1;
                return root.left;
            }
            Node leftmost = getMinNode(root.right);
            root.key = leftmost.key;
            root.val = leftmost.val;
            root.size--;
            root.right = privateDelete(root.key, root.right);
        }
        return root;
    }

    private Node getMinNode(Node root){
        Node min = root;
        while(root.left != null) {
            min = root.left;
            root = root.left;
        }
        return min;
    }
    public Iterator<Pair<K, V>> iterator(){
        return new MyIterator();
    }
    private class MyIterator implements Iterator<Pair<K,V>>{
        public List<Pair<K,V>> inOrder(){
            List<Pair<K,V>> list = new ArrayList<>();
            inOrderTraversal(root, list);
            return list;
        }
        private void inOrderTraversal(Node root, List<Pair<K,V>> list){
            if(root == null) return;
            inOrderTraversal(root.left, list);
            list.add(new Pair<>(root.key, root.val));
            inOrderTraversal(root.right, list);
        }
        List<Pair<K,V>> list = inOrder();
        int i = 0;
        @Override
        public boolean hasNext() {
            return i < list.size();
        }
        @Override
        public Pair<K,V> next() {
            return list.get(i++);
        }
    }
}
