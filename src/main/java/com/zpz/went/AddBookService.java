package com.zpz.went;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * Created by home on 2018/3/11.
 */
public class AddBookService {
    private static final AddBookService me = new AddBookService();
    private static final BookDetail bdDao = new BookDetail().dao();
    private static final Book bookDao = new Book().dao();

    public List<BookDetail> getBookDetailList() {
        List<BookDetail> bookDetailList = null;
        bookDetailList = bdDao.find(Db.getSqlPara("" +
                "bookDetail.findAllBook"));
        return bookDetailList;
    }

    public Ret deleteBook(int Id) {
        BookDetail bookDetail = bdDao.findFirst(
                Db.getSqlPara("bookDetail.findById", Id)
        );
        int bookId = bookDetail.getBookId();
        Book book = bookDao.findFirst(Db.getSqlPara(
                "book.findById", bookId
        ));
        if (bookDetail.getBelong() != 0) {
            return Ret.by("status", false).set("message",
                    "图书已借出，暂不可删除");
        } else {
            book.setNumb(book.getNumb() - 1).update();
            bookDetail.delete();
            return Ret.by("status", true);
        }
    }

    public Ret addBook(String bookName, int bookNum) {
        Book book = bookDao.findFirst(Db.getSqlPara(
                "book.findByName", bookName
        ));
        if (book == null) {
            Book newBook = new Book();
            newBook.setName(bookName).setNumb(bookNum).save();
        }
        book = bookDao.findFirst(Db.getSqlPara(
                "book.findByName", bookName
        ));
        int bookId = book.getId();
        for (int i = 0; i < bookNum; i++) {
            BookDetail bookDetail = new BookDetail();
            bookDetail.setName(bookName).setBookId(bookId).save();
        }
        book.setNumb(book.getNumb() + bookNum).update();
        return Ret.by("status", true);
    }
}
