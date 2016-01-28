package org.knoxcraft.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.logger.Logman;

public class UndoAccess extends DataAccess
{
    public static Logman logger=Logman.getLogman(UndoAccess.class.getName());
    
    // XXX should we read UNDO_TABLE_NAME out of a configuration file?
    public static final String UNDO_TABLE_NAME="undo";
    
    public UndoAccess() {
        super(UNDO_TABLE_NAME);
        timestamp=System.currentTimeMillis();
    }

    @Column(columnName="playerName",
            dataType=DataType.STRING,
            notNull=true)
    public String playerName;

    @Column(columnName="timestamp",
            dataType=DataType.LONG)
    public Long timestamp;
    
    //TODO:  Need to figure out how to store buffer-- maybe represent stacks as strings?
    @Column(columnName="buffer",
            dataType=DataType.STRING)
    public String buffer;
    
    @Override
    public DataAccess getInstance() {
        return new UndoAccess();
    }
    
    //TODO:  What does this method need to do?  Just return all of the buffers in the db?
    /*public static Map<String,UndoAccess> getMostRecentScripts() throws DatabaseReadException {
        Map<String,UndoAccess> mostRecentScripts=new HashMap<String,UndoAccess>();
        UndoAccess data=new UndoAccess();
        List<DataAccess> results=new LinkedList<>();
        Map<String,Object> filters=new HashMap<String,Object>();
        Database.get().loadAll(data, results, filters);
        for (DataAccess d : results) {
            UndoAccess scriptAccess=(UndoAccess)d;
            // Figure out the most recent script for each player-scriptname combo
            String key=scriptAccess.playerName+"-"+scriptAccess.scriptName;
            if (!mostRecentScripts.containsKey(key)) {
                mostRecentScripts.put(key, scriptAccess);
            } else {
                if (scriptAccess.timestamp > mostRecentScripts.get(key).timestamp) {
                    mostRecentScripts.put(key,scriptAccess);
                }
            }

        }
        return mostRecentScripts;
    }*/
}
