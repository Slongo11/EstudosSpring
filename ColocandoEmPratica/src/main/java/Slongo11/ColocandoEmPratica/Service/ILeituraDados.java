package Slongo11.ColocandoEmPratica.Service;

public interface ILeituraDados {

	public <T> T jsonToObject(String json, Class<T> classe);
}
