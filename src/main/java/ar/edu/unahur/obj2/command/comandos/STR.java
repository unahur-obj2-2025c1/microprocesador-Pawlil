package ar.edu.unahur.obj2.command.comandos;
import ar.edu.unahur.obj2.command.Programable;
public class STR extends InstruccionBase {
    private final Integer direccion;

    public STR(Integer direccion) {
        this.direccion = direccion;
    }

    @Override
    protected void ejecutarOperacion(Programable micro) {
        Integer valor = micro.getAcumuladorA();
        micro.setAddr(direccion, valor);
        
    }
    @Override
    public void undo(Programable micro) {
        // No hay un estado previo que restaurar, ya que STR simplemente almacena un valor.
        // Por lo tanto, no se implementa undo.
    }
}


