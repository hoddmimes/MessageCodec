package generated;

public enum CG2 {
    Y1(500), Y2(501), Y3(502);

    private static final CG2[] cValues = values();
    private final int mValue;

    CG2(final int pValue) {
        mValue = pValue;
    }

    public static CG2 get(int ordinal) {
        return cValues[ordinal];
    }

    public int getValue() {
        return mValue;
    }
}
                    