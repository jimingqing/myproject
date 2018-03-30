package com.yrtech.wx.capp.framework.core.paging;

/**
 * @author wanghui
 * @date 2012-04-15
 */
public class PagerService {

	public Pager getPager(String currentPage,String pagerMethod,int totalRows) {
        Pager pager = new Pager(totalRows);
        if (currentPage != null) {
            pager.refresh(Integer.parseInt(currentPage));
        }
        if (pagerMethod != null) {
            if (pagerMethod.equals("first")) {
                pager.first();
            } else if (pagerMethod.equals("previous")) {
                pager.previous();
            } else if (pagerMethod.equals("next")) {
                pager.next();
            } else if (pagerMethod.equals("last")) {
                pager.last();
            }
        }
        return pager;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
