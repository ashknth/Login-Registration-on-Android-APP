package com.dbworks;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {
    @Insert
    void insert(StudentEntity studentEntity);

    @Query("SELECT *from studentDB where studentEmail=(:studentEmail) and studentPassword=(:studentPassword)")
    StudentEntity login(String studentEmail,String studentPassword);



}
