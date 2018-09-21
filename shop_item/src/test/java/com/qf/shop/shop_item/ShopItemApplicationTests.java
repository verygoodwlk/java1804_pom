package com.qf.shop.shop_item;

import com.qf.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopItemApplicationTests {

	@Autowired
	private Configuration configuration;

	/**
	 * 使用freemarker生成静态页面
	 */
	@Test
	public void contextLoads() throws Exception {
		//1、加载指定的模板
		Template template = configuration.getTemplate("hello.ftl");
		//2、准备数据
		Map<String, Object> map = new HashMap<>();
		map.put("key", "World");

		Goods goods = new Goods();
		goods.setId(1);
		goods.setTitle("洗衣机");
		goods.setGimage("http://www.baidu.com");
		map.put("goods", goods);

		map.put("age", 46);

		List<Goods> goodsList = new ArrayList<>();
		goodsList.add(new Goods(1, "洗衣机1", "xxx", 10, 1, 100, 100, ""));
		goodsList.add(new Goods(2, "洗衣机2", "xxx", 10, 1, 100, 100, ""));
		goodsList.add(new Goods(3, "洗衣机3", "xxx", 10, 1, 100, 100, ""));
		goodsList.add(new Goods(4, "洗衣机4", "xxx", 10, 1, 100, 100, ""));
		goodsList.add(new Goods(5, "洗衣机5", "xxx", 10, 1, 100, 100, ""));
		map.put("goods", goodsList);

		map.put("now", new Date());

		map.put("money", 1234567.12313);

		map.put("obj", null);

		//3、生成静态页
		Writer writer = new FileWriter("C:\\Users\\ken\\Desktop\\hello.html");
		template.process(map, writer);
		writer.close();
	}

}
