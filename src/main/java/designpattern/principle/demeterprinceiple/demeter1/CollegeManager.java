package designpattern.principle.demeterprinceiple.demeter1;

import java.util.ArrayList;
import java.util.List;

public class CollegeManager {
   public List<CollegeEmployee> getAllEmployee(){
       List<CollegeEmployee> list=new ArrayList<>();
       for (int i = 0; i < 10; i++) {
           CollegeEmployee employee=new CollegeEmployee();
           employee.setId("学院员工ID："+i);
           list.add(employee);
       }
       return list;
   }
}
