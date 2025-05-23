class ListNode {

	private Object data;
	private ListNode next;

	public ListNode(Object o) {
		data = o;
		next = null;
	}

	public ListNode(Object o, ListNode nextNode) {
		data = o;
		next = nextNode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object o) {
		data = o;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

} // class ListNode

class EmptyListException extends RuntimeException {
	public EmptyListException() {
		super("List is empty");
	}
} // class EmptyListException

public class LinkedList {

	private ListNode head;
	private ListNode tail;

	private int length; // the length of the list

	public LinkedList() {
		head = tail = null;
		length = 0;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void addToHead(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else
			head = new ListNode(item, head);
		length++;
	}

	public void addToTail(Object item) {
		if (isEmpty())
			head = tail = new ListNode(item);
		else {
			tail.setNext(new ListNode(item));
			tail = tail.getNext();
		}
		length++;
	}

	public Object removeFromHead() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = head.getData();
		if (head == tail)
			head = tail = null;
		else
			head = head.getNext();
		length--;
		return item;
	}

	public Object removeFromTail() throws EmptyListException {
		Object item = null;
		if (isEmpty())
			throw new EmptyListException();
		item = tail.getData();
		if (head == tail)
			head = tail = null;
		else {
			ListNode current = head;
			while (current.getNext() != tail)
				current = current.getNext();
			tail = current;
			current.setNext(null);
		}
		length--;
		return item;
	}

	public int count() {
		return length;
	}

	// students need to revise toString method
	public String toString() {
		String str = "\n";
		ListNode current = head;
		while (current != null) {
			str = str + current.getData();
			current = current.getNext();
		}
		return str + "\n";
	}

	// to be completed ...
	// Method remove(int) is to remove a ListNode from the LinkedList with a
	// specific Member ID
	public void remove(int targetID) throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		}

		if (((FoodOrder) head.getData()).getMemberID() == targetID) {
			removeFromHead();
			return;
		}

		ListNode current = head;
		while (current.getNext() != null) {
			if (((FoodOrder) current.getNext().getData()).getMemberID() == targetID) {
				if (current.getNext() == tail) { // check removed is the last node in the linked list
					tail = current; // the second last node (current) will become the new tail
				}
				// set current to next point, next point --> next point
				current.setNext(current.getNext().getNext());
				length--;
				return;
			}
			// Update the current reference to point to the next node
			// until the FoodOrder object with a member ID equal to targetID is found
			// or the end of the linked list is reached
			current = current.getNext();

		}

	}

	// to be completed ...
	// Method add(Object) is to insert a new ListNode into the LinkedList in a
	// correct position
	public void add(Object item) {
		FoodOrder newOrder = (FoodOrder) item;

		if (isEmpty()) {
			addToHead(item);
			return;
		}
		// When Priority of the newOrder less than or equal to the priority of the
		// FoodOrder object at the head
		if (newOrder.getPriority() <= ((FoodOrder) head.getData()).getPriority()) {
			addToHead(item);
			return;
		}

		ListNode current = head;
		while (current.getNext() != null) {
			// the priority of the newOrder object is lower than or equal to the priority of
			// the next FoodOrder object
			if (newOrder.getPriority() <= ((FoodOrder) current.getNext().getData()).getPriority()) {
				ListNode newNode = new ListNode(item, current.getNext()); // set item to be the next node after current
				current.setNext(newNode); // set newNode after current.next
				length++;
				return;
			}
			current = current.getNext();
		}

		addToTail(item);
	}

} // class LinkedList
