package controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import query.GoodsQuery;
import service.GoodsService;
import entity.TGoods;

/**
 * @description 题目
 * @author xxx
 * @time 2019-3-7下午4:58:12
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	private Logger log = Logger.getLogger(GoodsController.class);
	
	@Resource
	@Qualifier("goodsService")
	private GoodsService goodsService;

	@RequestMapping("/list")
	public String list(Model model,GoodsQuery goodsQuery) throws SolrServerException{
		log.debug("query name="+ goodsQuery.getName());
		List<TGoods> goodsList = goodsService.queryGoodsList(goodsQuery);
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsQuery",goodsQuery);
		return "goodsList";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id) throws SolrServerException, IOException{
		goodsService.deleteGoodsById(id);
		return "redirect:/goods/list";
	}
	
	@RequestMapping("/add")
	public String add() throws SolrServerException, IOException{
		return "goodsAdd";
	}
	
	@RequestMapping("/save")
	public String save(TGoods goods) throws SolrServerException, IOException{
		log.debug("*****************save***********");
		goodsService.saveGoods(goods);
		return "redirect:/goods/list";
	}
}
