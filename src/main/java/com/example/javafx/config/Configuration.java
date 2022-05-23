package com.example.javafx.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Configuration instance;
    private Properties properties = new Properties();

    private Configuration(){

    }

    public static Configuration getInstance(){
        if(instance == null){
            instance = new Configuration();
            try{
            instance.load();
            }catch (IOException e){
                throw new RuntimeException("Cannot load properties.", e);
            }
        }
        return instance;
    }

    private void load() throws IOException{
        InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(in);
    }

    public String get(String key){
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}
