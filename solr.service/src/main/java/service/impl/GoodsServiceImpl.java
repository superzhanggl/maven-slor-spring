package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import query.GoodsQuery;
import service.GoodsService;
import util.Page;
import entity.TGoods;

/**
 * @description 题目
 * @author xxx
 * @time 2019-3-7下午5:00:56
 */
@Service
@Qualifier("goodsService")
public class GoodsServiceImpl implements GoodsService {
	
	private Logger log = Logger.getLogger(GoodsServiceImpl.class);

	@Resource
	@Qualifier("solrServer")
	private SolrServer solrServer;

	@Override
	public List<TGoods> queryGoodsList(GoodsQuery goodsQuery) throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		//设置关键字
		solrQuery.setQuery(goodsQuery.getName());
		//设置默认检索域
		solrQuery.set("df", "name");
		// 分页  - 默认查10条
//		solrQuery.setStart(0);
//		solrQuery.setRows(5);
		// 只查询指定域
		solrQuery.set("fl", "id,name,goods_price,goods_status,goods_type,goods_img,goods_describle");
		// 设置高亮
		solrQuery.setHighlight(true);
		// 指定高亮域
		solrQuery.addHighlightField("name");
		// 前缀
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		// 执行查询
		QueryResponse response = solrServer.query(solrQuery);
		// 文档结果集
		SolrDocumentList docs = response.getResults();
		
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		List<TGoods> goodsList = new ArrayList<TGoods>();
		for (SolrDocument doc : docs) {
			TGoods goods = new TGoods();
			/*log.debug("*********="+doc.get("id"));
			log.debug("*********="+doc.get("goods_price"));
			log.debug("*********="+doc.get("goods_status"));
			log.debug("*********="+doc.get("goods_type"));
			log.debug("*********="+doc.get("goods_img"));
			log.debug("*********="+doc.get("goods_describle"));*/
			goods.setId(Integer.parseInt(doc.get("id").toString()));
			goods.setPrice(Double.parseDouble(doc.get("goods_price").toString()));
			goods.setStatus(Integer.parseInt(doc.get("goods_status").toString()));
			goods.setType(Integer.parseInt(doc.get("goods_type").toString()));
			goods.setImg((String)doc.get("goods_img"));
			goods.setDescrible((String)doc.get("goods_describle"));
			
			Map<String, List<String>> map = highlighting.get((String) doc.get("id"));
			List<String> list = map.get("name");
			
			goods.setName(list.get(0));
			goodsList.add(goods);
		}
		return goodsList;
	}

	@Override
	public void saveGoods(TGoods goods) throws SolrServerException, IOException {
		//创建新的文档对象
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		//设置文档的域
		solrInputDocument.setField("id", goods.getId());
		solrInputDocument.setField("name", goods.getName());
		solrInputDocument.setField("goods_price", goods.getPrice());
		solrInputDocument.setField("goods_status", goods.getStatus());
		solrInputDocument.setField("goods_type", goods.getType());
		solrInputDocument.setField("goods_img", goods.getImg());
		solrInputDocument.setField("goods_describle", goods.getDescrible());
		//进行添加
		solrServer.add(solrInputDocument);
		//进行手动提交，否则无法进行添加
		solrServer.commit();
	}

	@Override
	public void deleteGoodsById(Integer id) throws SolrServerException, IOException {
		log.debug("*********deleteGoodsById************" + id);
		solrServer.deleteById(String.valueOf(id));
		solrServer.commit();
	}

	@Override
	public void updateGoods(TGoods goods) {
		// TODO Auto-generated method stub
		
	}

}
