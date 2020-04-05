import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {
	 private BubbleSort bubble;

	    @Before
	    public void setUp() throws Exception {
	        bubble = new BubbleSort();

	    }

	    @Test
	    public void test1() {
	        int a[] = new int[]{1,6,2,2,5};
	        int b[]=new int[]{2,2,2,1,2,13};
	        int a1[] = new int[]{1,2,2,5,6};
	        int b1[]=new int[]{1,2,2,2,2,13};
	        assertEquals(Arrays.toString(a1), Arrays.toString(bubble.BubbleSort(a)));
	        assertEquals(Arrays.toString(b1), Arrays.toString(bubble.BubbleSort(b)));     

	    }

	    @After
	    public void tearDown() throws Exception {
	        bubble = null;
	    }

}
