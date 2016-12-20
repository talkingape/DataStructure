package edu.algorithm;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author WangGang
 * @describe 非平衡二叉树，只实现了插入，而且完成了向控制台输出
 * 2016年10月19日
 * @param <T>
 */
public class UnbalancedBinaryTree<T extends Comparable<T>> {

	private Node<T> root;
	private int level;

	public static void main(String[] args) {
		UnbalancedBinaryTree<Integer> t = new UnbalancedBinaryTree<Integer>();
		Random r = new Random();
		t.insert(5);
		for (int i = 0; i < 10; i++) {
			t.insert(r.nextInt(10));
		}
		t.toStdIO();
	}

	// 插入节点
	public void insert(T node) {
		Node<T> n = new Node<T>(node);
		int curLevel = 1;
		if (root == null) {
			root = n;
			level = 1;
		} else {
			Node<T> tmp = root;
			while (true) {
				curLevel++;
				if (n.compareTo(tmp) <= 0) {
					if (tmp.leftChild == null) {
						tmp.leftChild = n;
						break;
					} else {
						tmp = tmp.leftChild;
						continue;
					}
				} else if (n.compareTo(tmp) > 0) {
					if (tmp.rightChild == null) {
						tmp.rightChild = n;
						break;
					} else {
						tmp = tmp.rightChild;
						continue;
					}
				}
			}
		}
		if (curLevel > level) {
			level = curLevel;
		}
	}

	// 中序遍历
	public void midTraversal(Node<T> node) {
		if (node != null) {
			midTraversal(node.leftChild);
			node.list();
			midTraversal(node.rightChild);
		}
	}

	// 前序遍历
	public void preTraversal(Node<T> node) {
		if (node != null) {
			node.list();
			preTraversal(node.leftChild);
			preTraversal(node.rightChild);
		}
	}

	// 后序遍历
	public void aftTraversal(Node<T> node) {
		if (node != null) {
			aftTraversal(node.leftChild);
			aftTraversal(node.rightChild);
			node.list();
		}
	}

	//向控制台输出二叉树
	public void toStdIO() {
		ArrayList<Node<T>> list = new ArrayList<Node<T>>();
		list.add(root);
		int curLevel = 0;
		while (++curLevel < level) {
			int count = (int) (Math.pow(2, curLevel) - 1);
			for (int i = 0; i < (int) Math.pow(2, curLevel); i++) {
				Node<T> node = list.get(count + i - 1);
				if (node != null) {
					list.add(list.get(count + i - 1).leftChild);
					list.add(list.get(count + i - 1).rightChild);
				} else {
					list.add(null);
					list.add(null);
				}
			}
		}
		for (int i = 1; i <= level; i++) {
			for (int j = 0; j < (int) Math.pow(2, i - 1); j++) {
				for (int k = 0; k < (int) (Math.pow(2, level) - (int) Math.pow(2, i - 1)) / (int) Math.pow(2, i - 1); k++) {
					System.out.print(" ");
				}
				if (i > 2 && j > 0) {
					System.out.print(" ");
				}
				if (i == 2 && j == 1) {
					System.out.print(" ");
				}
				if (list.get((int) Math.pow(2, i - 1) + j - 1)==null) {
					System.out.print("N");
				}else {
					System.out.print(list.get((int) Math.pow(2, i - 1) + j - 1).t);
				}
				for (int k = 0; k < (int) (Math.pow(2, level) - (int) Math.pow(2, i - 1)) / (int) Math.pow(2, i - 1); k++) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	@SuppressWarnings("hiding")
	private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
		T t;
		Node<T> leftChild;
		Node<T> rightChild;

		public Node(T t) {
			this.t = t;
		}

		public void list() {
			System.out.println(t.toString());
		}

		@Override
		public int compareTo(Node<T> o) {
			return t.compareTo(o.t);
		}
	}
}
