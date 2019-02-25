package Java;

import java.util.*;

/**
 * @author deepa.a
 *
 * This is to get the MAX Profit from the Cargo List given to you with MAX WEIGHT as constraint
 */
public class MaxProfitProblem {

    /*
    * This method would sort the TreeMap by the Values rather than keys
    *
    * */
    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k2).compareTo(map.get(k1));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    public static void main(String args[]){
        LinkedList<List<Integer>> cargoList = new LinkedList<>();
        LinkedList<Integer> one = new LinkedList<Integer>();
        LinkedList<Integer> two = new LinkedList<Integer>();
        LinkedList<Integer> three = new LinkedList<Integer>();
        one.add(23);
        one.add(100);
        one.add(1000);
        two.add(50);
        two.add(280);
        two.add(100);
        three.add(40);
        three.add(670);
        three.add(80);
        cargoList.add(one);
        cargoList.add(two);
        cargoList.add(three);
        System.out.println("SIZE of cargoList"+ cargoList);
        final TreeMap<Integer, Integer> profitMap = new TreeMap<Integer, Integer>();
        final HashMap<Integer, Integer> weightMap = new HashMap<Integer, Integer>();
        cargoList.forEach(value->{
            profitMap.put(value.get(0), value.get(2));
        });
        cargoList.forEach(value ->{
            weightMap.put(value.get(0), value.get(1));
        });
        Map sortedMap = sortByValues(profitMap);
        System.out.println("PROFIT MAP SIZE, "+ profitMap.size());
        // Sorted By Value
        sortedMap.forEach((key, value)->{
            System.out.println("Key  "+ key + ": value "+ value);
        });
        final Integer weight[]= new Integer[1];
        weight[0] =0;
        int maxWeight = 300;
        final int[] maxProfit = new int[1];
        final List<Integer> keys = new ArrayList<Integer>();
        sortedMap.forEach((key,value)->{
            weight[0] = weight[0]+weightMap.get(key);
            if(weight[0] < maxWeight){
                maxProfit[0] = maxProfit[0] + profitMap.get(key);
                keys.add((Integer) key);
            }
        });
        keys.add(maxProfit[0]);
        keys.forEach(value -> {
            System.out.println(value);
        });
    }
}
