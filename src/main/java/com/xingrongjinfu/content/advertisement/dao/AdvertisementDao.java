package com.xingrongjinfu.content.advertisement.dao;

import java.util.List;
import org.framework.base.util.PageUtilEntity;
import org.framework.base.util.TableDataInfo;
import org.framework.core.dao.DynamicObjectBaseDao;
import org.springframework.stereotype.Repository;

import com.xingrongjinfu.commodity.label.model.Label;
import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.model.Carousel;

/**
 * 数据层处理
 * 
 * @author 
 */
@Repository("advertisementDao")
public class AdvertisementDao extends DynamicObjectBaseDao implements IAdvertisementDao
{


    /**
     * 根据条件分页查询
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Advertisement> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {

        List<Advertisement> advertisementPageInfo = null;
        try
        {
        	advertisementPageInfo = (List<Advertisement>) this.findForList("ContentAdvertisementMapper.pageInfoQuery", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return advertisementPageInfo;

    }
    
    /**
     * 修改信息
     * 
     */
    public int updateAdvertisementInfo(Advertisement advertisement)
    {
        return this.update("ContentAdvertisementMapper.updateAdvertisementInfo", advertisement);
    }

    /**
     * 添加信息
     * 
     */
	public int addAdvertisementInfo(Advertisement advertisement) {
		return this.save("ContentAdvertisementMapper.addAdvertisementInfo", advertisement);
	}

	/**
     * 通过ID查询
     * 
     */
	@Override
	public Advertisement findByAdvertisementId(String advertisementId) {
		return (Advertisement) this.findForObject("ContentAdvertisementMapper.findByAdvertisementId", advertisementId);
	}

}
