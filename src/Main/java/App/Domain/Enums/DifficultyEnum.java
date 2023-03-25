package App.Domain.Enums;

import Utils.Tuple;

public enum DifficultyEnum {
    EASY(8,8),
    MEDIUM(12,12),
    HARD(16,16);

    private int width;
    private int height;

    private DifficultyEnum(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Tuple<Integer, Integer> getDimensions() {
        return new Tuple<>(width, height);
    }
}
