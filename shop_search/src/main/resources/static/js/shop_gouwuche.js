/**
 * 购物车 页面自定义JS
 * wll-2013/03/29
 */
jQuery(function(){
	/* 购物车商品加减 */
	jQuery(".this_good_nums").goodnums({zid:'good_zongjia',xclass:'good_xiaojis',max:5,min:1,typ:'+'});
	/* 删除购物车商品 */
	jQuery(".shop_good_delete").goodDelete({zid:'good_zongjia',xclass:'good_xiaojis'});
});