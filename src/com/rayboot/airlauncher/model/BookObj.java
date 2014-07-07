package com.rayboot.airlauncher.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-25 16:01
 * @TODO
 */

@Table(name = "books_db")
public class BookObj extends FileObj
{
    @Column(name = "desc")
    public String desc;
    @Column(name = "author")
    public String author;

    public BookObj()
    {
    }

    public static BookObj getObjFromTitle(String title)
    {
        return new Select().from(BookObj.class)
                .where("title = ?", title)
                .executeSingle();
    }

    public static List<BookObj> getAll()
    {
        return new Select().from(BookObj.class).execute();
    }

    public static List<BookObj> getTopObjs( int count)
    {
        return new Select().from(BookObj.class)
                .limit(count)
                .execute();
    }
}
