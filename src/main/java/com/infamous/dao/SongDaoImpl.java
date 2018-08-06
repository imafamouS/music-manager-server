package com.infamous.dao;

import com.infamous.hibernate.SessionFactoryBean;
import com.infamous.logging.Log;
import com.infamous.model.Song;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by infamouSs on 8/4/18.
 */
public class SongDaoImpl implements SongDao {
    
    private SessionFactory sessionFactory;
    
    public SongDaoImpl(SessionFactoryBean sessionFactoryBean) {
        this.sessionFactory = sessionFactoryBean.getSessionFactory();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Song> findAll() {
        List<Song> songs = new ArrayList<>();
        
        Session session = null;
        Transaction transaction = null;
        try {
            String queryStr = "SELECT * FROM song";
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryStr);
            query.addEntity(Song.class);
            
            songs.addAll(query.list());
            
            transaction.commit();
            
            Log.i("Get song is successful (" + songs.size() + " items)");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            Log.i("Failure to get songs");
            Log.e(ex.getMessage(), ex);
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return songs;
    }
    
    @Override
    public boolean save(Song song) {
        boolean result = false;
        
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            session.save(song);
            
            transaction.commit();
            
            result = true;
            Log.i("Song is created successfully");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            result = false;
            
            Log.i("Failure to create song");
            Log.e(ex.getMessage(), ex);
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
    
    @Override
    public boolean delete(Song song) {
        boolean result = false;
        
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            session.delete(song);
            
            transaction.commit();
            
            result = true;
            Log.i("Song(#" + song.getId() + ") is deleted");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            result = false;
            
            Log.i("Failure to delete song");
            Log.e(ex.getMessage(), ex);
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
    
    @Override
    public boolean delete(long id) {
        boolean result = false;
        
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            
            Song song = (Song) session.get(Song.class, id);
            session.delete(song);
            
            transaction.commit();
            
            result = true;
            Log.i("Song(#" + song.getId() + ") is deleted");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            
            result = false;
            
            Log.i("Failure to delete song");
            Log.e(ex.getMessage(), ex);
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
    
}
