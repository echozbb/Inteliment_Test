package com.inteliment.echo.counter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class CountResponseData {

	private List<Entry<String, Integer>> counts;

	public List<Entry<String, Integer>> getCounts() {
		return counts;
	}

	public void setCounts(List<Entry<String, Integer>> counts) {
		this.counts = counts;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Entry<String, Integer> count : counts){
			sb.append(count.getKey()).append("|").append(count.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void generateTopX(int x){
		sortCounts();	//sort by counts
		this.counts = this.counts.subList(0, x);
	}

	private void sortCounts(){
		if (counts == null || counts.size()==0) return;
		
		Collections.sort(this.counts, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		
		
	}
}
