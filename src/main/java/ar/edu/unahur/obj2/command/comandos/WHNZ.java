package ar.edu.unahur.obj2.command.comandos;
import ar.edu.unahur.obj2.command.Programable;
import java.util.List;

public class WHNZ extends InstruccionBase {
    private final List<Operable> instrucciones;

    public WHNZ(List<Operable> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    protected void ejecutarOperacion(Programable micro) {
        int maxIteraciones = 1000;
        int iteraciones = 0;
        while (micro.getAcumuladorA() != 0 && iteraciones < maxIteraciones) {
            for (Operable instruccion : instrucciones) {
                instruccion.execute(micro);
            }
        }
    }
}


