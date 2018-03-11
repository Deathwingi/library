#sql("findBookLeft")
select * FROM j_bookDetail where j_bookDetail.book_id=#para(0) and j_bookDetail.belong=0
#end

#sql("findByBelong")
SELECT * FROM  j_bookDetail where belong=#para(0) and book_id=#para(1)
#end

#sql("findBookOut")
select * FROM j_bookDetail where j_bookDetail.book_id=#para(0) and j_bookDetail.belong!=0
#end

#sql("findAllBook")
SELECT * FROM j_bookDetail
#end

#sql("findById")
SELECT * FROM j_bookDetail where id=#para(0)
#end