package com.isabella.dechat.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.isabella.dechat.bean.NearbyDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEARBY_DATA_BEAN".
*/
public class NearbyDataBeanDao extends AbstractDao<NearbyDataBean, Long> {

    public static final String TABLENAME = "NEARBY_DATA_BEAN";

    /**
     * Properties of entity NearbyDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Createtime = new Property(1, String.class, "createtime", false, "CREATETIME");
        public final static Property Phone = new Property(2, String.class, "phone", false, "PHONE");
        public final static Property Area = new Property(3, String.class, "area", false, "AREA");
        public final static Property Nickname = new Property(4, String.class, "nickname", false, "NICKNAME");
        public final static Property UserId = new Property(5, int.class, "userId", false, "USER_ID");
        public final static Property Introduce = new Property(6, String.class, "introduce", false, "INTRODUCE");
        public final static Property Gender = new Property(7, String.class, "gender", false, "GENDER");
        public final static Property Password = new Property(8, String.class, "password", false, "PASSWORD");
        public final static Property Lat = new Property(9, double.class, "lat", false, "LAT");
        public final static Property Lng = new Property(10, double.class, "lng", false, "LNG");
        public final static Property PicHeight = new Property(11, int.class, "picHeight", false, "PIC_HEIGHT");
        public final static Property PicWidth = new Property(12, int.class, "picWidth", false, "PIC_WIDTH");
        public final static Property ImagePath = new Property(13, String.class, "imagePath", false, "IMAGE_PATH");
        public final static Property Lasttime = new Property(14, long.class, "lasttime", false, "LASTTIME");
        public final static Property Age = new Property(15, int.class, "age", false, "AGE");
    }


    public NearbyDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public NearbyDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEARBY_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CREATETIME\" TEXT," + // 1: createtime
                "\"PHONE\" TEXT," + // 2: phone
                "\"AREA\" TEXT," + // 3: area
                "\"NICKNAME\" TEXT," + // 4: nickname
                "\"USER_ID\" INTEGER NOT NULL ," + // 5: userId
                "\"INTRODUCE\" TEXT," + // 6: introduce
                "\"GENDER\" TEXT," + // 7: gender
                "\"PASSWORD\" TEXT," + // 8: password
                "\"LAT\" REAL NOT NULL ," + // 9: lat
                "\"LNG\" REAL NOT NULL ," + // 10: lng
                "\"PIC_HEIGHT\" INTEGER NOT NULL ," + // 11: picHeight
                "\"PIC_WIDTH\" INTEGER NOT NULL ," + // 12: picWidth
                "\"IMAGE_PATH\" TEXT," + // 13: imagePath
                "\"LASTTIME\" INTEGER NOT NULL ," + // 14: lasttime
                "\"AGE\" INTEGER NOT NULL );"); // 15: age
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEARBY_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NearbyDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String createtime = entity.getCreatetime();
        if (createtime != null) {
            stmt.bindString(2, createtime);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(4, area);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
        stmt.bindLong(6, entity.getUserId());
 
        String introduce = entity.getIntroduce();
        if (introduce != null) {
            stmt.bindString(7, introduce);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(8, gender);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(9, password);
        }
        stmt.bindDouble(10, entity.getLat());
        stmt.bindDouble(11, entity.getLng());
        stmt.bindLong(12, entity.getPicHeight());
        stmt.bindLong(13, entity.getPicWidth());
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(14, imagePath);
        }
        stmt.bindLong(15, entity.getLasttime());
        stmt.bindLong(16, entity.getAge());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NearbyDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String createtime = entity.getCreatetime();
        if (createtime != null) {
            stmt.bindString(2, createtime);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(4, area);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
        stmt.bindLong(6, entity.getUserId());
 
        String introduce = entity.getIntroduce();
        if (introduce != null) {
            stmt.bindString(7, introduce);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(8, gender);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(9, password);
        }
        stmt.bindDouble(10, entity.getLat());
        stmt.bindDouble(11, entity.getLng());
        stmt.bindLong(12, entity.getPicHeight());
        stmt.bindLong(13, entity.getPicWidth());
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(14, imagePath);
        }
        stmt.bindLong(15, entity.getLasttime());
        stmt.bindLong(16, entity.getAge());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NearbyDataBean readEntity(Cursor cursor, int offset) {
        NearbyDataBean entity = new NearbyDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // createtime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phone
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // area
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nickname
            cursor.getInt(offset + 5), // userId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // introduce
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // gender
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // password
            cursor.getDouble(offset + 9), // lat
            cursor.getDouble(offset + 10), // lng
            cursor.getInt(offset + 11), // picHeight
            cursor.getInt(offset + 12), // picWidth
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // imagePath
            cursor.getLong(offset + 14), // lasttime
            cursor.getInt(offset + 15) // age
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NearbyDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCreatetime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setArea(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNickname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserId(cursor.getInt(offset + 5));
        entity.setIntroduce(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGender(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPassword(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setLat(cursor.getDouble(offset + 9));
        entity.setLng(cursor.getDouble(offset + 10));
        entity.setPicHeight(cursor.getInt(offset + 11));
        entity.setPicWidth(cursor.getInt(offset + 12));
        entity.setImagePath(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setLasttime(cursor.getLong(offset + 14));
        entity.setAge(cursor.getInt(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NearbyDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NearbyDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NearbyDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
