package ar.edu.unahur.obj2.command;
import ar.edu.unahur.obj2.command.comandos.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramBuilder {

    private List<Operable> instrucciones = new ArrayList<>();

    public void add(Operable instruccion) {
        instrucciones.add(instruccion);
    }

    public List<Operable> build() {
        return instrucciones;
    }
}