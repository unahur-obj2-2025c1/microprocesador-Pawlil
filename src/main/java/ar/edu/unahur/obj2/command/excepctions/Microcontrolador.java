package ar.edu.unahur.obj2.command.excepctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unahur.obj2.command.comandos.Operable;
import ar.edu.unahur.obj2.command.Programable;

public class Microcontrolador implements Programable {

    private Integer acumuladorA = 0;
    private Integer acumuladorB = 0;
    private Integer programCounter = 0;
    private Integer[] memoria = new Integer[1024];

    @Override
    public void run(List<Operable> operaciones) {
        while (programCounter < operaciones.size()) {
            Operable op = operaciones.get(programCounter);
            if (op == null) {
                throw new RuntimeException("Instrucción nula en la posición " + programCounter);
            }
            op.execute(this);
        }
    }

    @Override
    public void incProgramCounter() {
        programCounter++;
        System.out.println("incProgramCounter() -> PC ahora es: " + programCounter);
    }

    @Override
    public Integer getProgramCounter() {
        return programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        acumuladorB = value;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

    @Override
    public void setAddr(Integer addr, Integer value) {
        if (addr < 0 || addr >= 1024) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
        }
        memoria[addr] = value;
    }

    @Override
    public void setAddr(Integer addr) {
        if (addr < 0 || addr >= 1024) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
        }
        memoria[addr] = 0; // Asignar valor por defecto
    }

    @Override
    public Integer getAddr(Integer addr) {
        if (addr < 0 || addr >= 1024) {
            throw new IllegalArgumentException("Dirección de memoria fuera de rango: " + addr);
        }
        return memoria[addr];
    }

    @Override
    public void reset() {
        acumuladorA = 0;
        acumuladorB = 0;
        programCounter = 0;
        for (int i = 0; i < memoria.length; i++) {
            memoria[i] = 0;
        }
    }

    @Override
public Programable copy() {
    Microcontrolador copia = new Microcontrolador();
    copia.setAcumuladorA(this.acumuladorA);
    copia.setAcumuladorB(this.acumuladorB);
    copia.programCounter = this.programCounter;

    for (int i = 0; i < this.memoria.length; i++) {
        copia.memoria[i] = this.memoria[i];
    }

    return copia;
}

    @Override
    public void copyFrom(Programable programable) {
        this.acumuladorA = programable.getAcumuladorA();
        this.acumuladorB = programable.getAcumuladorB();
        this.programCounter = programable.getProgramCounter();

        for (int i = 0; i < memoria.length; i++) {
            this.memoria[i] = programable.getAddr(i);
        }

        Map<Integer, Integer> memCopia = programable.getMemoria();
this.memoria = new Integer[1024]; // o la longitud que uses
for (Map.Entry<Integer, Integer> entry : memCopia.entrySet()) {
    this.memoria[entry.getKey()] = entry.getValue();
}
    }

    @Override
public Map<Integer, Integer> getMemoria() {
    Map<Integer, Integer> copia = new HashMap<>();
    for (int i = 0; i < memoria.length; i++) {
        if (memoria[i] != 0) { 
            copia.put(i, memoria[i]);
        }
    }
    return copia;
    }

    
}