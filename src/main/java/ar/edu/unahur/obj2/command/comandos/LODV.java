package ar.edu.unahur.obj2.command.comandos;
import ar.edu.unahur.obj2.command.Programable;
public class LODV extends InstruccionBase {
    private final Integer valor;

    public LODV(Integer valor) {
        this.valor = valor;
    }

    @Override
protected void ejecutarOperacion(Programable micro) {
    System.out.println("PC antes: " + micro.getProgramCounter());
    
    // código que hace la operación...
    micro.setAcumuladorA(valor);
    System.out.println("PC después: " + micro.getProgramCounter());
}

    @Override
    public void undo(Programable micro) {
        // No hay un estado previo que restaurar, ya que LODV simplemente carga un valor.
        // Por lo tanto, no se implementa undo.
}
}

