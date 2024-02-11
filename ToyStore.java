package java_toys;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyStore {
    private PriorityQueue<Toy> toyQueue;

    public ToyStore() {
        toyQueue = new PriorityQueue<>((t1, t2) -> t2.getFrequency() - t1.getFrequency());
    }

    public void addToy(Toy toy) {
        toyQueue.add(toy);
    }

    public String getToy() {
        int totalWeight = 0;
        for (Toy toy : toyQueue) {
            totalWeight += toy.getFrequency();
        }

        int randomNumber = (int) (Math.random() * totalWeight) + 1;
        int currentWeight = 0;

        for (Toy toy : toyQueue) {
            currentWeight += toy.getFrequency();
            if (randomNumber <= currentWeight) {
                return toy.getName();
            }
        }

        // Если произошла ошибка, вернуть пустую строку
        return "";
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy("1", "Конструктор", 20));
        toyStore.addToy(new Toy("2", "Робот", 20));
        toyStore.addToy(new Toy("3", "Кукла", 60));

        try {
            FileWriter writer = new FileWriter("result.txt");
            for (int i = 0; i < 10; i++) {
                String toy = toyStore.getToy();
                writer.write(toy + "\n");
            }
            writer.close();
            System.out.println("Результат записан в файл result.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл.");
            e.printStackTrace();
        }
    }
}