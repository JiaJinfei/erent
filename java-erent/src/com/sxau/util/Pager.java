package com.sxau.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;


public class Pager {

	int currentPageNo = 1;//当前页号
	int totalPageCount = 1;//总页号
	int pageCount = 6;//每页记录数
	boolean hasNext;//是否有下一页
	boolean hasPrevious;//是否有上一页
	
	int totalCount;//总记录数
	int start;//当前页起始位置
	int end;//当前页结束位置
	
	String urlLink;
	
	public Pager() {
	}
	
	public Pager(String urlLink) {
		this.urlLink = urlLink;
	}
	
	public Pager(String urlLink,HttpServletRequest request) {
		
		this.urlLink = urlLink;
		 
		 Map<String,String[]> parameters = request.getParameterMap(); 
		 
		 for (Entry<String, String[]> parameter : parameters.entrySet()){
			 String key = parameter.getKey();
			 if (!"1".equals(key) && !"cno".equals(key)){
				 String[] values = parameter.getValue();
				 String value_str = "";
				 for (String value : values){
					 value_str += value + ",";
				 }
				 value_str = value_str.substring(0, value_str.length() - 1);
				 this.urlLink += "&" + key + "=" + value_str;
			 }
			
		 }
		 //获取页码
		String cno = request.getParameter("cno");
		if(StrUtil.isNotEmpty(cno)){
			this.setCurrentPageNo(Integer.parseInt(cno));
		}
		request.setAttribute("pager", this);
	}
	
	
	
	/**
	 * 产生求总记录数sql
	 * @return
	 */
	public String getCountSql(String sql){
		return "select count(1) from (" + sql + ") ";
	}
	
	/**
	 * 产生分页sql
	 * @return
	 */
	public String getPageSql(String sql){
		//起始位置
		int start = pageCount * (currentPageNo  -1 ) + 1;
		//结束位置
		int end = pageCount * currentPageNo; 
		sql = "select page2.*,page2.rn from (select page1.*,rownum rn  from (" + sql + ")page1) page2 where page2.rn >= " + start + " and page2.rn <= " + end;
		return sql;
	}
	
	
	/*
	 * Getters and Setters
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrevious() {
		return hasPrevious;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		
		this.totalCount = totalCount;
		/*
		 * 计算总页号 ,是否有上一页，是否有下一页
		 */
		
		//总页号
		if(totalCount != 0){
			totalPageCount = totalCount / pageCount;
			if (totalCount % pageCount > 0){
				totalPageCount += 1;
			}
		}
		
		//是否有上一页
		if (currentPageNo != 1){
			hasPrevious = true;
		}
		//是否有下一页
		if (currentPageNo < totalPageCount){
			hasNext = true;
		}
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	
	
}
