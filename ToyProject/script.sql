

drop table tblUser;


--회원 테이블
create table tblUser(
    id varchar2(30) primary key,
    pw varchar2(30) not null,
    name varchar2(30) not null,
    lv char(1) not null,                                -- 1.일반회원, 2.관리자
    pic varchar2(100) default 'pic.png' not null,  --프로필 사진
    regdate date default sysdate not null,         -- 가입 날짜
    active char(1) default 'y' not null                 --탈퇴 유무(활도중 y 탈퇴 n)
);

insert into tblUser(id, pw, name, lv, pic, regdate)
    values('hong', '1111', '홍길동', '1', default, default);
    
insert into tblUser(id, pw, name, lv, pic, regdate)
    values('admin', '1111', '관리자', '2', default, default);  
    
select * from tblUser order by regdate desc;

alter table tblUser add ( active char(1) default 'y' not null);

delete from tblUser where active='n';

commit;
    

    
    
    
    
    
    
    
    
    