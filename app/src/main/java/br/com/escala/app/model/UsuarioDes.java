package br.com.escala.app.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UsuarioDes implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement jsonElement = json.getAsJsonObject();

        if (json.getAsJsonObject() != null){
            jsonElement = json.getAsJsonObject();

        }
            return (new Gson().fromJson(jsonElement, Usuario.class));//salvo engano essa classe sai e entra a classe revista
    }
}
