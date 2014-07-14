package com.rayboot.airlauncher.util;

import com.google.gson.Gson;
import com.rayboot.airlauncher.model.BookObj;
import com.rayboot.airlauncher.model.MovieObj;
import com.rayboot.airlauncher.model.MusicObj;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rayboot
 * @from 14-6-25 16:09
 * @TODO
 */
public class FileUtil
{
    public static final String MOVIES_DIRECTORY = "/mnt/sdcard/电影";
    public static final String BOOKS_DIRECTORY = "/mnt/sdcard/书籍";
    public static final String MUSICS_DIRECTORY = "/mnt/sdcard/音乐";

    List<String> dirs = new ArrayList<String>(3);

    public void getAllMoviesInfo()
    {
        dirs.clear();
        getAllFile(new File(MOVIES_DIRECTORY));
        analyseMoviesInfo(dirs);
    }

    public void getAllBooksInfo()
    {
        dirs.clear();
        analyseBooksInfo(new File(BOOKS_DIRECTORY));
    }

    public void getAllMusicsInfo()
    {
        dirs.clear();
        getAllFile(new File(MUSICS_DIRECTORY));
        analyseMusicsInfo(dirs);
    }

    //音乐目录下
    //第一级为专辑名称
    //专辑内包含  .mp3文件  logo.jpg图片以及info文件
    //必须存在info文件
    //封面图片必须命名为logo.jpg
    private void analyseMusicsInfo(List<String> dataes)
    {
        for (String fPath : dataes)
        {
            if (!new File(fPath + "/info").exists())
            {
                continue;
            }
            String title = fPath.substring(fPath.lastIndexOf("/") + 1);
            MusicObj musicObj = MusicObj.getObjFromTitle(title);
            if (musicObj != null)
            {
                continue;
            }
            musicObj = new MusicObj();
            StringBuffer musicPath = new StringBuffer();
            File files[] = new File(fPath).listFiles();
            for (File file : files)
            {
                if (file.getAbsolutePath().indexOf(".mp3") > 0)
                {
                    musicPath.append(file.getAbsoluteFile());
                    musicPath.append(MovieObj.SPLIT_STRING);
                }
            }
            musicObj.title = title;
            musicObj.imgPath = fPath + "/logo.jpg";
            musicObj.filePath = musicPath.toString();
            musicObj.save();
        }
    }

    //书籍目录下
    //直接扫描pdf文件
    //封面图片和pdf文件名一样，扩展名为jpg
    private void analyseBooksInfo(File directory)
    {
        File files[] = directory.listFiles();

        if (files != null)
        {
            for (File f : files)
            {
                if (f.getAbsolutePath().indexOf(".pdf") > 0)
                {
                    String title = f.getName().replace(".pdf", "");
                    BookObj bookObj = BookObj.getObjFromTitle(title);
                    if (bookObj == null)
                    {
                        bookObj = new BookObj();
                        bookObj.title = title;
                        bookObj.filePath = f.getAbsolutePath();
                        bookObj.imgPath =
                                bookObj.filePath.replace(".pdf", ".jpg");
                        bookObj.save();
                    }
                }
            }
        }
    }

    //电影名目录下必须否存在info文件
    //info文件为json数据，解析导演，演员等信息
    //电影文件必须为.mp4文件
    //电影logo图片必须命名为logo.jpg
    //电影背景图片必须命名为bg.jpg
    private void analyseMoviesInfo(List<String> dataes)
    {
        for (String fPath : dataes)
        {
            if (!new File(fPath + "/info").exists())
            {
                continue;
            }
            String title = fPath.substring(fPath.lastIndexOf("/") + 1);
            MovieObj movieObj = MovieObj.getObjFromTitle(title);
            if (movieObj != null)
            {
                continue;
            }
            StringBuffer videoPath = new StringBuffer();
            File files[] = new File(fPath).listFiles();
            for (File file : files)
            {
                if (file.getAbsolutePath().indexOf(".mp4") > 0
                        || file.getAbsolutePath().indexOf(".apg") > 0)
                {
                    videoPath.append(file.getAbsoluteFile());
                    videoPath.append(MovieObj.SPLIT_STRING);
                }
            }
            movieObj = new Gson().fromJson(readData(fPath + "/info"),
                    MovieObj.class);
            movieObj.title = title;
            movieObj.imgPath = fPath + "/logo.jpg";
            movieObj.bgImgPath = fPath + "/bg.jpg";
            movieObj.filePath = videoPath.toString();
            movieObj.save();
        }
    }

    public void getAllFile(File directory)
    {
        File files[] = directory.listFiles();

        if (files != null)
        {
            for (File f : files)
            {
                if (f.isDirectory())
                {
                    getAllFile(f);
                }
                else
                {
                    dirs.add(f.getParentFile().getAbsolutePath());
                    break;
                }
            }
        }
    }

    public static String readData(String filePath)
    {
        try
        {
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuffer res = new StringBuffer();
                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    res.append(lineTxt);
                }
                read.close();
                return res.toString();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
