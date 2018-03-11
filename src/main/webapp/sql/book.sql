#sql("findBook")
select * FROM j_book
#end

#sql("findById")
select * FROM j_book where id =#para(0)
#end

#sql("findByName")
select * FROM j_book where name =#para(0)
#end