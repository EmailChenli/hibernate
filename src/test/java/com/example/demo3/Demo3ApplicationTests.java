package com.example.demo3;

import com.example.demo3.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class Demo3ApplicationTests {

    @Test
    void contextLoads() {
        List<Person> lamdaVOList = new ArrayList<>();
        Person user1 = new Person(1L, "小明", 1);
        Person user2 = new Person(2L, "小芳", 2);
        Person user3 = new Person(3L, "小华", 3);
        Person user4 = new Person(4L, "小华", 4);

        lamdaVOList.add(user1);
        lamdaVOList.add(user2);
        lamdaVOList.add(user3);
        lamdaVOList.add(user4);

        //lamda表达式 过滤加遍历
        System.out.println("lamda表达式 过滤加遍历");
        lamdaVOList.stream().filter(u -> u.getAge() > 1).
                forEach(u -> System.out.println(u.getAge() + ":::" + u.getName()));

        //lamda表达式对提取为map对象


        // Map<String, String> map = lamdaVOList.stream().
        //       collect(Collectors.toMap(LamdaVO::getName, LamdaVO::getEamil));
        //如果采用上面方式需要特别注意，使用Collectors.toMap时，需要保证生成map的唯一性，否则会报
        // java.lang.IllegalStateException: Duplicate key 错误。可以采用下面的方式规避，
        //下面  (o, n) -> o表示的 如果出现重复key，则用 原来代替新的key,所以打印的小华的邮箱 为
        //   小华::12324qq.com1.同样可以使用  (o, n) -> n 这样当出现重复key时候，则会用新的key代替老的key了，
        // 此时 打印的小华的邮箱  则为   小华::12324qq.com2


        System.out.println("lamda表达式对提取为map对象");
        Map<String, Integer> map = lamdaVOList.stream().
                collect(Collectors.toMap(Person::getName, Person::getAge, (o, n) -> o));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"---"+entry.getValue());
        }

        //lamda 表达式对对象里面某个属性提取List对象
        System.out.println("lamda 表达式对对象里面某个属性提取List对象");
        List<String> list = lamdaVOList.stream().map(Person::getName).collect(Collectors.toList());
        list.stream().forEach(s-> System.out.println(s));

        //lamda表达式变为map<String,List<String>>
        System.out.println("lamda表达式变为map<String,List<String>>");
        Map<String, List<Person>> map2 = lamdaVOList.stream().collect(Collectors.groupingBy(Person::getName));
        for (Map.Entry<String, List<Person>> entry : map2.entrySet()) {
            System.out.println(entry.getKey()+"::"+entry.getValue());
        }

    }

}
