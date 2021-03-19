package org.sg.test.util;

import org.sg.entities.StudentEntity;

public class EntityUtils {
    
    public static StudentEntity createStudent() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("Manuel");
        studentEntity.setSurname("Castro");
        studentEntity.setJobTitle("Waiter");
        studentEntity.setPaymentType("Confirmed");
        studentEntity.setSex('M');
        return studentEntity;
    }
    
    public static StudentEntity updateStudent() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName("updatedName");
        studentEntity.setSurname("updatedSurname");
        studentEntity.setJobTitle("updatedJJobTitle");
        studentEntity.setPaymentType("Confirmed");
        studentEntity.setSex('M');
        return studentEntity;
    }
}
