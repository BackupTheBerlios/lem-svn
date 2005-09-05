/*
 * AssignmentActionTest.java
 * JUnit based test
 *
 * Created on July 3, 2005, 10:59 PM
 */

package util;

public class LinkedPriorityQueueTest extends junit.framework.TestCase {

	Integer one = new Integer( 1 );
	Integer two = new Integer( 2 );
	Integer three = new Integer( 3 );
	Integer four = new Integer( 4 );
	Integer five = new Integer( 5 );
	Integer six = new Integer( 6 );

	public LinkedPriorityQueueTest(String testName) {
		super(testName);
	}

	public void testMinQueue() {
		LinkedPriorityQueue q = new LinkedPriorityQueue( LinkedPriorityQueue.MIN_QUEUE );

		q.push( four );
		q.push( three );
		q.push( five );
		assertEquals( "3 should come off first", three, q.pop() );
		assertEquals( "4 next", four, q.pop() );
		assertEquals( "5 next", five, q.pop() );

		q.clear();

		q.push( five );
		assertEquals( "5 should be the only element of the list", five, q.pop() );

		q.clear();
		assertEquals( "Popping an empty queue gives null", null, q.pop() );
	}

	public void testMaxQueue() {
		LinkedPriorityQueue q = new LinkedPriorityQueue( LinkedPriorityQueue.MAX_QUEUE );

		q.push( four );
		q.push( three );
		q.push( five );
		assertEquals( "5 should come off first", five, q.pop() );
		assertEquals( "4 next", four, q.pop() );
		assertEquals( "3 next", three, q.pop() );

		q.clear();

		q.push( five );
		assertEquals( "5 should be the only element of the list", five, q.pop() );

		q.clear();
		assertEquals( "Popping an empty queue gives null", null, q.pop() );
	}
}
