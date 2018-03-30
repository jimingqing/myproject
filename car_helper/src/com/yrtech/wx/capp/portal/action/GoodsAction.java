package com.yrtech.wx.capp.portal.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yrtech.wx.capp.framework.core.json.JSONUtil;
import com.yrtech.wx.capp.framework.core.paging.Pager;
import com.yrtech.wx.capp.framework.core.util.CheckUtil;
import com.yrtech.wx.capp.framework.core.util.Config;
import com.yrtech.wx.capp.framework.core.util.DateOper;
import com.yrtech.wx.capp.framework.core.util.FileUtil;
import com.yrtech.wx.capp.framework.core.util.GmsUtil;
import com.yrtech.wx.capp.portal.model.Goods;
import com.yrtech.wx.capp.portal.model.GoodsCat;
import com.yrtech.wx.capp.portal.model.GoodsImg;
import com.yrtech.wx.capp.portal.service.GoodsSrv;
import com.yrtech.wx.capp.portal.util.Constants;
import com.yrtech.wx.capp.portal.util.RetInfo;
import com.yrtech.wx.capp.portal.vo.GoodsVo;

public class GoodsAction extends BaseAction{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private String goodsId;
	private String catName;
	private String catDesc;
	private String state;
	private String goodsName;
	private String goodsCode;
	private String goodsDesc;
	private String price;
	private String store;
	private String marketable;
	private String expDate;
	private String catId;
	private String porder;
	
	private File smallPicName;//商品小图片
	private String smallPicNameFileName;
	private String smallPicNameContentType;
	private File largePicName;//商品大图片
	private String largePicNameFileName;
	private String largePicNameContentType;
	
	private String curPage;
	
	@Resource
	private GoodsSrv goodsSrv;
	
	public String editGoods(){
		
		response().setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;  
		try {
			pw = response().getWriter();
			if(StringUtils.isEmpty(goodsId)){
				log.info("商品id错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品id错误");
				return SUCCESS;
			}
			if(StringUtils.isEmpty(catId)){
				log.info("商品分类id错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品分类id错误");
				return null;
			}
			if(StringUtils.isEmpty(goodsName)){
				log.info("商品名称错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品名称错误");
				return null;
			}
			if(StringUtils.isEmpty(goodsCode)){
				log.info("商品编码错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品编码错误");
				return null;
			}
			if(StringUtils.isEmpty(price) ){
				log.info("商品价格错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品价格错误");
				return null;
			}
			if(StringUtils.isEmpty(store) || !CheckUtil.isNumeric(store)){
				log.info("商品库存数量错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品库存数量错误");
				return null;
			}
			if(StringUtils.isEmpty(marketable)){
				log.info("未设置是否上架");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "未设置是否上架");
				return null;
			}
			if(StringUtils.isEmpty(state)){
				log.info("状态标志错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "状态标志错误");
				return null;
			}
			
			Goods goods = new Goods();
			goods.setId(Integer.valueOf(goodsId));
			goods.setMerId(getMerOperInfoVo().getMerId());
			goods.setMerCode(getMerOperInfoVo().getMerCode());
			goods.setGoodsName(goodsName);
			goods.setGoodsCode(goodsCode);
			goods.setBrief(goodsDesc);
			goods.setCatId(Integer.valueOf(catId));
			goods.setPrice(Double.valueOf(price));
			goods.setState(state);
			goods.setStore(Integer.valueOf(store));
			goods.setMarketable(marketable);
			if(!StringUtils.isEmpty(expDate)){
				goods.setExpDate(new Timestamp(DateOper.string2Date(expDate).getTime()));
			}
			goods.setCreateTime(DateOper.getCurTimestamp());
			
			List<GoodsImg> imglist = new ArrayList<GoodsImg>();
			if(smallPicName!=null){
				File smallpicfile = new File(Config.getProperty("goods_pic_save_dir")+getMerOperInfoVo().getMerCode()+"/"+goodsCode+"_s."+smallPicNameFileName.split("\\.")[1]);
				FileUtil.copy(smallPicName, smallpicfile);
				
				GoodsImg sgimg = new GoodsImg();
				sgimg.setPicDesc(goodsName+"(小图)");
				sgimg.setPicType(Constants.GOODS_IMG_TYPE_M);
				sgimg.setPicUrl(smallpicfile.getName());
				sgimg.setMerId(getMerOperInfoVo().getMerId());
				sgimg.setPorder(0);
				sgimg.setCreateTime(DateOper.getCurTimestamp());
				sgimg.setGoodsId(Integer.valueOf(goodsId));
				imglist.add(sgimg);
			}
			
			if(largePicName!=null){
				File largepicfile = new File(Config.getProperty("goods_pic_save_dir")+getMerOperInfoVo().getMerCode()+"/"+goodsCode+"_l."+largePicNameFileName.split("\\.")[1]);
				FileUtil.copy(largePicName, largepicfile);
				
				GoodsImg sgimg = new GoodsImg();
				sgimg.setPicDesc(goodsName+"(大图)");
				sgimg.setPicType(Constants.GOODS_IMG_TYPE_L);
				sgimg.setPicUrl(largepicfile.getName());
				sgimg.setMerId(getMerOperInfoVo().getMerId());
				sgimg.setPorder(1);
				sgimg.setCreateTime(DateOper.getCurTimestamp());
				sgimg.setGoodsId(Integer.valueOf(goodsId));
				imglist.add(sgimg);
			}
			
			RetInfo retInfo = goodsSrv.editGoods(goods, imglist);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("修改商品信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改商品信息成功");
			}else{
				log.info("修改商品信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改商品信息失败");
			}
			
		} catch (Exception e) {
			log.error("修改商品信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改商品信息异常");
		} finally{
			pw.write(JSONUtil.map2json(resultMap));
			pw.flush();  
	        pw.close();
	        return null;
		}
		
	}
	
	public String removeGoods(){
		if(StringUtils.isEmpty(goodsId)){
			log.info("商品id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品id错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = goodsSrv.deleteGoods(goodsId, getMerOperInfoVo().getMerId());
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				log.info("删除商品信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "删除商品信息成功");
			}else{
				log.info("删除商品信息成功失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "删除商品信息成功失败！");
			}
			
		} catch (Exception e) {
			log.error("删除商品信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "删除商品信息异常");
		}
		return SUCCESS;
	}
	
	public String qryGoodsDetail(){
		if(StringUtils.isEmpty(goodsId)){
			log.info("商品id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品id错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = goodsSrv.qryGoodsById(goodsId, getMerOperInfoVo().getMerId());
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				List<GoodsImg> imglist = retInfo.getList();
				GoodsVo vo = new GoodsVo();
				PropertyUtils.copyProperties(vo, retInfo.getObject());
				for(GoodsImg img : imglist){
					if(img.getPicType().equals(Constants.GOODS_IMG_TYPE_L)){
						vo.setLargePicName(Config.getProperty("goods_pic_file_path")+getMerOperInfoVo().getMerCode()+"/"+img.getPicUrl());
					}
					if(img.getPicType().equals(Constants.GOODS_IMG_TYPE_M)){
						vo.setSmallPicName(Config.getProperty("goods_pic_file_path")+getMerOperInfoVo().getMerCode()+"/"+img.getPicUrl());
					}
				}
				vo.setPriceStr(GmsUtil.double2amt(vo.getPrice()));
				log.info("查询商品明细信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商品明细信息成功");
				resultMap.put(Constants.JSON_CONTENT, vo);
			}else{
				log.info("查询商品明细信息成功失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商品明细信息成功失败！");
			}
			
		} catch (Exception e) {
			log.error("查询商品明细信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商品明细信息异常");
		}
		return SUCCESS;
	}
	
	public String removeGoodsCat(){
		if(StringUtils.isEmpty(catId)){
			log.info("商品分类id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品分类id错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = goodsSrv.deleteGoodsCat(catId, getMerOperInfoVo().getMerId());
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				log.info("删除商品分类信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "删除商品分类信息成功");
			}else{
				log.info("删除商品分类信息失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "删除商品分类信息失败！");
			}
			
		} catch (Exception e) {
			log.error("删除商品分类信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "删除商品分类信息异常");
		}
		return SUCCESS;
	}
	
	public String editGoodsCat(){
		if(StringUtils.isEmpty(catId)){
			log.info("商品分类id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品分类id错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(catName)){
			log.info("商品分类名称错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品分类名称错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(porder)){
			log.info("顺序编号错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "顺序编号错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(state)){
			log.info("商品分类状态错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品分类名称错误");
			return SUCCESS;
		}
		try {
			GoodsCat cat = new GoodsCat();
			cat.setMerId(getMerOperInfoVo().getMerId());
			cat.setId(Integer.valueOf(catId));
			cat.setCatName(catName);
			cat.setCatDesc(catDesc);
			cat.setPorder(Integer.valueOf(porder));
			cat.setState(state);
			cat.setLastTime(DateOper.getCurTimestamp());
			RetInfo retInfo = goodsSrv.editGoodsCat(cat);
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				log.info("修改商品分类信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "修改商品分类信息成功");
			}else{
				log.info("修改商品分类信息成功失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "修改商品分类信息成功失败！");
			}
			
		} catch (Exception e) {
			log.error("修改商品分类信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "修改商品分类信息异常");
		}
		return SUCCESS;
	}
	
	public String qryGoodsCatDetail(){
		if(StringUtils.isEmpty(catId)){
			log.info("商品分类id错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "商品分类id错误");
			return SUCCESS;
		}
		try {
			RetInfo retInfo = goodsSrv.qryGoodsCatById(catId);
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				log.info("查询商品分类信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商品分类信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getObject());
			}else{
				log.info("查询商品分类信息成功失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商品分类信息成功失败！");
			}
			
		} catch (Exception e) {
			log.error("查询商品分类信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商品分类信息异常");
		}
		return SUCCESS;
	}
	
	public String qryMerGoods(){
		try {
			
			Pager pager = new Pager();
			pager.setCurrentPage(Integer.valueOf(curPage==null?"1":curPage));
			RetInfo retInfo = goodsSrv.qryGoods(getMerOperInfoVo().getMerId(), goodsName, goodsCode, catId, state, marketable, pager);
			if(Constants.RET_CODE_SUCCESS.equals(retInfo.getRetCode())){
				
				RetInfo retInfo2 = goodsSrv.qryGoodsCat(getMerOperInfoVo().getMerId(), "", "all");
				if(Constants.RET_CODE_SUCCESS.equals(retInfo2.getRetCode())){
					Map<Integer, GoodsCat> catmap = new HashMap<Integer, GoodsCat>();
					for(GoodsCat o : (List<GoodsCat>)retInfo2.getList()){
						catmap.put(o.getId(), o);
					}
					GoodsVo vo = new GoodsVo();
					List<GoodsVo> list = new ArrayList<GoodsVo>();
					for(Goods o : (List<Goods>)retInfo.getList()){
						vo = new GoodsVo();
						PropertyUtils.copyProperties(vo, o);
						vo.setCatName(catmap.get(o.getCatId()).getCatName());
						list.add(vo);
					}
					
					Map<String, Object> retmap = new HashMap<String, Object>();
					retmap.put("datalist", list);
					retmap.put("pager", pager);
					
					log.info("查询商品信息成功，merId="+getMerOperInfoVo().getMerId()+", userId="+getMerOperInfoVo().getId());
					resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
					resultMap.put(Constants.JSON_RETMSG, "查询商品信息成功");
					resultMap.put(Constants.JSON_CONTENT, retmap);
				}else{
					log.info("查询商品信息失败！");
					resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
					resultMap.put(Constants.JSON_RETMSG, "查询商品信息失败！");
				}
				
			}else{
				log.info("查询商品信息失败！");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商品信息失败！");
			}
		} catch (Exception e) {
			log.error("查询商品信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商品信息异常");
		}
		return SUCCESS;
	}
	
	public String addGoods(){
		response().setContentType("text/html;charset=utf-8");
		PrintWriter pw = null;  
		try {
			pw = response().getWriter();
			if(StringUtils.isEmpty(catId)){
				log.info("商品分类错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品分类错误");
				return null;
			}
			if(StringUtils.isEmpty(goodsName)){
				log.info("商品名称错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品名称错误");
				return null;
			}
			if(StringUtils.isEmpty(goodsCode)){
				log.info("商品编码错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品编码错误");
				return null;
			}
			if(StringUtils.isEmpty(price) ){
				log.info("商品价格错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品价格错误");
				return null;
			}
			if(StringUtils.isEmpty(store) || !CheckUtil.isNumeric(store)){
				log.info("商品库存数量错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "商品库存数量错误");
				return null;
			}
			if(StringUtils.isEmpty(marketable)){
				log.info("未设置是否上架");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "未设置是否上架");
				return null;
			}
			if(StringUtils.isEmpty(state)){
				log.info("状态标志错误");
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "状态标志错误");
				return null;
			}
			
		
			Goods goods = new Goods();
			goods.setMerId(getMerOperInfoVo().getMerId());
			goods.setMerCode(getMerOperInfoVo().getMerCode());
			goods.setGoodsName(goodsName);
			goods.setGoodsCode(goodsCode);
			goods.setBrief(goodsDesc);
			goods.setCatId(Integer.valueOf(catId));
			goods.setPrice(Double.valueOf(price));
			goods.setState(state);
			goods.setStore(Integer.valueOf(store));
			goods.setMarketable(marketable);
			if(!StringUtils.isEmpty(expDate)){
				goods.setExpDate(new Timestamp(DateOper.string2Date(expDate).getTime()));
			}
			goods.setCreateTime(DateOper.getCurTimestamp());
			
			List<GoodsImg> imglist = new ArrayList<GoodsImg>();
			if(smallPicName!=null){
				File smallpicfile = new File(Config.getProperty("goods_pic_save_dir")+getMerOperInfoVo().getMerCode()+"/"+goodsCode+"_s."+smallPicNameFileName.split("\\.")[1]);
				FileUtil.copy(smallPicName, smallpicfile);
				
				GoodsImg sgimg = new GoodsImg();
				sgimg.setPicDesc(goodsName+"(小图)");
				sgimg.setPicType(Constants.GOODS_IMG_TYPE_M);
				sgimg.setPicUrl(smallpicfile.getName());
				sgimg.setMerId(getMerOperInfoVo().getMerId());
				sgimg.setPorder(0);
				sgimg.setCreateTime(DateOper.getCurTimestamp());
				imglist.add(sgimg);
			}
			
			if(largePicName!=null){
				File largepicfile = new File(Config.getProperty("goods_pic_save_dir")+getMerOperInfoVo().getMerCode()+"/"+goodsCode+"_l."+largePicNameFileName.split("\\.")[1]);
				FileUtil.copy(largePicName, largepicfile);
				
				GoodsImg sgimg = new GoodsImg();
				sgimg.setPicDesc(goodsName+"(大图)");
				sgimg.setPicType(Constants.GOODS_IMG_TYPE_L);
				sgimg.setPicUrl(largepicfile.getName());
				sgimg.setMerId(getMerOperInfoVo().getMerId());
				sgimg.setPorder(1);
				sgimg.setCreateTime(DateOper.getCurTimestamp());
				imglist.add(sgimg);
			}
			
			RetInfo retInfo = goodsSrv.addGoods(goods, imglist);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("添加商品信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "添加商品信息成功");
			}else{
				log.info("添加商品信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "添加商品信息失败");
			}
			
		} catch (Exception e) {
			log.error("添加商品信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "添加商品信息异常");
		} finally{
			pw.write(JSONUtil.map2json(resultMap));
			pw.flush();  
	        pw.close();
	        return null;
		}
	}
	
	public String qryGoodsCat(){
		try {
			RetInfo retInfo = goodsSrv.qryGoodsCat(getMerOperInfoVo().getMerId(), catName, state);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("查询商品分类信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "查询商品分类信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("查询商品分类信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "查询商品分类信息失败");
			}
			
		} catch (Exception e) {
			log.error("查询商品分类信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "查询商品分类信息异常");
		}
		return SUCCESS;
	}
	
	public String addGoodsCat(){
		if(StringUtils.isEmpty(catName)){
			log.info("分类名称错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "分类名称错误");
			return SUCCESS;
		}
		if(StringUtils.isEmpty(state)){
			log.info("状态标志错误");
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_PARAM_ERROR);
			resultMap.put(Constants.JSON_RETMSG, "状态标志错误");
			return SUCCESS;
		}
		try {
			GoodsCat goodsCat = new GoodsCat();
			goodsCat.setCatName(catName);
			goodsCat.setCatDesc(catDesc);
			goodsCat.setMerId(getMerOperInfoVo().getMerId());
			goodsCat.setMerCode(getMerOperInfoVo().getMerCode());
			goodsCat.setPorder(Integer.valueOf(porder));
			goodsCat.setState(state);
			goodsCat.setCreateTime(DateOper.getCurTimestamp());
			
			RetInfo retInfo = goodsSrv.addGoodsCat(goodsCat);
			if(retInfo.getRetCode().equals(Constants.RET_CODE_SUCCESS)){
				log.info("新增商品分类信息成功，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_SUCCESS);
				resultMap.put(Constants.JSON_RETMSG, "新增商品分类信息成功");
				resultMap.put(Constants.JSON_CONTENT, retInfo.getList());
			}else{
				log.info("新增商品分类信息失败，merCode="+getMerOperInfoVo().getMerCode()+", operCode="+getMerOperInfoVo().getOperCode());
				resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_ERROR);
				resultMap.put(Constants.JSON_RETMSG, "新增商品分类信息失败");
			}
			
		} catch (Exception e) {
			log.error("新增商品分类信息异常！", e);
			resultMap.put(Constants.JSON_RETCODE, Constants.RET_CODE_EXCEP);
			resultMap.put(Constants.JSON_RETMSG, "新增商品分类信息异常");
		}
		return SUCCESS;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getMarketable() {
		return marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}

	public File getSmallPicName() {
		return smallPicName;
	}

	public void setSmallPicName(File smallPicName) {
		this.smallPicName = smallPicName;
	}

	public String getSmallPicNameFileName() {
		return smallPicNameFileName;
	}

	public void setSmallPicNameFileName(String smallPicNameFileName) {
		this.smallPicNameFileName = smallPicNameFileName;
	}

	public String getSmallPicNameContentType() {
		return smallPicNameContentType;
	}

	public void setSmallPicNameContentType(String smallPicNameContentType) {
		this.smallPicNameContentType = smallPicNameContentType;
	}

	public File getLargePicName() {
		return largePicName;
	}

	public void setLargePicName(File largePicName) {
		this.largePicName = largePicName;
	}

	public String getLargePicNameFileName() {
		return largePicNameFileName;
	}

	public void setLargePicNameFileName(String largePicNameFileName) {
		this.largePicNameFileName = largePicNameFileName;
	}

	public String getLargePicNameContentType() {
		return largePicNameContentType;
	}

	public void setLargePicNameContentType(String largePicNameContentType) {
		this.largePicNameContentType = largePicNameContentType;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getPorder() {
		return porder;
	}

	public void setPorder(String porder) {
		this.porder = porder;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
}