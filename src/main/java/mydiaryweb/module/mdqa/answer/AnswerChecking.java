package mydiaryweb.module.mdqa.answer;

import mydiaryweb.module.mdqa.model.Action;

import java.util.ArrayList;
import java.util.List;


public class AnswerChecking {

    public Action CheckAnswer(String answer, List<Action> actions) {
        Answer ans = new Answer();
        List<String> Time = new ArrayList<String>();
        int index = 0;
        boolean ok = false;

        ans.setAnswer(answer);
        Time = ans.extractTemporalData();

        List<String> words = new ArrayList<String>();
        words = ans.getWordsByPosType("NN");

        int[] ind = new int[actions.size()];

        if (!Time.isEmpty()) {
            for (int index1 = 0; index1 < Time.size(); index1++) {
                for (int index2 = 0; index2 < actions.size(); index2++) {
                    String str1 = actions.get(index2).getActionDate().toString().toLowerCase();
                    String str2 = Time.get(index1).toString();

                    if (str1.contains(str2)) {
                        index = index2;
                        ok = true;
                        break;
                    }
                }

                if (ok == true)
                    break;
            }
        } else {
            for (int index1 = 0; index1 < actions.size(); index1++) {
                ok = false;
                int count = 0;

                for (int it = 0; it < words.size(); it++) {
                    String[] array = words.get(it).split("\\|");

                    if (actions.get(index1).getFace().getName().toLowerCase().contains(array[1].toLowerCase())) {
                        ok = true;
                        count++;
                    } else if (actions.get(index1).getLocation().getLocation().toLowerCase().contains(array[1].toLowerCase())
                            || actions.get(index1).getLocation().getProximity().contains(array[1])) {
                        ok = true;
                        count++;
                    }
                }

                ind[index1] = count;
            }

            int max = ind[0];
            for (int i = 0; i < ind.length; i++) {
                if (max < ind[i]) {
                    max = ind[i];
                    index = i;
                }
            }
//            System.out.print("Index:" + index);
        }

        return actions.get(index);
    }
}
