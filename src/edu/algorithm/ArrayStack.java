package edu.algorithm;

import java.util.Arrays;
/**
 * 
 * @author WangGang
 * @describe 采用数组实现，不支持多线程
 * @param <T>
 */
public class ArrayStack<T> {
	private T[] array;
	private int elementCount = 0;
	private int capacity = 1024;
	private static final int MIN_CAPACITY = 0;
	private static final int MAX_CAPACITY = (2 << 30) - 1;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		array = (T[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int size) {
		if (size < MIN_CAPACITY || size > MAX_CAPACITY) {
			capacity = size;
		}
		array = (T[]) new Object[capacity];
	}

	public T push(T t) {
		if (elementCount+1>capacity&&elementCount+1<MAX_CAPACITY) {
			if (capacity*2<=MAX_CAPACITY) {
				capacity*=2;
			}else {
				capacity=MAX_CAPACITY;
			}
			array=Arrays.copyOf(array, capacity);
		}else if(elementCount+1>MAX_CAPACITY){
			throw new RuntimeException("Out of MAX_CAPACITY:"+elementCount);
		}
		array[elementCount]=t;
		elementCount++;
		return t;
	}

	public T pop() {
		if (elementCount<=0) {
			return null;
		}
		T t=array[elementCount-1];
		array[elementCount-1]=null;
		elementCount--;
		return t;
	}

	public T peek() {
		if (elementCount<=0) {
			return null;
		}
		T t=array[elementCount-1];
		return t;
	}

	public boolean empty() {
		return elementCount==0;
	}

	public T serach(T t) {
		for (int i = 0; i < elementCount; i++) {
			if (t.equals(array[i])) {
				return array[i];
			}
		}
		return null;
	}
	
}