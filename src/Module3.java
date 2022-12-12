import java.util.*;

/**
 * This class includes generic object methods in order to shuffle and sort and arraylist.
 * @author Michael Soskin
 * @version 1.7
 * @date 4/18/21
 */

public class Module3 {

    /**
     * shuffle Arraylist object
     */
    public static <E> void shuffle(ArrayList<E> list) {
        // random class instantiation
        Random ran = new Random();
        int index1, index2;

        // loop through arraylist
        for (int i = 0; i < list.size() / 2; i++) {

            // variable too swap position in an array.
            index1 = ran.nextInt(list.size());
            index2 = ran.nextInt(list.size());

            if (index1 > index2) {
                E num = list.remove(index1);
                list.add(index2, num);
                num = list.remove(index2 + 1);
                list.add(index1, num);

            } else if (index2 > index1) {
                E num = list.remove(index2);
                list.add(index1, num);
                num = list.remove(index1 + 1);
                list.add(index2, num);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * method to sort a generic list
     *
     * @param list
     */
    public static <Flashcard extends Comparable<Flashcard>> void sort(ArrayList<Flashcard> list) {

        int min;

        // for loop to going through arraylist.
        for (int i = 0; i < list.size() - 1; i++) {
            min = i;

            // for loop to compare objects in the array
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(min)) < 0)
                    min = j;
            }
            // remove and add back to arraylist.
                Flashcard temp1 = list.remove(min);
                Flashcard temp2 = list.remove(i);
                list.add(i, temp1);
                list.add(min, temp2);
            }
        }
    }






