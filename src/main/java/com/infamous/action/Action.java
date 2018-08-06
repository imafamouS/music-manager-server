package com.infamous.action;

/**
 * Created by infamouSs on 8/5/18.
 */
public class Action {
    
    private String id;
    private String message;
    
    public Action() {

    }
    
    public Action(String id, String message) {
        this.id = id;
        this.message = message;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        Action action = (Action) o;
        
        return id != null ? id.equals(action.id) : action.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "Action{" +
               "id='" + id + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}
