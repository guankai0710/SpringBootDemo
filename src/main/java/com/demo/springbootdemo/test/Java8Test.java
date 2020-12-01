package com.demo.springbootdemo.test;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * jdk1.8 中 Lambda表达式、stream流 测试
 *
 * @author guankai
 * @date 2020/8/10
 **/
public class Java8Test {

    public static void main(String[] args) {

        //测试数据
        Person p1 = new Person(1, "张三",25);
        Person p2 = new Person(2, "李四",21);
        Person p3 = new Person(3, "王五",31);
        Person p4 = new Person(4, "赵六",18);
        Person p5 = new Person(5, "AA",19);
        Person p6 = new Person(6, "BB",21);
        Person p7 = new Person(7, "CC",31);

        List<Person> list = new ArrayList<>(4);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);

        System.out.println("年龄升序===");
        List<Person> list1 = list.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
        list1.forEach(System.out::println);
        System.out.println();

        System.out.println("年龄降序===");
        List<Person> list2 = list.stream()
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .collect(Collectors.toList());
        list2.forEach(System.out::println);
        System.out.println();

        System.out.println("先按id升序，再按年龄降序===");
        List<Person> list3 = list.stream()
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparingInt(Person::getId).reversed())
                .collect(Collectors.toList());
        list3.forEach(System.out::println);
        System.out.println();

        System.out.println("去除年龄小于20的，再根据id降序===");
        List<Person> list4 = list.stream()
                .filter(person -> person.getAge() >= 20)
                .sorted(Comparator.comparingInt(Person::getId).reversed())
                .collect(Collectors.toList());
        list4.forEach(System.out::println);
        System.out.println();

        System.out.println("去除年龄大于30的，再根据年龄降序，输出所有名字字符串，使用“/”拼接===");
        String collect = list.stream()
                .filter(person -> person.getAge() <= 30)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .map(person -> person.getName())
                .collect(Collectors.joining("/"));
        System.out.println(collect);
        System.out.println();

        //抽取某个属性转map
        Map<String, Person> map = list.stream().collect(Collectors.toMap(Person::getName, person -> person));
        Map<String, Integer> map1 = list.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
        Map<String, Person> map2 = list.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
        Map<Integer, List<Integer>> map3 = list.stream().collect(Collectors.toMap(
                person -> person.age,
                person -> Lists.newArrayList(person.id),
                (List<Integer> oldList, List<Integer> newList) -> {
                    newList.addAll(oldList);
                    return newList;
                }
        ));

        System.out.println();

    }

    /** 测试人员实体类 */
    static class Person implements Serializable {
        private Integer id;

        private String name;

        private Integer age;

        public Person() {
        }

        public Person(Integer id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
