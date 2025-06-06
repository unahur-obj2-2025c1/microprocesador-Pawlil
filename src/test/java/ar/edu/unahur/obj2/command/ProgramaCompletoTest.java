package ar.edu.unahur.obj2.command;

import ar.edu.unahur.obj2.command.comandos.*;
import ar.edu.unahur.obj2.command.excepctions.Microcontrolador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ProgramaCompletoTest {

    private Programable micro;

    @BeforeEach
    public void setup() {
        micro = new Microcontrolador();
    }

    @Test
    public void testTresNOPsAvanzaProgramCounterATres() {
        ProgramBuilder builder = new ProgramBuilder();
        builder.add(new NOP());
        builder.add(new NOP());
        builder.add(new NOP());

        micro.reset();
        micro.run(builder.build());

        assertEquals(3, micro.getProgramCounter(), "El PC debe estar en 3 tras 3 NOPs");
    }

    @Test
    public void testSuma20Mas17Da37() {
        ProgramBuilder builder = new ProgramBuilder();
        builder.add(new LODV(20));
        builder.add(new SWAP());
        builder.add(new LODV(17));
        builder.add(new ADD());

        micro.reset();
        micro.run(builder.build());

        assertEquals(37, micro.getAcumuladorA(), "20 + 17 = 37");
        assertEquals(0, micro.getAcumuladorB(), "El acumulador B queda en 0");
        assertEquals(4, micro.getProgramCounter(), "PC debe estar en 4");
    }

    @Test
    public void testSuma2Mas8Mas5Da15() {
        ProgramBuilder builder = new ProgramBuilder();
        builder.add(new LODV(2));
        builder.add(new STR(0));
        builder.add(new LODV(8));
        builder.add(new SWAP());
        builder.add(new LODV(5));
        builder.add(new ADD());
        builder.add(new SWAP());
        builder.add(new LOD(0));
        builder.add(new ADD());

        micro.reset();
        micro.run(builder.build());

        assertEquals(15, micro.getAcumuladorA(), "2 + 8 + 5 = 15");
        assertEquals(0, micro.getAcumuladorB(), "Acumulador B queda en 0");
        assertEquals(9, micro.getProgramCounter(), "PC debe estar en 9");
    }

    @Test
    public void testUndoDeInstruccion() {

        micro.reset();
        micro.setAcumuladorA(10);
        micro.setAcumuladorB(5);
        micro.setAddr(0, 3);
        micro.setAddr(1, 7);
        micro.setAddr(2, 11);

        InstruccionBase instr = new ADD();
        instr.execute(micro);

        instr.undo(micro);

        assertEquals(10, micro.getAcumuladorA());
        assertEquals(5, micro.getAcumuladorB());

    }

    @Test
    public void testUndoDeAddYSWAP() {
        micro.reset();
        micro.setAcumuladorA(4);
        micro.setAcumuladorB(6);

        InstruccionBase add = new ADD();
        add.execute(micro);
        assertEquals(10, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        add.undo(micro);
        assertEquals(4, micro.getAcumuladorA());
        assertEquals(6, micro.getAcumuladorB());

        InstruccionBase swap = new SWAP();
        swap.execute(micro);
        assertEquals(6, micro.getAcumuladorA());
        assertEquals(4, micro.getAcumuladorB());
        swap.undo(micro);
        assertEquals(4, micro.getAcumuladorA());
        assertEquals(6, micro.getAcumuladorB());
    }

    @Test
    public void testUndoIfnz() {
        micro.reset();
        micro.setAcumuladorA(1);

        List<Operable> instrucciones = List.of(new LODV(5), new SWAP());
        IFNZ ifnz = new IFNZ(instrucciones);

        ifnz.execute(micro);

       
        assertEquals(0, micro.getAcumuladorA()); 
        

        ifnz.undo(micro);

        assertEquals(1, micro.getAcumuladorA()); 
        
    }

}
