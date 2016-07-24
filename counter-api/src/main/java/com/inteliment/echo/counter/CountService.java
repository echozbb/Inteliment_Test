package com.inteliment.echo.counter;

public interface CountService {
	
	CountResponseData getWordCount(int x);
	
	CountResponseData getWordCount(SearchRequestData searchInput);
	
}
