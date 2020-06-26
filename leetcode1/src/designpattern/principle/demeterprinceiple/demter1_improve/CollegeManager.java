package designpattern.principle.demeterprinceiple.demter1_improve;

import java.util.ArrayList;
import java.util.Iterator;
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

   public void printCollegeEmployee(){
       List<CollegeEmployee> list1=this.getAllEmployee();
       System.out.println("分公司员工！");
       Iterator<CollegeEmployee> it1=list1.iterator();
       while (it1.hasNext()){
           System.out.println(it1.next().getId());
       }
   }
}
