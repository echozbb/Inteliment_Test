package com.inteliment.echo.counter;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountWordsRestController {
	
	private final static Log LOG = LogFactory.getLog(CountWordsRestController.class.getName());

	@Autowired
	CountService countService;

	
	@RequestMapping(value = "/top/{x}", method = RequestMethod.GET, produces = "text/csv")
	public ResponseEntity<String> getTop(@PathVariable("x") int x) {
		StringBuffer sb = new StringBuffer();
		LOG.info(sb.append("getTop ").append(x).toString());

		CountResponseData responseData = countService.getWordCount(x);
		if(responseData==null)	{
			return new ResponseEntity<String>(sb.toString(), HttpStatus.NOT_FOUND);
		}
		//String result = responseData.getTopXString(x);
		return new ResponseEntity<String>(responseData.toString(), HttpStatus.OK);
	}

	

	@RequestMapping(value = "/search/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountResponseData> search(@RequestBody SearchRequestData searchInput) {
		if(searchInput!=null){
			LOG.info(Arrays.asList(searchInput.getSearchText()));
		}else{
			LOG.info("SearchRequestData is null");
		}

		CountResponseData result = countService.getWordCount(searchInput);
		if(result==null)	{
			return new ResponseEntity<CountResponseData>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CountResponseData>(result, HttpStatus.OK);
	}

	
	

}
