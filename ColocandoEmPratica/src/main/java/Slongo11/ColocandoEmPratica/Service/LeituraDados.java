package Slongo11.ColocandoEmPratica.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class LeituraDados implements ILeituraDados {
	private ObjectMapper mapper;

	public LeituraDados() {
		this.mapper = new ObjectMapper();
	}
	public <T> T jsonToObject(String json, Class<T> classe) {
		try {
			return mapper.readValue(json,classe);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	public <T> List<T> jsonToObjectList(String json, Class<T> classe) {
		try {
			return Arrays.asList( mapper.readValue(json,(Class<T[]>) java.lang.reflect.Array.newInstance(classe, 0).getClass() ));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}



}
