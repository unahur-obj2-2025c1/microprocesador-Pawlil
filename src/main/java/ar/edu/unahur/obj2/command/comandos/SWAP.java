package ar.edu.unahur.obj2.command.comandos;
import ar.edu.unahur.obj2.command.Programable;

public class SWAP extends InstruccionBase {
    @Override
    protected void ejecutarOperacion(Programable micro) {
        Integer temp = micro.getAcumuladorA();
        micro.setAcumuladorA(micro.getAcumuladorB());
        micro.setAcumuladorB(temp);
        
    }

    @Override
    public void undo(Programable micro) {
        
        Integer temp = micro.getAcumuladorA();
        micro.setAcumuladorA(micro.getAcumuladorB());
        micro.setAcumuladorB(temp);
    }
}

