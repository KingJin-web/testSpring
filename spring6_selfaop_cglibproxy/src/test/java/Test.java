import com.king.LogAspectCglib;
import com.king.biz.StudentBizImpl;

/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-10 20:53
 */
public class Test {


    private static  StudentBizImpl studentBiz = new StudentBizImpl();
    private static LogAspectCglib logAspectCglib = new LogAspectCglib(studentBiz);


    public static void main(String[] args) {
        Object obj = logAspectCglib.createProxy();
        System.out.println(obj);
        if (obj instanceof StudentBizImpl){
//            StudentBizImpl s = new StudentBizImpl();
//            s.find("张三强");
//            s.add("张三强");
//            s.update("张三强");

            StudentBizImpl s = (StudentBizImpl) obj;
            s.find("张三强");
            s.add("张三强");
            s.update("张三强");
        }
    }
}
