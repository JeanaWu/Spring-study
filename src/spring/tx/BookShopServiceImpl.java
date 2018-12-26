package spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
    @Autowired//自动装配成员变量
    private BookShopDao bookShopDao;

    //添加事物注解
    @Transactional
    @Override
    public void purchase(String username, String isbn) {
        //1.get price
        int price=bookShopDao.findBookPriceByIsbn(isbn);
        //2.update stock
        bookShopDao.updateBookStock(isbn);
        //3.update balance
        bookShopDao.updateUserAccount(username,price);
    }
}
