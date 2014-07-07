package com.rayboot.airlauncher.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-24 17:47
 * @TODO
 */
@Table(name = "movies_db")
public class MovieObj extends FileObj
{
    public static final String SPLIT_STRING = "$$";
    @Column(name = "desc")
    public String desc;
    @Column(name = "type")
    public String type;
    @Column(name = "timelength")
    public String timelength;
    @Column(name = "bgImgPath")
    public String bgImgPath;
    @Column(name = "director")
    public String director;
    @Column(name = "acts")
    public String acts;

    public MovieObj()
    {
    }

    public static MovieObj getObjFromTitle(String title)
    {
        return new Select().from(MovieObj.class)
                .where("title = ?", title)
                .executeSingle();
    }

    public static List<MovieObj> getAll()
    {
        return new Select().from(MovieObj.class).execute();
    }

    public static List<MovieObj> getAllByPlayTimes()
    {
        return new Select().from(MovieObj.class)
                .orderBy("playtimes ASC")
                .execute();
    }
}
