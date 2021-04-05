package com;

import java.util.Random;

import com.huwei.bean.Container;
import com.mimi.bean.Person;
import com.mimi.bean.PersonBmiTool;


public class Test {

	public static void main(String[] args) {
		Container c=new Container();   //创建容器
		PersonBmiTool pbt=new PersonBmiTool(); //创建测量工具
		//将测量工具加入到容器中
		c.setMeasurable(   pbt );   //   设置测量工具，并初始化所有的容器数据
		
		Person p1=new Person( "张三", 1.70, 80);
		Person p2=new Person( "李四", 1.70, 60);     //bmi最小
		Person p3=new Person( "王五", 1.60, 90);     // bmi值大
		Person p4=new Person( "赵六", 1.66, 90); 
		Person p5=new Person( "田七", 1.65, 90); 
		Person p6=new Person( "王八", 1.67, 190); 
		
		c.save(   p1 );
		c.save(p2);
		c.save(  p3);
		c.save(p4);
		c.save(p5);
		c.save(p6);
		
		Random r=new Random();
		for(   int i=0;i<1000;i++){
			//  Math.random()   生成 0-1之间的小数
			Person p7=new Person( "王八"+i,   1+Math.random()    ,   r.nextInt(80)+30  ); 
			c.save(p7);
		}
		
	
		
		Person max=(Person)c.getMax();   //取最大值
		Person min=(Person)c.getMin();   //最最小值
		
		System.out.println("最大值:"+  max.getName() );
		System.out.println("最小值:"+ min.getName());
		
		System.out.println("平均bmi:"+ c.getAvg() );
		
		Object[] objs=c.getObjs();
		for(   Object o: objs ){
			 Person pp=(Person)o;
			 System.out.println(    pp.getName()+"\t"+pp.getHeight()+"\t"+pp.getWeight() +"\t"+   pbt.measure(   pp  )  );
		}
		
	}

}
