package edu.algorithm;

import java.util.Arrays;
import java.util.Random;

public class ArraySort {

	private static int[] array = new int[100000];
	private static int[] array1 = new int[100000];
	private static int[] array2 = new int[100000];
	private static int[] array3 = new int[100000];
	private static int[] array4 = new int[100000];
	private static Random r = new Random();
	private static long time = 0l;

	private static void genRandomArray() {
		for (int i = 0; i < array.length; i++) {
			array[i] = r.nextInt(100000);
		}
		array1 = Arrays.copyOf(array, array.length);
		array2 = Arrays.copyOf(array, array.length);
		array3 = Arrays.copyOf(array, array.length);
		array4 = Arrays.copyOf(array, array.length);
	}

	private static void start() {
		time = System.currentTimeMillis();
	}

	private static void endAndPrint() {
		time = System.currentTimeMillis() - time;
		System.out.println(time);
	}

	public static void main(String[] args) {
		genRandomArray();

		start();
		bubbleSort(array);
		endAndPrint();

		start();
		selectionSort(array1);
		endAndPrint();

		start();
		insertSort(array2);
		endAndPrint();

		start();
		shellSort(array3);
		endAndPrint();

		start();
		quickSort(array4, 0, array.length - 1);
		endAndPrint();
	}

	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < i; j++) {
				if (array[i] < array[j]) {
					swap(array, i, j);
				}
			}
		}
	}

	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			swap(array, min, i);
		}
	}

	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				int temp = array[i];
				for (int j = i; j > 0; j--) {
					if (array[j - 1] > temp) {
						swap(array, j, j - 1);
					} else {
						array[j] = temp;
						break;
					}
				}
			}
		}
	}

	public static void shellSort(int[] array) {
		for (int i = array.length / 2; i > 0; i /= 2) {
			for (int j = 0; j < i; j++) {
				for (int k = i; k < array.length; k += i) {
					if (array[k] < array[k - i]) {
						int temp = array[k];
						for (int l = k; l > 0; l -= i) {
							if (array[l - i] > temp) {
								swap(array, l, l - i);
							} else {
								array[l] = temp;
								break;
							}
						}
					}
				}
			}
		}
	}

	public static void quickSort(int[] array, int start, int end) {
		if (start >= end || start < 0 || end >= array.length) {
			return;
		}
		if (start + 1 == end) {
			if (array[start] > array[end]) {
				swap(array, start, end);
			}
			return;
		}
		int center = (start + end) / 2;
		int sign = array[center];
		int left = start;
		int right = end;
		while (left != right) {
			while (array[left] <= sign && left < right)
				left++;
			while (array[right] >= sign && left < right)
				right--;
			if (array[left] > sign && array[right] < sign) {
				swap(array, left, right);
			}
		}
		if (left == right
				&& ((left < center && array[left] > array[center]) || (left > center && array[left] < array[center]))) {
			swap(array, center, left);
		}
		quickSort(array, start, left - 1);
		quickSort(array, right, end);
	}

	private static void swap(int[] array, int i, int j) {
		if (i != j) {
			array[i] += array[j];
			array[j] = array[i] - array[j];
			array[i] = array[i] - array[j];
		}
	}

	private static void printInLine() {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
