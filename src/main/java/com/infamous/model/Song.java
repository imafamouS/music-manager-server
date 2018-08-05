package com.infamous.model;

import java.io.Serializable;

public class Song implements Serializable {
    
    private static final long serialVersionUID = -4229371387864775863L;
    
    private long id;
    private String name;
    private String dateAdded;
    
    
    public Song() {
    
    }
    
    public Song(String name, String dateAdded) {
        this.name = name;
        this.dateAdded = dateAdded;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    @Override
    public String toString() {
        return "Song [id=" + id + ", name=" + name + ", dateAdded=" + dateAdded + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Song other = (Song) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}
