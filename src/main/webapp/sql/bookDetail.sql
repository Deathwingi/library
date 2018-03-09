#sql("findBookLeft")
select * FROM j_bookDetail where j_bookDetail.book_id=#para(0) and j_bookDetail.belong=0
#end