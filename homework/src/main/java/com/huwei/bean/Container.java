package com.huwei.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class Container {
	
	public static final int LENGTH=5;    //定义常量
	
	
	private Object[] objs=new Object[   LENGTH   ];
	private int count;    //计数器，计量objs数组实际存的已测量对象的数目
	private Object max;   //数组中最大值的对象
	private Object min;   //数组中最小值的对象
	private double avg;   //平均值
	private double sum;   //总值
	
	private Measurable measurable;    //用于测量的测量工具
	
	private double objvalue;
	
	public double getObjvalue(){
		return this.objvalue;
	}
	
	
	public Object[] getObjs(){
		//只返回有效数据，无效数据不能返回
		
		Object[] newojbs=new Object[count];
		//根据count来创建一个新数组，将 objs数组的值复制到   新数组中
		System.arraycopy(objs, 0, newojbs, 0, count);
		//     System.arraycopy();
		return newojbs;
	}
	
	//设置当前容器所使用的测量工具. 
	public void setMeasurable( Measurable measurable){
		this.measurable=measurable;    //
		//因为测量工具已经更换，所以数据要清零
		objs=new Object[   LENGTH   ];
		count=0;
		max=null;
		min=null;
		avg=0;
	}
	
	public Object getMax(){
		return this.max;
	}
	public Object getMin(){
		return this.min;
	}
	public double getAvg(){
		return this.avg;
	}
	public void save(    Object obj){
		//判断对象是否为空null, 如果是null, 则返回
		if(   obj==null){
			System.out.println("要测量的对象不能为空");
			return;
		}
		//判断当前数组是否已经满了         count>=数组.length
		//TODO: 将数组做成动态数组，   2.0版本.
		if(   count>= objs.length ){
			//创建一个新数组，它的长度为   objs长度的两倍
			Object[] newobjects=new Object[   objs.length*2  ];
			//将objs中原来的值放到  新数组   System.arraycopy
			System.arraycopy(objs, 0, newobjects, 0, objs.length);
			// 将新数组的引用赋给  objs
			objs=newobjects;
		}
		//如果不能存了， 则返回. 将这个obj存到  objs数组中. 
		objs[count]=obj;
		//调用measurable.measure( obj) 对obj进行测量，得到测量后的值  double
		 objvalue=measurable.measure(  obj  );    // objvalue就是测量后的值
		//判断当前这个obj是否是第一个被没的对象，如果是，则最大值和最小值都是这个obj
		if(  count==0 ){
			max=obj;
			min=obj;
		}else{
			//如果不是第一个被测的对象, 将当前对象测量后的值与max及min进行比较，找到最大值与最小值存起来  max, min
			double maxvalue=measurable.measure(   max );    //调用测量工具测出最大值
			double minvalue=measurable.measure(  min );     //调测量工具测出最小值
			if( objvalue>maxvalue  ){
				max=obj;
			}
			if(   objvalue<minvalue){
				min=obj;
			}
		}
		//计数器加一, 
		count++;
		//sum累加测量值
		sum+=  objvalue;
		//计算平均值
		avg=sum/count;
	}
	
}














