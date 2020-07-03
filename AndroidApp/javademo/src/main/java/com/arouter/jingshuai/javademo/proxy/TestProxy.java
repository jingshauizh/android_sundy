package com.arouter.jingshuai.javademo.proxy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by jings on 2020/6/8.
 */

public class TestProxy {


    public static class ProxyHandler implements InvocationHandler{
        private Object object;
        public ProxyHandler(Object object){
            this.object = object;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke "  + method.getName());
            Object result = method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return result;
        }
    }
    public static void main(String... args) throws IOException {
        System.out.println("Run test");
        NameServiceImpl tNameServiceImpl = new NameServiceImpl();
        INameService serviceImp = (INameService)Proxy.newProxyInstance(INameService.class.getClassLoader(),tNameServiceImpl.getClass().getInterfaces(),new ProxyHandler(tNameServiceImpl));

        //byte [] bytes= ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{INameService.class});
        //FileOutputStream fis=new FileOutputStream("E://abc.class");
        //fis.write(bytes);
        //fis.close();


        String name = serviceImp.getName();
        System.out.println("Run test name="+name);
    }


    //public void testPoxy(){
    //    byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{WhcBatchAttributeService.class});
    //    try(
    //        FileOutputStream fos =new FileOutputStream(new File("D:/proxyClass/$Proxy.class"))
    //    ){
    //        fos.write(bytes);
    //        fos.flush();
    //    }catch (Exception e){
    //        e.printStackTrace();
    //    }
    //}

    public static void generateClassFile(Class clazz,String proxyName)
    {
        ////根据类信息和提供的代理类名称，生成字节码
        //byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        //String paths = "D:\\"; // 这里写死路径为 D 盘，可以根据实际需要去修改
        //System.out.println(paths);
        //FileOutputStream out = null;
        //
        //try {
        //    //保留到硬盘中
        //    out = new FileOutputStream(paths+proxyName+".class");
        //    out.write(classFile);
        //    out.flush();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //} finally {
        //    try {
        //        out.close();
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}
    }


}
