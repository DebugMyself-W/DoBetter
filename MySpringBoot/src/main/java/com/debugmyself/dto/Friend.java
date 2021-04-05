package com.debugmyself.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *
 * @author 123
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 * prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 * <p>
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 */
@Component
@ConfigurationProperties(value = "friend")
public class Friend {

    private String name;
    private String age;
    private Date birth;
    private Map<String, String> maps;
    private List<String> pet;
    private Array pets;
    private Dog dog;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public List<String> getPet() {
        return pet;
    }

    public void setPet(List<String> pet) {
        this.pet = pet;
    }

    public Array getPets() {
        return pets;
    }

    public void setPets(Array pets) {
        this.pets = pets;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birth=" + birth +
                ", maps=" + maps +
                ", pet=" + pet +
                ", pets=" + pets +
                ", dog=" + dog +
                '}';
    }
}
