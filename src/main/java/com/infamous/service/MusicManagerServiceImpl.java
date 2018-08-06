package com.infamous.service;

import com.infamous.action.MusicManagerAction;
import com.infamous.dao.SongDao;
import com.infamous.model.Song;
import com.infamous.notification.ActionNotification;
import java.util.List;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

/**
 * Created by infamouSs on 8/4/18.
 */
public class MusicManagerServiceImpl implements MusicManagerService, NotificationPublisherAware {
    
    private SongDao songDao;
    private NotificationPublisher publisher;
    
    public MusicManagerServiceImpl() {
    
    }
    
    @Override
    public synchronized List<Song> findAll() {
        this.publisher
                  .sendNotification(new ActionNotification(MusicManagerAction.FIND_ALL, this, 0));
        
        return songDao.findAll();
    }
    
    @Override
    public synchronized boolean save(Song song) {
        this.publisher.sendNotification(new ActionNotification(MusicManagerAction.SAVE, this, 1));
        return songDao.save(song);
    }
    
    @Override
    public synchronized boolean delete(Song song) {
        this.publisher.sendNotification(new ActionNotification(MusicManagerAction.DELETE, this, 2));
        return songDao.delete(song);
    }
    
    @Override
    public synchronized boolean delete(long id) {
        this.publisher.sendNotification(
                  new ActionNotification(MusicManagerAction.DELETE_BY_ID, this, 3));
        return songDao.delete(id);
    }
    
    public SongDao getSongDao() {
        return songDao;
    }
    
    public void setSongDao(SongDao songDao) {
        this.songDao = songDao;
    }
    
    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.publisher = notificationPublisher;
    }
}
