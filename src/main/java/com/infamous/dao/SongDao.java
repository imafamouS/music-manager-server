package com.infamous.dao;

import com.infamous.model.Song;
import java.util.List;

/**
 * Created by infamouSs on 8/4/18.
 */
public interface SongDao {
    
    List<Song> findAll();
    
    boolean save(Song song);
    
    boolean delete(Song sone);
    
    boolean delete(long id);
}
