from json import load
from bs4 import BeautifulSoup
import sqlite3

class Bookmark:
    def __init__(self,title,url,image):
        self.title = title
        self.url = url
        self.image = image

    def getTitle(self):
        return self.title
    def getUrl(self):
        return self.url
    def getImage(self):
        return self.image

def db(bookmarks):
    # データベースを作成・登録
    DBNAME = './data/bookmark.db'
    conn = sqlite3.connect('./bookmark.db')
    cur = conn.cursor()

    cur.execute(
        'CREATE TABLE bookmarks(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\
                                title TEXT,\
                                url TEXT,\
                                image TEXT)')

    sql = 'INSERT INTO bookmarks(title,url,image) values (?,?,?)'
    print("begin insert to db")
    for n, i in enumerate(bookmarks):
        data = (i.getTitle(), i.getUrl(), i.getImage())
        cur.execute(sql, data)
        if n == len(bookmarks) - 1:
            print("end insert to db ({})".format(n))

    conn.commit()

    cur.close()
    conn.close()


    
if __name__ == '__main__':
    load_url = "./data/test.html" #localのhtmlを対象にする
    soup = BeautifulSoup(open(load_url), "html.parser")

    # html解析
    li_list = soup.find_all("h3")
    ref_list = soup.find_all("a")

    bookmarks = []

    for i, ref in enumerate(ref_list):
        title = ""
        if len(ref.contents) == 0:# 名無しブックマークへの対策
            title = "None"
        else:
            title = ref.contents[0]
        bookmarks.append(Bookmark(title , ref.get('href'), ref.get('icon')))

    db(bookmarks)

