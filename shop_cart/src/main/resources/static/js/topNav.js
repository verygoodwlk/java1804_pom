jQuery(function(){
    
    /* Header TopNav */
    jQuery(".topNav_menu").hover(function(){
        jQuery(this).find('a.topNavHover').addClass('aHover').next('.topNav_menu_bd').show();
    },function(){
        jQuery(this).find('a.topNavHover').removeClass('aHover').next('.topNav_menu_bd').hide();
    });
    
    /* search Form */
    jQuery("ul.shop_hd_header_search_tab li").click(function(){
        jQuery("ul.shop_hd_header_search_tab li").removeClass("current");
        jQuery(this).addClass("current");
    });
    
    jQuery("#shop_hd_menu_all_category").hover(function(){
        jQuery(this).addClass('shop_hd_menu_hover');
    },function(){
        jQuery(this).removeClass('shop_hd_menu_hover');
    });
    
    jQuery(".shop_hd_menu_all_category_hd_menu li").hover(function(){
        jQuery(this).addClass('hover');
    },function(){
        jQuery(this).removeClass('hover');
    });
    
});