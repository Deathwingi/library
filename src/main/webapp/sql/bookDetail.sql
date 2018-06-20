#sql("findBookLeft")
select * FROM j_book_detail where j_book_detail.book_id=#para(0) and j_book_detail.belong=0
#end

#sql("findByBelong")
SELECT * FROM  j_book_detail where belong=#para(0) and book_id=#para(1)
#end

#sql("findBookOut")
select * FROM j_book_detail where j_book_detail.book_id=#para(0) and j_book_detail.belong!=0
#end

#sql("findAllBook")
SELECT * FROM j_book_detail
#end

#sql("findById")
SELECT * FROM j_book_detail where id=#para(0)
#end