import os
import re
import pymysql

# 连接到MySQL数据库
db = pymysql.connect(
    host='localhost',
    user='root',
    password='123456',
    database='mlib'
)

def addBooks(Path:str):
    # 创建游标对象
    cursor = db.cursor()

     # 提取图书类型（根据文件名）
    book_type = Path.split('/')[-1].split('.')[0]  # 获取文件名并去除扩展名

    # 查询图书类型是否已存在于数据库中
    query = "SELECT id FROM book_type WHERE type_name = %s"
    cursor.execute(query, (book_type,))
    result = cursor.fetchone()

    if result:
        # 图书类型已存在，使用已有的类型ID
        type_id = result[0]
    else:
        # 图书类型不存在，插入新的类型，并获取新插入的类型ID
        insert_query = "INSERT INTO book_type (type_name) VALUES (%s)"
        cursor.execute(insert_query, (book_type,))
        type_id = cursor.lastrowid

    # 打开文本文件
    with open(Path, 'r', encoding='utf-8') as file:
        # 逐行读取文件
        for line in file:
            # 解析文本行，提取字段值
            data = eval(line)  # 使用eval将字符串转换为字典

            # 提取字段值
            title = data.get('title', '')

            # 检查标题是否已存在于数据库中
            query = "SELECT book_id FROM book WHERE title = %s"
            cursor.execute(query, (title,))
            result = cursor.fetchone()

            if result is not None:
                book_id = result[0]
                print(f"标题 '{title}' 已存在于数据库中，跳过插入。")
                # 将图书与类型关系添加到图书类型关系表
                # 检查关系是否已存在于数据库中
                query = "SELECT COUNT(*) FROM book_type_relation WHERE book_id = %s AND type_id = %s"
                cursor.execute(query, (book_id, type_id))
                result = cursor.fetchone()[0]
                if result <= 0:
                    relation_insert_query = "INSERT INTO book_type_relation (book_id, type_id) VALUES (%s, %s)"
                    cursor.execute(relation_insert_query, (book_id, type_id))
                continue
            if re.match(r'^[^\u4e00-\u9fff]+$', title):
                language = 'en'
            else:
                language = 'zh'
            author_info = ', '.join(data.get('author_info', []))
            publisher = ', '.join(data.get('publisher', []))
            publish_date = ', '.join(data.get('publishDate', []))
            description = data.get('descript', '')
            cover_image = data.get('cover_image', '')
            price_info = ', '.join(data.get('price_info', []))
            rating_num = data.get('rating_num', '')
            if len(price_info) > 20:
                price_info = ''
            if len(rating_num) > 10:
                rating_num = ''
            if len(publish_date) > 20:
                publish_date = '暂无'
    
            # 构建插入语句
            insert_query = "INSERT INTO book (title, author, publisher, publish_date, description, price, rating_num, language, cover_image) " \
                        "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"

            # 执行插入操作
            cursor.execute(insert_query, (title, author_info, publisher, publish_date, description, price_info, rating_num, language, cover_image))

            # 获取新插入行的book_id
            book_id = cursor.lastrowid

            # 将图书与类型关系添加到图书类型关系表
            relation_insert_query = "INSERT INTO book_type_relation (book_id, type_id) VALUES (%s, %s)"
            cursor.execute(relation_insert_query, (book_id, type_id))
        

        # 提交更改
        db.commit()
        # 关闭游标连接
        cursor.close()

if __name__ == "__main__":
    books_directory = 'books'
    for filename in os.listdir(books_directory):
        file_path = os.path.join(books_directory, filename)
        if os.path.isfile(file_path):
            addBooks(file_path)
