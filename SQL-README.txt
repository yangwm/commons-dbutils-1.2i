
1. see ibatis.sqlmap.engine.mapping.parameter.ParameterMap

2. 
Oracle page sql :
select *
from (select temp.*, rownum row_num 
      from ( select * from book order by id ) temp 
) where row_num between 1 and 15

select *
from (select temp.*, rownum row_num 
      from ( select * from book order by id ) temp 
      where rownum <= 15
) where row_num > 0 


MySQL page sql :
select * from book order by id limit 0, 15

select * from book where id >= (select id from book order by id limit 0, 1) limit 15


PostgreSQL page sql: 
select * from book order by id limit 15 offset 0

Sybase page sql: 
set rowcount 15
select id from book order by id 
set rowcount 0

set top 0 start at 15 * 
select id from book order by id 



