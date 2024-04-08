package org.example;

public class Student {
    private String surName;
    private String name;
    private String course;
    private String city;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "surName='" + surName + '\'' +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
