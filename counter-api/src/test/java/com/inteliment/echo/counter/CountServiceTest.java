package com.inteliment.echo.counter;

import org.junit.Test;


import junit.framework.Assert;

public class CountServiceTest {
	@Test
	public void testReadFile(){
		CountService cs = new CountServiceImpl();

		//searchText is null
		Assert.assertNull(cs.getWordCount(null));
		
		//empty search request
		Assert.assertNull(cs.getWordCount(new SearchRequestData()));
		
		//normal case
		String[] word = new String[]{"Vel","ipsuM", "Eu"};
		SearchRequestData requestData = new SearchRequestData();
		requestData.setSearchText(word);
		String expected = "Vel|17\nipsuM|11\nEu|13\n";
		Assert.assertEquals(expected, cs.getWordCount(requestData).toString());
		
		//Top x is invalid
		Assert.assertNull(cs.getWordCount(0));
		Assert.assertNull(cs.getWordCount(-1));
		
		//Top x normal
		expected = "vel|17\neget|17\nsed|16\n";
		Assert.assertEquals(expected, cs.getWordCount(3).toString());


	}
}
