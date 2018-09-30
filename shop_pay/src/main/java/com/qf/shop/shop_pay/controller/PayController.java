package com.qf.shop.shop_pay.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.qf.entity.Orders;
import com.qf.service.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Reference
    private IOrderService orderService;

    @RequestMapping("/alipay")
    public String alipay(String orderid, Model model){

        //通过订单id查询订单的详情
//        Orders orders = orderService.queryByOrderId(orderid);

        //调用支付宝进行支付 - 订单号 总金额
        //pay(orders, response);
        model.addAttribute("orderid", orderid);
        return "gopay";
    }

    @RequestMapping("/goalipay")
    public void pay(String orderid, HttpServletResponse response){
        Orders orders = orderService.queryByOrderId(orderid);

        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016073000127352",
                "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDoMpnOmVT1Gf+V/A08bXUACVSjx+3Gec8bBzxCWEMpxXTsMY9CJ9gow20ZP8O2F2d+HT76RLVdYJtoY+3idow9rGlOan0Fbx/k9Y0unRYKwkp7nOF2wLA6yhGmghF8OjI5L3cJGeGu5H7V0pARZtGwbaUOJOR6XWFCvh7OXWpjFwFcw8NHgrFk2jFHeenYEEbrqtE8zXpsGFGhqh6XMOjcofw/4q2rh4JzXHhGZSJxkTL80tA4JCmZRtq0ybSQaPMRChlxzBvDXNr/mCtSHoEZnk6IFrMyXllerxq/mGfcXRYTzw8V1BxiEpxG2bhaj6B9KCzCTrLfYRZhlyn976YxAgMBAAECggEBAOKPjPfDMNw+0926pAeUHZc9g3KTP3RroiMTOj7Z9B6r5dwYt1mrmwSbOrZqO0JPL9IkIzD7HaPRAZIn/xL67Pe4ZL/4s6g7t3kmBu22pK02yoo2lt/SP6H9TJ+VRg5akmqt343nzQdjOEbWP3BbTLrDIUvdv3mN57rB2zkseQPYsKcJcg5Bb0Hy6BjJvynh60XAOP2I5F/Zq2ZsL2oYeXW7SnyBHdE2JGfmF+ehr94Nx5g++JR1ZcJgyR4Zm7W95fP8CTlqG+5pSfG9jnmSerbN1XWy8fyAEF0EtLAp5wry3K4janqPvYR3jSnSa+90mKMHkDRWmcY9tkMnd9ExnWECgYEA+tXHZR2IJkL0gzXEfCcf/DojAm463ON4ChhKd7opHUmFTZW+uSDurrCtMRwFqHNmXeR7hlNfptl/VFnO7LapAuhnZcKZkbNo4TqZsWssOcbmVhoSGILpSCeYr9E9du3zlH9MCW+Z65nutY/5hn5VRdmDjrHUw2XT+jpy3bdHPb0CgYEA7PqUNinWK7Xadln0TDdmVriP/N7qO9D3ikGsv+AY9Jm28IdsvECpqrvUMqtY7+CZixCEH4g7z36qkQA2TbvxMmg3nV0dZWW1YJ7Dh5K6PwXiNHt3vBFZAhP9W5Qh+/gd4DdJ7GVnWzmLLID1ve5SMqOUA7XkhVufv2g83/Mwj4UCgYEAzT5JloJQmkYk/ovrR09YS7FiXiXGAmIaIeB4ZnksFSvIaJCD68V3p/cm780nEAvFEkTXkjT8SnEjT+jgeS6JIrj5ifluYRr1UX36roKsQGZwqLlSXz4XFhRYSlyit4LC0ZpLpFjuECr3eRbirNsIeUwe6o62MmBK0DAtNyA1qd0CgYEAg0fl54ORxDFATyRSNA7UwOIJ0lpjhLOlDpCHdNxettKZB/LeCAQCejUHvvIxmOHh7ao+p4E/MRnIv/9QFgwx+GzHsoOtD0kjC92oEGj2iIdn4w8rK6KonoAYcS1FDmNLmbTHXRBoC597PDJM8OUf4DHCg7O2XzJ88vp6Ds6YbkkCgYEAkvmse/KiLr3DDs2CPGZPE6wyuO8mI2v3BGhI/rGIblIghDp1LrxIX8O8SD5Rk99CG/00YtbNB2mkzd+WkO02FsrGIYx24Dfd0KBO8YfksVN3AZiqbJGfinvbxDHdo32YyOhe/t+lhiZko6m8OqZ/E6b9RT+gZWsZsnonFFDQVdw=",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx+n+pEqTVi27mtj3CuUQXi2ixqeeTwE/0tGrW+sg6xtfajvJV67GYf2zzNxxBV0TYfhdbi70VI3DftEijg7GSNKoOilAu2DKQFqidnSxmN1Es1oRTaiaehqm1Uzs2uswpzBVR21iygLHujwthC8kNkMgxVFkjbE/qTn7z5wlsailtg6wF+hY3BcDCiaLyVLjEDngmrLyLXPLenjAuvXq20h9zV7CW9HXuhpPBDfsn4fv5TjgEl1smjJNr4O/VxICKDNPsvrCyNXhfroK9PEFFwH+4IWGeBUJAP2cSufNU0OA+UH+2xQnaR8Cz30QIgIslckBGuXQZvxqaY2mMMz14QIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://www.baidu.com");//设置同步响应的url
        alipayRequest.setNotifyUrl("http://www.baidu.com");//设置异步响应的url
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + orders.getOrderid() + "\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":" + orders.getOprice() + "," +
                "    \"subject\":\"" + orders.getOrderid() + "\"," +
                "    \"body\":\"" + orders.getOrderid() + "\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证当前支付时候成功
     * @param orderid
     * @return
     */
    @RequestMapping("/isok")
    public String isOk(String orderid){

        //通过订单号查询支付宝是否支付成功
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016073000127352",
                "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDoMpnOmVT1Gf+V/A08bXUACVSjx+3Gec8bBzxCWEMpxXTsMY9CJ9gow20ZP8O2F2d+HT76RLVdYJtoY+3idow9rGlOan0Fbx/k9Y0unRYKwkp7nOF2wLA6yhGmghF8OjI5L3cJGeGu5H7V0pARZtGwbaUOJOR6XWFCvh7OXWpjFwFcw8NHgrFk2jFHeenYEEbrqtE8zXpsGFGhqh6XMOjcofw/4q2rh4JzXHhGZSJxkTL80tA4JCmZRtq0ybSQaPMRChlxzBvDXNr/mCtSHoEZnk6IFrMyXllerxq/mGfcXRYTzw8V1BxiEpxG2bhaj6B9KCzCTrLfYRZhlyn976YxAgMBAAECggEBAOKPjPfDMNw+0926pAeUHZc9g3KTP3RroiMTOj7Z9B6r5dwYt1mrmwSbOrZqO0JPL9IkIzD7HaPRAZIn/xL67Pe4ZL/4s6g7t3kmBu22pK02yoo2lt/SP6H9TJ+VRg5akmqt343nzQdjOEbWP3BbTLrDIUvdv3mN57rB2zkseQPYsKcJcg5Bb0Hy6BjJvynh60XAOP2I5F/Zq2ZsL2oYeXW7SnyBHdE2JGfmF+ehr94Nx5g++JR1ZcJgyR4Zm7W95fP8CTlqG+5pSfG9jnmSerbN1XWy8fyAEF0EtLAp5wry3K4janqPvYR3jSnSa+90mKMHkDRWmcY9tkMnd9ExnWECgYEA+tXHZR2IJkL0gzXEfCcf/DojAm463ON4ChhKd7opHUmFTZW+uSDurrCtMRwFqHNmXeR7hlNfptl/VFnO7LapAuhnZcKZkbNo4TqZsWssOcbmVhoSGILpSCeYr9E9du3zlH9MCW+Z65nutY/5hn5VRdmDjrHUw2XT+jpy3bdHPb0CgYEA7PqUNinWK7Xadln0TDdmVriP/N7qO9D3ikGsv+AY9Jm28IdsvECpqrvUMqtY7+CZixCEH4g7z36qkQA2TbvxMmg3nV0dZWW1YJ7Dh5K6PwXiNHt3vBFZAhP9W5Qh+/gd4DdJ7GVnWzmLLID1ve5SMqOUA7XkhVufv2g83/Mwj4UCgYEAzT5JloJQmkYk/ovrR09YS7FiXiXGAmIaIeB4ZnksFSvIaJCD68V3p/cm780nEAvFEkTXkjT8SnEjT+jgeS6JIrj5ifluYRr1UX36roKsQGZwqLlSXz4XFhRYSlyit4LC0ZpLpFjuECr3eRbirNsIeUwe6o62MmBK0DAtNyA1qd0CgYEAg0fl54ORxDFATyRSNA7UwOIJ0lpjhLOlDpCHdNxettKZB/LeCAQCejUHvvIxmOHh7ao+p4E/MRnIv/9QFgwx+GzHsoOtD0kjC92oEGj2iIdn4w8rK6KonoAYcS1FDmNLmbTHXRBoC597PDJM8OUf4DHCg7O2XzJ88vp6Ds6YbkkCgYEAkvmse/KiLr3DDs2CPGZPE6wyuO8mI2v3BGhI/rGIblIghDp1LrxIX8O8SD5Rk99CG/00YtbNB2mkzd+WkO02FsrGIYx24Dfd0KBO8YfksVN3AZiqbJGfinvbxDHdo32YyOhe/t+lhiZko6m8OqZ/E6b9RT+gZWsZsnonFFDQVdw=",
                "json",
                "UTF-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx+n+pEqTVi27mtj3CuUQXi2ixqeeTwE/0tGrW+sg6xtfajvJV67GYf2zzNxxBV0TYfhdbi70VI3DftEijg7GSNKoOilAu2DKQFqidnSxmN1Es1oRTaiaehqm1Uzs2uswpzBVR21iygLHujwthC8kNkMgxVFkjbE/qTn7z5wlsailtg6wF+hY3BcDCiaLyVLjEDngmrLyLXPLenjAuvXq20h9zV7CW9HXuhpPBDfsn4fv5TjgEl1smjJNr4O/VxICKDNPsvrCyNXhfroK9PEFFwH+4IWGeBUJAP2cSufNU0OA+UH+2xQnaR8Cz30QIgIslckBGuXQZvxqaY2mMMz14QIDAQAB",
                "RSA2"); //获得初始化的AlipayClient
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + orderid + "\"" +
                "  }");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            String tradeStatus = response.getTradeStatus();
            if(tradeStatus.equals("TRADE_SUCCESS")){
                //支付成功
                orderService.updateStatusByOrderId(orderid, 1);
            }
        } else {
            System.out.println("调用失败");
        }



        //如果支付成功修改订单状态

        return "redirect:http://localhost:8086/order/orderlist";
    }
}
