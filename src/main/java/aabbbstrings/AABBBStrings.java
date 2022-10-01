package aabbbstrings;

import java.util.*;

public class AABBBStrings {

    /**
     * @param n >= 0
     * @return the number of words in the AABBB language of length n
     */
    public static long countStrings(int n) {
        if (n == 0 || n == 1) {
            return 0L;
        }

        long[] dp = new long[n + 2];
        dp[0] = -1L; // Dummy
        dp[1] = 0L;
        dp[2] = 1L;
        dp[3] = 1L;
        for (int i = 4; i < n + 1; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }

	    return dp[n];
    }

    /**
     *
     * @param n
     *            >= 0
     * @return the set of words in the AABBB language of length n
     * @throws TooManyWordsException
     *             when the length of a word exceeds 30
     */
    public static Set<String> getWords(int n) throws TooManyWordsException {
        if (n > 30) {
            throw new TooManyWordsException();
        }

        List<List<String>> cache = new ArrayList<>();
        cache.add(0, Arrays.asList());
        cache.add(1, Arrays.asList());
        cache.add(2, Arrays.asList("aa"));
        cache.add(3, Arrays.asList("bbb"));
        for (int i = 4; i <= n; i++) {
            cache.add(i, null);
        }

        List<String> wordList = createWords(n, cache);
        Set<String> words = new HashSet<>();
        for (String word : wordList) {
            words.add(word);
        }

	    return words;
    }

    private static List<String> createWords (int n, List<List<String>> cache) {
        if (n == 0) return cache.get(0);
        if (n == 1) return cache.get(1);
        if (n == 2) return cache.get(2);
        if (n == 3) return cache.get(3);
        if (cache.get(n) != null) return cache.get(n);

        List<String> wordList = new ArrayList<>();

        List<String> aaWords = createWords(n - 2, cache);
        for (String words : aaWords) {
            String str = "aa" + words;
            wordList.add(str);
        }

        List<String> bbbWords = createWords(n - 3, cache);
        for (String words : bbbWords) {
            String str = "bbb" + words;
            wordList.add(str);
        }

        cache.set(n, wordList); // Caching

        return wordList;
    }
}
