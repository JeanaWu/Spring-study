package spring.tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTransactionTest {

    private ApplicationContext ctx= null;
    private BookShopDao bookShopDao=null;
    private BookShopService bookShopService=null;
    {
        ctx=new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");
        bookShopDao= (BookShopDao) ctx.getBean("bookShopDao");
        bookShopService=(BookShopService)ctx.getBean("bookShopService");
    }

    @Test
    public void testBookShopService(){
        bookShopService.purchase("AA","1001");
    }

    @Test
    public void findBookPriceByIsbn() {
        System.out.println(bookShopDao.findBookPriceByIsbn("1001"));

    }

    @Test
    public void updateBookStock() {
        bookShopDao.updateBookStock("1001");
    }


    @Test
    public void updateUserAccount() {
        bookShopDao.updateUserAccount("AA",100);
    }
}