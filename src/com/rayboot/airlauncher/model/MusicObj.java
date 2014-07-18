package com.rayboot.airlauncher.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-25 15:59
 * @TODO
 */
@Table(name = "musics_db")
public class MusicObj extends FileObj
{
    @Column(name = "desc")
    public String desc;

    public MusicObj()
    {
    }

    public static MusicObj getObjFromTitle(String title)
    {
        return new Select().from(MusicObj.class)
                .where("title = ?", title)
                .executeSingle();
    }

    public static List<MusicObj> getAll()
    {
        return new Select().from(MusicObj.class).execute();
    }

    public static List<MusicObj> getAllByPlayTimes()
    {
        return new Select().from(MusicObj.class)
                .orderBy("playtimes ASC")
                .execute();
    }

    public static List<MusicObj> getTopObjs( int count)
    {
        return new Select().from(MusicObj.class)
                .orderBy("playtimes ASC")
                .limit(count)
                .execute();
    }
}
