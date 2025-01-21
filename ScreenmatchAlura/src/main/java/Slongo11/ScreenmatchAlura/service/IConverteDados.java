package Slongo11.ScreenmatchAlura.service;

public interface IConverteDados {

	<T> T obertDados(String json,Class<T> classe);
}
