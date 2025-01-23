package Slongo11.ColocandoEmPratica.Service;

import java.util.List;

public interface ILeituraDados {

	public <T> T jsonToObject(String json, Class<T> classe);
	public <T> List<T> jsonToObjectList(String json, Class<T> classe);
}
