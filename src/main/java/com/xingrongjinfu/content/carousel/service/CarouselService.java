package com.xingrongjinfu.content.carousel.service;

import java.util.List;

import org.framework.base.util.PageUtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingrongjinfu.content.advertisement.model.Advertisement;
import com.xingrongjinfu.content.carousel.common.CarouselConstant;
import com.xingrongjinfu.content.carousel.dao.ICarouselDao;
import com.xingrongjinfu.content.carousel.model.Carousel;
import com.xingrongjinfu.utils.ObjectUtil;

/**
 * 业务层处理
 * 
 * @author
 */
@Service("carouselService")
public class CarouselService implements ICarouselService
{

    @Autowired
    private ICarouselDao carouselDao;
  
    /**
     * 根据条件分页查询
     */
    @Override
    public List<Carousel> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        return carouselDao.pageInfoQuery(pageUtilEntity);
    }
    
    /**
     * 修改状态
     * 
     */
    public int changeCarouselStatus(Carousel carousel)
    {
        return carouselDao.updateCarouselInfo(carousel);
    }

    /**
     * 添加
     * 
     */
	@Override
	public int addCarouselInfo(Carousel carousel) {
		return carouselDao.addCarouselInfo(carousel);
	}

	/**
     * 修改信息
     * 
     */
	@Override
	public int updateCarouselInfo(Carousel carousel) {
		return carouselDao.updateCarouselInfo(carousel);
	}

	/**
     * 通过ID查询
     */
	@Override
	public Carousel findByCarouselId(String carouselId) {
		return carouselDao.findByCarouselId(carouselId);
	}

	@Override
	public int deleteById(Carousel carousel) {
		return carouselDao.deleteById(carousel);
	}

	@Override
	public Carousel findByCarouselName(String carouselName) {
		return carouselDao.findByCarouselName(carouselName);
	}

	@Override
	public String checkNameUnique(Carousel carousel) {
		if(carousel.getCarouselId()==null){
			carousel.setCarouselId("-1");
    	}
    	String carouselName = carousel.getCarouselName();
    	String carouselId = carousel.getCarouselId();
    	carousel = new Carousel();
    	carousel.setCarouselName(carouselName);
    	Carousel newCarousel = carouselDao.findByCarouselName(carouselName);
    	if(ObjectUtil.isNotNull(newCarousel)&&newCarousel.getCarouselId()!=carouselId){
    		return CarouselConstant.NAME_NOT_UNIQUE;
    	}
    	
    	return CarouselConstant.NAME_UNIQUE;
	}
	
}
