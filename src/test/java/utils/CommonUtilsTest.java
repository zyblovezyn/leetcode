package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class CommonUtilsTest {

    @Test
    void getElementsBySplitCount() {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> list1 = new ArrayList<>();
        for (int i = 0; i < 211; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        Assertions.assertEquals(211, list1.get(0).size());
        list.clear();
        for (int i = 0; i <= 501; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        Assertions.assertEquals(501, list1.get(0).size());
        list.clear();

        for (int i = 0; i <= 500; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        Assertions.assertEquals(501, list1.get(0).size());
        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        Assertions.assertEquals(501, list1.get(0).size());
        Assertions.assertEquals(499, list1.get(1).size());
        list.clear();

        for (int i = 0; i <= 1000; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        Assertions.assertEquals(501, list1.get(0).size());
        Assertions.assertEquals(500, list1.get(1).size());
        list.clear();

        for (int i = 0; i < 1031; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        Assertions.assertEquals(501, list1.get(0).size());
        Assertions.assertEquals(500, list1.get(1).size());
        Assertions.assertEquals(30, list1.get(2).size());
        list.clear();
    }

    @Test
    void getElementsBySplitCount2() {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> list1 = new ArrayList<>();
        for (int i = 0; i < 211; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list);
        Assertions.assertEquals(211, list1.get(0).size());
        list.clear();
        for (int i = 0; i <= 501; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list);
        Assertions.assertEquals(501, list1.get(0).size());
        list.clear();

        for (int i = 0; i <= 500; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list);
        Assertions.assertEquals(501, list1.get(0).size());
        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list);
        Assertions.assertEquals(501, list1.get(0).size());
        Assertions.assertEquals(499, list1.get(1).size());
        list.clear();

        for (int i = 0; i <= 1000; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list);
        Assertions.assertEquals(501, list1.get(0).size());
        Assertions.assertEquals(500, list1.get(1).size());
        list.clear();

        for (int i = 0; i < 1031; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list);
        Assertions.assertEquals(501, list1.get(0).size());
        Assertions.assertEquals(500, list1.get(1).size());
        Assertions.assertEquals(30, list1.get(2).size());
        list.clear();
    }


    @Test
    void print() {
    }

    @Test
    void faceType2Type() throws IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("zyb");
        person.setAge(100);

        SubPerson subPerson = new SubPerson();
        subPerson = CommonUtils.type2Type(person, subPerson);
        Assertions.assertEquals(1, subPerson.getId());
        Assertions.assertEquals("zyb", subPerson.getName());
        Assertions.assertEquals("100", subPerson.getAge().toString());
    }

    @Test
    void type2TypeExtension() throws IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("zyb");
        person.setAge(100);

        SubPerson subPerson = new SubPerson();
        subPerson = CommonUtils.type2TypeExtension(person, subPerson);
        Assertions.assertEquals(1, subPerson.getId());
        Assertions.assertEquals("zyb", subPerson.getName());
        Assertions.assertEquals("100", subPerson.getAge().toString());
    }

    @Test
    void type2Type() throws IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("zyb");
        person.setAge(100);

        SubPerson subPerson = new SubPerson();
        subPerson = CommonUtils.type2Type(person, subPerson);
        Assertions.assertEquals(1, subPerson.getId());
        Assertions.assertEquals("zyb", subPerson.getName());
        Assertions.assertEquals("100", subPerson.getAge().toString());
    }

    @Test
    void list2List() throws IllegalAccessException {
        Person person = new Person();
        person.setId(1);
        person.setName("zyb");
        person.setAge(100);

        List<Person> list = Arrays.asList(person);
        List<SubPerson> rest = new ArrayList<>();
        SubPerson subPerson1=new SubPerson();
        try {
            rest =CommonUtils.list2List(list, subPerson1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SubPerson subPerson=rest.get(0);
        Assertions.assertEquals(1, subPerson.getId());
        Assertions.assertEquals("zyb", subPerson.getName());
        Assertions.assertEquals("100", subPerson.getAge().toString());
    }
}