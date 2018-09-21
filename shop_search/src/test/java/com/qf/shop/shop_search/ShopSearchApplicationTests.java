package com.qf.shop.shop_search;

import org.apache.solr.client.solrj.SolrClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSearchApplicationTests {

	@Autowired
	private SolrClient solrClient;
//
	/**
	 * 添加索引库
	 */
//	@Test
//	public void solrAdd(){
//		//1、创建索引对象
//        for(int i = 0; i < 100; i++){
//            SolrInputDocument solrInputDocument = new SolrInputDocument();
//            solrInputDocument.addField("id", i);
//            solrInputDocument.addField("gtitle", "华为手机" + i);
//            solrInputDocument.addField("ginfo", "华为手机,手机中的战斗机" + i);
//            solrInputDocument.addField("gprice", 800 + i);
//            solrInputDocument.addField("gimage", "http://www.baidu.com" + i);
//
//            try {
//                //2、将索引对象添加到索引库中
//                solrClient.add(solrInputDocument);
//            } catch (SolrServerException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        //3、提交
//        try {
//            solrClient.commit();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 删除索引库
     */
//	@Test
//	public void delete() throws IOException, SolrServerException {
//	    //根据id删除索引库
////        solrClient.deleteById("1");
//
//        //根据查询结果删除索引库
//        solrClient.deleteByQuery("gtitle:华为");
//
//        solrClient.commit();
//    }

//    /**
//     * 搜索索引库
//     */
//    @Test
//    public void query() throws IOException, SolrServerException {
//        SolrQuery solrQuery = new SolrQuery();
//        solrQuery.setQuery("gtitle:战斗 AND ginfo:战斗");
//
//        //执行查询
//        QueryResponse response = solrClient.query(solrQuery);
//        SolrDocumentList results = response.getResults();
//
//        for(SolrDocument solrDocument : results){
//            String id = (String) solrDocument.getFieldValue("id");
//            String title = (String) solrDocument.getFieldValue("gtitle");
//            String ginfo = (String) solrDocument.getFieldValue("ginfo");
//            float gprice = (float) solrDocument.getFieldValue("gprice");
//            String gimage = (String)solrDocument.getFieldValue("gimage");
//            System.out.println(id);
//            System.out.println(title);
//            System.out.println(ginfo);
//            System.out.println(gprice);
//            System.out.println(gimage);
//            System.out.println("---------------------------------------------");
//        }
//
//    }

}
