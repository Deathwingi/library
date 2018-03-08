package com.zpz.went;

import com.jfinal.kit.Ret;

import java.util.List;

/**
 * Created by home on 2018/3/8.
 */
public class BorrowController extends BaseController {
    private static final BorrowService bs=new BorrowService();
    public void index(){
        List<Book> bookList=bs.getBookList();
        setAttr("bookList", bookList);
        render("borrow.html");
    }

    public void sub(){
        Integer bookId=getParaToInt();
        Ret ret=bs.BorrowBook(bookId);
        renderJson(ret);
    }
}
