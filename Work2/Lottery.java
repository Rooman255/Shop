package Work2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Lottery {
    public static void main(String[] args) {

        List<Toy> toys = new ArrayList<Toy>();
        
        Toy input1 = new Toy(1, "Bear", 50);
        Toy input2 = new Toy(2, "Train", 25);
        Toy input3 = new Toy(3, "Car", 20);
        Toy input4 = new Toy(4, "Ball", 100);
        
        toys.add(input1);
        toys.add(input2);
        toys.add(input3);
        toys.add(input4);
        
        PriorityQueue<Toy> queue = new PriorityQueue<Toy>(Comparator.comparingInt(Toy::getWeight));
        for (Toy toy : toys) {
            queue.offer(toy);
        }

        int totalWeight = 0;
        int count = 0;
        for (Toy toy : toys) {
            count += 1;
            totalWeight += toy.getWeight();
        }
        System.out.println("Всего игрушек: " + totalWeight);

        for (int i = 0; i < count; i++) {
            Toy current = queue.poll();
            String name = current.getName();
            float chance = current.getWeight()*100/totalWeight;
            System.out.println(i+1 + ". " + name + ". Шанс выпадения: " + chance + "%");
            writeToFile(i, name, chance);
        }

    }

    private static void writeToFile(int i, String string, float chance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Lotery.txt", true))){
            writer.write(i+1 + ". " + string + ". Chance to fall: " + chance + "%");
            writer.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}