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

    public List<String> list() {
        List<String> returnList = new ArrayList<String>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Excuse>> typeReference = new TypeReference<List<Excuse>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
        try {
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

    public void add(String tag, String message) {
        try {
            Gson gson = new Gson();
            JSONParser parser = new JSONParser();
            List<Excuse> excuses = getAllExcuses();
            int last_id = excuses.get(excuses.size() - 1).getHttp_code();
            Excuse excuse = new Excuse(last_id + 1, tag, message);
            String fileName = "src/main/resources/data/data.json";
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/data.json");
            Object object = parser.parse(inputStream);
            JSONArray jsonObject = (JSONArray) object;
            List<Excuse> newExcuses = gson.fromJson(jsonObject.toJSONString(), new TypeToken<List<Excuse>>(){}.getType());
            newExcuses.add(excuse);
            String json = gson.toJson(newExcuses);
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.print(json);
            writer.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
