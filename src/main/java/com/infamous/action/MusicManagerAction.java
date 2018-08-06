package com.infamous.action;

/**
 * Created by infamouSs on 8/5/18.
 */
public class MusicManagerAction {
    
    public static Action SAVE = new Action("SAVE_SONG", "save song");
    
    public static Action FIND_ALL = new Action("FIND_ALL", "get all song");
    
    public static Action DELETE = new Action("DELETE", "delete song");
    
    public static Action DELETE_BY_ID = new Action("DELETE_BY_ID", "delete by id");
    
    public static Action[] getActions = new Action[]{
              SAVE,
              FIND_ALL,
              DELETE,
              DELETE_BY_ID
    };
}
