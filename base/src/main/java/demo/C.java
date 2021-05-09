package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C {

    public static boolean findLevel(int level, int beginIndex, List<String> eventList, Map<Integer, Long> map,
        String eventString) {
        if (level == 0) {
            return true;
        }
        for (int i = beginIndex; i < eventList.size(); i++) {
            int nextLevel = level - 1;
            String actionText = eventList.get(i);
            Long interVal = map.get(level);
            Integer action = Integer.valueOf(actionText.split("_")[0]);
            Long t = Long.valueOf(actionText.split("_")[1]);
            if (action != level) {
                continue;
            }
            Long lastT = Long.valueOf(eventString.split("_")[1]);

            if ((lastT - t) > interVal || lastT < t) {
                continue;
            }
            if (findLevel(nextLevel, i + 1, eventList, map, actionText)) {
                return true;
            }

        }
        return false;
    }

    public static List<String> findLevel(int level, int beginIndex, List<String> eventList, Map<Integer, Long> map) {
        List<String> list = new ArrayList<>();
        if (level == 0) {
            return list;
        }
        for (int i = beginIndex; i < eventList.size(); i++) {
            int nextLevel = level - 1;
            String actionText = eventList.get(i);
            Integer action = Integer.valueOf(actionText.split("_")[0]);
            if (action != level) {
                continue;
            }
            if (findLevel(nextLevel, i + 1, eventList, map, actionText)) {
                list.add(actionText);
            }
        }
        return list;
    }

    public static Object invoke(Object obj, List params) {
        try {
            if (obj instanceof List) {
                int count = 0;
                List<String> mixActionList = (List<String>)obj;
                Map<String, List<String>> actionMap = new HashMap<>();
                Map<Integer, Long> timeMap = new HashMap<>();
                timeMap.put(1, 259200000l);
                for (String mixActionText : mixActionList) {
                    String[] mixActionArray = mixActionText.split("@");
                    String dim = mixActionArray[0];
                    String actionText = mixActionArray[1];
                    List<String> actionList = new ArrayList<>();
                    if (actionMap.get(dim) != null) {
                        actionList = actionMap.get(dim);
                    } else {
                        actionMap.put(dim, actionList);
                    }
                    actionList.add(actionText);
                }
                List<String> keyList = new ArrayList<>();
                for (Map.Entry<String, List<String>> entry : actionMap.entrySet()) {
                    String key = entry.getKey();
                    List<String> actionList = entry.getValue();
                    Collections.sort(actionList, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o2.split("_")[1].compareTo(o1.split("_")[1]);
                        }
                    });
                    List<String> list = findLevel(2, 0, actionList, timeMap);
                    keyList.addAll(list);
                }
                System.out.println(keyList);
                return keyList.size();

            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * 原测试数据是下面这样，我想获取符合条件的数据，条件如下： 1.时间间隔为 timeMap.put(1,259200000l); 259200000ms 2. @2_ 后面的数（如： @2_1591377610000 的
     * 1591377610000）减去 @1_ 后面的数 小于 259200000ms 3. 将符合以上两点的数据个数返回
     */
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nypoc20201221001@1_1591261200000", "nypoc20201221001@2_1591290720000",
            "nypoc20201221001@2_1591377600000", "nypoc20201221001@2_1591377610000", "nypoc20201221001@2_1591520400009",
            "nypoc20201221001@3_1591377610000");
        /*List<String> list = Arrays.asList("test@1_1", "test@2_3", "test@2_5", "test@2_16", "test2@1_1", "test2@2_3",
            "test2@2_5", "test2@2_16");*/
        int size = (Integer)invoke(list, null);
        System.out.println(size);
    }
}