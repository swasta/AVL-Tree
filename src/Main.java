import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        AVLTree avl = new AVLTree();

        avl.add("March");
        avl.add("May");
        avl.add("November");
        avl.add("August");
        avl.add("April");
        avl.add("January");
        avl.add("December");
        avl.add("July");
        avl.add("February");
        avl.add("June");
        avl.add("October");
        avl.add("September");

        avl.print(); //prints tree with Level Order Traversal

        avl.delete("April");
        avl.delete("August");
        avl.delete("December");

        System.out.println("\n\nAfter deletion: ");
        avl.print();

        // ****** Find Checking ****** //
        System.out.println(avl.find("January"));
        System.out.println(avl.find("February"));
        System.out.println(avl.find("March"));
        System.out.println(avl.find("April"));
        System.out.println(avl.find("May"));
        System.out.println(avl.find("June"));
        System.out.println(avl.find("July"));
        System.out.println(avl.find("August"));
        System.out.println(avl.find("September"));
        System.out.println(avl.find("October"));
        System.out.println(avl.find("November"));
        System.out.println(avl.find("December"));


        // ****** Task 2 ********** //

        StringBuilder sb = new StringBuilder();
        FileWriter ostream = new FileWriter("write.txt");
        BufferedWriter out = new BufferedWriter(ostream);
        AVLTree avl2 = new AVLTree();
        long startTime, estimatedTime;
        for (int i = 0; i < 1_000_000; i++) {
            startTime = System.nanoTime();
            avl2.add(i);
            estimatedTime = System.nanoTime() - startTime;
            if (i % 1000 == 0) {
                sb.append(i);
                sb.append("   ");
                sb.append(avl2.treeHeight());
                sb.append("   ");
                sb.append(estimatedTime);
                out.append(sb);
                out.append(System.getProperty("line.separator"));
                sb.setLength(0);
                sb.trimToSize();
            }
        }
        out.close();
        ostream = new FileWriter("delete.txt");
        out = new BufferedWriter(ostream);
        for (int i = 1_000_000; i > 0; i--) {
            startTime = System.nanoTime();
            avl2.delete(i);
            estimatedTime = System.nanoTime() - startTime;
            if (i % 1000 == 0) {
                sb.append(i);
                sb.append("   ");
                sb.append(avl2.treeHeight());
                sb.append("   ");
                sb.append(estimatedTime);
                out.append(sb);
                out.append(System.getProperty("line.separator"));
                sb.setLength(0);
                sb.trimToSize();
            }
        }
        out.close();
    }
}
