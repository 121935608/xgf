package com.xingrongjinfu.content.exhibition.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.content.exhibition.model.Exhibition;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("exhibitionDao")
public class ExhibitionDao extends DynamicObjectBaseDao implements IExhibitionDao
{


    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Exhibition> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<Exhibition> exhibitionPageInfo = null;
        try
        {
        	exhibitionPageInfo = (List<Exhibition>) this.findForList("ContentExhibitionMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return exhibitionPageInfo;

    }
    
    /**
     * 修改信息
     * 
     */
    public int updateExhibitionInfo(Exhibition exhibition)
    {
        return this.update("ContentExhibitionMapper.updateExhibitionInfo", exhibition);
    }

    /**
     * 添加信息
     * 
     */
	public int addExhibitionInfo(Exhibition exhibition) {
		return this.save("ContentExhibitionMapper.addExhibitionInfo", exhibition);
	}

	/**
     * 通过ID查询
     * 
     */
	@Override
	public Exhibition findByExhibitionId(String exhibitionId) {
		return (Exhibition) this.findForObject("ContentExhibitionMapper.findByExhibitionId", exhibitionId);
	}

}
