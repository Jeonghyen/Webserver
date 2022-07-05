drop table tblBoard;
drop table tblComment;

--게시판
create table tblBoard(

    seq number primary key,
    subject varchar2(300) not null,
    content varchar2(4000) not null,
    id varchar2(30) not null references tblUser(id),
    regdate date default sysdate not null,
    readcount number default 0 not null,
    thread number not null,
    depth number not null,
    filename varchar2(100) null, --첨부파일
    orgfilename varchar2(100) null
);

delete from tblboard;

select * from tblboard where seq = 1;
select * from tblboard;

commit;


create or replace view vwBoard
as
select 
    seq,
    subject,
    content,
    id,
    (select name from tblUser where id=tblboard.id) as name,
    regdate,
    readcount,
    (select count(*) from tblComment where pseq =tblboard.seq) as commentcount,
    depth,
    (sysdate- regdate) as isnew,
    filename
from tblBoard order by thread desc;

select * from vwBoard;

commit;

--페이징처리
select * from (select a.*, rownum as rnum from vwBoard a) where rnum between 1 and 10;
select * from (select a.*, rownum as rnum from vwBoard a) where rnum between 11 and 20;
select * from (select a.*, rownum as rnum from vwBoard a) where rnum between 21 and 30;




commit;

drop sequence seqBoard;
create sequence seqBoard;

drop table tblComment;

--댓글 테이블
create table tblComment(

    seq number primary key,
    content varchar2(1000) not null,
    id varchar2(30) not null references tblUser(id),
    regdate date default sysdate not null,
    pseq number not null references tblBoard(seq)

);

drop sequence seqComment;
create sequence seqComment;

select * from tblComment;

delete from tblComment;



--해시 태그 테이블
create table tblHashTag(
    seq number primary key,
    tag varchar2(100) unique not null   --해시 태그
);

create sequence seqHashTag;

--게시글과 태그 연결 테이블
create table tblTagging(
    seq number primary key,     --번호
    bseq number not null references tblBoard(seq),
    hseq number not null references tblHashTag(seq)
);

create sequence seqTagging;

select * from tblHashTag;
select * from tblTagging;



select * from tblHashTag h inner join tblTagging t on h.seq = t.hseq where bseq = 295;

select tag from tblHashTag order by tag asc;


select b.* from vwBoard b inner join tblTagging t on b.seq= t.bseq inner join tblHashTag h on h.seq = t.hseq where h.tag='후후';



--좋아요
create table tblGoodBad(

    seq number primary key,
    id varchar2(30) not null references tblUser(id),
    bseq number not null references tblBoard(seq),
    good number default 0 not null,
    bad number default 0 not null
);

create sequence seqGoodBad;

select * from tblGoodBad;

select tblBoard.*, (select name from tblUser where id = tblBoard.id) as name, (select sum(good) from tblGoodBad where bseq=tblBoard.seq)  as good, (select sum(bad) from tblGoodBad where bseq=tblBoard.seq) as bad, 
(select 
    case 
        when good =1 then 1
        when bad = 1 then 2
        else 3
    end
    from tblGoodBad where bseq = tblBoard.seq and id='hong') as goodbad from tblBoard where seq = 300;


select * from tblBoard; -- 회원별 글쓴 횟수
select * from tblComment; -- 회원별 댓글 횟수
select * from tblHashTag;   -- 태그별 카운트
select * from tblGoodBad;

select u.id, (select name from tblUser where id = u.id)as name, (select count(*) from tblBoard where id= u.id) as cnt from tblBoard b right outer join tblUser u on u.id = b.id group by u.id, name;

select u.id, (select name from tblUser where id = u.id)as name, (select count(*) from tblComment where id= u.id) as cnt from tblComment b right outer join tblUser u on u.id = b.id group by u.id, name;

select h.tag, (select count(*) from tblTagging where hseq = h.seq) as cnt from tblHashTag h left outer join tblTagging t on h.seq = t.hseq group by h.tag, h.seq;

select * from tblTagging;


create table tblFood(
    seq number primary key,
    name varchar2(100) not null,
    lat number not null,
    lng number not null,
    star number(1) not null,
    category number not null references tblCategory(seq)
);

drop table tblFood;
create sequence seqFood;
alter table tblFood modify(star number(3));

select * from tblFood;

create table tblCategory(
    seq number primary key,
    name varchar2(100) not null,
    marker varchar2(100) not null,
    icon varchar2(100) not null
);

insert into tblCategory values (1,'한식', 'm1','fa-solid fa-bowl-food');
insert into tblCategory values (2,'양식', 'm2','fa-solid fa-pizza-slice');
insert into tblCategory values (3,'카페', 'm3','fa-solid fa-mug-saucer');


select tblFood.*, (select marker from tblCategory where seq = tblFood.category) as marker, (select icon from tblCategory where seq = tblFood.category) as icon from tblFood order by name asc;
commit;

--<i class="fa-solid fa-bowl-food"></i>
--<i class="fa-solid fa-pizza-slice"></i>
--<i class="fa-solid fa-mug-saucer"></i>