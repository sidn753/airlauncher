package com.rayboot.airlauncher.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import java.io.Serializable;

/**
 * @author rayboot
 * @from 14-6-24 17:56
 * @TODO
 */
public class FileObj extends Model implements Serializable
{
    public static final String SPLIT_STRING = "-0--1-";
    @Column(name = "title")
    public String title;
    @Column(name = "filePath")
    public String filePath;
    @Column(name = "imgPath")
    public String imgPath;
    @Column(name = "playtimes")
    public int playtimes;

    public FileObj()
    {
    }
}
