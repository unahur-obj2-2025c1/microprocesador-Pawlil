package ar.edu.unahur.obj2.command.comandos;
import ar.edu.unahur.obj2.command.Programable;

public class NOP extends InstruccionBase {

    @Override
    protected void ejecutarOperacion(Programable micro) {
        // Incrementa el contador de programa sin hacer nada
    }

   
    @Override
    public void undo(Programable micro) {
        
    }
}

