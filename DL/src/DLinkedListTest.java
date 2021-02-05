import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DLinkedListTest {

	private DLinkedList<Integer> empty;  
	private DLinkedList<Integer> single;
	private DLinkedList<Integer> multi;  
	private DLinkedList<Integer> multi2;


	static <E> DLinkedList<E> load(E... items)
	{
		DLinkedList<E> list = new DLinkedList<>();
		for (E value : items) {
			list.addLast(value);
		}
		return list;
	}


	@Before
	public void setUp()
	{
		empty = load();                          
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		single = load(8);                        
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		multi = load(4, 3, 5, 7, 1, 6);        
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );
		
		multi2 = load(5, 6, 5, 7, 3, 2);     
		assertEquals( multi2.toString(), "[5 6 5 7 3 2]" );
		assertEquals( multi2.toStringRev(), "[2 3 7 5 6 5]" );

	}


	@Test
	public void test_isEmpty()
	{
		assertTrue( empty.isEmpty() );
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		assertFalse( single.isEmpty() );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertFalse( multi.isEmpty() );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );
	}


	@Test
	public void test_addFirst()
	{
		empty.addFirst( 4 );
		assertEquals( empty.toString(), "[4]" );
		assertEquals( empty.toStringRev(), "[4]" );

		single.addFirst( 7 );
		assertEquals( single.toString(), "[7 8]" );
		assertEquals( single.toStringRev(), "[8 7]" );

		multi.addFirst( 8 );
		assertEquals( multi.toString(), "[8 4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4 8]");
	}



	@Test
	public void test_addLast()
	{
		empty.addLast( 7 );
		assertEquals( empty.toString(), "[7]" );
		assertEquals( empty.toStringRev(), "[7]" );

		single.addLast( 9 );
		assertEquals( single.toString(), "[8 9]" );
		assertEquals( single.toStringRev(), "[9 8]" );

		multi.addLast( 9 );
		assertEquals( multi.toString(), "[4 3 5 7 1 6 9]" );
		assertEquals( multi.toStringRev(), "[9 6 1 7 5 3 4]" );
	}

	@Test
	public void test_get()
	{
		try { empty.get(0); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		try { single.get(-1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		try { single.get(1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertTrue(single.get(0).equals(8));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );  

		try{ multi.get( 7 ); fail(); }
		catch (IndexOutOfBoundsException e) { }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		try{ multi.get( -2 ); fail(); }
		catch (IndexOutOfBoundsException e) { }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );  
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue(multi.get( 1).equals(3));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );  

		assertTrue(multi.get( 0).equals(4));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue(multi.get( 5 ).equals(6));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );



	}


	@Test
	public void test_set()
	{
		try { empty.set(0,4); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		try { single.set(-1,5); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		try { single.set(1,8); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertTrue(single.set(0, 9).equals(8));
		assertEquals( single.toString(), "[9]" );
		assertEquals( single.toStringRev(), "[9]" );

		try{ multi.set( 7, 3 ); fail(); }
		catch (IndexOutOfBoundsException e) { }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		try{ multi.set( -2 , 7); fail(); }
		catch (IndexOutOfBoundsException e) { }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue(multi.set( 0, 6 ).equals(4));
		assertEquals( multi.toString(), "[6 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 6]" );

		assertTrue(multi.set( 3, 5 ).equals(7));
		assertEquals( multi.toString(), "[6 3 5 5 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 5 5 3 6]" );

		assertTrue(multi.set( 5, 3 ).equals(6));
		assertEquals( multi.toString(), "[6 3 5 5 1 3]" );
		assertEquals( multi.toStringRev(), "[3 1 5 5 3 6]" );



	}


	@Test
	public void test_getFirst()
	{
		try { empty.getFirst(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }  
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		assertTrue( single.getFirst().equals(8) );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertTrue( multi.getFirst().equals(4) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );  
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );
	}

	@Test
	public void test_getLast()
	{
		try { empty.getLast(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		assertTrue(single.getLast().equals(8));
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertTrue(multi.getLast().equals(6));
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );  
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );
	}


	@Test
	public void test_contains()
	{
		assertFalse( empty.contains(7) );
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		assertTrue( single.contains(8) );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertFalse( single.contains(6) );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertFalse( multi.contains(9) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue( multi.contains(6) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue( multi.contains(5) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue( multi.contains(4) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );


	}


	@Test
	public void test_clear()
	{
		empty.clear(  );
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		single.clear(  );
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(), "[]" );

		multi.clear(  );
		assertEquals( multi.toString(), "[]" );
		assertEquals( multi.toStringRev(), "[]" );
	}


	@Test
	public void test_containsIter()
	{
		assertFalse( empty.containsIter(5) );
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		assertTrue( single.containsIter(8) );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertFalse( single.containsIter(7) );
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertFalse( multi.containsIter(9) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue( multi.containsIter(6) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue( multi.containsIter(5) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue( multi.containsIter(4) );
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );
	}


	@Test
	public void test_add()
	{
		empty.add(0, 2 );
		assertEquals( empty.toString(), "[2]" );
		assertEquals( empty.toStringRev(), "[2]" );

		try { single.add(-1, 6); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		try { single.add(2, 8); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		single.add(0, 2);
		assertEquals( single.toString(), "[2 8]" );
		assertEquals( single.toStringRev(), "[8 2]" );


		try { multi.add(-1, 6); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		try { multi.add(7, 3); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );


		multi.add(0, 4);  
		assertEquals( multi.toString(), "[4 4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4 4]" );

		multi.add(7, 7);
		assertEquals( multi.toString(), "[4 4 3 5 7 1 6 7]" );
		assertEquals( multi.toStringRev(), "[7 6 1 7 5 3 4 4]" );

		multi.add( 3, 5 );
		assertEquals( multi.toString(), "[4 4 3 5 5 7 1 6 7]" );
		assertEquals( multi.toStringRev(), "[7 6 1 7 5 5 3 4 4]" );


	}

	@Test
	public void test_removeFirst()
	{
		try { empty.removeFirst(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		assertTrue(single.removeFirst(  ).equals(8)); 
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(), "[]" );

		assertTrue(multi.removeFirst().equals(4));
		assertEquals( multi.toString(), "[3 5 7 1 6]" ); 
		assertEquals( multi.toStringRev(), "[6 1 7 5 3]");
	}
	@Test
	public void test_removeLast()
	{
		try { empty.removeLast(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ } 
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );


		assertTrue(single.removeLast(  ).equals(8)); 
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(), "[]" );

		assertTrue(multi.removeLast().equals(6));
		assertEquals( multi.toString(), "[4 3 5 7 1]" ); 
		assertEquals( multi.toStringRev(), "[1 7 5 3 4]");
	}


	@Test
	public void test_remove()
	{
		try { empty.remove(0); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );

		try { single.remove(-1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		try { single.remove(1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( single.toString(), "[8]" );
		assertEquals( single.toStringRev(), "[8]" );

		assertTrue(single.remove(0).equals(8));
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(), "[]" );  

		try{ multi.remove( 7 ); fail(); }
		catch (IndexOutOfBoundsException e) { }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		try{ multi.remove( -2 ); fail(); }
		catch (IndexOutOfBoundsException e) { }
		assertEquals( multi.toString(), "[4 3 5 7 1 6]" );  
		assertEquals( multi.toStringRev(), "[6 1 7 5 3 4]" );

		assertTrue(multi.remove( 1).equals(3));
		assertEquals( multi.toString(), "[4 5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5 4]" );  

		assertTrue(multi.remove( 0).equals(4));
		assertEquals( multi.toString(), "[5 7 1 6]" );
		assertEquals( multi.toStringRev(), "[6 1 7 5]" );

		assertTrue(multi.remove( 3).equals(6));
		assertEquals( multi.toString(), "[5 7 1]" );
		assertEquals( multi.toStringRev(), "[1 7 5]" );



	}

	@Test
	public void test_removeAll()
	{
		assertFalse(empty.removeAll(0));
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );


		assertTrue(single.removeAll( 8 )); 
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(), "[]" );

		assertTrue(multi.removeAll(4)); 
		assertEquals( multi.toString(), "[3 5 7 1 6]" ); 
		assertEquals( multi.toStringRev(), "[6 1 7 5 3]");

		assertTrue(multi.removeAll(7)); 
		assertEquals( multi.toString(), "[3 5 1 6]" ); 
		assertEquals( multi.toStringRev(), "[6 1 5 3]");

		assertTrue(multi.removeAll(6)); 
		assertEquals( multi.toString(), "[3 5 1]" ); 
		assertEquals( multi.toStringRev(), "[1 5 3]"); 
		
		assertTrue(multi2.removeAll(5));    //5 6 5 7 3 2
		assertEquals( multi2.toString(), "[6 7 3 2]" ); 
		assertEquals( multi2.toStringRev(), "[2 3 7 6]");


	}
	
	@Test
	public void test_removeAllIter()
	{
		assertFalse(empty.removeAllIter(0));
		assertEquals( empty.toString(), "[]" );
		assertEquals( empty.toStringRev(), "[]" );


		assertTrue(single.removeAllIter( 8 )); 
		assertEquals( single.toString(), "[]" );
		assertEquals( single.toStringRev(), "[]" );

		assertTrue(multi.removeAllIter(4)); 
		assertEquals( multi.toString(), "[3 5 7 1 6]" ); 
		assertEquals( multi.toStringRev(), "[6 1 7 5 3]");

		assertTrue(multi.removeAllIter(3)); 
		assertEquals( multi.toString(), "[5 7 1 6]" ); 
		assertEquals( multi.toStringRev(), "[6 1 7 5]");

		assertTrue(multi.removeAllIter(5)); 
		assertEquals( multi.toString(), "[7 1 6]" ); 
		assertEquals( multi.toStringRev(), "[6 1 7]"); 
		
		assertTrue(multi2.removeAllIter(5));    //5 6 5 7 3 2
		assertEquals( multi2.toString(), "[6 7 3 2]" ); 
		assertEquals( multi2.toStringRev(), "[2 3 7 6]");


	}



}