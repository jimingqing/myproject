package com.yrtech.wx.capp.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.portal.dao.impl.GoodsCatDao;
import com.yrtech.wx.capp.portal.dao.impl.GoodsDao;
import com.yrtech.wx.capp.portal.dao.impl.GoodsImgDao;
import com.yrtech.wx.capp.portal.model.Goods;
import com.yrtech.wx.capp.portal.model.GoodsCat;
import com.yrtech.wx.capp.portal.model.GoodsImg;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.PageData;
import com.yrtech.wx.capp.portal.util.RetInfo;

public class GoodsSrv {

private Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private GoodsCatDao goodsCatDao;
	@Resource
	private GoodsImgDao goodsImgDao;
	
	public RetInfo editGoods(Goods goods, List<GoodsImg> imglist) throws Exception{
		RetInfo retInfo = new RetInfo();
		Goods g = goodsDao.find(goods.getId());
		PropertyUtils.copyProperties(g, goods);
		
		goodsDao.update(g);
		
		List<GoodsImg> list = goodsImgDao.findTbyHql("from GoodsImg where merId=? and goodsId=?", 
				new Object[]{goods.getMerId(), goods.getId()}
		);
		if(list.size()>0){
			if(imglist.size()>0){
				for(GoodsImg gi : list){
					if(gi.getPicType().equals(Constants.GOODS_IMG_TYPE_M)){
						gi.setLastTime(DateOper.getCurTimestamp());
						goodsImgDao.update(gi);
					}
					if(gi.getPicType().equals(Constants.GOODS_IMG_TYPE_L)){
						gi.setLastTime(DateOper.getCurTimestamp());
						goodsImgDao.update(gi);
					}
				}
			}
		}else{
			for(GoodsImg o : imglist){
				goodsImgDao.save(o);
			}
		}
		
		log.info("修改商品信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("修改商品信息成功");
		return retInfo;
	}
	
	public RetInfo deleteGoods(String goodsId, Integer merId){
		RetInfo retInfo = new RetInfo();
		Goods goods = goodsDao.find(Integer.valueOf(goodsId));
		if(goods!=null){
			if(goods.getMerId()*1 != merId*1){
				log.info("查询商户商品信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询商户商品信息失败");
				return retInfo;
			}
			
			String hql = "from GoodsImg where merId=? and goodsId=?";
			List<GoodsImg> list = goodsImgDao.findTbyHql(hql, new Object[]{merId, Integer.valueOf(goodsId)});
			if(list.size()>0){
				goodsImgDao.deleteAll(list);
			}
			goodsDao.delete(goods.getId());
			
			log.info("删除商品信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("删除商品信息成功");
		}else{
			log.info("删除商品信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("删除商品信息失败");
		}
		return retInfo;
	}
	
	public RetInfo qryGoodsById(String goodsId, Integer merId){
		RetInfo retInfo = new RetInfo();
		Goods goods = goodsDao.find(Integer.valueOf(goodsId));
		if(goods!=null){
			if(goods.getMerId()*1 != merId*1){
				log.info("查询商户商品信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询商户商品信息失败");
				return retInfo;
			}
			List<GoodsImg> list = goodsImgDao.findTbyHql("from GoodsImg where goodsId="+goodsId);
			log.info("查询商品明细信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("删除商品明细信息成功");
			retInfo.setObject(goods);
			retInfo.setList(list);
		}else{
			log.info("查询商品明细信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询商品明细信息失败");
		}
		return retInfo;
	}
	
	public RetInfo deleteGoodsCat(String catId, Integer merId){
		RetInfo retInfo = new RetInfo();
		GoodsCat goodsCat = goodsCatDao.find(Integer.valueOf(catId));
		if(goodsCat!=null){
			if(goodsCat.getMerId()*1 != merId*1){
				log.info("查询商户商品分类信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("查询商户商品分类信息失败");
				return retInfo;
			}
			
			String hql = "from Goods g, GoodsCat c where g.catId=c.id and c.id=?";
			List list = goodsDao.findTbyHql(hql, new Object[]{Integer.valueOf(catId)});
			if(list.size()>0){
				log.info("商品分类下已有商品信息，无法删除分类");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("商户商品分类下已有商品信息，无法删除分类");
				return retInfo;
			}else{
				goodsCatDao.delete(goodsCat.getId());
			}
			
			log.info("删除商品分类信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("删除商品分类信息成功");
		}else{
			log.info("删除商品分类信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("删除商品分类信息失败");
		}
		return retInfo;
	}
	
	public RetInfo editGoodsCat(GoodsCat cat){
		RetInfo retInfo = new RetInfo();
		GoodsCat goodsCat = goodsCatDao.find(cat.getId());
		if(goodsCat!=null){
			if(goodsCat.getMerId()*1 != cat.getMerId()*1){
				log.info("修改商品分类信息失败");
				retInfo.setRetCode(Constants.RET_CODE_ERROR);
				retInfo.setRetMsg("修改商品分类信息失败");
				return retInfo;
			}
			goodsCat.setCatName(cat.getCatName());
			goodsCat.setCatDesc(cat.getCatDesc());
			goodsCat.setPorder(cat.getPorder());
			goodsCat.setState(cat.getState());
			goodsCat.setLastTime(cat.getLastTime());
			
			goodsCatDao.update(goodsCat);
			log.info("修改商品分类信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("修改商品分类信息成功");
		}else{
			log.info("修改商品分类信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("修改商品分类信息失败");
		}
		return retInfo;
	}
	
	public RetInfo qryGoods(Integer merId, String goodsName, String goodsCode, String catId, String state, String marketable, Pager pager){
		RetInfo retInfo = new RetInfo();
		String hql = "and merId='"+merId+"' ";
		if(!StringUtils.isEmpty(goodsName)){
			hql += "and goodsName like '%"+goodsName+"%' ";
		}
		if(!StringUtils.isEmpty(goodsCode)){
			hql += "and goodsCode ='"+goodsName+"' ";
		}
		if(!StringUtils.isEmpty(catId)&&!Constants.SELECT_ALL.equals(catId.toUpperCase())){
			hql += "and catId ='"+catId+"' ";
		}
		if(!StringUtils.isEmpty(state)&&!Constants.SELECT_ALL.equals(state.toUpperCase())){
			hql += "and state ='"+state+"' ";
		}
		if(!StringUtils.isEmpty(marketable)&&!Constants.SELECT_ALL.equals(marketable.toUpperCase())){
			hql += "and marketable ='"+marketable+"' ";
		}
		hql += "order by createTime desc";
		
		PageData data = goodsDao.getScrollData(pager.getStartRow(), pager.getPageSize(), hql, null);
		pager.setTotalRows(Integer.parseInt(String.valueOf(data.getTotalRecords())));
		log.info("查询商品信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询商品信息成功");
		retInfo.setList(data.getResultlist());
		return retInfo;
	}
	
	public RetInfo qryGoodsCat(Integer merId, String catName, String state){
		RetInfo retInfo = new RetInfo();
		String hql = "from GoodsCat where merId= "+merId;
		if(!StringUtils.isEmpty(catName)){
			hql += " and catName like '%"+catName+"%' ";
		}
		if(!StringUtils.isEmpty(state)&&!Constants.SELECT_ALL.equals(state.toUpperCase())){
			hql += " and state = '"+state+"' ";
		}
		hql += " order by porder";
		List<GoodsCat> list = goodsCatDao.findTbyHql(hql);
		log.info("查询商品分类信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("查询商品分类信息成功");
		retInfo.setList(list);
		return retInfo;
	}
	
	public RetInfo qryGoodsCatById(String catId){
		RetInfo retInfo = new RetInfo();
		GoodsCat cat = goodsCatDao.find(Integer.valueOf(catId));
		if(cat!=null){
			log.info("查询商品分类信息成功");
			retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
			retInfo.setRetMsg("查询商品分类信息成功");
			retInfo.setObject(cat);
		}else{
			log.info("查询商品分类信息失败");
			retInfo.setRetCode(Constants.RET_CODE_ERROR);
			retInfo.setRetMsg("查询商品分类信息失败");
		}
		return retInfo;
	}
	
	public RetInfo addGoodsCat(GoodsCat goodsCat){
		RetInfo retInfo = new RetInfo();
		goodsCatDao.save(goodsCat);
		log.info("新增商品分类信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("新增商品分类信息成功");
		return retInfo;
	}
	
	public RetInfo addGoods(Goods goods, List<GoodsImg> imglist){
		RetInfo retInfo = new RetInfo();
		goodsDao.save(goods);
		
		for(GoodsImg o : imglist){
			o.setGoodsId(goods.getId());
			goodsImgDao.save(o);
		}
		
		log.info("新增商品信息成功");
		retInfo.setRetCode(Constants.RET_CODE_SUCCESS);
		retInfo.setRetMsg("新增商品信息成功");
		return retInfo;
	}
	
}
