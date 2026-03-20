package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //开启了SpringBoot对Servlet组件的扫描 这样才会扫描@WebServlet注解，起作用
@SpringBootApplication
public class TliasWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebApplication.class, args);
    }

}
/**
 * 事务管理：保证数据库的信息完整，一致
 * 事务是一组操作的集合，他是以一个不可分割的工作单位，
 * 会把所有操作作为一个整体一起向系统提交或撤销，也就是一个事务中的所有操作都成功，或者都失败。
 * 也就是说只有事务的操作全部成功才会提交，否则只要一个操作失败，则所有操作都失败。
 * 默认mysql默认是自动提交事务，即一个sql语句执行完毕，就自动提交，不能回滚。
 * 操作：1. 开启事务 2. 提交事务 3. 回滚事务
 *   1. 开启事务：
 *   start transaction/begin;
 *   2. 提交事务：
 *   commit;
 *   3. 回滚事务：
 *   rollback;
 *
 *   在数据库中，可使用
 *
 *   spring 事务管理 使用注解@Transactional 默认出现RuntimeException异常时才会回滚事务
 *   作用：将此方法交给spring进行事务管理，方法执行前开启事务，方法执行后提交事务，出现异常回滚事务
 *   可以写在类上，方法上，接口上
 *
 *   写在service层
 *
 * @Transactional(属性)
 *   rollbackFor = Exception.class  指定什么异常会回滚
 *   propagation = Propagation.REQUIRED  指定事务传播特性（指的就是一个事物
 *   方法被另一个事务方法调用时，这个事务方法应该如何进行事务控制 是创建，添加）
 *
 *
 *  事务的四大特性：原子性，一致性，隔离性（事务不受外部并发影响的独立环境下运行），持久性(一旦事务提交还是回滚都是永久的)
 */

/**
 * 文件上传：是指将本地图片等文件上传到服务器中，并保存在服务器中，供其他用户浏览下载的过程
 *  本地存储：将文件保存在服务器的本地磁盘中
 *    缺点：1，存储空间有限，不能保存海量数据 2，浏览器不能直接访问存储的文件 3，文件上传速度慢
 *  使用云存储：将文件保存在云服务器中，并返回一个url，供用户访问
 *   阿里云是一个云服务提供商，提供各种服务
 *   oss是阿里云的一种服务，可以通过网络随时存储和访问文件
 *   步骤：1. 创建一个阿里云账号 2. 开通服务 3. 创建一个bucket （是一个存储空间
 *   ，是用户存储对象的容器，所有的对象（就是指文件）必须隶属于某个存储空间）
 *   4. 获取并配置一个accessKey（密钥，是身份凭证）
 *
 *
 *  使用云服务： 要去查看SDK（软件开发工具包，包括辅助软件开发的依赖 （jar包），代码示例等）
 */

/**
 *  全局异常处理器 来处理异常
 *  异常会层层传递，直到被处理或者抛出到最顶层 可以在Controller层用try catch处理,
 *  但是太过于臃肿了 所以推荐使用全局异常处理器,会自动处理Controller层抛出的异常
 *  全局异常处理器会处理异常，显示给前端页面
 *  定义全局异常处理器 先要创建一个类
 */

/**
 * AOP - Aspect Oriented Programming(面向切面编程，面向方面编程)，
 *   可简单理解为面向特定方法编程
 *
 *   场景：比如定位执行耗时较长的方法，查找到这些方法，并打印耗时
 *   好处：1、代码复用性高 2，代码无侵入（不修改源代码）
 *
 *   spring AOP ：spring框架提供的一种面向切面编程
 *   步骤：1，引入依赖 2，编写aop类，写方法
 *      使用场景：1，记录日志 2，事务管理 3，数据权限控制
 *   事务管理的底层就是基于AOP
 *
 *  &&&&&代码在program3的项目中&&&&&&&&&
 */
