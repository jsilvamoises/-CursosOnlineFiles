package com.jsm.resources.util;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URLUtils {

	public static List<Long> decodeStringListToLong(String s){
		String[] content = s.split(",");
		List<Long> listLong = new ArrayList<>();
		
		for(String str:content) {
			listLong.add(new Long(str));
		}
		
		return listLong;
		//return Arrays.asList(s.split(",")).stream().map( x -> Integer.parseIn
	}
	
	
	public static List<Integer> decodeStringListToInteger(String s){
		String[] content = s.split(",");
		List<Integer> listInteger = new ArrayList<>();
		
		for(String str:content) {
			listInteger.add(new Integer(str));
		}
		
		return listInteger;
		//return Arrays.asList(s.split(",")).stream().map( x -> Integer.parseIn
	}
	
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
