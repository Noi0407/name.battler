package party_operation;

public enum OperationType {
    RANDOM_SELECT(0, "てきとう", "何も考えない"), RECOVERY_FIAST(1, "いのちだいじに", "回復を優先する"),
    REDUCE_LOW_HP_ENEMY(2, "てきをへらす", "HPの低い敵を優先して倒す"), REDUCE_LOW_DEF_ENEMY(3, "たくさんだめーじ", "防御力の低い敵を優先して攻撃する"),
    REDUCE_STRONG_ENEMY(4, "つよいのたおす", "攻撃力が強い敵を優先して倒す"),;

    private final int id;
    private final String operationName;
    private final String operationManual;

    private OperationType(final int id, final String operationName, final String operationManual) {
        this.id = id;
        this.operationName = operationName;
        this.operationManual = operationManual;
    }

    private int getId() {
        return this.id;
    }

    private String getOprationName() {
        return this.operationName;
    }

    private String getOpetationManual() {
        return this.operationManual;
    }

    // すべての作戦の説明を表示
    public static void printDescription() {

        for (OperationType oType : OperationType.values()) {

            System.out.printf("番号 : %1d  作戦名 : %10s  内容 : %20s \n", oType.getId(), oType.getOprationName(),
                    oType.getOpetationManual());

        }

    }

}