package Slongo11.ScreenmatchAlura.service;

public interface IConverteDados {

	<T> T obterDados(String json,Class<T> classe);
}
