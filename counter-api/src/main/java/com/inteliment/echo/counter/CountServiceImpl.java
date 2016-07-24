package com.inteliment.echo.counter;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


@Service("countService")
public class CountServiceImpl implements CountService {
	
	private final static Log LOG = LogFactory.getLog(CountServiceImpl.class.getName());

	
	private static final String DELIM = ", ?.!:\"\"''\n"; 
	private static final String TEST_FILE_NAME = "test.txt";
	

	
	public CountResponseData getWordCount(int x) {
		if (x <= 0) {
			LOG.info(new StringBuffer().append("Invalid topX input :").append(x));
			return null;
		}
		Map<String, Integer> wordCountMap = convertContentToWordCountMap();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(wordCountMap.entrySet());
		CountResponseData result = new CountResponseData();
		result.setCounts(list);
		result.generateTopX(x);;
		
		return result;
		
	}
	
	
	private Map<String, Integer> convertContentToWordCountMap(){
		
		String content = this.getStringFromFile();
		if(content == null){
			return null;
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		StringTokenizer token = new StringTokenizer(content);
		while (token.hasMoreTokens()) {
			String word = token.nextToken(DELIM).toLowerCase();
			if (map.containsKey(word)) {
				int count = map.get(word);
				map.put(word, count + 1);
			} else
				map.put(word, 1);
		}
		return map;
	}

	
	public CountResponseData getWordCount(SearchRequestData searchText) {

		if (searchText == null) return null;
		
		String[] words = searchText.getSearchText();
		if (words == null || words.length==0){
			return null;
		}
		Map<String, Integer> wordCountMap = convertContentToWordCountMap();
		List<Entry<String, Integer>> resultList = new ArrayList<Entry<String, Integer>>();
		for (String aWord: words){
			Integer count = wordCountMap.get(aWord.toLowerCase()) == null ? 0 : wordCountMap.get(aWord.toLowerCase());
			resultList.add(new SimpleEntry<String, Integer>(aWord,count));
		}
		
		CountResponseData response = new CountResponseData();
		response.setCounts(resultList);
		return response;
		
	}

	private String getStringFromFile(){
		try {
			URL url = this.getClass().getClassLoader().getResource(TEST_FILE_NAME);
			LOG.info(new StringBuffer().append("Read ").append(url).toString());
			String content = new String(Files.readAllBytes(Paths.get(url.getFile())));
			return content;
		} catch (IOException e1) {
			LOG.info(e1);
		}
		return null;
	}

}
