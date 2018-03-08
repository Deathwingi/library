package com.zpz.went;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by home on 2018/3/8.
 */
public class BorrowService {
    public static final BorrowService me=new BorrowService();
    private static final Book bookDao=new Book().dao();


    public List<Book> getBookList(){
        List<Book> bookList=null;
        bookList=bookDao.find(Db.getSqlPara("book.findBook"));
        return bookList;
    }

    public Ret BorrowBook(Integer bookId){
        Book book=bookDao.findFirst(Db.getSqlPara("book.findById",bookId));
        if(book.getNumb()==0){
            return Ret.by("status",false).set("message","没有库存了");
        }else{
            if(book.setNumb(book.getNumb()-1).update()){

                return Ret.by("status",true);
            } else {

                return Ret.by("status",false).set("message","错误");
            }
        }
    }
}
