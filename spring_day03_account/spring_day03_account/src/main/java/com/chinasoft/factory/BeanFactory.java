package com.chinasoft.factory;

import com.chinasoft.service.AccountService;
import com.chinasoft.service.impl.AccountServiceImpl;
import com.chinasoft.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *   此类为生产serviceimpl的代理对象的工厂类
 */
@Component
public class BeanFactory {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionManager manager;

    /**
     * 获取AccountService的代理对象
     * @return
     */
    @Bean(value = "proxyAccountService")
    public AccountService getAccountServcie(){
         return (AccountService) Proxy.newProxyInstance(
                 accountService.getClass().getClassLoader(),
                 accountService.getClass().getInterfaces(),
                 new InvocationHandler() {
                     /**
                      *  用于执行被代理对象的方法都会经过此处代码
                      * @param proxy  代理对象的引用
                      * @param method 当前需要执行的方法
                      * @param args   执行当前方法传来的参数
                      * @return       返回代理增强后的代理对象
                      * @throws Throwable
                      */
                     @Override
                     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                         Object renObj;
                         try {
//                             开启事务
                             manager.startTransaction();
//                             执行方法
                             renObj = method.invoke(accountService, args);
//                             提交事务
                             manager.commit();
                             return renObj;
                         } catch (Exception e) {
//                             回滚事务
                             manager.rollback();
                             throw new RuntimeException("提交失败");
                         } finally {
//                             关闭事务
                             manager.closeTransation();
                         }
                     }
                 });
    }




}
