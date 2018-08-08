package com.infamous.service;

import com.infamous.model.Song;
import java.util.List;

/**
 * Created by infamouSs on 8/4/18.
 */
public interface MusicManagerServiceMBean extends Service {
    
    List<Song> findAll();
    
    boolean save(Song song);
    
    boolean delete(Song song);
    
    boolean delete(long id);
}
