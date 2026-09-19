public class Scorecard {
    private boolean[] results;
    private final int totalQuestions;
    private int answersRecorded;

    public Scorecard(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        this.results = new boolean[totalQuestions];
        this.answersRecorded = 0;
    }

    public void recordAnswer(boolean correct) {
        if (answersRecorded < totalQuestions) {
            results[answersRecorded] = correct;
            answersRecorded++;
        }
    }

    public int getScore() {
        int score = 0;

        for (int i = 0; i < answersRecorded; i++) {
            if (results[i]) {
                score++;
            }
        }

        return score;
    }
}