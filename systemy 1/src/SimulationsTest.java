import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SimulationsTest {

    @Test
    public void FCFSSimulationTest() {

        //test simulations behaviour with just one process in queue
        FCFS fcfs = new FCFS(createOneProcessQueue());
        //(0)/1=0
        assertEquals(0, fcfs.run(), 0.1);

        //test simulations behaviour with normal processes queue
        fcfs = new FCFS(createNormalQueue());
        //(0+28+33+38)/4=24.75
        assertEquals(24.75, fcfs.run(), 0.01);

        //test simulations behaviour with queue of processes, where there are empty spots between end of executing
        //one process and arrival of another one
        fcfs = new FCFS(createDelayedQueue());
        //(0+0+1+0)/4=0.25
        assertEquals(0.25, fcfs.run(), 0.01);

        //tests simulations behaviour with queue in which shorter processes come within cycles
        fcfs = new FCFS(createSJFTestCase());
        //(0+10+20+16)/4=11.5
        assertEquals(11.5, fcfs.run(), 0.1);
    }

    @Test
    public void SJFWithoutSimulationTest() {

        //tests simulations behaviour with just one process in queue
        SJFbez sjfWithout = new SJFbez(createOneProcessQueue());
        //(0)/1=0
        assertEquals(0, sjfWithout.run(), 0.1);

        //tests simulations behaviour with normal processes queue
        sjfWithout = new SJFbez(createNormalQueue());
        //(0+5+10+26)/4=10.25
        assertEquals(10.25, sjfWithout.run(), 0.01);

        //test simulations behaviour with queue of processes, where there are empty spots between end of executing
        //one process and arrival of another one
        sjfWithout = new SJFbez(createDelayedQueue());
        //(0+0+1+0)/4=0.25
        assertEquals(0.25, sjfWithout.run(), 0.01);

        //tests simulations behaviour with shorter processes coming within cycles
        sjfWithout = new SJFbez(createSJFTestCase());
        //(0+17+5+1)/4=5.75
        assertEquals(5.75, sjfWithout.run(), 0.01);
    }

    @Test
    public void SJFWithSimulationTest() {

        //tests simulations behaviour with just one process in queue
        SJFz sjfWith = new SJFz(createOneProcessQueue());
        //(0)/1=0
        assertEquals(0, sjfWith.run(), 0.1);

        //tests whether average time for regular queue and normal quantity is correct
        sjfWith = new SJFz(createNormalQueue());
        //(26+0+5+10)/4=10.25
        assertEquals(10.25, sjfWith.run(), 0.01);

        //test simulations behaviour with queue of processes, where there are empty spots between end of executing
        //one process and arrival of another one
        sjfWith = new SJFz(createDelayedQueue());
        //(0+0+1+0)/4=0.25
        assertEquals(0.25, sjfWith.run(), 0.01);

        //tests simulations behaviour with queue in which shorter processes come within cycles
        sjfWith = new SJFz(createSJFTestCase());
        //(3+17+0+1)/4=5.25
        assertEquals(5.25, sjfWith.run(), 0.01);
    }

    @Test
    public void RRSimulationTest() {

        //tests simulations behaviour with just one process in queue
        RR rr = new RR(createOneProcessQueue(), 5);
        //(0)/1=0
        assertEquals(0, rr.run(), 0.1);

        //tests whether average time for regular queue and normal quantity is correct
        rr = new RR(createNormalQueue(), 8);
        //(26+8+13+26)/4=18.25
        assertEquals(18.25, rr.run(), 0.01);

        //tests whether average time for regular queue and small quantity is correct
        rr = new RR(createNormalQueue(), 2);
        //(26+14+15+26)/4=20.25
        assertEquals(20.25, rr.run(), 0.01);

        //test simulations behaviour with queue of processes, where there are empty spots between end of executing
        //one process and arrival of another one
        rr = new RR(createDelayedQueue(), 4);
        //(0+0+1+0)/4=0.25
        assertEquals(0.25, rr.run(), 0.01);

        //tests whether average time for queue with shorter processes coming within cycles is correct
        rr = new RR(createSJFTestCase(), 10);
        //(0+17+15+11)/4=10.75
        assertEquals(10.75, rr.run(), 0.01);
    }

    private static kolejka createOneProcessQueue() {
        ArrayList<proces> queue = new ArrayList<>();

        queue.add(new proces(0, 5, 0));
        
        kolejka kolejka = new kolejka();
        kolejka.setLista(queue);

        return kolejka;
    }

    //creating regular processes queue without any empty cycles
    private static kolejka createNormalQueue() {
        ArrayList<proces> queue = new ArrayList<>();

        queue.add(new proces(0, 28, 0));
        queue.add(new proces(1, 5, 0));
        queue.add(new proces(2, 6, 0));
        queue.add(new proces(2, 15, 1));

        kolejka kolejka = new kolejka();
        kolejka.setLista(queue);

        return kolejka;
    }

    //creating processes queue causing empty cycles
    private static kolejka createDelayedQueue() {
        ArrayList<proces> queue = new ArrayList<>();

        queue.add(new proces(0, 5, 0));
        queue.add(new proces(1, 3, 10));
        queue.add(new proces(2, 6, 12));
        queue.add(new proces(3, 4, 21));

        kolejka kolejka = new kolejka();
        kolejka.setLista(queue);

        return kolejka;
    }

    //creating processes queue in which shorter processes come within cpu cycles
    private static kolejka createSJFTestCase() {
        ArrayList<proces> queue = new ArrayList<>();

        queue.add(new proces(0, 10, 0));
        queue.add(new proces(1, 15, 0));
        queue.add(new proces(2, 3, 5));
        queue.add(new proces(3, 4, 12));

        kolejka kolejka = new kolejka();
        kolejka.setLista(queue);

        return kolejka;
    }

}