package cse12pa1student;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BasketTest {

	public static Collection<Object[]> BAGNUMS =
			Arrays.asList(new Object[][] { {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12} });
	private int bagType;

	public BasketTest(int bagType) {
		super();
		this.bagType = bagType;
	}

	@Parameterized.Parameters(name = "Basket{index}")
	public static Collection<Object[]> bags() {
		return BAGNUMS;
	}
	
	private Basket makeBasket() {
		switch(this.bagType) {
			case 0: return new Basket0();
			case 1: return new Basket1();
			case 2: return new Basket2();
			case 3: return new Basket3();
			case 4: return new Basket4();
			case 5: return new Basket5();
			case 6: return new Basket6();
			case 7: return new Basket7();
			case 8: return new Basket8();
			case 9: return new Basket9();
			case 10: return new Basket10();
			case 11: return new Basket11();
			case 12: return new Basket12();
		}
		return null;
	}
	
	@Test
	public void addedHasCount1() {
		Basket basketToTest = makeBasket();

		Item i = new Item("Shampoo", 5);
		basketToTest.addToBasket(i);
		assertEquals(1, basketToTest.count());
	}

	@Test 
	//checks if add method allows null in basket
	public void testAddToBasket2(){
		Basket basketToTest= makeBasket();

		basketToTest.addToBasket(null);
		//test fail if it added null; should be 0
		assertEquals(0,basketToTest.count());
	}

	@Test
	//tests if add method is capable of resizing if surpasses limit
    public void testAddToBasket3() {
        Basket basketToTest = makeBasket();

        for (int i = 0; i < 11; i++) {
            Item item = new Item("Item" + i, 100 * (i + 1));
            basketToTest.addToBasket(item);
        }

        // checks if there are 11 items in basket
        assertEquals(11, basketToTest.count());
    }

	@Test 
	//adds 3 different items and removes them 1 by 1
	public void removeFromBasket1(){
		Basket basketToTest= makeBasket();
		Item a = new Item("Apple", 15);
		Item b = new Item("banana", 20);
		Item c = new Item("cucumber", 25);

		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(c);

		boolean removed1 = basketToTest.removeFromBasket(c);
		assertEquals(2, basketToTest.count());

		boolean removed2 = basketToTest.removeFromBasket(b);
		assertEquals(1, basketToTest.count());

		boolean removed3 = basketToTest.removeFromBasket(b);
		assertEquals(0, basketToTest.count());
	}

	@Test
	//checks if method tries to remove item not in basket again
	public void testRemoveFromBasket2(){
		Basket basketToTest= makeBasket();
		Item a = new Item("Apple", 15);
		Item b = new Item("banana", 20);
		Item c = new Item("cucumber", 25);

		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(c);

		//removes cucumber
		boolean r1 = basketToTest.removeFromBasket(c);

		assertFalse(basketToTest.removeFromBasket(c));
	}

	@Test
	//checks if method removes item not in basket
	public void testRemoveFromBasket3() {
		Basket basketToTest = makeBasket();

		Item item1 = new Item("Shampoo", 5);
		Item item2 = new Item("Shampoo", 5);

		basketToTest.addToBasket(item1);

		boolean res = basketToTest.removeFromBasket(item2);

		assertFalse(res);
	}

	@Test
	//tests if empty() works - all succeeded
	public void checkEmpty(){
		Basket basketToTest= makeBasket();
		Item a = new Item("Apple", 15);
		Item b = new Item("banana", 20);
		Item c = new Item("cucumber", 25);

		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(c);

		basketToTest.empty();

		assertEquals(0, basketToTest.count());
	}

	@Test
	//checks if count method counts items, including dublicates, in basket properly
	public void testCount1() {
		Basket basketToTest = makeBasket();

		Item a = new Item("Apple", 25);
		Item b = new Item("Banana", 18);
		Item c = new Item("Cranberry", 30);

		basketToTest.addToBasket(a);
		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(c);
		basketToTest.addToBasket(c);
		basketToTest.addToBasket(c);

		int totalCount = basketToTest.count();

		assertEquals(6, totalCount); //checks if there are 6 items total
	}
	
	@Test
	//checks if every newly added item is counted 
	//and if an item not added to basket will be counted
	 public void testCountItem1(){
		Basket basketToTest= makeBasket();

        Item a = new Item("Cheese", 1200);
        Item b = new Item("Crackers", 20);
        Item c = new Item("Milk", 400);

        basketToTest.addToBasket(a);
        basketToTest.addToBasket(b);
        basketToTest.addToBasket(c);

        // Test counting items
        assertEquals(1, basketToTest.countItem(a)); 
        assertEquals(1, basketToTest.countItem(b)); 
        assertEquals(1, basketToTest.countItem(c)); 
        assertEquals(0, basketToTest.countItem(new Item("Blueberries", 450))); // no blueberries
    }

	@Test
	//checks if it'll count a duplicate item not in basket
	public void testCountItem2() {
		Basket basketToTest = makeBasket();

		Item a = new Item("Shampoo", 5);
		Item b = new Item("Shampoo", 5);

		basketToTest.addToBasket(a);

		int count = basketToTest.countItem(b);

		assertNotEquals(1, count); //test fails if false
	}

	@Test
	//tests if countItem will count null as an item
	public void testCountItem3() {
		Basket basketToTest = makeBasket();

		Item a = new Item("Strawberry", 80);
		Item b = null;

		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);

		assertEquals(1, basketToTest.countItem(a));
		//null should be counted as 0 otherwise test fails
		assertEquals(0, basketToTest.countItem(b)); 
	}

	@Test
	//tests if countItem method can count the amount of a specific item
	public void testCountItem4() {
		Basket basketToTest = makeBasket();
	
		Item a = new Item("Apple", 20);
		Item b = new Item("Banana", 100);
	
		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(b);
	
		//calculates count of each item
		int appleCount = basketToTest.countItem(a);
		int bananaCount = basketToTest.countItem(b);
		int nonExistentItemCount = basketToTest.countItem(new Item("Strawberry", 85));
	
		assertEquals(1, appleCount); //1 apple
		assertEquals(3, bananaCount); //3 banana
		assertEquals(0, nonExistentItemCount); //0 strawberry
	}

	@Test
	//tests that method caculates totalCost
	public void testTotalCost1() {
        Basket basketToTest= makeBasket();

        Item a = new Item("Grapes", 129); 
        Item b = new Item("Berries", 23); 
        Item c = new Item("Cheese", 3290); 

        basketToTest.addToBasket(a);
        basketToTest.addToBasket(b);
        basketToTest.addToBasket(c);

        //checks if calculation is right
        assertEquals(129 + 23 + 3290, basketToTest.totalCost()); // equal to 3442
    }

	@Test
	//checks if method will return 0 if basket is empty
    public void testTotalCost2() {
        Basket basketToTest = makeBasket();
	
        int totalCost = basketToTest.totalCost();
        // confirms if total cost is 0 since it's empty
        assertEquals(0, totalCost);
    }

	@ Test
	//returns false if item not in basket is removed
	public void testRemoveAllFromBasket1() {
		Basket basketToTest = makeBasket();
	
		Item a = new Item("Apple", 50);
		Item b = new Item("Banana", 30);

		basketToTest.addToBasket(a);
		basketToTest.addToBasket(b);
		basketToTest.addToBasket(a);
		basketToTest.addToBasket(a);
		
		//remove apple
		boolean removedAllApples = basketToTest.removeAllFromBasket(a);
	
		int aCount = basketToTest.countItem(a);
		int bCount = basketToTest.countItem(b);
	
		assertTrue(removedAllApples);
		//makes sure item is removed and other other items aren't affected
		assertEquals(0, aCount); //0 apples
		assertEquals(1, bCount); //1 banana
	}

	@Test
	//returns false if trying to remove item not in basket
	//returns true once all copies of specific item is removed
    public void testRemoveAllFromBasket2() {
        Basket basketToTest = makeBasket();
        Item a = new Item("Apple", 100);
        Item b = new Item("Banana", 50);

        //Item not in the basket
        assertFalse(basketToTest.removeAllFromBasket(a));
        
        basketToTest.addToBasket(a);
        basketToTest.addToBasket(b);
        basketToTest.addToBasket(a);
        
        assertTrue(basketToTest.removeAllFromBasket(a));
        
        assertFalse(basketToTest.countItem(a) > 0);
    }

	@Test
	//tests how removeAll.. handles null
    public void testRemoveAllFromBasket3() {
        Basket basketToTest = makeBasket();
        Item a = new Item("Apple", 100);
        Item n = null;
        
		//return false when dealing with null item
        assertFalse(basketToTest.removeAllFromBasket(n));
        
        basketToTest.addToBasket(a);

        //return false when dealing with null item
        assertFalse(basketToTest.removeAllFromBasket(n));
        
		//makes sure other items in basket not affected
		assertEquals(1, basketToTest.countItem(a)); 
    }

	@Test
	//tests that removeAllFromBasket doesn't affect other items
	public void testRemoveAllFromBasket4() {
		 Basket basketToTest = makeBasket();
        // Create items
        Item itemA = new Item("Item A", 100);
        Item itemB = new Item("Item B", 200);
        Item itemC = new Item("Item C", 300);

        // Add items to the basket
        basketToTest.addToBasket(itemA);
        basketToTest.addToBasket(itemA);
        basketToTest.addToBasket(itemB);
        basketToTest.addToBasket(itemC);
        basketToTest.addToBasket(itemA);

        // Check initial counts
        assertEquals(3, basketToTest.countItem(itemA));
        assertEquals(1, basketToTest.countItem(itemB));
        assertEquals(1, basketToTest.countItem(itemC));

        // Remove all copies of itemA
        boolean result = basketToTest.removeAllFromBasket(itemA);

        // Check if all copies of itemA are removed
        assertEquals(true, result);
        assertEquals(0, basketToTest.countItem(itemA));

        // Check if itemB and itemC counts remain unaffected
        assertEquals(1, basketToTest.countItem(itemB));
        assertEquals(1, basketToTest.countItem(itemC));
    }

}


