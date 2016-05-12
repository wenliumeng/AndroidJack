package com.cn.seymour.androidjack.reflect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.*;

import java.lang.reflect.Method;
import java.util.Properties;
/**
 * 反射
 * <br>Created by seyMour on 2016/4/22.</br>
 * <br>☞120465271@qq.com☜</br>
 */
public class hello {



    /**
     * @param args
     */
    public static void main(String[] args) {
        Class<?> demo = null;
        try {
            demo = Class.forName("Reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //通过Class调用其他类中的构造函数 （也可以通过这种方式通过Class创建其他类的对象）
		/*Person pre = null;
		try {
			pre = (Person)demo.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pre.setName("symour");
		pre.setAge(3);
		System.out.println(pre);*/

		/*Person pre1=null;
		Person pre2=null;
		Person pre3=null;
		Person pre4=null;

		Constructor<?> cons[] = demo.getConstructors();

		for(int i  =0 ; i < cons.length;i++)
		{
			System.out.println(i + "  " + cons[i]);
		}


		System.out.println(cons.length);
		try {
			pre1 = (Person)cons[3].newInstance();
			pre2 = (Person)cons[0].newInstance("seymour");
			pre3 = (Person)cons[1].newInstance(20);
			pre4 = (Person)cons[2].newInstance("seymour",20);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(pre1);
		System.out.println(pre2);
		System.out.println(pre3);
		System.out.println(pre4);*/

        //接下来让我们取得其他类的全部属性吧，最后我讲这些整理在一起，也就是通过class取得一个类的全部框架
		/*Field[] field = demo.getDeclaredFields();
		for(int i = 0 ;i < field.length;i++)
		{
			int mo = field[i].getModifiers();
			String str = Modifier.toString(mo);

			Class<?> type = field[i].getType();
			System.out.println(str + " " + type.getName()+" " + field[i].getName());
		}*/

        //调用其他类的set和get方法
		/*Class<?>[] inter = demo.getInterfaces();
		System.out.println(inter[0].getName());

		Class<?> supe = demo.getSuperclass();

		System.out.println(demo.getSuperclass().getName());*/

		/*try {
			Method method = demo.getMethod("sayChina");
			method.invoke(demo.newInstance());

			method = demo.getMethod("sayHello",String.class,int.class);
			method.invoke(demo.newInstance(), "symout",10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*Object obj = demo.getClass();
		setter(obj, "Name", "男", String.class);
		getter(obj, "Name");*/

        //通过反射操作属性
		/*try {
			Object obj =demo.newInstance();
			Field field = demo.getDeclaredField("name");
			field.setAccessible(true);
			field.set(obj, "xxx");
			System.out.println(field.get(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

        //通过反射取得并修改数组的信息：
		/*int[] temp = {1,2,3,4,5};
		Class<?> demo1 = temp.getClass().getComponentType();
//		System.out.println(Array.get(temp, 1));
		Array.set(temp, 1, 10);
		System.out.println(Array.get(temp, 1));*/

        //通过反射修改数组大小
		/*int[] temp = {1,2,3,4,5};
		int[] newTemp = (int[])arrayInc(temp,15);
		print(newTemp);
		String[] atr = {"a","b","c"};
		String[] str1 = (String[])arrayInc(atr, 8);
		print(str1);*/

        //类加载器，动态加载类
		/*Person p = new Person();
		System.out.println(p.getClass().getClassLoader().getClass().getName());*/

        //反射 代理
		/*MyInvocationHandler m = new MyInvocationHandler();
		Subject sub = (Subject)m.bind(new RealSubject());
		String info = sub.say("Rollen", 20);
		System.out.println(info);*/

        //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
        //工厂模式
		/*fruit f = Factory.getInstance("Apple");
		f.eat();*/

        //反射工厂模式
		/*fruit f1 = Factory1.getInstance("Reflect.Orange");
		f1.eat();*/

        //propertise反射工厂模式
        Properties p = init.getpro();
        fruit f2 = Factory1.getInstance(p.getProperty("orange"));
        f2.eat();
        //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
    }

    private static void print(Object obj) {
        Class<?> c = obj.getClass();
        if(!c.isArray())
        {
            return;
        }

        System.out.println("数组的长度为   " + Array.getLength(obj));
        for(int i = 0 ; i < Array.getLength(obj); i++)
        {
            System.out.print(Array.get(obj, i));
        }

    }


    private static Object arrayInc(Object obj, int i) {
        Class<?> target = obj.getClass().getComponentType();
        Object newArray = Array.newInstance(target, i);
        System.arraycopy(obj, 0, newArray, 0, Array.getLength(obj));
        return newArray;
    }


    public static void getter(Object obj, String att) {
        try {
            Method method = obj.getClass().getMethod("get" + att);
            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setter(Object obj,String att,Object value,Class<?> type){
        Method method;
        try {
            method = obj.getClass().getMethod("set"+att, type);
            method.invoke(method, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

interface fruit{
    public abstract void eat();
}

class Apple implements fruit{
    @Override
    public void eat() {
        System.out.println("Apple");
    }
}

class Orange implements fruit{
    @Override
    public void eat() {
        System.out.println("Orange");
    }
}

class Factory{
    public static fruit getInstance(String fruitName){
        fruit f = null;
        if(fruitName.equals("Apple"))
        {
            f =new Apple();
        }
        if(fruitName.equals("Orange"))
        {
            f = new  Orange();
        }
        return f;
    }
}

class Factory1{
    public static fruit getInstance(String fruitName){
        fruit f = null;
        try {
            f = (fruit)Class.forName(fruitName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}

class init {
    public static Properties getpro() {
        Properties pro = new Properties();
        File file = new File("fruit.properties");
        try {
            if (file.exists()) {

                pro.load(new FileInputStream(file));

            } else {
                pro.setProperty("apple", "Reflect.Apple");
                pro.setProperty("orange", "Reflect.Orange");
                pro.store(new FileOutputStream(file), "Frult class");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }
}

interface Subject{
    public String say(String name,int age);
}

class RealSubject implements Subject{

    @Override
    public String say(String name, int age) {
        return name + "  " + age;
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object obj = null;

    public Object bind(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object temp = method.invoke(this.obj, args);
        return temp;
    }

}


interface China{
    public static final String name = "seymour";
    public static int age =20;
    public void sayChina();
    public void sayHello(String name,int age);
}

class Person implements China{

    public Person(){
    }

    public Person(String name) {
        super();
        this.name = name;
    }

    public Person(int age) {
        super();
        this.age = age;
    }

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String toString(){
        return "["+this.name+" " + this.age + "]";
    }

    @Override
    public void sayChina() {
        System.out.println("hello ,china");
    }

    @Override
    public void sayHello(String name, int age) {
        System.out.println(name+"  "+age);
    }
}
