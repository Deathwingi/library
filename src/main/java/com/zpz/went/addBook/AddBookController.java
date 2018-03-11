package com.zpz.went.addBook;

import com.jfinal.kit.Ret;
import com.zpz.went.common.controller.BaseController;
import com.zpz.went.common.model.BookDetail;

import java.util.List;

/**
 * Created by home on 2018/3/11.
 */
public class AddBookController extends BaseController {
    AddBookService srv = new AddBookService();

    public void index() {
        List<BookDetail> bookDetailList = srv.getBookDetailList();
        setAttr("bookDetailList", bookDetailList);
        render("AddBook.html");
    }

    public void sub() {
        int bookId = getParaToInt();
        Ret ret = srv.deleteBook(bookId);
        renderJson(ret);
    }

    public void plus() {
        String bookName = getPara("addBookName");
        int bookNum = getParaToInt("addBookNum");
        Ret ret = srv.addBook(bookName, bookNum);
        renderJson(ret);
    }
}
