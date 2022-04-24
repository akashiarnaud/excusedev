package com.excuse.excusedev.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.excuse.excusedev.domain.Excuse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Service
public class ExcuseService {

    /*
     * This method is used to read the JSON file and return the list of excuses
     * 
     * @return List<String>
     */
    public List<String> list() {
        List<String> returnList = new ArrayList<String>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Excuse>> typeReference = new TypeReference<List<Excuse>>() {
        };
        // read file
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
        try {
            // map JSON to Java object
            List<Excuse> excuse = mapper.readValue(inputStream, typeReference);
            for (Excuse e : excuse) {
                returnList.add(e.getMessage());
            }
            inputStream.close();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }
    /*
     * This method is used to read the JSON file and return the list of excuses
     * 
     * @return List<Excuse>
     */
    public List<Excuse> getAllExcuses() {
        List<Excuse> returnList = new ArrayList<Excuse>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Excuse>> typeReference = new TypeReference<List<Excuse>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
        try {
            List<Excuse> excuse = mapper.readValue(inputStream, typeReference);
            returnList = excuse;
            inputStream.close();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }
    /*
     * This method is used to add the new excuse to the JSON file
     * 
     * @param tag 
     * @param message
     * 
     */
    public void add(String tag, String message) {
        try {
            // using GSON library
            Gson gson = new Gson();
            JSONParser parser = new JSONParser();
            // get all existing excuses
            List<Excuse> excuses = getAllExcuses();
            // get last id
            int last_id = excuses.get(excuses.size() - 1).getHttp_code();
            // create new excuse with new id = last id + 1
            Excuse excuse = new Excuse(last_id + 1, tag, message);
            // file path
            String fileName = "src/main/resources/data/data.json";
            // read file
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
            // store file in an object
            Object object = parser.parse(inputStream);
            // convert object to JSONArray
            JSONArray jsonObject = (JSONArray) object;
            // transform json in to object list to get all existing excuses from json
            List<Excuse> newExcuses = gson.fromJson(jsonObject.toJSONString(), new TypeToken<List<Excuse>>() {
            }.getType());
            // add new excuse to existing excuses
            newExcuses.add(excuse);
            // reconvert object list to json
            String json = gson.toJson(newExcuses);
            // write a new json file
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.print(json);
            // close all resources
            writer.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
