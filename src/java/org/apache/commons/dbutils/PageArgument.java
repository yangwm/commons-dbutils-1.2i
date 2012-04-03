
package org.apache.commons.dbutils;

import java.io.Serializable;

/**
 * page argument 
 * 
 * @author yangwm in May 30, 2009 10:02:21 PM
 */
public class PageArgument implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1581427664355863626L;

    /**
     * current page number.
     */
    private int curPage = 1;

    /**
     * one page size.
     */
    private int pageSize = Integer.MAX_VALUE;

    /**
     * total row.
     */
    private long totalRow = 0;

    /**
     * total page.
     */
    private int totalPage = 1;


	/**
     * constructor for PageArgument.
     */
    public PageArgument() {
		super();
    }
    
    /**
     * constructor for PageArgument. 
	 *
     * @param curPage
     * @param pageSize
     */
    public PageArgument(int curPage, int pageSize) {
		super();
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    public void clear() {
        this.curPage = 1;
        this.pageSize = Integer.MAX_VALUE;
        this.totalRow = 0L;
		this.totalPage = 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PageArgument{curPage=");
        sb.append(curPage);
        sb.append(", pageSize=");
        sb.append(pageSize);
        sb.append(", totalRow=");
        sb.append(totalRow);
        sb.append(", totalPage=");
        sb.append(totalPage);
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Access method for the curPage property.
     *
     * @return   the current value of the curPage property
     */
    public int getCurPage() {
        return curPage;
    }

    /**
     * Sets the value of the curPage property.
     *
     * @param curPage the new value of the curPage property
     */
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    /**
     * Access method for the pageSize property.
     *
     * @return   the current value of the pageSize property
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     *
     * @param pageSize the new value of the pageSize property
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
	
	/**
     * Access method for the totalRow property.
     *
     * @return   the current value of the totalRow property
     */
    public long getTotalRow() {
        return totalRow;
    }

    /**
     * Sets the value of the totalRow property.
     *
     * @param totalRow the new value of the totalRow property
     */
    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }
	
	/**
     * Access method for the totalPage property.
     *
     * @return   the current value of the totalPage property
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Sets the value of the totalPage property.
     *
     * @param totalPage the new value of the totalPage property
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
