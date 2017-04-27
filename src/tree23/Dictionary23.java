package tree23;

import java.util.ArrayList;
import java.util.Collections;

public class Dictionary23<K extends Comparable<? super K>, V> {

    private Node23 root;

    public V get(K key) {
        Node23 parent = search(key, root);
        for (Node23 child : parent.children) {
            if (child.key.compareTo(key) == 0) {
                return child.value;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node23(key, value);
            return;
        }
        if (root.isLeaf) {
            if (root.key.compareTo(key) != 0) {
                Node23 tmp = new Node23(key, value);
                Node23 tmp2 = root;
                root = new Node23(tmp, tmp2);
            } else {
                root.value = value;
            }
            return;
        }
        insert(key, value);
    }

    public void remove(K key) {
        if (root == null) {
            return;
        }
        if (root.isLeaf) {
            if (root.key.compareTo(key) == 0) {
                root = null;
            }
            return;
        }
        delete(key);
    }

    public boolean contains(K key) {
        Node23 parent = search(key, root);
        for (Node23 child : parent.children) {
            if (child.key.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    private Node23 search(K key, Node23 node) {
        for (Node23 n : node.children) {
            if (n.isLeaf) {
                return node;
            }
        }
        if (key.compareTo(node.left) <= 0) {
            return search(key, node.children.get(0));
        } else if (key.compareTo(node.right) <= 0) {
            return search(key, node.children.get(1));
        } else {
            return search(key, node.children.get(node.children.size() - 1));
        }
    }

    private void insert(K key, V value) {
        Node23 parent = search(key, root);
        for (Node23 child : parent.children) {
            if (child.key.compareTo(key) == 0) {
                child.value = value;
                return;
            }
        }
        parent.addchild(new Node23(key, value));
        balance(parent);
    }

    private void delete(K key) {
        Node23 parent = search(key, root);
        for (Node23 child : parent.children) {
            if (child.key.compareTo(key) == 0) {
                recursiveDelete(child);
                return;
            }
        }
    }

    private void recursiveDelete(Node23 v) {
        Node23 f = v.parent;
        if (f.parent == null && f.children.size() == 2) {
            f.removeChild(v);
            root = f.children.get(0);
            f.removeChild(root);
            return;
        }
        if (f.children.size() == 3) {
            f.removeChild(v);
            return;
        }
        Node23 fPrime = f.parent.children.get((f.parent.children.indexOf(f) + 1) % 2);
        if (fPrime.children.size() == 3) {
            f.removeChild(v);
            Node23 bro = fPrime.children.get(fPrime.left.compareTo(f.left) < 0 ? 2 : 0);
            fPrime.removeChild(bro);
            f.addchild(bro);
            return;
        }
        f.removeChild(v);
        fPrime.addchild(f.children.get(0));
        recursiveDelete(f);
    }

    private void balance(Node23 node) {
        if (node.children.size() > 3) {
            Node23 parent;
            if (node.parent == null) {
                parent = new Node23();
                root = parent;
                parent.addchild(node);
            } else {
                parent = node.parent;
            }
            Node23 rightmost = node.children.get(node.children.size() - 1);
            Node23 almost = node.children.get(node.children.size() - 2);
            node.removeChild(rightmost);
            node.removeChild(almost);
            parent.addchild(new Node23(almost, rightmost));
            balance(parent);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }
        return root.toString();
    }

    private final class Node23 implements Comparable<Node23> {

        K left;
        K right;

        boolean isLeaf;
        K key;
        V value;

        Node23 parent;
        ArrayList<Node23> children = new ArrayList<>();

        public Node23() {

        }

        public Node23(Node23 n1, Node23 n2) {
            addchild(n1);
            addchild(n2);
            isLeaf = false;
        }

        public Node23(K key, V value) {
            this.key = key;
            this.value = value;
            isLeaf = true;
        }

        @Override
        public String toString() {
            if (isLeaf) {
                return key + ": " + value;
            }
            StringBuilder s = new StringBuilder();
            for (Node23 child : children) {
                s.append(child.toString());
                s.append(", ");
            }
            return s.toString();
        }

        @Override
        public int compareTo(Node23 o) {
            if (isLeaf) {
                return key.compareTo(o.key);
            }
            return left.compareTo(o.left);
        }

        void addchild(Node23 node) {
            node.parent = this;
            children.add(node);
            Collections.sort(children);
            setLR();
        }

        void removeChild(Node23 node) {
            node.parent = null;
            children.remove(node);
            Collections.sort(children);
            setLR();
        }
        
        void setLR(){
            if (children.size() > 0) {
                left = getMax(children.get(0));
            }
            if (children.size() > 1) {
                right = getMax(children.get(1));
            }
            if(parent!=null){
                parent.setLR();
            }
        }

        K getMax(Node23 node) {
            if (node.isLeaf) {
                return node.key;
            }
            return getMax(node.children.get(node.children.size()-1));
        }

    }

}
