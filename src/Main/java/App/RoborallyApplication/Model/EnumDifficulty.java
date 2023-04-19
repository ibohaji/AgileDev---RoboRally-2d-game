package App.RoborallyApplication.Model;

import Utils.Tuple;

public enum EnumDifficulty {
    EASY(8,8),
    MEDIUM(12,12),
    HARD(12,12);

    private int width;
    private int height;

    private int tileSizeInPixels;

    private EnumDifficulty(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Tuple<Integer, Integer> getDimensions() {
        return new Tuple<>(width, height);
    }


}
