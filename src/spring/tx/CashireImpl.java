package spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cashier")
public class CashireImpl implements Cashier{

    @Autowired
    private BookShopService bookShopService;
    //添加事物注解
    //使用propagation指定事物的传播行为，即当前的事物方法被另外一个事物方法调用时
    //如何使用事物：默认取值为required，即调用方法的事物。requires_new为使用新的事物
    //isolation指定事物隔离级别，最常用的是读已提交
    //默认情况下是对所有运行异常进行回滚，可以通过对应的属性进行设置,通常用默认即可
    //使用readonly指定事物是否为只读，只读 readonly=true
    //timeout指定强制回滚之前事物可以占用的时间
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED, noRollbackFor ={UserAccountException.class} )
    @Override
    public void checkout(String username, List<String> isbns){
        for(String isbn:isbns){
            bookShopService.purchase(username,isbn);
        }
    }
}
