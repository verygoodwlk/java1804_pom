package com.qf.shop.shop_search.controller;

import com.qf.entity.Goods;
import com.qf.entity.SolrPage;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    /**
     * 添加索引库
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean solrAdd(@RequestBody Goods goods){
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", goods.getId());
        solrInputDocument.addField("gtitle", goods.getTitle());
        solrInputDocument.addField("gimage", goods.getGimage());
        solrInputDocument.addField("ginfo", goods.getGinfo());
        solrInputDocument.addField("gprice", goods.getPrice());

        try {
            solrClient.add(solrInputDocument);
            solrClient.commit();

            return true;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 搜索商品
     * @return
     */
    @RequestMapping("/query")
    public String solrQuery(String keyword, SolrPage<Goods> solrPage, Model model){

        System.out.println("关键字：" + keyword);

        SolrQuery solrQuery = new SolrQuery();

        if(keyword == null || keyword.trim().equals("")){
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.setQuery("goods_info:" + keyword);// iphonex  xxxxxxx  10000  苹果、肾机 <field  indexed=true stroed=false/>
        }

        //设置搜索的高亮
        solrQuery.setHighlight(true);//表示开启高亮

        //设置高亮的前缀和后缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        //添加需要高亮的字段
        solrQuery.addHighlightField("gtitle");

        //设置高亮的折叠
//        solrQuery.setHighlightSnippets(3);//摘要分成几部分
//        solrQuery.setHighlightFragsize(7);//每部分的长度

        //设置分页 limit ?,?
        solrQuery.setStart((solrPage.getPage() - 1) * solrPage.getPageSize());
        solrQuery.setRows(solrPage.getPageSize());


        QueryResponse query = null;
        List<Goods> list = new ArrayList<>();
        try {
            query = solrClient.query(solrQuery);

            //获得高亮的结果
            //返回值Map<String, Map<String, List<String>>>
            //{id:{fieldname:[高亮的内容,.....]}}
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            for(Map.Entry<String, Map<String, List<String>>> map1 : highlighting.entrySet()){
                System.out.println("key:" + map1.getKey());
                System.out.println("value:" + map1.getValue());
                System.out.println("--------------------------------");
            }


            //获得普通的搜索结果
            SolrDocumentList results = query.getResults();

            //处理总条数
            long pageSum = results.getNumFound();
            solrPage.setPageSum((int)pageSum);//设置总条数
            solrPage.setPageCount(solrPage.getPageSum() % solrPage.getPageSize() == 0 ?
                    solrPage.getPageSum() / solrPage.getPageSize() :
                    solrPage.getPageSum() / solrPage.getPageSize() + 1);//设置总页码

            for (SolrDocument solrDocument : results){
                Goods goods = new Goods();
                goods.setId(Integer.parseInt(solrDocument.getFieldValue("id") + ""));
                goods.setTitle(solrDocument.getFieldValue("gtitle") + "");
                goods.setGimage(solrDocument.getFieldValue("gimage") + "");
                goods.setPrice(Float.parseFloat(solrDocument.getFieldValue("gprice") + ""));

                //处理高亮的内容
                if(highlighting.containsKey(goods.getId() + "")){
                    //说明当前的商品有高亮的信息
                    List<String> gtitleHL = highlighting.get(goods.getId()+"").get("gtitle");
                    if(gtitleHL != null){
//                        String strs = "";
//                        for(String str : gtitleHL){
//                            strs += str + "...";
//                        }
                        goods.setTitle(gtitleHL.get(0));
                    }
                }

                list.add(goods);
                System.out.println("goods的标题：" + goods.getTitle());
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //查询结果放入分页对象中
        solrPage.setDatas(list);

        model.addAttribute("page", solrPage);
        model.addAttribute("keyword", keyword);
        return "searchlist";
    }
}
