package com.yrtech.wx.capp.portal.util;

import java.util.List;

public class PageView<T> {
	/** ��ҳ��� **/  
    private List<T> records;  
    /** ҳ�뿪ʼ����ͽ������� **/  
    private PageIndex pageindex;  
    /** ��ҳ�� **/  
    private long totalpage = 1;  
    /** ÿҳ��ʾ��¼�� **/  
    private int maxresult = 12;  
    /** ��ǰҳ **/  
    private int currentpage = 1;  
    /** �ܼ�¼�� **/  
    private long totalrecord;  
    /** βҳ **/  
    private int lastpage;  
    /** ҳ������ **/  
    private int pagecode = 15;  
    /** Ҫ��ȡ��¼�Ŀ�ʼ���� **/  
    public int getFirstResult() {  
        return (this.currentpage-1)*this.maxresult;  
    }  
    /**ȡ����ҳ**/  
    public int getTopPageNo() {  
        return 1;  
    }  
    /** ȡ��βҳ**/  
    public long getBottomPageNo() {  
        return getTotalpage();  
    }  
      
    /**��һҳ**/  
    public int getPreviousPageNo() {  
        if (currentpage <= 1) {  
            return 1;  
        }  
        return currentpage - 1;  
    }  
      
    /**��һҳ* */  
    public long getNextPageNo() {  
        if (currentpage >= getBottomPageNo()) {  
            return getBottomPageNo();  
        }  
        return currentpage + 1;   
    }  
      
    public int getPagecode() {  
        return pagecode;  
    }  
  
    public void setPagecode(int pagecode) {  
        this.pagecode = pagecode;  
    }  
  
    public PageView(int maxresult, int currentpage) {  
        this.maxresult = maxresult;  
        this.currentpage = currentpage;  
    }  
      
    public void setPageData(PageData<T> pageData){  
        setTotalrecord(pageData.getTotalRecords());  
        setRecords(pageData.getResultlist());  
    }  
      
    public long getTotalrecord() {  
        return totalrecord;  
    }  
    public void setTotalrecord(long totalrecord) {  
        this.totalrecord = totalrecord;  
        setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);  
    }  
    public List<T> getRecords() {  
        return records;  
    }  
    public void setRecords(List<T> records) {  
        this.records = records;  
    }  
    public PageIndex getPageindex() {  
        return pageindex;  
    }  
    public long getTotalpage() {  
        return totalpage;  
    }  
    public void setTotalpage(long totalpage) {  
        this.totalpage = totalpage == 0 ? 1 : totalpage;  
        this.pageindex = getPageIndex(pagecode, currentpage, totalpage);  
    }  
    public int getMaxresult() {  
        return maxresult;  
    }  
    public int getCurrentpage() {  
        return currentpage;  
    }  
  
  
    public int getLastpage() {  
        return lastpage;  
    }  
      
  public static PageIndex getPageIndex(long viewpagecount, int currentPage, long totalpage){  
        long startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);  
        long endpage = currentPage+viewpagecount/2;  
        if(startpage<1){  
            startpage = 1;  
            if(totalpage>=viewpagecount) endpage = viewpagecount;  
            else endpage = totalpage;  
        }  
        if(endpage>totalpage){  
            endpage = totalpage;  
            if((endpage-viewpagecount)>0) startpage = endpage-viewpagecount+1;  
            else startpage = 1;  
        }  
        return new PageIndex(startpage, endpage);  
  }  
    
    public PageView() {  
           
    }  
      
    public void setCurrentpage(int currentpage) {  
        this.currentpage = currentpage;  
    }  
    public void setMaxresult(int maxresult) {  
        this.maxresult = maxresult;  
    }  
}
