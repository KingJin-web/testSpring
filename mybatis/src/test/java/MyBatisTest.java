/**
 * @program: testSpring
 * @description:
 * @author: King
 * @create: 2021-04-04 21:29
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.king.dao.MyUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisTest {
    public static void main(String[] args) {
        try {
            // 读取配置文件 mybatis-config.xml
            InputStream config = Resources.getResourceAsStream("mybatis-config.xml");

            // 根据配置文件构建SqlSessionFactory
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
            // 通过 SqlSessionFactory 创建 SqlSession
            SqlSession ss = ssf.openSession();
            // SqlSession执行映射文件中定义的SQL，并返回映射结果
            /*
             * com.mybatis.selectUserById 为 UserMapper.xml
             * 中的命名空间+select 的 id
             */
            // 查询一个用户
            MyUser mu = ss.selectOne(
                    "selectUserById", 1);
            System.out.println(mu);
            // 添加一个用户
            MyUser addmu = new MyUser();
            addmu.setUname("陈恒");
            addmu.setUsex("男");
            ss.insert("addUser", addmu);
            // 修改一个用户
            MyUser updatemu = new MyUser();
            updatemu.setUid(1);
            updatemu.setUname("张三");
            updatemu.setUsex("女");
            ss.update("updateUser", updatemu);
            // 删除一个用户
            ss.delete("deleteUser", 3);
            // 查询所有用户
            List<MyUser> listMu = ss.selectList("selectAllUser");
            for (MyUser myUser : listMu) {
                System.out.println(myUser);
            }
            // 提交事务
            ss.commit();
            // 关闭 SqlSession
            ss.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}