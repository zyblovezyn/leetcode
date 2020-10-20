package utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        list.clear();
        for (int i = 0; i <= 501; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        list.clear();

        for (int i = 0; i <= 500; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        list.clear();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        list.clear();

        for (int i = 0; i <= 1000; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        list.clear();

        for (int i = 0; i < 1031; i++) {
            list.add(i);
        }
        list1 = CommonUtils.getElementsBySplitCount(list, 500);
        list.clear();
    }

    @Test
    void print() {
    }

    @Test
    void faceType2Type() throws IllegalAccessException {
        Person person = new Person();
        person.setAge(100);
        person.setId(1);
        person.setName("zyb");

        SubPerson subPerson = new SubPerson();
        subPerson = CommonUtils.type2Type(person, subPerson);

    }

}