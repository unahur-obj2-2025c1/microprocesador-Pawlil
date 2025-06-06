package ar.edu.unahur.obj2.command.comandos;

import ar.edu.unahur.obj2.command.Programable;

public abstract class InstruccionBase implements Operable {

    protected Programable estadoPrevio;

    @Override
    public final void execute(Programable micro) {
        estadoPrevio = micro.copy();  
        ejecutarOperacion(micro);
        micro.incProgramCounter();
    }

    protected abstract void ejecutarOperacion(Programable micro);

    @Override
    public void undo(Programable micro) {
        if (estadoPrevio != null) {
            micro.copyFrom(estadoPrevio);
        }
    }
}