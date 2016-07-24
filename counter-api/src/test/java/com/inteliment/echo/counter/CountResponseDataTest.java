package com.inteliment.echo.counter;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Assert;
import org.junit.Test;
import com.inteliment.echo.counter.CountResponseData;

public class CountResponseDataTest {

	@Test
	public void testToString(){

		CountResponseData wc = new CountResponseData();
		List<Entry<String, Integer>> counts = new ArrayList<Entry<String, Integer>>();
		

		counts.add(new SimpleEntry<String, Integer>("echo", 12));
		counts.add(new SimpleEntry<String, Integer>("james", 13));
		counts.add(new SimpleEntry<String, Integer>("molly", 14));
		counts.add(new SimpleEntry<String, Integer>("ally", 15));
		counts.add(new SimpleEntry<String, Integer>("chilly", -1));

		wc.setCounts(counts);

		String expected = "echo|12\njames|13\nmolly|14\nally|15\nchilly|-1\n";
		Assert.assertEquals(expected, wc.toString());
		
		expected = "ally|15\nmolly|14\njames|13\n";
		wc.generateTopX(3);
		Assert.assertEquals(expected, wc.toString());

	}

}