package org.framework.core.web;

import javax.servlet.http.HttpServletRequest;
import org.framework.base.util.PageUtilEntity;
import org.framework.core.uitl.MapDataUtil;

import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 表格数据对象处理
 * 
 * @author y
 */
public class DatatablesSupport
{
    /** 请求标识 */
    private String sEcho;
    /** 列数量 */
    private int iColumns;
    /** 每页开始数标 */
    private int iDisplayStart;
    /** 每页显示条数 */
    private int iDisplayLength;
    /** 单排序列的下标 */
    private int iSortCol;
    /** 单排序的方法 */
    private String sSortDir;
    /** 单排序列名 */
    private String sortCol;
    /** 排序的列数 */
    private int iSortingCols;
    /** 表列集合 */
    private String[] mDataProp;

    /**
     * 封装分页对象
     */
    public static PageUtilEntity getPageUtilEntity(int page, int size, String sortCol, String sSortDir)
    {
        PageUtilEntity pageUtilEntity = new PageUtilEntity();
        pageUtilEntity.setPage(page);
        pageUtilEntity.setSize(size);
        pageUtilEntity.setOrderByColumn(sortCol);
        pageUtilEntity.setIsAsc(sSortDir);
        return pageUtilEntity;
    }

    public static PageUtilEntity buildPageRequest(HttpServletRequest request)
    {
        DatatablesSupport adapter = new DatatablesSupport(request);
        PageUtilEntity pr = getPageUtilEntity(adapter.getiDisplayStart(), adapter.getiDisplayLength(),
                adapter.getSortCol(), adapter.getsSortDir());
        pr.setRelationMap(MapDataUtil.convertDataMap(request));
        return pr;
    }

    public DatatablesSupport(HttpServletRequest request)
    {
        convertColumn(request);
        convertDataProps(request);
    }

    private void convertColumn(final HttpServletRequest request)
    {
        this.sEcho = request.getParameter("sEcho");
        this.iColumns = Integer.valueOf(request.getParameter("iColumns"));
        this.iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
        this.iDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        if (ObjectUtil.isNotNull(request.getParameter("iSortCol_0")))
        {
            this.iSortCol = Integer.valueOf(request.getParameter("iSortCol_0"));
            this.sSortDir = request.getParameter("sSortDir_0");
        }
        this.iSortingCols = Integer.valueOf(request.getParameter("iSortingCols"));
    }

    private void convertDataProps(final HttpServletRequest request)
    {
        mDataProp = new String[iColumns];
        for (int i = 0; i < iColumns; i++)
        {
            mDataProp[i] = request.getParameter("mDataProp_" + i);
        }
        sortCol = mDataProp[iSortCol];
    }

    public String getsEcho()
    {
        return sEcho;
    }

    public void setsEcho(String sEcho)
    {
        this.sEcho = sEcho;
    }

    public int getiColumns()
    {
        return iColumns;
    }

    public void setiColumns(int iColumns)
    {
        this.iColumns = iColumns;
    }

    public int getiDisplayStart()
    {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart)
    {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength()
    {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength)
    {
        this.iDisplayLength = iDisplayLength;
    }

    public int getiSortCol()
    {
        return iSortCol;
    }

    public void setiSortCol(int iSortCol)
    {
        this.iSortCol = iSortCol;
    }

    public String getsSortDir()
    {
        return sSortDir;
    }

    public void setsSortDir(String sSortDir)
    {
        this.sSortDir = sSortDir;
    }

    public String getSortCol()
    {
        return sortCol;
    }

    public void setSortCol(String sortCol)
    {
        this.sortCol = sortCol;
    }

    public int getiSortingCols()
    {
        return iSortingCols;
    }

    public void setiSortingCols(int iSortingCols)
    {
        this.iSortingCols = iSortingCols;
    }

    public String[] getmDataProp()
    {
        return mDataProp;
    }

    public void setmDataProp(String[] mDataProp)
    {
        this.mDataProp = mDataProp;
    }

}