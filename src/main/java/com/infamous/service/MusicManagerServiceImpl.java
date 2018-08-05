package com.infamous.service;

import com.infamous.dao.SongDao;
import com.infamous.model.Song;
import java.util.List;

/**
 * Created by infamouSs on 8/4/18.
 */
public class MusicManagerServiceImpl implements MusicManagerService {
    
    private SongDao songDao;
    
    public MusicManagerServiceImpl() {
    
    }
    
    @Override
    public synchronized List<Song> findAll() {
        return songDao.findAll();
    }
    
    @Override
    public synchronized boolean save(Song song) {
        return songDao.save(song);
    }
    
    @Override
    public synchronized boolean delete(Song song) {
        return songDao.delete(song);
    }
    
    @Override
    public synchronized boolean delete(long id) {
        return songDao.delete(id);
    }
    
    public SongDao getSongDao() {
        return songDao;
    }
    
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }
}
