import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
    List<Integer> result = new ArrayList<>();

        // Sort the flavors by cost
        Collections.sort(arr);

        boolean exactMatchFound = false;
        int closestSum = Integer.MAX_VALUE;

        for (int i = 0; i < arr.size() - 2; i++) {
            int left = i + 1;
            int right = arr.size() - 1;

            while (left < right) {
                int sum = arr.get(i) + arr.get(left) + arr.get(right);

                if (sum == m) {
                    // If the current combination is an exact match, return immediately.
                    result.add(arr.get(i));
                    result.add(arr.get(left));
                    result.add(arr.get(right));
                    return result;
                } else if (sum < m) {
                    // If the current combination is within budget, check if it's closer to the budget.
                    if (m - sum < m - closestSum) {
                        closestSum = sum;
                        result.clear();
                        result.add(arr.get(i));
                        result.add(arr.get(left));
                        result.add(arr.get(right));
                    }
                    left++;
                } else {
                    // If the current combination exceeds the budget, move the right pointer to decrease the cost.
                    right--;
                }
            }
        }

        return result;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> result = Result.icecreamParlor(m, arr);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
