package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;
import java.util.List;


    public class IFNZ extends InstruccionBase {
    private List<Operable> instrucciones;

    public IFNZ(List<Operable> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    protected void ejecutarOperacion(Programable micro) {
        if (micro.getAcumuladorA() != 0) {
            for (Operable ins : instrucciones) {
                ins.execute(micro);  // cada instrucción guarda su propio estado previo
            }
        }
    }
}



