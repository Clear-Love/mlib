create table mlib.book_format
(
    format_name  varchar(50)  not null comment '格式名称'
        primary key,
    location_url varchar(255) null comment '图书路径'
)
    comment '图书格式';

create table mlib.book_type
(
    id        int auto_increment
        primary key,
    type_name varchar(50) not null comment '图书类型名称',
    constraint type_name
        unique (type_name)
)
    comment '图书类型';

create table mlib.db_account
(
    id       int auto_increment comment '用户id'
        primary key,
    username varchar(255) null comment '用户名',
    password varchar(255) null comment '密码',
    email    varchar(255) null comment '邮箱',
    role     varchar(5)   not null comment '角色',
    constraint unique_email
        unique (email) comment '邮箱唯一',
    constraint unique_name
        unique (username) comment '用户名唯一'
)
    comment '账号';

create table mlib.bookList
(
    list_id       int auto_increment comment '书单ID'
        primary key,
    list_name     varchar(100)                       not null comment '书单名称',
    description   varchar(500)                       null comment '书单描述',
    create_time   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    creat_user    int                                not null comment '创建者',
    collect_count int      default 0                 null comment '收藏数量',
    constraint bookList_db_account_id_fk
        foreign key (creat_user) references mlib.db_account (id)
)
    comment '书单';

create definer = root@`%` trigger mlib.tr_db_account_role_check
    before insert
    on mlib.db_account
    for each row
BEGIN
    IF NEW.role NOT IN ('admin', 'user') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid role value';
END IF;
END;

create table mlib.publisher
(
    publisher_name varchar(100) not null comment '出版社名称',
    publisher_id   int          not null comment '出版社id'
        primary key
)
    comment '出版社';

create table mlib.book
(
    ISBN             varchar(20)    not null comment 'ISBN编号',
    language         varchar(50)    null comment '语言',
    word_count       int default -1 not null comment '字数',
    collect_count    int default 0  not null comment '收藏次数',
    book_name        varchar(100)   not null comment '书名',
    author           varchar(100)   not null comment '作者',
    introduction     varchar(500)   null comment '简介',
    publication_time date           null comment '出版时间',
    book_format      varchar(50)    null comment '图书格式',
    publisher_id     int            null comment '出版社id',
    id               int auto_increment comment '图书id'
        primary key,
    constraint book_fk_1
        foreign key (book_format) references mlib.book_format (format_name),
    constraint book_fk_2
        foreign key (publisher_id) references mlib.publisher (publisher_id)
)
    comment '图书';

create table mlib.bookList_book_relation
(
    id      int auto_increment comment 'ID'
        primary key,
    book_id int not null comment '图书ID',
    list_id int not null comment '书单ID',
    constraint bookList_book_relation_fk_1
        foreign key (book_id) references mlib.book (id),
    constraint bookList_book_relation_fk_2
        foreign key (list_id) references mlib.bookList (list_id)
)
    comment '书单和书的关系';

create index book_id
    on mlib.bookList_book_relation (book_id);

create index list_id
    on mlib.bookList_book_relation (list_id);

create table mlib.book_type_relation
(
    book_id int not null comment '图书id',
    type_id int not null comment '图书类型id',
    primary key (book_id, type_id),
    constraint book_type_relation_fk_1
        foreign key (book_id) references mlib.book (id),
    constraint book_type_relation_fk_2
        foreign key (type_id) references mlib.book_type (id)
)
    comment '图书类型标签关系';

create index type_id
    on mlib.book_type_relation (type_id);

create table mlib.user_collection_bookLists
(
    user_id     int not null comment '用户id',
    bookList_id int not null comment '书单id',
    primary key (user_id, bookList_id),
    constraint user_collection_bookLists_fk_1
        foreign key (user_id) references mlib.db_account (id),
    constraint user_collection_bookLists_fk_2
        foreign key (bookList_id) references mlib.bookList (list_id)
);

create index bookList_id
    on mlib.user_collection_bookLists (bookList_id);

create definer = root@`%` trigger mlib.increase_bookList_collect_count
    after insert
    on mlib.user_collection_bookLists
    for each row
BEGIN
UPDATE bookList
SET collect_count = collect_count + 1
WHERE bookList.list_id = NEW.bookList_id;
END;

create table mlib.user_collection_books
(
    user_id int not null comment '用户id',
    book_id int not null comment '图书id',
    primary key (user_id, book_id),
    constraint user_collection_books_fk_1
        foreign key (user_id) references mlib.db_account (id),
    constraint user_collection_books_fk_2
        foreign key (book_id) references mlib.book (id)
);

create index book_id
    on mlib.user_collection_books (book_id);

create definer = root@`%` trigger mlib.increase_book_collect_count
    after insert
    on mlib.user_collection_books
    for each row
BEGIN
UPDATE book
SET collect_count = collect_count + 1
WHERE book.id = NEW.book_id;
END;

create table mlib.user_config
(
    user_id       int                                      not null comment '用户uid'
        primary key,
    phone_number  varchar(20)                              null comment '电话',
    introduction  varchar(255)                             null comment '简介',
    address       varchar(50)                              null comment '地址',
    nickname      varchar(20)                              not null comment '昵称',
    date_of_birth datetime                                 null comment '生日',
    level         int                       default 0      null comment '等级',
    exp           int                       default 0      null comment '经验',
    avatar        varchar(100)                             null comment '头像url',
    gender        enum ('男', '女', '私密') default '私密' not null comment '性别',
    constraint user_config_db_account_id_fk
        foreign key (user_id) references mlib.db_account (id),
    check (`level` between 0 and 6)
);

create table mlib.comment
(
    comment_id int auto_increment
        primary key,
    book_id    int                                             not null,
    user_id    int                                             not null,
    content    varchar(500)                                    null,
    time       timestamp             default CURRENT_TIMESTAMP null,
    type       enum ('短评', '长评') default '短评'            not null,
    constraint comment_fk_1
        foreign key (book_id) references mlib.book (id),
    constraint comment_fk_2
        foreign key (user_id) references mlib.user_config (user_id)
);

create index book_id
    on mlib.comment (book_id);

create index user_id
    on mlib.comment (user_id);

