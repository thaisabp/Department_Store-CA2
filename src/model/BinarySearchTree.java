package model;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(Employee employee) {
        root = insertRecursive(root, employee);
    }

    private TreeNode insertRecursive(TreeNode current, Employee employee) {
        // If the current node is empty, create a new node here
        if (current == null) {
            return new TreeNode(employee);
        }

        //compare the full name to decide left or right
        int compareResult = employee.getFullName().compareTo(current.employee.getFullName());

        if (compareResult < 0) {
            // if name comes before, go to the left
            current.left = insertRecursive(current.left, employee);
        } else if (compareResult > 0) {
            //if name comes after, go to the right
            current.right = insertRecursive(current.right, employee);
        } else {
            //duplicate name found, ignoring to keep the tree simple
        }
        return current;
    }

    public void displayInOrder() {
        inOrderRecursive(root);
    }

    //recursive method for in-order traversal (from left to root and right)
    private void inOrderRecursive(TreeNode node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.println(node.employee);
            inOrderRecursive(node.right);
        }
    }
}