package ar.edu.unahur.obj2.command.comandos;
import ar.edu.unahur.obj2.command.Programable;

public class LOD extends InstruccionBase {
    private final Integer direccion;

    public LOD(Integer direccion) {
        this.direccion = direccion;
    }

    

    @Override
    protected void ejecutarOperacion(Programable micro) {
        Integer valor = micro.getAddr(direccion);
        micro.setAcumuladorA(valor);
        
    }


    @Override
    public void undo(Programable micro) {
       
    }
 
}



