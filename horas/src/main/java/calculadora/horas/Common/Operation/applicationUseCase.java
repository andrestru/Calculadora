package calculadora.horas.Common.Operation;

public interface applicationUseCase<IN extends applicationRequest, OUT extends applicationResponse>{
    OUT execute(IN request);
}
