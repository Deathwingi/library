package com.zpz.went;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;


/**
 * Created by home on 2018/3/9.
 */
public class RestoreService {
    private static final RestoreService me = new RestoreService();
    private static final Book bookDao = new Book().dao();
    private static final BookDetail bdDao = new BookDetail().dao();


    public List<Book> getBookList() {
        List<Book> bookList = null;
        bookList = bookDao.find(Db.getSqlPara("book.findBook"));
        return bookList;
    }


    public Ret restoreBook(int book_id, int user_id) {
        Book book = bookDao.findFirst(Db.getSqlPara(
                "book.findById", book_id
        ));
        BookDetail bd = bdDao.findFirst(Db.getSqlPara(
                "bookDetail.findByBelong", user_id, book_id
        ));
        if (bd == null) {
            return Ret.by("status", false).set("message", "无此书可还");
        } else {
            book.setNumb(book.getNumb() + 1).update();
            bd.setBelong(0).update();
            return Ret.by("status", true);
        }
    }
}
