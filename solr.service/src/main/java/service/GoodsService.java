package service;

import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrServerException;

import entity.TGoods;
import query.GoodsQuery;

/**
 * @description 题目
 * @author xxx
 * @time 2019-3-7下午5:00:43
 */
public interface GoodsService {

	public List<TGoods> queryGoodsList(GoodsQuery goodsQuery) throws SolrServerException;
	
	public void saveGoods(TGoods goods) throws SolrServerException, IOException;
	
	public void deleteGoodsById(Integer id) throws SolrServerException, IOException;
	
	public void updateGoods(TGoods goods);
}
