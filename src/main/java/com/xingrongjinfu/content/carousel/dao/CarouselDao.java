package com.xingrongjinfu.content.carousel.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.carousel.model.Carousel;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("carouselDao")
public class CarouselDao extends DynamicObjectBaseDao implements ICarouselDao
{


    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TableDataInfo> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<TableDataInfo> carouselPageInfo = null;
        try
        {
        	carouselPageInfo = (List<TableDataInfo>) this.findForList("ContentCarouselMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return carouselPageInfo;

    }
    
    /**
     * 修改信息
     * 
     */
    public int updateCarouselInfo(Carousel carousel)
    {
        return this.update("ContentCarouselMapper.updateCarouselInfo", carousel);
    }

    /**
     * 添加信息
     * 
     */
	public int addCarouselInfo(Carousel carousel) {
		return this.save("ContentCarouselMapper.addCarouselInfo", carousel);
	}

	/**
     * 通过ID查询
     * 
     */
	@Override
	public Carousel findByCarouselId(String carouselId) {
		return (Carousel) this.findForObject("ContentCarouselMapper.findByCarouselId", carouselId);
	}

	@Override
	public int deleteById(Carousel carousel) {
		return (int) this.delete("ContentCarouselMapper.deleteCarouselInfo", carousel);
	}

}
